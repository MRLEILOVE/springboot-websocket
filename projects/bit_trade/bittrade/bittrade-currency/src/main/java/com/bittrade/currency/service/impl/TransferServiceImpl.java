package com.bittrade.currency.service.impl;

import com.bittrade.__default.service.impl.DefaultTWalletServiceImpl;
import com.bittrade.common.enums.SourceChannelEnumer;
import com.bittrade.common.enums.StatusEnumer;
import com.bittrade.common.enums.TransferStatusEnumer;
import com.bittrade.common.enums.TypeChannelEnumer;
import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.currency.api.service.ITransferService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.currency.feign.ITransferFeignService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.pojo.vo.TWalletVO;
import com.core.common.DTO.ReturnDTO;
import com.core.tool.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class TransferServiceImpl extends DefaultTWalletServiceImpl<ITWalletDAO, TWallet, TWalletDTO, TWalletVO> implements ITransferService {
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private ITransferFeignService transferFeignService;
    @Autowired
    private ITWalletDAO walletDAO;
    @Autowired
    private ITCurrencyDAO currencyDAO;
    @Autowired
    private ITWalletTransferService walletTransferService;


    /**
     * 资金划转（币币账户划转c2c账户）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnDTO transferOfFundsB2C(TransferDto transferDto) {
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



        //写入钱包划转记录表
        TWalletTransfer walletTransfer = writeTransferRecord(transferDto.getUserId(),currency.getId(),transferDto.getNum(),TransferStatusEnumer.PENDING.getCode(),TypeChannelEnumer.B_TO_C.getCode());

        //远程调用（增加c2c钱包金额、写入c2c账户流水）
        ReturnDTO<String> result = transferFeignService.c2cAccountEntry(transferDto);
        System.out.println(result);
        return null;
    }

    /**
     * 写入钱包划转记录表
     *
     * @param userId 用户id
     * @param currencyId  币种id
     * @param num 数量
     * @param transferStatus 状态
     * @param typeChannel 操作渠道
     * @return
     */
    private TWalletTransfer writeTransferRecord(Long userId, Integer currencyId, BigDecimal num, Byte transferStatus, Byte typeChannel) {
        TWalletTransfer walletTransfer = TWalletTransfer.builder()
                .userId(userId)
                .currency(currencyId)
                .count(num)
                .businessNumber(SNOW_FLAKE__ENTRUST.nextId()+"")
                .status(transferStatus)
                .typeChannel(typeChannel)
                .sourceChannel(SourceChannelEnumer.APP.getCode())
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
