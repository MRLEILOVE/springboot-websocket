package com.wallet.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.DateTimeUtils;
import com.wallet.biz.dto.AccountNameDto;
import com.wallet.biz.dto.AccountTypeDto;
import com.wallet.biz.dto.TransferDto;
import com.wallet.biz.enumer.*;
import com.wallet.biz.feign.IC2CTransferFeignService;
import com.wallet.biz.feign.ITransferFeignService;
import com.wallet.biz.mapper.*;
import com.wallet.biz.pojo.*;
import com.wallet.biz.service.ITwalletFundAccountService;
import com.wallet.biz.service.ParameterConfigService;
import com.wallet.biz.tool.SnowFlake;
import com.wallet.biz.utils.RedisKeyUtil;
import com.wallet.biz.vo.AssetsVO;
import com.wallet.biz.vo.ConversionVo;
import com.wallet.biz.vo.RecordVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service

public class ITwalletFundAccountServiceImpl extends ServiceImpl<TwalletFundAccountMapper, TWalletFundAccount> implements ITwalletFundAccountService {
    private static final Logger LOG	= LoggerFactory.getLogger( ITwalletFundAccountServiceImpl.class );
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private TwalletFundAccountMapper walletFundAccountMapper;
    @Autowired
    private TwalletFundAccountRecordMapper walletFundAccountRecordMapper;
    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private TAccountManageMapper accountManageMapper;
    @Autowired
    private TAccountConfigMapper accountConfigMapper;
    @Autowired
    private TWalletTransferMapper walletTransferMapper;
    @Autowired
    private ITransferFeignService transferFeignService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private IC2CTransferFeignService c2cTransferFeignService;




    /**
     * 写入钱包划转日志表
     * @param userId 用户id
     * @param currencyId  币种id
     * @param num 数量
     * @param transferStatus 状态
     * @param typeChannel 操作渠道
     * @return
     */
    private TWalletTransfer writeTransferRecord(Long userId, Integer currencyId, BigDecimal num, Byte transferStatus, Byte typeChannel) {
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        TWalletTransfer walletTransfer = TWalletTransfer.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currency(currencyId)
                .count(num)
                .businessNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep)
                .status(transferStatus)
                .typeChannel(typeChannel)
                .sourceChannel(SourceChannelEnumer.APP.getCode())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        walletTransferMapper.insert(walletTransfer);
        return walletTransfer;
    }



    /**
     * 资金账户流水（转出）
     */
    public void fundRecordOut(BigDecimal fundTotal,Long userId,TransferDto transferDto,Integer type){
        TWalletFundAccountRecord fundAccountRecord = TWalletFundAccountRecord.builder()
                .userId(userId)
                .currency(transferDto.getCurrency())
                .beforeAmount(fundTotal)
                .afterAmount(fundTotal.subtract(transferDto.getNum()))
                .changeAmount(transferDto.getNum().negate())//转成负数
                .type(type)
                .createTime(new Date()).build();
        walletFundAccountRecordMapper.insert(fundAccountRecord);
    }

    /**
     * 资金账户流水（转入）
     */
    public void fundRecordIn(BigDecimal fundAccountTotal,Long userId,TransferDto transferDto,Integer type){
        TWalletFundAccountRecord fundAccountRecord = TWalletFundAccountRecord.builder()
                .userId(userId)
                .currency(transferDto.getCurrency())
                .beforeAmount(fundAccountTotal)
                .afterAmount(fundAccountTotal.add(transferDto.getNum()))
                .changeAmount(transferDto.getNum())
                .type(type)
                .createTime(new Date()).build();
        walletFundAccountRecordMapper.insert(fundAccountRecord);
    }

    /**
     * 资金账户出账
     */
    public void fundAccountOut(TWalletFundAccount walletFundAccount,TransferDto transferDto) throws Exception {
        BigDecimal fundTotal = walletFundAccount.getTotal();
        walletFundAccount.setTotal(fundTotal.subtract(transferDto.getNum()));//减少可用总数量
        walletFundAccountMapper.updateById(walletFundAccount);
        Integer result2 = walletFundAccountMapper.changeMoney(walletFundAccount);
        if(result2 <= 0){
            throw new Exception("资金账户划转法币账户失败");
        }
    }

    /**
     * 资金账户入账
     */
    public void fundAccountIn(TWalletFundAccount walletFundAccount,TransferDto transferDto){
        BigDecimal fundAccountTotal = walletFundAccount.getTotal();
        walletFundAccount.setTotal(fundAccountTotal.add(transferDto.getNum()));//增加可用总数量
        Integer result = walletFundAccountMapper.changeMoney(walletFundAccount);
        if(result <= 0){
            new Exception("法币账户划转资金账户失败");
        }
    }



    /**
     * 获取资金账户
     */
    private TWalletFundAccount getFundAccount(Long userId, String currency) {
        TWalletFundAccount account = new TWalletFundAccount();
        account.setUserId(userId);
        account.setCurrency(currency);
        QueryWrapper<TWalletFundAccount> qw = new QueryWrapper<TWalletFundAccount>(account);
        TWalletFundAccount walletFundAccount = walletFundAccountMapper.selectOne(qw);
        if(null == walletFundAccount){
            //账户不存在，为用户开通一个资金账户
            walletFundAccount = createFundAccount(userId,currency);
        }
        return walletFundAccount;
    }

    /**
     * 开通资金账户
     */
    @Transactional
    @Override
    public TWalletFundAccount createFundAccount(Long userId, String currency) {
        TWalletFundAccount walletFundAccount = TWalletFundAccount.builder()
                .userId(userId)
                .currency(currency)
                .total(BigDecimal.ZERO)
                .transferFrozen(BigDecimal.ZERO)
                .version(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        walletFundAccountMapper.insert(walletFundAccount);
        return walletFundAccount;
    }


    /**
     * 根据账户名称查询可以划转的方向
     * 备注：第一个账户的金额划出的账户
     * 第一个之外的是金额划进的账户
     */
    @Override
    public ReturnDTO<List<TAccountManage>> queryAccountDirectionList(AccountNameDto accountNameDto) {
        List<TAccountManage> list = new ArrayList<>();
        //将第一条记录查询出来(金额划出的账户)
        TAccountManage a = new TAccountManage();
        a.setName(accountNameDto.getName());
        QueryWrapper<TAccountManage> qw = new QueryWrapper<TAccountManage>(a);
        TAccountManage outAccount = accountManageMapper.selectOne(qw);
        list.add(outAccount);

        //查询金额划进的账户
        List<TAccountManage> inList = accountManageMapper.queryAccountDirectionList(outAccount.getId());

        if(null == inList || inList.size() <= 0){
            return ReturnDTO.error("暂时不支持该账户类型的资金划转");
        }else {
            list.addAll(inList);
        }
        return ReturnDTO.ok(list);
    }

    /**
     * 查询两个账户共同的币种
     * @param accountId1 账户1id
     * @param accountId2 账户2id
     */
    @Override
    public List<TAccountConfig> commonCurrency(Integer accountId1, Integer accountId2) {
        //判断是否包含币币账户，币币账户的币种需要用feign获取
        TAccountManage qry = TAccountManage.builder().name("币币账户").build();
        QueryWrapper<TAccountManage> qw = new QueryWrapper<TAccountManage>(qry);
        TAccountManage biBiManage = accountManageMapper.selectOne(qw);
        if(biBiManage != null){
            if(biBiManage.getId().equals(accountId1)){
                //账户1是币币账户
                List<TAccountConfig> configs = sameCurrency(accountId2);
                return configs;
            }else if(biBiManage.getId().equals(accountId2)){
                //账户2是币币账户
                List<TAccountConfig> configs = sameCurrency(accountId1);
                return configs;
            }
        }

        //没有包含币币账户的，直接根据sql查出来就可以了
        List<TAccountConfig> configs = accountConfigMapper.commonCurrency(accountId1, accountId2);
        return configs;
    }

    /**
     * 获取该账户与币币账户共同的币种列表
     * @param accountId 账户id
     * @return 共同的币种列表
     */
    private List<TAccountConfig> sameCurrency(Integer accountId) {
        List<TAccountConfig> together = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        List<String> list = transferFeignService.findUsableCurrency();
        if(list != null && list.size() >0){
            list.stream().forEach(x ->{
                map.put(x,x);
            });

            //获取改账户的币种列表
            List<TAccountConfig> currencies = accountConfigMapper.getCurrenciesByAccountId(accountId);
            if(currencies != null && currencies.size() > 0){
                currencies.stream().forEach(x -> {
                    if(map.get(x.getKeyword()) != null){
                        together.add(x);
                    }
                });
            }
            return together;
        }else {
            return null;
        }
    }


    /**
     * 查找币种列表
     */
    @Override
    public List<TAccountConfig> currencyList() {
        QueryWrapper<TAccountConfig> wrapper = new QueryWrapper<>();
        return accountConfigMapper.selectList(wrapper);
    }

    /**
     * 查詢资金账户记录
     * @param userId 用户id
     * @param accountTypeDto 条件对象
     * @return
     */
    @Override
    public Page<RecordVO> queryFundAccountRecord(Long userId, AccountTypeDto accountTypeDto) {
        //1.提币  2.充币  3.转入 4.转出
        List<Integer> types = new ArrayList<>();
        if(accountTypeDto.getType() != null){
            switch (accountTypeDto.getType()){
                case 1 :
                    types = Arrays.asList(AccountRecordEnum.RECHARGE.getCode());
                    break;
                case 2 :
                    types = Arrays.asList(AccountRecordEnum.WITHDRAW.getCode());
                    break;
                case 3 :
                    types = Arrays.asList(AccountRecordEnum.LEGAL_TO_FUNDS.getCode(),AccountRecordEnum.BETA_TO_FUNDS.getCode());
                    break;
                case 4 :
                    types = Arrays.asList(AccountRecordEnum.FUNDS_TO_LEGAL.getCode(),AccountRecordEnum.FUNDS_TO_BETA.getCode());
                    break;
                default:
                    types = Arrays.asList(AccountRecordEnum.RECHARGE.getCode(),AccountRecordEnum.WITHDRAW.getCode(),AccountRecordEnum.FUNDS_TO_LEGAL.getCode(),AccountRecordEnum.LEGAL_TO_FUNDS.getCode(),AccountRecordEnum.FUNDS_TO_BETA.getCode(),AccountRecordEnum.BETA_TO_FUNDS.getCode());
                    break;
            }
        }

        Integer currencyId = accountTypeDto.getCurrencyId();
        TAccountConfig accountConfig = new TAccountConfig();
        if(null != currencyId){
            accountConfig = accountConfigMapper.selectById(currencyId);
        }
        Page<RecordVO> page = new Page<>(accountTypeDto.getCurrent(),accountTypeDto.getSize());
        List<RecordVO> list = walletFundAccountRecordMapper.queryFundAccountRecord(page,userId,types,accountConfig.getKeyword());
        page.setRecords(list);
        return page;
    }

    /**
     * 查询用户的资金账户钱包列表
     * @param userId 用户id
     * @return
     */
    @Override
    public List<AssetsVO> detail(Long userId) {
        List<AssetsVO> assetsVOs = walletFundAccountMapper.getAssetsVO(userId);


        //为用户创建资金账户
        if(null == assetsVOs || assetsVOs.size() == 0 || assetsVOs.size() == 1){
            //查询有没有udst资金账户
            TWalletFundAccount fundUSDT = new TWalletFundAccount();
            fundUSDT.setUserId(userId);
            fundUSDT.setCurrency(CoinType.USDT.toString());
            QueryWrapper<TWalletFundAccount> qw = new QueryWrapper<>(fundUSDT);
            TWalletFundAccount usdtAccount = walletFundAccountMapper.selectOne(qw);
            if(usdtAccount == null){
                //创建udst资金账户
                usdtAccount = createFundAccount(userId, CoinType.USDT.toString());
                assetsVOs.add(AssetsVO.builder().currencyName(usdtAccount.getCurrency()).totalFrozen(BigDecimal.ZERO).total(BigDecimal.ZERO).build());
            }

            //查询有没有btc资金账户
            TWalletFundAccount fundCNY = new TWalletFundAccount();
            fundCNY.setUserId(userId);
            fundCNY.setCurrency(CoinType.BTC.toString());
            QueryWrapper<TWalletFundAccount> qw_fundCNY = new QueryWrapper<>(fundCNY);
            TWalletFundAccount btcAccount = walletFundAccountMapper.selectOne(qw_fundCNY);
            //创建btc资金账户
            if(btcAccount == null){
                btcAccount = createFundAccount(userId, CoinType.BTC.toString());
                assetsVOs.add(AssetsVO.builder().currencyName(usdtAccount.getCurrency()).totalFrozen(BigDecimal.ZERO).total(BigDecimal.ZERO).build());
            }
        }
        return assetsVOs;
    }

    /**
     * 查询用户的资金账户总资金折合
     * @param userId 用户Id
     * @return
     */
    @Override
    public ConversionVo totalConversion(Long userId) {
        ConversionVo vo = ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).build();
        //获取人民币-usdt汇率
        Object value = redisTemplate.opsForValue().get(RedisKeyUtil.USD_TO_CNY_RATE_KEY);
        BigDecimal USDT_TO_CNY_RATE;
        if(value == null){
            USDT_TO_CNY_RATE = BigDecimal.ONE;
        }else{
            USDT_TO_CNY_RATE = new BigDecimal(JSONObject.parseObject(value.toString()).get("rate").toString());
        }


        BigDecimal totalUSDT = BigDecimal.ZERO;

        QueryWrapper<TWalletFundAccount> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("user_id", userId);
        List<TWalletFundAccount> tWalletFundAccounts = walletFundAccountMapper.selectList(entityWrapper);
        for (TWalletFundAccount w : tWalletFundAccounts) {
            BigDecimal all = w.getTotal().add(w.getTransferFrozen());
            if("USDT".equals(w.getCurrency())){
                totalUSDT = totalUSDT.add(all);
            }else {
                //获取汇率
                Object o = redisTemplate.opsForValue().get(RedisKeyUtil.REDIS_PREFIX_LINE_PRICE + w.getCurrency());
                if(o == null){
                    o = 2;
                }
                BigDecimal rate = new BigDecimal(o.toString());
                totalUSDT = totalUSDT.add(rate.multiply(all));
            }
        }

        vo.setUSDT(totalUSDT);
        vo.setCNY(totalUSDT.multiply(USDT_TO_CNY_RATE));
        return vo;
    }

    /**
     * 获取划转类型
     * @param accountInId 入账钱包id
     * @param accountOutId 出账钱包id
     * @return 划转类型
     */
    @Override
    public Integer getType(Integer accountInId, Integer accountOutId) {
        //获取转入、转出账户
        TAccountManage accountIn =  accountManageMapper.selectById(accountInId);
        TAccountManage accountOut = accountManageMapper.selectById(accountOutId);

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

}
