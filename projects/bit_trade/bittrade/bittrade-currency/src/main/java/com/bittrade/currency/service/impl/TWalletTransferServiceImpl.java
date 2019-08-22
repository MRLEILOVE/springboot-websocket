package com.bittrade.currency.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.c2c.service.ITLegalCurrencyCoinService;
import com.bittrade.c2c.service.ITLegalCurrencyRecordService;
import com.bittrade.common.enums.*;
import com.bittrade.currency.api.service.ITWalletRecordService;
import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.currency.dao.*;
import com.bittrade.currency.feign.ITransferFeignService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.*;
import com.bittrade.pojo.vo.CoinVo;
import com.bittrade.pojo.vo.LegalCurrencyCoinVO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.core.common.DTO.ReturnDTO;
import com.core.tool.SnowFlake;
import com.wallet.biz.api.service.IWWalletAccountRecordService;
import com.wallet.biz.api.service.IWWalletAccountService;
import com.wallet.biz.pojo.model.WWalletAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TWalletTransferServiceImpl extends DefaultTWalletTransferServiceImpl<ITWalletTransferDAO, TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> implements ITWalletTransferService {
    private static final Logger LOG					= LoggerFactory.getLogger( TWalletTransferServiceImpl.class );
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private ITWalletDAO walletDAO;
    @Autowired
    private ITWalletRecordDAO walletRecordDAO;
    @Autowired
    private ITCurrencyDAO currencyDAO;
    @Autowired
    private ITWalletRecordService walletRecordService;
    @Autowired
    private ITWalletTransferService walletTransferService;
    @Reference
    private ITLegalCurrencyAccountService legalCurrencyAccountService;
    @Reference
    private ITLegalCurrencyRecordService legalCurrencyRecordService;
    @Reference
    private ITLegalCurrencyCoinService legalCurrencyCoinService;
    @Autowired
    private ITAccountManageDAO accountManageDAO;
    @Reference
    private IWWalletAccountService wWalletAccountService;
    @Reference
    private IWWalletAccountRecordService wWalletAccountRecordService;

    /**
     * 资金划转
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO transferOfFunds(TransferDto transferDto) throws Exception {
        //验证小数位长度
        String[] split = transferDto.getNum().toPlainString().split("\\.");
        if(split != null && split.length == 2){
            int length = split[ 1 ].length();
            if (length > 8) {
                return ReturnDTO.error( "数量小数位过长" );
            }
        }

        //获取划转类型
        Integer type = getType(transferDto.getAccountInId(), transferDto.getAccountOutId());
        if(type == null){
            return ReturnDTO.error("划转失败,网络繁忙，请稍后再试");
        }
        transferDto.setType(type);
        if(type == null){
            return ReturnDTO.error("网络繁忙，请扫后再试");
        }

        if(TransferTypeEnum.BIBI_TO_PERSONAL.getType().equals(type)){//币币钱包划转法币钱包
            return tf_bibi_to_personal(transferDto);

        }else if(TransferTypeEnum.PERSONAL_TO_BIBI.getType().equals(type)){//法币钱包划转币币钱包
            return tf_personal_to_bibi(transferDto);

        }else if(TransferTypeEnum.BIBI_TO_FUND.getType().equals(type)){//币币钱包划转资金钱包
            return tf_bibi_to_fund(transferDto);

        }else if(TransferTypeEnum.FUND_TO_BIBI.getType().equals(type)){//资金钱包划转币币钱包
            return tf_fund_to_bibi(transferDto);

        }else if(TransferTypeEnum.PERSONAL_TO_FUND.getType().equals(type)){//法币钱包划转资金钱包
            return tf_personal_to_fund(transferDto);

        }else if(TransferTypeEnum.FUND_TO_PERSONAL.getType().equals(type)){//资金账户划转法币账户
            return tf_fund_to_personal(transferDto);

        }else {
            return ReturnDTO.error("暂不支持该类型划转");
        }
    }

    /**
     * 获取划转类型
     * @param accountInId 入账钱包id
     * @param accountOutId 出账钱包id
     * @return 划转类型
     */
    private Integer getType(Integer accountInId, Integer accountOutId) {
        //获取转入、转出账户
        TAccountManage accountIn =  accountManageDAO.getByPK(accountInId);
        TAccountManage accountOut = accountManageDAO.getByPK(accountOutId);

        if(null == accountIn){
            return null;
        }
        if(null == accountOut){
            return null;
        }

        String value = accountOut.getName() + "划转" + accountIn.getName();
        //获取划转类型
        Integer type = TransferTypeEnum.getKeyByValue(value);
        return type;
    }

    /**
     * 资金账户划转法币账户
     */
    private ReturnDTO tf_fund_to_personal(TransferDto transferDto) throws Exception {
        //获取币种
        TCurrency currency = getCurrency(transferDto.getCurrency());
        if(currency == null){
            throw new Exception("币币账户币种为空");
        }

        //获取资金钱包
        WWalletAccount fundAccount = wWalletAccountService.getAccount(transferDto.getUserId(), currency.getId());
        //搞个变量存储资金钱包原来有多少钱
        BigDecimal fundTotal = fundAccount.getTotal();
        //检查资金钱包余额是否充足
        if(fundAccount.getTotal().compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,资金账户余额不足");
        }

        //获取c2c账户币种
        TLegalCurrencyCoin coin = legalCurrencyAccountService.getCoinByName(transferDto.getCurrency());
        if(coin == null){
            throw new Exception("c2c账户币种为空");
        }
        //获取c2c钱包
        TLegalCurrencyAccount c2cAccount = legalCurrencyAccountService.getC2CAccount(transferDto.getUserId(),coin.getId());
        //搞个变量存储原来有多少钱
        BigDecimal c2cbeforeAmount = c2cAccount.getBalanceAmount();

        //开始划转
        //资金钱包-
        Integer fundResult = wWalletAccountService.fundAccountOut(fundAccount,transferDto.getNum());
        if(fundResult <= 0){
            throw new Exception("资金钱包划转法币钱包失败，请稍后再试");
        }
        //资金钱包流水（划出）
        wWalletAccountRecordService.recordOut(transferDto.getUserId(),transferDto.getNum(),currency.getId(),fundTotal,FundRecordTypeEnumer.FUNDS_TO_C2C.getCode());

        //c2c钱包+
        Integer c2cResult = legalCurrencyAccountService.c2cIn(c2cAccount.getId(),transferDto.getNum(),c2cAccount.getVersion());
        if(c2cResult <= 0){
            throw new Exception("资金钱包划转法币钱包失败，请稍后再试");
        }
        //c2c钱包流水(划入)
        legalCurrencyRecordService.c2cRecordIn(transferDto.getUserId(),coin,c2cbeforeAmount,LegalRecordStatusEnumer.FUNDS_TO_LEGAL.getCode(),transferDto.getNum());
        return ReturnDTO.ok("划转成功");
    }

    /**
     * 法币钱包划转资金钱包
     */
    private ReturnDTO tf_personal_to_fund(TransferDto transferDto) throws Exception {
        //获取c2c账户币种
        TLegalCurrencyCoin coin = legalCurrencyAccountService.getCoinByName(transferDto.getCurrency());
        if(coin == null){
            throw new Exception("c2c账户币种为空");
        }
        //获取c2c钱包
        TLegalCurrencyAccount c2cAccount = legalCurrencyAccountService.getC2CAccount(transferDto.getUserId(),coin.getId());
        //搞个变量存储原来有多少钱
        BigDecimal c2cbeforeAmount = c2cAccount.getBalanceAmount();
        //检查c2c钱包余额是否充足
        if(c2cbeforeAmount.compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,资金账户余额不足");
        }

        //获取币种
        TCurrency currency = getCurrency(transferDto.getCurrency());
        if(currency == null){
            throw new Exception("币币账户币种为空");
        }
        //获取资金钱包
        WWalletAccount fundAccount = wWalletAccountService.getAccount(transferDto.getUserId(), currency.getId());
        //搞个变量存储资金钱包原来有多少钱
        BigDecimal fundTotal = fundAccount.getTotal();

        //开始划转
        //c2c钱包-
        Integer c2cResult = legalCurrencyAccountService.c2cOut(c2cAccount.getId(),transferDto.getNum(),c2cAccount.getVersion());
        if(c2cResult <= 0){
            throw new Exception("c2c钱包划转资金钱包失败，请稍后再试");
        }
        //c2c钱包流水(划出)
        legalCurrencyRecordService.c2cRecordOut(transferDto.getUserId(),coin,c2cbeforeAmount,LegalRecordStatusEnumer.LEGAL_TO_FUNDS.getCode(),transferDto.getNum());

        //资金钱包+
        Integer fundResult = wWalletAccountService.fundAccountIn(fundAccount,transferDto.getNum());
        if(fundResult <= 0){
            throw new Exception("c2c钱包划转资金钱包失败，请稍后再试");
        }
        //资金钱包流水（划入）
        wWalletAccountRecordService.recordIn(transferDto.getUserId(),transferDto.getNum(),currency.getId(),fundTotal,FundRecordTypeEnumer.C2C_TO_FUNDS.getCode());

        return ReturnDTO.ok("划转成功");
    }

    /**
     * 资金钱包划转币币钱包
     */
    private ReturnDTO tf_fund_to_bibi(TransferDto transferDto) throws Exception {
        //获取币种
        TCurrency currency = getCurrency(transferDto.getCurrency());
        if(currency == null){
            throw new Exception("币币账户币种为空");
        }

        //获取资金钱包
        WWalletAccount fundAccount = wWalletAccountService.getAccount(transferDto.getUserId(), currency.getId());
        //搞个变量存储资金钱包原来有多少钱
        BigDecimal fundTotal = fundAccount.getTotal();
        //检查资金钱包余额是否充足
        if(fundTotal.compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,资金账户余额不足");
        }

        //获取币币钱包
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        //搞个变量存储原来有多少钱
        BigDecimal bibibeforeAmount = wallet.getTotal();

        //开始划转
        //币币钱包+
        Integer bibiResult = walletDAO.biBiAccountIn(wallet.getId(), transferDto.getNum(), wallet.getVersion());
        if(bibiResult <= 0){
            throw new Exception("币币钱包划转资金钱包失败，请稍后再试");
        }
        //币币钱包流水(划入)
        walletRecordIn(transferDto,currency,bibibeforeAmount, WalletRecordTypeEnumer.FUNDS_TO_BIBI.getCode());
        //资金钱包-
        Integer fundResult = wWalletAccountService.fundAccountOut(fundAccount,transferDto.getNum());
        if(fundResult <= 0){
            throw new Exception("币币钱包划转资金钱包失败，请稍后再试");
        }
        //资金钱包流水（划出）
        wWalletAccountRecordService.recordOut(transferDto.getUserId(),transferDto.getNum(),currency.getId(),fundTotal,FundRecordTypeEnumer.BIBI_TO_FUNDS.getCode());
        return ReturnDTO.ok("划转成功");
    }

    /**
     * 币币钱包划转资金钱包
     */
    private ReturnDTO tf_bibi_to_fund(TransferDto transferDto) throws Exception {
        //获取币种
        TCurrency currency = getCurrency(transferDto.getCurrency());
        if(currency == null){
            throw new Exception("币币账户币种为空");
        }

        //获取币币钱包
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        //搞个变量存储原来有多少钱
        BigDecimal bibibeforeAmount = wallet.getTotal();
        //检查币币钱包余额是否充足
        if(wallet.getTotal().compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,币币账户余额不足");
        }

        //获取资金钱包
        WWalletAccount fundAccount = wWalletAccountService.getAccount(transferDto.getUserId(), currency.getId());
        //搞个变量存储资金钱包原来有多少钱
        BigDecimal fundTotal = fundAccount.getTotal();

        //开始划转
        //币币钱包-
        Integer bibiResult = walletDAO.biBiAccountOut(wallet.getId(), transferDto.getNum(), wallet.getVersion());
        if(bibiResult <= 0){
            throw new Exception("币币钱包划转资金钱包失败，请稍后再试");
        }
        //币币钱包流水(划出)
        walletRecordOut(transferDto,currency,bibibeforeAmount, WalletRecordTypeEnumer.BIBI_TO_FUNDS.getCode());

        //资金钱包+
        Integer fundResult = wWalletAccountService.fundAccountIn(fundAccount,transferDto.getNum());
        if(fundResult <= 0){
            throw new Exception("币币钱包划转资金钱包失败，请稍后再试");
        }
        //资金钱包流水（划入）
        wWalletAccountRecordService.recordIn(transferDto.getUserId(),transferDto.getNum(),currency.getId(),fundTotal,FundRecordTypeEnumer.BIBI_TO_FUNDS.getCode());

        return ReturnDTO.ok("划转成功");
    }

    /**
     * 法币钱包划转币币钱包
     */
    private ReturnDTO tf_personal_to_bibi(TransferDto transferDto) throws Exception {
        //获取c2c账户币种
        TLegalCurrencyCoin coin = legalCurrencyAccountService.getCoinByName(transferDto.getCurrency());
        if(coin == null){
            throw new Exception("c2c账户币种为空");
        }
        //获取c2c钱包
        TLegalCurrencyAccount c2cAccount = legalCurrencyAccountService.getC2CAccount(transferDto.getUserId(),coin.getId());
        if(c2cAccount.getBalanceAmount().compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,资金账户余额不足");
        }
        //搞个变量存储原来有多少钱
        BigDecimal c2cbeforeAmount = c2cAccount.getBalanceAmount();

        //获取币币账户币种
        TCurrency currency = getCurrency(transferDto.getCurrency());
        if(currency == null){
            throw new Exception("币币账户币种为空");
        }

        //获取币币钱包
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        //搞个变量存储原来有多少钱
        BigDecimal bibibeforeAmount = wallet.getTotal();

        //开始划转
        //c2c钱包-
        Integer c2cResult = legalCurrencyAccountService.c2cOut(c2cAccount.getId(),transferDto.getNum(),c2cAccount.getVersion());
        if(c2cResult <= 0){
            throw new Exception("法币钱包划转币币钱包失败，请稍后再试");
        }
        //c2c钱包流水(划出)
        legalCurrencyRecordService.c2cRecordOut(transferDto.getUserId(),coin,c2cbeforeAmount,LegalRecordStatusEnumer.LEGAL_TO_BIBI.getCode(),transferDto.getNum());

        //币币钱包+
        Integer bibiResult = walletDAO.biBiAccountIn(wallet.getId(), transferDto.getNum(), wallet.getVersion());
        if(bibiResult <= 0){
            throw new Exception("币币钱包划转法币钱包失败，请稍后再试");
        }
        //币币钱包流水(划入)
        walletRecordIn(transferDto,currency,bibibeforeAmount, WalletRecordTypeEnumer.PERSONAL_TO_BIBI.getCode());
        return ReturnDTO.ok("划转成功");
    }

    /**
     * 币币钱包划转法币钱包
     */
    private ReturnDTO tf_bibi_to_personal(TransferDto transferDto) throws Exception {
        //获取币币账户币种
        TCurrency currency = getCurrency(transferDto.getCurrency());
        if(currency == null){
            throw new Exception("币币账户币种为空");
        }

        //检查币币钱包余额是否充足
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        if(wallet.getTotal().compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,资金账户余额不足");
        }
        //搞个变量存储原来有多少钱
        BigDecimal bibibeforeAmount = wallet.getTotal();

        //获取c2c账户币种
        TLegalCurrencyCoin coin = legalCurrencyAccountService.getCoinByName(transferDto.getCurrency());
        if(coin == null){
            throw new Exception("c2c账户币种为空");
        }
        //获取c2c钱包
        TLegalCurrencyAccount c2cAccount = legalCurrencyAccountService.getC2CAccount(transferDto.getUserId(),coin.getId());
        //搞个变量存储原来有多少钱
        BigDecimal c2cbeforeAmount = c2cAccount.getBalanceAmount();

        //开始划转
        //币币钱包-
        Integer bibiResult = walletDAO.biBiAccountOut(wallet.getId(), transferDto.getNum(), wallet.getVersion());
        if(bibiResult <= 0){
            throw new Exception("币币钱包划转法币钱包失败，请稍后再试");
        }
        //币币钱包流水(划出)
        walletRecordOut(transferDto,currency,bibibeforeAmount, WalletRecordTypeEnumer.BIBI_TO_PERSONAL.getCode());


        //c2c钱包+
        Integer c2cResult = legalCurrencyAccountService.c2cIn(c2cAccount.getId(),transferDto.getNum(),c2cAccount.getVersion());
        if(c2cResult <= 0){
            throw new Exception("币币钱包划转法币钱包失败，请稍后再试");
        }
        //c2c钱包流水(划入)
        legalCurrencyRecordService.c2cRecordIn(transferDto.getUserId(),coin,c2cbeforeAmount,LegalRecordStatusEnumer.BIBI_TO_LEGAL.getCode(),transferDto.getNum());
        return ReturnDTO.ok("划转成功");
    }

    /**
     * 获取币币币种
     * @param currencyName
     * @return
     */
    private TCurrency getCurrency(String currencyName) throws Exception {
        TCurrency c = TCurrency.builder().name(currencyName).status(StatusEnumer.ENABLE.getCode()).build();
        return currencyDAO.getBy(c);
    }

    /**
     * 回滚划转冻结
     * @param userId 用户id
     * @param id id
     * @param num 数量
     */
    private void rollBack(Long userId, Long id, BigDecimal num) {
        int result = 0;
        int count = 1;
        while(result == 0){
            LOG.info("用户id："+ userId +",币币账户划转法币账户，回滚尝试次数：" + count + "次！！！");
            //先获取最新版本号
            TWallet wallet = walletDAO.getByPK(id);
            result = walletDAO.modifyTransferFrozen(wallet.getId(),num,wallet.getVersion());
            count ++;
        }
    }

    /**
     * 写入币币账户流水(划出)
     * @param transferDto 划转对象
     * @param currency 币种对象
     * @param beforeAmount 转账前金额
     * @param type 划转类型
     */
    private void walletRecordOut(TransferDto transferDto, TCurrency currency, BigDecimal beforeAmount, Byte type) {
        TWalletRecord walletRecord = TWalletRecord.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(transferDto.getUserId())
                .currencyId(currency.getId())
                .beforeAmount(beforeAmount)
                .afterAmount(beforeAmount.subtract(transferDto.getNum()))
                .changeAmount(transferDto.getNum().negate())
                .type(type)
                .createTime(LocalDateTime.now())
                .build();
        walletRecordService.save(walletRecord);
    }

    /**
     * 写入币币账户流水(划入)
     * @param transferDto 划转对象
     * @param currency 币种对象
     * @param beforeAmount 转账前金额
     * @param type 划转类型
     */
    private void walletRecordIn(TransferDto transferDto, TCurrency currency, BigDecimal beforeAmount, Byte type) {
        TWalletRecord walletRecord = TWalletRecord.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(transferDto.getUserId())
                .currencyId(currency.getId())
                .beforeAmount(beforeAmount)
                .afterAmount(beforeAmount.add(transferDto.getNum()))
                .changeAmount(transferDto.getNum())
                .type(type)
                .createTime(LocalDateTime.now())
                .build();
        walletRecordService.save(walletRecord);
    }

    /**
     * 写入钱包划转日志表
     *
     * @param userId 用户id
     * @param currencyId  币种id
     * @param num 数量
     * @param transferStatus 状态
     * @param typeChannel 操作渠道
     * @Param des 描述
     * @return
     */
    private TWalletTransfer writeTransferRecord(Long userId, Integer currencyId, BigDecimal num, Byte transferStatus, Byte typeChannel,String des) {
        TWalletTransfer walletTransfer = TWalletTransfer.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currency(currencyId)
                .count(num)
                .businessNumber(SNOW_FLAKE__ENTRUST.nextId()+"")
                .status(transferStatus)
                .typeChannel(typeChannel)
                .sourceChannel(SourceChannelEnumer.APP.getCode())
                .des(des)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        walletTransferService.save(walletTransfer);

        return walletTransfer;
    }

    /**
     * 获取币币账户
     * @param userId 用户id
     * @param currencyId 币种id
     * @return
     */
    private TWallet getWallet(Long userId, Integer currencyId) {
        TWallet w = new TWallet();
        w.setUserId(userId);
        w.setCurrencyId(currencyId);
        TWallet tWallet = walletDAO.getBy(w);
        if(tWallet == null){//如果账户为空，为用户开用一个账户
            TWallet wallet = TWallet.builder()
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .currencyId(currencyId)
                    .total(BigDecimal.ZERO)
                    .tradeFrozen(BigDecimal.ZERO)
                    .userId(userId)
                    .version(0)
                    .build();
            walletDAO.add(wallet);
            return wallet;
        }else{
            return tWallet;
        }
    }

    /**
     * 查询用户钱包可用余额
     * @param userId 用户id
     * @param accountId 账户id
     * @param currencyName 币种名称
     * @return
     */
    @Override
    public BigDecimal availableBalance(Long userId, Long accountId, String currencyName) {
        //获取账户
        TAccountManage account = accountManageDAO.getByPK(accountId);
        if(account == null){
            return BigDecimal.ZERO;
        }

        if("資金賬戶".equals(account.getName())){
            //获取币种
            TCurrency qry = TCurrency.builder().name(currencyName).build();
            TCurrency currency = currencyDAO.getBy(qry);
            //获取用户钱包
            WWalletAccount funds = wWalletAccountService.getAccount(userId,currency.getId());
            if(funds == null){
                return BigDecimal.ZERO;
            }
            return funds.getTotal();

        }else if("幣幣賬戶".equals(account.getName())){
            //获取币种
            TCurrency qry = TCurrency.builder().name(currencyName).build();
            TCurrency currency = currencyDAO.getBy(qry);
            if(currency == null){
                return BigDecimal.ZERO;
            }
            //获取用户钱包
            TWallet qryWallet = TWallet.builder().userId(userId).currencyId(currency.getId()).build();
            TWallet wallets = walletDAO.getBy(qryWallet);
            if(wallets == null){
                return BigDecimal.ZERO;
            }
            return wallets.getTotal();
        }else if("法幣賬戶".equals(account.getName())){
            //获取币种
            TLegalCurrencyCoin coin = legalCurrencyCoinService.getByName(currencyName);
            if(coin == null){
                return BigDecimal.ZERO;
            }
            //获取用户钱包
            TLegalCurrencyAccount c2cAccount = legalCurrencyAccountService.getC2CAccount(userId, coin.getId());
            if(c2cAccount == null){
                return BigDecimal.ZERO;
            }
            return c2cAccount.getBalanceAmount();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 两个账户共同币种
     * @param accountId1 账户1id
     * @param accountId2 账户2id
     * @return 币种列表
     */
    @Override
    public List<CoinVo> togetherCoin(Long accountId1, Long accountId2) {
        Map<String,String> map = new HashMap<>();
        List<CoinVo> together = new ArrayList<>();
        List<CoinVo> coins1 = getCoins(accountId1);//账户1币种列表
        List<CoinVo> coins2 = getCoins(accountId2);//账户2币种列表
        if(coins1 == null || coins1.size() <= 0 || coins2 == null || coins2.size() <= 0){
            return null;
        }
        coins1.forEach(x ->{
            map.put(x.getName(),x.getName());
        });
        coins2.forEach(x ->{
            if(map.get(x.getName()) != null){
                together.add(x);
            }
        });
        return together;
    }

    /**
     * 获取账户币种
     * @param accountId 账户id
     * @return 币种名称列表
     */
    private List<CoinVo> getCoins(Long accountId) {
        //获取账户1
        TAccountManage account = accountManageDAO.getByPK(accountId);
        if(account == null){
            return null;
        }
        if("資金賬戶".equals(account.getName())){
            //通过枚举获取
            List<String> coins = FundCoinEnumer.getValues();
            return currencyDAO.getFundCoinVo(coins);
        }else if("幣幣賬戶".equals(account.getName())){
            List<CoinVo> coins = new ArrayList<>();
            TCurrency qry = TCurrency.builder().status(StatusEnumer.ENABLE.getCode()).build();
            List<TCurrency> currencies = currencyDAO.getsBy(qry);
            for(TCurrency c : currencies){
                CoinVo vo = CoinVo.builder().name(c.getName()).shortName(c.getShortName()).build();
                coins.add(vo);
            }
            return coins;
        }else if("法幣賬戶".equals(account.getName())){
            List<CoinVo> coins = new ArrayList<>();
            List<LegalCurrencyCoinVO> coinVOS = legalCurrencyCoinService.listLegalCurrencyCoins();
            for (LegalCurrencyCoinVO c : coinVOS){
                CoinVo vo = CoinVo.builder().name(c.getName()).shortName(c.getTitle()).build();
                coins.add(vo);
            }
            return coins;
        }
        return null;
    }
}
