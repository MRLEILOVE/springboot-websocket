package com.walletbiz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.DateTimeUtils;
import com.core.web.tool.WebUtil;
import com.walletbiz.dto.AccountNameDto;
import com.walletbiz.dto.AccountTypeDto;
import com.walletbiz.dto.TransferDto;
import com.walletbiz.enumer.AccountRecordEnum;
import com.walletbiz.enumer.AccountTypeEnum;
import com.walletbiz.enumer.BetaAccountEnum;
import com.walletbiz.enumer.CPersonalAccountEnum;
import com.walletbiz.enumer.CoinType;
import com.walletbiz.enumer.ContractEnum;
import com.walletbiz.enumer.SourceChannelEnumer;
import com.walletbiz.enumer.TransferStatusEnumer;
import com.walletbiz.enumer.TransferTypeEnum;
import com.walletbiz.enumer.TypeChannelEnumer;
import com.walletbiz.feign.IC2CTransferFeignService;
import com.walletbiz.feign.ITransferFeignService;
import com.walletbiz.mapper.GBetaAccountMapper;
import com.walletbiz.mapper.GBetaAccountRecordMapper;
import com.walletbiz.mapper.PersonalAccountMapper;
import com.walletbiz.mapper.PersonalAccountRecordMapper;
import com.walletbiz.mapper.TAccountConfigMapper;
import com.walletbiz.mapper.TAccountManageMapper;
import com.walletbiz.mapper.TWalletTransferMapper;
import com.walletbiz.mapper.TwalletFundAccountMapper;
import com.walletbiz.mapper.TwalletFundAccountRecordMapper;
import com.walletbiz.pojo.CPersonalAccount;
import com.walletbiz.pojo.CPersonalAccountRecord;
import com.walletbiz.pojo.GBetaAccount;
import com.walletbiz.pojo.GBetaAccountRecord;
import com.walletbiz.pojo.TAccountConfig;
import com.walletbiz.pojo.TAccountManage;
import com.walletbiz.pojo.TWalletFundAccount;
import com.walletbiz.pojo.TWalletFundAccountRecord;
import com.walletbiz.pojo.TWalletTransfer;
import com.walletbiz.service.ITwalletFundAccountService;
import com.walletbiz.service.ParameterConfigService;
import com.walletbiz.tool.SnowFlake;
import com.walletbiz.utils.RedisKeyUtil;
import com.walletbiz.vo.ConversionVo;
import com.walletbiz.vo.RecordVO;

import feign.RetryableException;

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
    private PersonalAccountMapper personalAccountMapper;
    @Autowired
    private PersonalAccountRecordMapper personalAccountRecordMapper;
    @Autowired
    private GBetaAccountRecordMapper betaAccountRecordMapper;
    @Autowired
    private TAccountManageMapper accountManageMapper;
    @Autowired
    private TAccountConfigMapper accountConfigMapper;
    @Autowired
    private GBetaAccountMapper betaAccountMapper;
    @Autowired
    private TWalletTransferMapper walletTransferMapper;
    @Autowired
    private ITransferFeignService transferFeignService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private IC2CTransferFeignService c2cTransferFeignService;

    /**
     * 资金划转
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO<String> transferOfFunds(TransferDto transferDto) throws Exception {
        //验证小数位长度
        String[] split = transferDto.getNum().toPlainString().split("\\.");
        if(split != null && split.length == 2){
            int length = split[ 1 ].length();
            if (length > 8) {
                return ReturnDTO.error( "数量小数位过长" );
            }
        }

        //获取法币账户/beta账户的币种类型
        TAccountConfig config = new TAccountConfig();
        config.setValue(transferDto.getCurrency());
        QueryWrapper<TAccountConfig> qw = new QueryWrapper<TAccountConfig>(config);
        TAccountConfig tAccountConfig = accountConfigMapper.selectOne(qw);
        if(null == tAccountConfig){
            return ReturnDTO.error("划转失败,暂时不支持该币种");
        }
        Integer productType = tAccountConfig.getAccountType();

        //先验证密码
        Long userId = WebUtil.getLoginUser().getUser_id();
        // 判断支付密码是否正确
        /*Wrapper wrapper = userPayPasswordService.attestationPayPassword(userId, transferDto.getPassword());
        if (wrapper.getCode() == 2) {
            return wrapper;
        }*/

        //获取划转类型
        Integer type = getType(transferDto.getAccountInId(), transferDto.getAccountOutId());
        if(type == null){
            return ReturnDTO.error("划转失败,网络繁忙，请稍后再试");
        }
        //将类型放在对象中，远程调用时，其他系统可以从这个字段中知道是什么类型转换
        transferDto.setType(type);

        /*****************************************开始划转*******************************************************************/
        if(TransferTypeEnum.FUND_TO_PERSONAL.getType().equals(type)){//类型:划转：资金账户-->法币钱包
            //判断资金账户金额是否足够
            TWalletFundAccount walletFundAccount = getFundAccount(userId,transferDto.getCurrency());
            if(walletFundAccount.getTotal().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,资金账户余额不足");
            }

            //获取法币账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId,productType);

            //划转
            //法币账户 +
            BigDecimal balance = cPersonalAccount.getBalance();
            personIn(balance,cPersonalAccount,transferDto);

            //资金账户 -
            BigDecimal fundTotal = walletFundAccount.getTotal();
            fundAccountOut(walletFundAccount,transferDto);

            //写资金账户记录（转出）
            fundRecordOut(fundTotal,userId,transferDto,AccountRecordEnum.FUNDS_TO_LEGAL.getCode());

            //写法币账户记录（转入）
            personalRecordIn(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.FUNDS_TO_PERSONAL.getCode(),balance,productType);
        }else if(TransferTypeEnum.PERSONAL_TO_FUND.getType().equals(type)){//类型：划转：法币钱包-->资金账户
            //判断法币账户金额是否足够
            //获取法币账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId, productType);

            //判断资金是否充足
            if(cPersonalAccount.getBalance().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,法币账户余额不足");
            }

            //获取资金账户
            TWalletFundAccount walletFundAccount = getFundAccount(userId, transferDto.getCurrency());

            //划转
            //资金账户 +
            BigDecimal fundAccountTotal = walletFundAccount.getTotal();
            fundAccountIn(walletFundAccount,transferDto);

            //法币账户 -
            BigDecimal balance = cPersonalAccount.getBalance();
            personOut(balance,cPersonalAccount,transferDto);

            //写资金账户记录
            fundRecordIn(fundAccountTotal,userId,transferDto,AccountRecordEnum.LEGAL_TO_FUNDS.getCode());
            //写法币账户记录
            personalRecordOut(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.PERSONAL_TO_FUNDS.getCode(),balance,productType);
        }else if(TransferTypeEnum.FUND_TO_BETA.getType().equals(type)){//资金账户划转beta账户
            //判断资金账户金额是否足够
            TWalletFundAccount walletFundAccount = getFundAccount(userId,transferDto.getCurrency());
            if(walletFundAccount.getTotal().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,资金账户余额不足");
            }

            //获取beta账户
            GBetaAccount betaAccount = getBetaAccount(userId, productType);

            //划转
            //beta账户 +
            BigDecimal betaBalance = betaAccount.getBalance();
            betaIn(betaAccount,transferDto);
            //资金账户 -
            BigDecimal fundTotal = walletFundAccount.getTotal();
            fundAccountOut(walletFundAccount,transferDto);

            //写资金账户记录
            fundRecordOut(fundTotal,userId,transferDto,AccountRecordEnum.FUNDS_TO_BETA.getCode());
            //写beta账户记录
            betaRecordIn(betaBalance,userId,transferDto,betaAccount,BetaAccountEnum.FUNDS_TO_BETA.getCode());
        }else if(TransferTypeEnum.PERSONAL_TO_BETA.getType().equals(type)){//法币账户划转beta账户
            //判断法币账户金额是否足够
            //获取法币账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId, productType);

            //判断资金是否充足
            if(cPersonalAccount.getBalance().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,法币账户余额不足");
            }

            //获取beta账户
            GBetaAccount betaAccount = getBetaAccount(userId, productType);

            //划转
            //法币账户 -
            BigDecimal balance = cPersonalAccount.getBalance();
            personOut(balance,cPersonalAccount,transferDto);
            //beta账户 +
            BigDecimal betaBalance = betaAccount.getBalance();
            betaIn(betaAccount,transferDto);

            //法币账户流水（转出）
            personalRecordOut(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.PERSONAL_TO_BETA.getCode(),balance,productType);
            //beta账户流水（转入）
            betaRecordIn(betaBalance,userId,transferDto,betaAccount,BetaAccountEnum.PERSONAL_TO_BETA.getCode());
        }else if(TransferTypeEnum.BETA_TO_FUND.getType().equals(type)){//beta账户划转资金账户
            //判断beta账户金额是否足够
            //获取beta账户
            GBetaAccount betaAccount = getBetaAccount(userId, productType);

            //判断资金是否充足
            if(betaAccount.getBalance().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,beta账户余额不足");
            }

            //获取资金账户
            TWalletFundAccount walletFundAccount = getFundAccount(userId, transferDto.getCurrency());

            //划转
            //beta账户 -
            BigDecimal betaBalance = betaAccount.getBalance();
            betaOut(betaAccount,transferDto);
            //资金账户 +
            BigDecimal fundAccountTotal = walletFundAccount.getTotal();
            fundAccountIn(walletFundAccount,transferDto);

            //beta账户流水（流出）
            betaRecordOut(betaBalance,userId,transferDto,betaAccount,BetaAccountEnum.BETA_TO_FUNDS.getCode());
            //资金账户流水（转入）
            fundRecordIn(fundAccountTotal,userId,transferDto,AccountRecordEnum.BETA_TO_FUNDS.getCode());

        }else if(TransferTypeEnum.BETA_TO_PERSONAL.getType().equals(type)){//beta账户划转法币账户
            //判断beta账户金额是否足够
            //获取beta账户
            GBetaAccount betaAccount = getBetaAccount(userId, productType);

            //判断资金是否充足
            if(betaAccount.getBalance().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,beta账户余额不足");
            }

            //获取法币账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId, productType);

            //划转
            //beta账户 -
            BigDecimal betaBalance = betaAccount.getBalance();
            betaOut(betaAccount,transferDto);
            //法币账户 +
            BigDecimal balance = cPersonalAccount.getBalance();
            personIn(balance,cPersonalAccount,transferDto);

            //beta账户流水（流出）
            betaRecordOut(betaBalance,userId,transferDto,betaAccount,BetaAccountEnum.BETA_TO_PERSONAL.getCode());
            //写法币账户记录（转入）
            personalRecordIn(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.BETA_TO_PERSONAL.getCode(),balance,productType);
        }else if(TransferTypeEnum.FUND_TO_BIBI.getType().equals(type)){//资金账户划转币币账户
            //获取资金账户
            TWalletFundAccount walletFundAccount = getFundAccount(userId,transferDto.getCurrency());
            //先用个变量记录原来有多少钱
            BigDecimal fundTotal = walletFundAccount.getTotal();
            //判断资金是否充足
            if(walletFundAccount.getTotal().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,资金账户余额不足");
            }

            //写入划转日志表
            TWalletTransfer walletTransfer = writeTransferRecord(transferDto.getUserId(),tAccountConfig.getId(),transferDto.getNum(), TransferStatusEnumer.PENDING.getCode(), TypeChannelEnumer.FUND_TO_B.getCode());

            //冻结资金账户金额
            Integer r = walletFundAccountMapper.modifyTransferFrozen(walletFundAccount.getId(),transferDto.getNum(),walletFundAccount.getVersion());
            if(r <= 0){
                throw new Exception("资金账户划转币币账户失败");
            }
            //远程调用
            String result = "";
            try {
                result = transferFeignService.biBiAccountEntry(transferDto);
            }catch (RetryableException e){
                LOG.error("用户id：" + userId + "资金账户划转币币账户：超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //更新状态为 状态未明
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.error("划转超时");
            } catch (Exception e){
                LOG.error("用户id：" + userId + "资金账户划转币币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //回滚金额数量，释放冻结
                rollBackFunds(userId,walletFundAccount.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                if(result.length() >= 500){
                    result = result.substring(0,200);
                }
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.error("划转失败");
            }

            if(result.contains("succ")){
                //先获取最新版本号
                walletFundAccount = walletFundAccountMapper.selectById(walletFundAccount.getId());
                //释放划转解冻
                Integer integer = walletFundAccountMapper.decreaseFreeze(walletFundAccount.getId(), transferDto.getNum(), walletFundAccount.getVersion());

                //写资金账户记录（转出）
                fundRecordOut(fundTotal,userId,transferDto,AccountRecordEnum.FUNDS_TO_BIBI.getCode());

                //修改划转日志状态：成功
                walletTransfer.setStatus(TransferStatusEnumer.SUCCESS.getCode());
                walletTransfer.setDes("划转成功");
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.ok("划转成功");
            }else if(result.equals("timeOut")){
                //更新状态为 状态未明
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                LOG.error("用户id：" + userId + "资金账户划转币币账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转超时");
            }else{
                //回滚金额数量，释放冻结
                rollBackFunds(userId,walletFundAccount.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                LOG.error("用户id：" + userId + "资金账户划转币币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转失败");
            }

        }else if(TransferTypeEnum.PERSONAL_TO_BIBI.getType().equals(type)){//法币账户划转币币账户
            //获取法币账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId, productType);
            BigDecimal balance = cPersonalAccount.getBalance();

            //判断资金是否充足
            if(cPersonalAccount.getBalance().compareTo(transferDto.getNum()) == -1){
                return ReturnDTO.error("划转失败,法币账户余额不足");
            }

            //写入划转日志表
            TWalletTransfer walletTransfer = writeTransferRecord(transferDto.getUserId(),tAccountConfig.getId(),transferDto.getNum(), TransferStatusEnumer.PENDING.getCode(), TypeChannelEnumer.C_TO_B.getCode());

            //冻结法币账户金额
            Integer r = personalAccountMapper.modifyTransferFrozen(cPersonalAccount.getId(), transferDto.getNum(), cPersonalAccount.getVersion());
            if(r <= 0){
                throw new Exception("法币账户划转币币账户失败");
            }

            //远程调用
            String result = "";
            try {
                result = transferFeignService.biBiAccountEntry(transferDto);
            }catch (RetryableException e){
                LOG.error("用户id：" + userId + "法币账户划转币币账户：超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //更新状态为 状态未明
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.error("划转超时");
            } catch (Exception e){
                LOG.error("用户id：" + userId + "法币账户划转币币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //回滚金额数量，释放冻结
                rollBackPersonal(userId,cPersonalAccount.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                if(result.length() >= 500){
                    result = result.substring(0,200);
                }
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.error("划转失败");
            }

            if(result.contains("succ")){
                //先获取最新版本号
                cPersonalAccount = personalAccountMapper.selectById(cPersonalAccount.getId());
                //释放划转解冻,增加总出金
                Integer integer = personalAccountMapper.decreaseFreeze(cPersonalAccount.getId(), transferDto.getNum(), cPersonalAccount.getVersion());

                //写入币币账户流水(划出)
                //法币账户流水（转出）
                personalRecordOut(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.PERSONAL_TO_BIBI.getCode(),balance,productType);

                //修改划转日志状态：成功
                walletTransfer.setStatus(TransferStatusEnumer.SUCCESS.getCode());
                walletTransfer.setDes("划转成功");
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.ok("划转成功");
            }else if(result.equals("timeOut")){
                //更新状态为 状态未明
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                LOG.error("用户id：" + userId + "法币账户划转币币账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转超时");
            }else{
                //回滚金额数量，释放冻结
                rollBackPersonal(userId,cPersonalAccount.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                LOG.error("用户id：" + userId + "法币账户划转币币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转失败");
            }
        }/*else if(TransferTypeEnum.BIBI_TO_PERSONAL.getType().equals(type)){//币币账户划转法币账户
            //获取法币账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId, productType);
            BigDecimal balance = cPersonalAccount.getBalance();
            //写入划转日志表
            TWalletTransfer walletTransfer = writeTransferRecord(transferDto.getUserId(),tAccountConfig.getId(),transferDto.getNum(), TransferStatusEnumer.PENDING.getCode(), TypeChannelEnumer.B_TO_C.getCode());

            //远程调用
            String result = "";
            try {
                result = transferFeignService.biBiAccountOut(transferDto);
            }catch (RetryableException e){
                LOG.error("用户id：" + userId + "币币账户划转法币账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //更新状态为 状态未明
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.error("划转超时");
            } catch (Exception e){
                LOG.error("用户id：" + userId + "币币账户划转法币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //回滚金额数量，释放冻结
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                if(result.length() >= 499){
                    result = result.substring(0,200);
                }
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.error("划转失败");
            }
            if(result.contains("succ")){
                //法币账户 +
                personIn(balance,cPersonalAccount,transferDto);
                //写法币账户记录（转入）
                personalRecordIn(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.BIBI_TO_PERSONAL.getCode(),balance,productType);

                //修改划转日志状态：成功
                walletTransfer.setStatus(TransferStatusEnumer.SUCCESS.getCode());
                walletTransfer.setDes("划转成功");
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                return ReturnDTO.ok("划转成功");
            }else if(result.equals("timeOut")){
                //更新状态为 状态未明
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                LOG.error("用户id：" + userId + "币币账户划转法币账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转超时");
            }else{
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setDes(result);
                walletTransfer.setUpdateTime(new Date());
                walletTransferMapper.updateById(walletTransfer);
                LOG.error("用户id：" + userId + "币币账户划转法币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转失败");
            }
        }*/else{
            return ReturnDTO.error("暂不支持该类型划转");
        }
        return ReturnDTO.ok("划转成功");
    }

    /**
     * 账户入账（资金划转，被远程调用）
     * @param userId 用户id
     * @param currency 币种名称
     * @param num 数量
     * @return 返回字符串 成功 : succ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String accountEntryFeign(Long userId, String currency, BigDecimal num,Integer type) throws Exception {
        TransferDto transferDto = new TransferDto();
        transferDto.setNum(num);

        //验证小数位长度
        String[] split = transferDto.getNum().toPlainString().split("\\.");
        if(split != null && split.length == 2){
            int length = split[ 1 ].length();
            if (length > 8) {
                return "数量小数位过长";
            }
        }

        //获取币种
        TAccountConfig config = new TAccountConfig();
        config.setValue(currency);
        QueryWrapper<TAccountConfig> qw = new QueryWrapper<TAccountConfig>(config);
        TAccountConfig tAccountConfig = accountConfigMapper.selectOne(qw);
        if(null == tAccountConfig){
            return "划转失败,暂时不支持该币种";
        }
        Integer productType = tAccountConfig.getAccountType();
        if(null == productType){
            return "划转失败,币种类型异常";
        }

        if(TransferTypeEnum.PERSONAL_TO_BIBI.getType().equals(type)){
            //获取用户的c2c账户
            CPersonalAccount cPersonalAccount = getPersonalAccount(userId,productType);

            //划转
            //法币账户 +
            BigDecimal balance = cPersonalAccount.getBalance();
            personIn(balance,cPersonalAccount,transferDto);

            //写法币账户记录（转入）
            personalRecordIn(userId,cPersonalAccount,transferDto,CPersonalAccountEnum.BIBI_TO_PERSONAL.getCode(),balance,productType);

            //写入划转日志
            writeTransferRecord(userId,tAccountConfig.getId(),transferDto.getNum(), TransferStatusEnumer.SUCCESS.getCode(), TypeChannelEnumer.C_TO_B.getCode());
            return "succ";
        }else if(TransferTypeEnum.FUND_TO_BIBI.getType().equals(type)){
            //获取资金账户
            TWalletFundAccount walletFundAccount = getFundAccount(userId, transferDto.getCurrency());

            //划转
            //资金账户 +
            BigDecimal fundAccountTotal = walletFundAccount.getTotal();
            fundAccountIn(walletFundAccount,transferDto);

            //资金账户流水（转入）
            fundRecordIn(fundAccountTotal,userId,transferDto,AccountRecordEnum.BIBI_TO_FUNDS.getCode());

            //写入划转日志
            writeTransferRecord(userId,tAccountConfig.getId(),transferDto.getNum(), TransferStatusEnumer.SUCCESS.getCode(), TypeChannelEnumer.B_TO_FUND.getCode());
            return "succ";
        }else {
            return "暂不支持该类型划转";
        }


    }

    /**
     * 法币账户回滚划转冻结
     * @param userId 用户id
     * @param id id
     * @param num 数量
     */
    private void rollBackPersonal(Long userId, Integer id, BigDecimal num) {
        int result = 0;
        int count = 1;
        while(result == 0){
            LOG.info("用户id："+ userId +"，法币账户划转币币账户，回滚尝试次数：" + count + "次！！！");
            //先获取最新版本号
            CPersonalAccount cPersonalAccount = personalAccountMapper.selectById(id);
            result = personalAccountMapper.modifyTransferFrozen(cPersonalAccount.getId(),num,cPersonalAccount.getVersion());
            count ++;
        }
    }

    /**
     * 资金账户回滚冻结金额
     * @param userId 用户id
     * @param id 钱包id
     * @param num 数量
     */
    private void rollBackFunds(Long userId, Integer id, BigDecimal num) {
        int result = 0;
        int count = 1;
        while(result == 0){
            LOG.info("用户id："+ userId +"，资金账户划转币币账户，回滚尝试次数：" + count + "次！！！");
            //先获取最新版本号
            TWalletFundAccount walletFundAccount = walletFundAccountMapper.selectById(id);
            result = walletFundAccountMapper.modifyTransferFrozen(walletFundAccount.getId(),num,walletFundAccount.getVersion());
            count ++;
        }
    }

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
     * 法币账户(入账)
     */
    public void personIn(BigDecimal balance,CPersonalAccount cPersonalAccount,TransferDto transferDto) throws Exception {
        cPersonalAccount.setBalance(balance.add(transferDto.getNum()));//增加账户余额
        cPersonalAccount.setTotalEnter(cPersonalAccount.getTotalEnter().add(transferDto.getNum()));//增加总入金
        Integer result = personalAccountMapper.inMoney(cPersonalAccount);
        if(result <= 0){
            throw new Exception("资金账户划转法币账户失败");
        }
    }

    /**
     * 法币账户(出账)
     */
    public void personOut(BigDecimal balance,CPersonalAccount cPersonalAccount,TransferDto transferDto){
        cPersonalAccount.setBalance(balance.subtract(transferDto.getNum()));//减少账户余额
        cPersonalAccount.setTotalExit(cPersonalAccount.getTotalExit().add(transferDto.getNum()));//增加总出金
        Integer result2 = personalAccountMapper.outMoney(cPersonalAccount);
        if(result2 <= 0){
            new Exception("法币账户划转资金账户失败");
        }
    }

    /**
     * 法币账户流水（转入）
     */
    public void personalRecordIn(Long userId,CPersonalAccount cPersonalAccount,TransferDto transferDto,Integer type,BigDecimal balance,Integer productType){
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        CPersonalAccountRecord CPersonalAccountRecord = com.walletbiz.pojo.CPersonalAccountRecord.builder()
                .createTime(new Date())
                .recordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep)//流水号
                .userId(userId)
                .peraccountId(cPersonalAccount.getId())//关联个人资产表ID
                .beforeAmount(balance)
                .amount(transferDto.getNum())
                .afterAmount(balance.add(transferDto.getNum()))
                .productType(productType)
                .version(0)
                .recordType(type)
                .remark("资金划转").build();
        personalAccountRecordMapper.insert(CPersonalAccountRecord);
    }

    /**
     * 法币账户流水（转出）
     */
    public void personalRecordOut(Long userId,CPersonalAccount cPersonalAccount,TransferDto transferDto,Integer type,BigDecimal balance,Integer productType){
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        CPersonalAccountRecord CPersonalAccountRecord = com.walletbiz.pojo.CPersonalAccountRecord.builder()
                .createTime(new Date())
                .recordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep)// 流水号
                .userId(userId)
                .peraccountId(cPersonalAccount.getId())//关联个人资产表ID
                .beforeAmount(balance)
                .amount(transferDto.getNum().negate())//转成负数
                .afterAmount(balance.subtract(transferDto.getNum()))
                .productType(productType)
                .version(0)
                .recordType(type)
                .remark("资金划转").build();
        personalAccountRecordMapper.insert(CPersonalAccountRecord);
    }


    /**
     * beta账户流水（转入）
     */
    public void betaRecordIn(BigDecimal betaBalance,Long userId,TransferDto transferDto,GBetaAccount betaAccount,Integer type){
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        GBetaAccountRecord betaRecord = GBetaAccountRecord.builder()
                .createTime(new Date())
                .userId(userId)
                .recordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep)
                .betaAccountId(betaAccount.getId())
                .beforeAmount(betaBalance)
                .amount(transferDto.getNum())
                .afterAmount(betaBalance.add(transferDto.getNum()))
                .recordType(type)
                .remark("资金划转").build();
        betaAccountRecordMapper.insert(betaRecord);
    }

    /**
     * beta账户流水（转出）
     */
    public void betaRecordOut(BigDecimal betaBalance,Long userId,TransferDto transferDto,GBetaAccount betaAccount,Integer type){
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        GBetaAccountRecord betaRecord = GBetaAccountRecord.builder()
                .createTime(new Date())
                .userId(userId)
                .recordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep)
                .betaAccountId(betaAccount.getId())
                .beforeAmount(betaBalance)
                .amount(transferDto.getNum().negate())//转成负数
                .afterAmount(betaBalance.subtract(transferDto.getNum()))
                .recordType(type)
                .remark("资金划转").build();
        betaAccountRecordMapper.insert(betaRecord);
    }

    /**
     * beta账户入账
     */
    public void betaIn(GBetaAccount betaAccount,TransferDto transferDto) throws Exception {
        BigDecimal balance = betaAccount.getBalance();
        betaAccount.setBalance(balance.add(transferDto.getNum()));//增加账户余额
        betaAccount.setTotalEnter(betaAccount.getTotalEnter().add(transferDto.getNum()));//增加总入金
        Integer result = betaAccountMapper.inMoney(betaAccount);
        if(result <= 0){
            throw new Exception("资金账户划转法币账户失败");
        }
    }

    /**
     * beta账户出账
     */
    public void betaOut(GBetaAccount betaAccount,TransferDto transferDto) throws Exception {
        BigDecimal balance = betaAccount.getBalance();
        betaAccount.setBalance(balance.subtract(transferDto.getNum()));//减少账户余额
        betaAccount.setTotalExit(betaAccount.getTotalExit().add(transferDto.getNum()));//增加总出金
        Integer result = betaAccountMapper.outMoney(betaAccount);
        if(result <= 0){
            throw new Exception("资金账户划转法币账户失败");
        }
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
     * 获取beta账户
     */
    private GBetaAccount getBetaAccount(Long userId, Integer productType) {
        GBetaAccount account = GBetaAccount.builder()
                .userId(userId)
                .productType(productType).build();
        QueryWrapper<GBetaAccount> qw = new QueryWrapper<GBetaAccount>(account);
        GBetaAccount betaAccount = betaAccountMapper.selectOne(qw);
        if(null == betaAccount){
            //账户不存在，为用户开通一个资金账户
            betaAccount = createBetaAccount(userId,productType);
        }
        return betaAccount;
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
     * 获取法币账户
     */
    public CPersonalAccount getPersonalAccount(Long userId, Integer productType) {
        //查找法币账户
        CPersonalAccount P = CPersonalAccount.builder()
                .userId(userId)
                .productType(productType).build();
        QueryWrapper<CPersonalAccount> qw = new QueryWrapper<CPersonalAccount>(P);
        CPersonalAccount cPersonalAccount = personalAccountMapper.selectOne(qw);
        if(null == cPersonalAccount){
            //账户为空，为用户开通一个账户
            cPersonalAccount = createPersonalAccount(userId,productType);
        }
        return cPersonalAccount;
    }

    /**
     * 开通法币账户
     */
    public CPersonalAccount createPersonalAccount(Long userId, Integer productType) {
        CPersonalAccount cPersonalAccount = CPersonalAccount.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .usedMargin(BigDecimal.ZERO)
                .dynamicBalance(BigDecimal.ZERO)
                .totalEnter(BigDecimal.ZERO)
                .totalExit(BigDecimal.ZERO)
                .productType(productType)
                .version(0).build();
        personalAccountMapper.insert(cPersonalAccount);
        return cPersonalAccount;
    }

    /**
     * 开通Beta账户
     */
    private GBetaAccount createBetaAccount(Long userId, Integer productType) {
        GBetaAccount gBetaAccount = GBetaAccount.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .usedMargin(BigDecimal.ZERO)
                .dynamicBalance(BigDecimal.ZERO)
                .totalEnter(BigDecimal.ZERO)
                .totalExit(BigDecimal.ZERO)
                .productType(productType)
                .version(0).build();
        betaAccountMapper.insert(gBetaAccount);
        return gBetaAccount;
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
     * 查詢账户余额
     * @param userId 用户id
     * @param accountId 账户id
     * @param currency 币种
     * @return 余额
     */
    @Override
    public String amountBalance(Long userId, Integer accountId, String currency) {
        //获取账户
        TAccountManage m = accountManageMapper.selectById(accountId);
        String accountType = m.getName();

        //查询币种
        TAccountConfig config = new TAccountConfig();
        config.setValue(currency);
        QueryWrapper<TAccountConfig> qw = new QueryWrapper<TAccountConfig>(config);
        TAccountConfig tAccountConfig = accountConfigMapper.selectOne(qw);

        if(AccountTypeEnum.FUND.getName().equals(accountType)){
            //查询资金账户可用余额
            TWalletFundAccount walletFundAccount = new TWalletFundAccount();
            walletFundAccount.setUserId(userId);
            walletFundAccount.setCurrency(currency);
            QueryWrapper<TWalletFundAccount> qw_walletFundAccount = new QueryWrapper<TWalletFundAccount>(walletFundAccount);
            TWalletFundAccount account = walletFundAccountMapper.selectOne(qw_walletFundAccount);
            if(null == account){
                return "0";
            }else{
                return account.getTotal().toPlainString();
            }
        }else if(AccountTypeEnum.PERSONERAL.getName().equals(accountType)){
            //远程调用
            return c2cTransferFeignService.availableBalanceFeign(userId,tAccountConfig.getKeyword());
        }else if(AccountTypeEnum.BETA.getName().equals(accountType)){
            if(null == tAccountConfig){
                //币种表配置记录为空，回传余额为0
                return "0";
            }
            Integer productType = tAccountConfig.getAccountType();

            //查询beta账户的可用余额
            GBetaAccount gBetaAccount = new GBetaAccount();
            gBetaAccount.setUserId(userId);
            gBetaAccount.setProductType(productType);
            QueryWrapper<GBetaAccount> qw_GBetaAccount = new QueryWrapper<GBetaAccount>(gBetaAccount);
            GBetaAccount account = betaAccountMapper.selectOne(qw_GBetaAccount);
            if(null == account){
                return "0";
            }else{
                return account.getBalance().toPlainString();
            }
        }else if(AccountTypeEnum.BIBI.getName().equals(accountType)){
            //远程获取
            return transferFeignService.availableBalanceFeign(userId,tAccountConfig.getKeyword());
        }
        return "0";
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
     * 查詢划转记录
     */
    /*@Override
    @Deprecated
    public Page<RecordVO> queryTransferRecord(Long userId, AccountTypeDto accountTypeDto) {
        Integer currencyId = accountTypeDto.getCurrencyId();
        TAccountConfig accountConfig = new TAccountConfig();
        if(null != currencyId){
            accountConfig = accountConfigMapper.selectById(currencyId);
        }

        Page<RecordVO> page = new Page<>(accountTypeDto.getCurrent(),accountTypeDto.getSize());
        if(null == accountTypeDto.getType()){
            //资金记录类型为空。。。就查询全部
            //查询topN
            List<TopNVO> topN = walletFundAccountRecordMapper.topN(page,userId,accountConfig.getAccountType(),accountConfig.getValue());
            List<RecordVO> list = new ArrayList<>();
            if(null != topN && topN.size() > 0){
                //根据id去获取具体的数据
                topN.forEach(x ->{
                    if("1".equals(x.getAccount())){
                        //资金账户
                        RecordVO vo = walletFundAccountRecordMapper.getRecordById(x.getId());
                        if("提币".equals(vo.getType())){
                            //查询手续费
                            TOrder o = new TOrder();
                            o.setOrderId(vo.getOrderId());
                            TOrder order = orderMapper.selectOne(o);
                            if(null != order){
                                BigDecimal fee = order.getFee();
                                vo.setChangeAmount(vo.getChangeAmount().add(fee));//消去手续费
                                vo.setFee(fee);
                            }
                        }
                        list.add(vo);
                    }else if("2".equals(x.getAccount())){
                        //beta账户
                        RecordVO vo = betaAccountRecordMapper.getRecordById(x.getId());
                        list.add(vo);
                    }else if("3".equals(x.getAccount())){
                        //法币账户
                        RecordVO vo = personalAccountRecordMapper.getRecordById(x.getId());
                        list.add(vo);
                    }
                });
                page.setRecords(list);
                return  page;
            }else{
                return page;
            }
        }else if(1 == accountTypeDto.getType()){
            //提币记录
            List<RecordVO> list = walletFundAccountRecordMapper.selectRecord(page,userId,accountConfig.getValue(), Arrays.asList(2));
            list.forEach(x -> {
                //查询手续费
                TOrder o = new TOrder();
                o.setOrderId(x.getOrderId());
                TOrder order = orderMapper.selectOne(o);
                if(null != order){
                    BigDecimal fee = order.getFee();
                    x.setChangeAmount(x.getChangeAmount().add(fee));//消去手续费
                    x.setFee(fee);
                }
            });
            page.setRecords(list);
            return page;
        }else if(2 == accountTypeDto.getType()){
            //充值记录
            List<RecordVO> list = walletFundAccountRecordMapper.selectRecord(page,userId,accountConfig.getValue(), Arrays.asList(1));
            page.setRecords(list);
            return page;
        }else if(3 == accountTypeDto.getType()){
            //转入资金账户记录
            List<RecordVO> list = walletFundAccountRecordMapper.selectRecord(page,userId,accountConfig.getValue(), Arrays.asList(4,6));
            page.setRecords(list);
            return page;
        }else if(4 == accountTypeDto.getType()){
            //转出资金账户
            List<RecordVO> list = walletFundAccountRecordMapper.selectRecord(page,userId,accountConfig.getValue(), Arrays.asList(3,5));
            page.setRecords(list);
            return page;
        }else if(5 == accountTypeDto.getType()){
            //转入法币账户
            List<RecordVO> list = personalAccountRecordMapper.selectRecord(page,userId,accountConfig.getAccountType(), Arrays.asList(2,4));
            page.setRecords(list);
            return page;
        }else if(6 == accountTypeDto.getType()){
            //转出法币账户
            List<RecordVO> list = personalAccountRecordMapper.selectRecord(page,userId,accountConfig.getAccountType(), Arrays.asList(1,3));
            page.setRecords(list);
            return page;
        }else if(7 == accountTypeDto.getType()){
            //转入beta账户
            List<RecordVO> list = betaAccountRecordMapper.selectRecord(page,userId,accountConfig.getAccountType(), Arrays.asList(1,4));
            page.setRecords(list);
            return page;
        }else if(8 == accountTypeDto.getType()){
            //转出beta账户
            List<RecordVO> list = betaAccountRecordMapper.selectRecord(page,userId,accountConfig.getAccountType(), Arrays.asList(2,3));
            page.setRecords(list);
            return page;
        }
        return null;
    }*/

    /**
     * 查找币种列表
     */
    @Override
    public List<TAccountConfig> currencyList() {
        QueryWrapper<TAccountConfig> wrapper = new QueryWrapper<>();
        return accountConfigMapper.selectList(wrapper);
    }

    /**
     * 查询用户的所有的usdt
     * @param userId 用户id
     * @return
     */
    @Override
    public String getAssets(Long userId) {
        ConversionVo vo = ConversionVo.builder().USDT(BigDecimal.ZERO).CNY(BigDecimal.ZERO).build();
        //btc转cny汇率
        BigDecimal BTC_TO_CNY_RATE = parameterConfigService.getRateByKey(RedisKeyUtil.BTC_TO_CNY_RATE);
        //BITT_CNY汇率
        BigDecimal BITT_TO_CNY_RATE = parameterConfigService.getRateByKey(RedisKeyUtil.BITT_TO_CNY_RATE);
        //usdt转人民币汇率
        BigDecimal USDT_TO_CNY_RATE = parameterConfigService.getRateByKey(RedisKeyUtil.USD_TO_CNY_RATE_KEY);

        BigDecimal totalUsdt = BigDecimal.ZERO;
        BigDecimal totalCny = BigDecimal.ZERO;

        /** 资金账户 */
        QueryWrapper<TWalletFundAccount> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("user_id", userId);
        List<TWalletFundAccount> tWalletFundAccounts = walletFundAccountMapper.selectList(entityWrapper);
        for (TWalletFundAccount t : tWalletFundAccounts) {
            BigDecimal all = t.getTotal().add(t.getTransferFrozen());
            if("USDT".equals(t.getCurrency())){
                totalUsdt = totalUsdt.add(all);
                continue;
            }
            Object o = redisTemplate.opsForValue().get(RedisKeyUtil.REDIS_PREFIX_LINE_PRICE + t.getCurrency() + "-USDT");
            BigDecimal rate = new BigDecimal(o.toString());
            totalUsdt = totalUsdt.add(rate.multiply(all));
        }

        //USDT保留四位小数精度计算
        BigDecimal usdtAmount = totalCny.divide(USDT_TO_CNY_RATE,4, BigDecimal.ROUND_HALF_UP);
        totalUsdt = totalUsdt.add(usdtAmount);
        vo.setUSDT(totalUsdt);
        return totalUsdt.toString();
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
    public List<TWalletFundAccount> detail(Long userId) {
        QueryWrapper<TWalletFundAccount> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("user_id", userId);
        List<TWalletFundAccount> tWalletFundAccounts = walletFundAccountMapper.selectList(entityWrapper);


        //为用户创建资金账户
        if(null == tWalletFundAccounts || tWalletFundAccounts.size() == 0 || tWalletFundAccounts.size() == 1){
            //查询有没有udst资金账户
            TWalletFundAccount fundUSDT = new TWalletFundAccount();
            fundUSDT.setUserId(userId);
            fundUSDT.setCurrency(CoinType.USDT.toString());
            QueryWrapper<TWalletFundAccount> qw = new QueryWrapper<>(fundUSDT);
            TWalletFundAccount usdtAccount = walletFundAccountMapper.selectOne(qw);
            if(usdtAccount == null){
                //创建udst资金账户
                usdtAccount = createFundAccount(userId, CoinType.USDT.toString());
                tWalletFundAccounts.add(usdtAccount);
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
                tWalletFundAccounts.add(btcAccount);
            }
        }
        return tWalletFundAccounts;
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
