package com.bittrade.currency.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.c2c.service.ITLegalCurrencyRecordService;
import com.bittrade.common.enums.*;
import com.bittrade.currency.api.service.ITWalletRecordService;
import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.currency.dao.ITWalletRecordDAO;
import com.bittrade.currency.dao.ITWalletTransferDAO;
import com.bittrade.currency.feign.ITransferFeignService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.*;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.core.common.DTO.ReturnDTO;
import com.core.tool.SnowFlake;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private ITransferFeignService transferFeignService;
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

        //远程调用，获取划转类型
//        Integer type = transferFeignService.getTypeFeign(transferDto.getAccountInId(),transferDto.getAccountOutId());
        Integer type = 6;
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
            //#TODO
            return null;
        }else if(TransferTypeEnum.PERSONAL_TO_FUND.getType().equals(type)){//法币钱包划转资金钱包
            //#TODO
            return null;
        }else if(TransferTypeEnum.FUND_TO_PERSONAL.getType().equals(type)){//资金账户划转法币账户
            //#TODO
            return null;
        }else {
            return ReturnDTO.error("暂不支持该类型划转");
        }
    }

    /**
     * 币币钱包划转资金钱包
     */
    private ReturnDTO tf_bibi_to_fund(TransferDto transferDto) throws Exception {
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

        //获取资金钱包

        //搞个变量存储资金钱包原来有多少钱

        //开始划转
        //币币钱包-
        Integer bibiResult = walletDAO.biBiAccountOut(wallet.getId(), transferDto.getNum(), wallet.getVersion());
        if(bibiResult <= 0){
            throw new Exception("币币钱包划转法币钱包失败，请稍后再试");
        }
        //币币钱包流水(划出)
        walletRecordOut(transferDto,currency,bibibeforeAmount, WalletRecordTypeEnumer.BIBI_TO_FUNDS.getCode());

        //资金钱包+

        //资金钱包流水（划入）

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
     * @param currencyName 币种名称
     * @return 钱包余额
     */
    @Override
    public String availableBalance(Long userId, String currencyName) {
        //获取币种
        TCurrency aryCurrency = TCurrency.builder().name(currencyName).build();
        TCurrency currency = currencyDAO.getBy(aryCurrency);
        if(currency == null){
            return "0";
        }
        //获取用户钱包
        TWallet qryWallet = TWallet.builder().userId(userId).currencyId(currency.getId()).build();
        TWallet wallets = walletDAO.getBy(qryWallet);
        if(wallets == null){
            return "0";
        }
        return wallets.getTotal().toString();
    }
}
