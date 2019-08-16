package com.bittrade.currency.service.impl;

import com.bittrade.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.common.enums.*;
import com.bittrade.currency.api.service.ITWalletRecordService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.currency.dao.ITWalletRecordDAO;
import com.bittrade.currency.dao.ITWalletTransferDAO;
import com.bittrade.currency.feign.ITransferFeignService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.model.TWalletRecord;
import com.bittrade.pojo.model.TWalletTransfer;
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

    /**
     * 资金划转（币币账户划转c2c账户）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO transferOfFundsB2C(TransferDto transferDto) throws Exception {
        //验证小数位长度
        String[] split = transferDto.getNum().toPlainString().split("\\.");
        if(split != null && split.length == 2){
            int length = split[ 1 ].length();
            if (length > 8) {
                return ReturnDTO.error( "数量小数位过长" );
            }
        }

        //获取币种
        TCurrency c = new TCurrency();
        c.setName(transferDto.getCurrency());
        TCurrency currency = currencyDAO.getBy(c);
        if(currency == null){
            return ReturnDTO.error( "划转失败，不存在该币种" );
        }
        if(!currency.getStatus().equals(StatusEnumer.ENABLE.getCode())){
            return ReturnDTO.error( "划转失败，该币种状态不可用" );
        }

        //检查币币钱包余额是否充足
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        if(wallet.getTotal().compareTo(transferDto.getNum())  == -1){
            return ReturnDTO.error("划转失败,资金账户余额不足");
        }

        //远程调用，获取划转类型
        Integer type = transferFeignService.getTypeFeign(transferDto.getAccountInId(),transferDto.getAccountOutId());
        if(type == null){
            return ReturnDTO.error("网络繁忙，请扫后再试");
        }

        //搞个变量存储原来有多少钱
        BigDecimal beforeAmount = wallet.getTotal();

        if(TransferTypeEnum.BIBI_TO_PERSONAL.getType().equals(type)){//币币钱包划转法币钱包
            //写入划转日志表
            TWalletTransfer walletTransfer = writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.PENDING.getCode(), TypeChannelEnumer.B_TO_C.getCode(),"");

            //冻结
            Integer r = walletDAO.modifyTransferFrozen(wallet.getId(),transferDto.getNum(),wallet.getVersion());
            if(r <= 0){
                throw new Exception("币币账户划转c2c账户失败");
            }

            //远程调用（增加c2c钱包金额、写入c2c账户流水）
            String result = "";
            try {
                result = transferFeignService.accountEntry(transferDto.getUserId(),transferDto.getCurrency(),transferDto.getNum(),type);
            }catch (RetryableException e){
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转法币账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                return ReturnDTO.error("划转超时");
            }catch (Exception e){
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转法币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //回滚金额数量，释放冻结
                rollBack(transferDto.getUserId(),wallet.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                if(result.length() >= 500){
                    result = result.substring(0,200);
                }
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                return ReturnDTO.error("划转失败");
            }

            if("succ".equals(result)){
                //先获取最新版本号
                wallet = walletDAO.getByPK(wallet.getId());
                //释放划转解冻
                Integer integer = walletDAO.decreaseTransferFreeze(wallet.getId(), transferDto.getNum(), wallet.getVersion());

                //写入币币账户流水(划出)
                walletRecordOut(transferDto,currency,beforeAmount, WalletRecordTypeEnumer.BIBI_TO_PERSONAL.getCode());

                //修改划转日志状态：成功
                walletTransfer.setStatus(TransferStatusEnumer.SUCCESS.getCode());
                walletTransfer.setDes("划转成功");
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransferService.updateById(walletTransfer);
                return ReturnDTO.ok("划转成功");
            }else if("timeOut".equals(result)){
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转法币账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转超时");
            }else{
                //回滚金额数量，释放冻结
                rollBack(transferDto.getUserId(),wallet.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转法币账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转失败");
            }
        }else if(TransferTypeEnum.BIBI_TO_FUND.getType().equals(type)){//币币钱包划转资金钱包
            //写入划转日志表
            TWalletTransfer walletTransfer = writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.PENDING.getCode(), TypeChannelEnumer.B_TO_FUND.getCode(),"");

            //冻结
            Integer r = walletDAO.modifyTransferFrozen(wallet.getId(),transferDto.getNum(),wallet.getVersion());
            if(r <= 0){
                throw new Exception("币币账户划转资金账户失败");
            }

            //远程调用（增加资金钱包金额、写入资金账户流水）
            String result = "";
            try {
                result = transferFeignService.accountEntry(transferDto.getUserId(),transferDto.getCurrency(),transferDto.getNum(),type);
            }catch (RetryableException e){
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转资金账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                return ReturnDTO.error("划转超时");
            }catch (Exception e){
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转资金账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                //回滚金额数量，释放冻结
                rollBack(transferDto.getUserId(),wallet.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                if(result.length() >= 500){
                    result = result.substring(0,200);
                }
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                return ReturnDTO.error("划转失败");
            }

            if("succ".equals(result)){
                //先获取最新版本号
                wallet = walletDAO.getByPK(wallet.getId());
                //释放划转解冻
                Integer integer = walletDAO.decreaseTransferFreeze(wallet.getId(), transferDto.getNum(), wallet.getVersion());

                //写入币币账户流水(划出)
                walletRecordOut(transferDto,currency,beforeAmount, WalletRecordTypeEnumer.BIBI_TO_FUNDS.getCode());

                //修改划转日志状态：成功
                walletTransfer.setStatus(TransferStatusEnumer.SUCCESS.getCode());
                walletTransfer.setDes("划转成功");
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransferService.updateById(walletTransfer);
                return ReturnDTO.ok("划转成功");
            }else if("timeOut".equals(result)){
                walletTransfer.setStatus(TransferStatusEnumer.UNKNOW.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转资金账户:超时，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转超时");
            }else{
                //回滚金额数量，释放冻结
                rollBack(transferDto.getUserId(),wallet.getId(),transferDto.getNum().negate());
                //修改划转日志状态：失败
                walletTransfer.setStatus(TransferStatusEnumer.FAIL.getCode());
                walletTransfer.setUpdateTime(LocalDateTime.now());
                walletTransfer.setDes(result);
                walletTransferService.updateById(walletTransfer);
                LOG.error("用户id：" + transferDto.getUserId() + "币币账户划转资金账户:失败，划转金额：" + transferDto.getNum() + "，币种：" + transferDto.getCurrency());
                return ReturnDTO.error("划转失败");
            }
        }else {
            return ReturnDTO.error("暂不支持该类型划转");
        }
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
     * 币币账户充值
     * @param transferDto
     * @return 成功：succ
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String biBiAccountEntry(TransferDto transferDto) {
        //获取币种
        TCurrency c = new TCurrency();
        c.setName(transferDto.getCurrency());
        TCurrency currency = currencyDAO.getBy(c);
        if(currency == null){
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.C_TO_B.getCode(),"划转失败，不存在该币种");
            return "划转失败，不存在该币种";
        }
        if(!currency.getStatus().equals(StatusEnumer.ENABLE.getCode())){
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.C_TO_B.getCode(),"划转失败，该币种状态不可用");
            return "划转失败，该币种状态不可用";
        }

        //验证小数位长度
        String[] split = transferDto.getNum().toPlainString().split("\\.");
        if(split != null && split.length == 2){
            int length = split[ 1 ].length();
            if (length > 8) {
                writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.C_TO_B.getCode(),"数量小数位过长");
                return "数量小数位过长" ;
            }
        }

        //获取币币账户
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        //搞个变量存储原来有多少钱
        BigDecimal beforeAmount = wallet.getTotal();

        if(TransferTypeEnum.PERSONAL_TO_BIBI.getType().equals(transferDto.getType())){
            //币币账户 +
            Integer r = walletDAO.biBiAccountEntry(wallet.getId(),transferDto.getNum(),wallet.getVersion());
            if(r <= 0){
                writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.C_TO_B.getCode(),"法币账户转入币币账户资金失败");
                return "法币账户转入币币账户资金失败";
            }

            //写入币币账户流水(划入)
            walletRecordIn(transferDto,currency,beforeAmount, WalletRecordTypeEnumer.PERSONAL_TO_BIBI.getCode());

            //记录日志
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.SUCCESS.getCode(), TypeChannelEnumer.C_TO_B.getCode(),"划转成功");
            return "succ";
        }else if(TransferTypeEnum.FUND_TO_BIBI.getType().equals(transferDto.getType())){
            //币币账户 +
            Integer r = walletDAO.biBiAccountEntry(wallet.getId(),transferDto.getNum(),wallet.getVersion());
            if(r <= 0){
                writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.FUND_TO_B.getCode(),"资金账户转入币币账户失败");
                return "资金账户转入币币账户失败";
            }

            //写入币币账户流水(划入)
            walletRecordIn(transferDto,currency,beforeAmount, WalletRecordTypeEnumer.FUNDS_TO_BIBI.getCode());

            //记录日志
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.SUCCESS.getCode(), TypeChannelEnumer.FUND_TO_B.getCode(),"划转成功");
            return "succ";
        }else {
            return "暂时不支持该类型划转";        }

    }


    /**
     * 币币账户出账
     * @param transferDto
     * @return 成功：succ
     */
    @Override
    public String biBiAccountOut(TransferDto transferDto) {
        //获取币种
        TCurrency c = new TCurrency();
        c.setName(transferDto.getCurrency());
        TCurrency currency = currencyDAO.getBy(c);
        if(currency == null){
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.B_TO_C.getCode(),"划转失败，不存在该币种");
            return "划转失败，不存在该币种";
        }
        if(!currency.getStatus().equals(StatusEnumer.ENABLE.getCode())){
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.B_TO_C.getCode(),"划转失败，该币种状态不可用");
            return "划转失败，该币种状态不可用";
        }

        //验证小数位长度
        String[] split = transferDto.getNum().toPlainString().split("\\.");
        if(split != null && split.length == 2){
            int length = split[ 1 ].length();
            if (length > 8) {
                writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.B_TO_C.getCode(),"数量小数位过长");
                return "数量小数位过长" ;
            }
        }

        //获取币币账户
        TWallet wallet = getWallet(transferDto.getUserId(),currency.getId());
        //搞个变量存储原来有多少钱
        BigDecimal beforeAmount = wallet.getTotal();

        //币币账户 -
        Integer r = walletDAO.biBiAccountOut(wallet.getId(),transferDto.getNum(),wallet.getVersion());
        if(r <= 0 ){
            writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.FAIL.getCode(), TypeChannelEnumer.B_TO_C.getCode(),"币币账户资金转出失败");
            return "币币账户资金转出失败";
        }

        //写入币币账户流水(划入)
        walletRecordOut(transferDto,currency,beforeAmount, WalletRecordTypeEnumer.BIBI_TO_PERSONAL.getCode());

        //记录日志
        writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(), TransferStatusEnumer.SUCCESS.getCode(), TypeChannelEnumer.B_TO_C.getCode(),"划转成功");
        return "succ";
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
}
