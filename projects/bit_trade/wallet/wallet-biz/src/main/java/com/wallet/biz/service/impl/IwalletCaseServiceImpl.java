package com.wallet.biz.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import com.bittrade.pojo.model.*;
import com.common.bittrade.service.IWWalletAccountRecordService;
import com.wallet.biz.api.service.*;
import com.wallet.biz.constant.FlagConstant;
import com.wallet.biz.tool.SnowFlake;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.pojo.vo.AddressResultDto;
import com.bittrade.pojo.vo.CoinTypeVO;
import com.bittrade.pojo.vo.WalletAddressVO;
import com.bittrade.pojo.vo.WithdrawBillParamVo;
import com.common.bittrade.service.IWCoinService;
import com.common.bittrade.service.IWWalletAccountService;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.utils.AesUtils;


@Service
public class IwalletCaseServiceImpl implements IwalletCaseService {
/*    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private IJsonRpcService jsonRpcService;
*/
private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);

    @Autowired
    IWWalletAccountService wWalletAccountService;
    @Autowired
    IWWalletAccountRecordService walletAccountRecordService;
    @Autowired
    IWCoinService wCoinService;
    @Autowired
    IWWalletAddressService wWalletAddressService;
    @Autowired
    ITParamConfigService tParamConfigService;
    @Autowired
    IWWalletBillService walletBillService;
    @Autowired
    IWOrderService orderService;
    @Autowired
    IWUserWalletService wUserWalletService;
    @Autowired
    private NetworkParameters networkParameters;
    @Value("${btc.user-wallet.encrypt-key}")
    private String encryptKey;


    @Override
    public ReturnDTO auditStatus(/*PageDto pageDto*/) {
        return null;
    }

    @Override
    public ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo,Long userID) {
//        SnowFlake snowFlake = new SnowFlake(1, 1);
        WOrder daworder = WOrder.builder()
                .userId(userID)
//                .orderId(String .valueOf(snowFlake.nextId()))
                .orderType(-1)
                .coinType(withdrawBillParamVo.getCoinType())
                .token(withdrawBillParamVo.getToken())
                .fee(new BigDecimal(withdrawBillParamVo.getFree()))
                .amount(new BigDecimal(withdrawBillParamVo.getAmount()))
                .receiverAddress(withdrawBillParamVo.getReceiverAddress())
                .type(1)
                .build();
        return orderService.addOrder(daworder);
    }

    @Override
    public ReturnDTO chongbi(Long userId, CoinTypeVO coinTypeVO) {
        WUserWallet userWallet = wUserWalletService.getOne(new QueryWrapper<>(WUserWallet.builder()
                .userId(userId)
                .coinType(coinTypeVO.getCoinType())
                .build()));

        if (userWallet == null) {
            //创建钱包
            try {
                ECKey key = new ECKey();
                userWallet = WUserWallet.builder()
                        .userId(userId)
                        .coinType(coinTypeVO.getCoinType())
                        .address(key.toAddress(networkParameters).toBase58())
                        .privateKey(AesUtils.aesEncrypt(key.getPrivateKeyAsWiF(networkParameters), encryptKey))
                        .flag((byte)1)
                        .build();
                wUserWalletService.save(userWallet);
            } catch (Exception e) {
//                log.error("CreateAddressStrategyBTC.execute={}", JSON.toJSONString(AddressParamDto), e);
                return ReturnDTO.error("生成钱包地址失败，请重试");
            }
        }

        return ReturnDTO.ok(AddressResultDto.builder()
                .userId(userWallet.getUserId())
                .token(coinTypeVO.getToken())
                .address(userWallet.getAddress())
                .build());
    }

    @Override
    public ReturnDTO rechargeRecord(Long userId/*, CoinTypeVO coinTypeVO*/) {
        List<WOrder> list = orderService.list(new QueryWrapper<>(WOrder.builder()
                .userId(userId)
//                .coinType(coinTypeVO.getCoinType())
//                .token(coinTypeVO.getToken())
                .orderType(1).build()));
        list.sort(new Comparator<WOrder>() {
            @Override
            public int compare(WOrder o1, WOrder o2) {
//                return o1.getCreateTime().compareTo(o2.getCreateTime());
                if (o1.getCreateTime().isAfter(o2.getCreateTime())) {

                    return -1;
                }
                return 1;
            }
        });
        if(list.size()>10){
            return ReturnDTO.ok(list.subList(0,10));
        }
        return ReturnDTO.ok(list);
    }

    @Override
    public ReturnDTO withdrawRecord(Long userId/*, CoinTypeVO coinTypeVO*/) {
        List<WOrder> list = orderService.list(new QueryWrapper<>(WOrder.builder()
                .userId(userId)
//                .coinType(coinTypeVO.getCoinType())
//                .token(coinTypeVO.getToken())
                .orderType(-1).build()));
        Collections.sort(list, new Comparator<WOrder>() {
            @Override
            public int compare(WOrder o1, WOrder o2) {
//                return o1.getCreateTime().compareTo(o2.getCreateTime());
                if(o1.getCreateTime().isAfter(o2.getCreateTime())) {

                    return -1;
                }
                return 1;
            }
        });
        if(list.size()>10){
            return ReturnDTO.ok(list.subList(0,10));
        }
        return ReturnDTO.ok(list);
    }

    @Override
    public ReturnDTO qrCode(Long userId, CoinTypeVO coinTypeVO) {
        WUserWallet userWallet = wUserWalletService.getOne(new QueryWrapper<>(WUserWallet.builder()
                .userId(userId)
                .coinType(coinTypeVO.getCoinType())
                .build()));

        if (userWallet == null || userWallet.getCodeQr()==null) {
            return ReturnDTO.error("该用户还没有二维码");
        }
        return ReturnDTO.ok(userWallet.getCodeQr());
    }

    @Override
    public ReturnDTO addaddress(Long userId, WalletAddressVO walletAddressVO) {
        WCoin wCoin = wCoinService.getOne(new QueryWrapper<>(WCoin.builder()
                .token(walletAddressVO.getTokenType()).build()));
        if (wCoin == null) {
            return ReturnDTO.error("尚不支持该币种");
        }

        WWalletAddress wWalletAddress = wWalletAddressService.getOne(new QueryWrapper<>(WWalletAddress.builder()
                .userId(userId)
                .tokenType(walletAddressVO.getTokenType())
                .address(walletAddressVO.getAddress()).build()));
        if (null == wWalletAddress) {
            wWalletAddress = WWalletAddress.builder()
                    .status((byte) 0)
                    .tokenType(walletAddressVO.getTokenType())
                    .userId(userId)
                    .address(walletAddressVO.getAddress())
                    .name(walletAddressVO.getName())
                    .currencyId(wCoin.getId().intValue())
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now()).build();
        }
        if (wWalletAddress.getStatus() == (byte) 1) {
            return ReturnDTO.error("提币地址已存在，请勿重复添加");
        }
        try {
            wWalletAddress.setStatus((byte) 1);
            boolean insert = wWalletAddressService.saveOrUpdate(wWalletAddress);
            if (insert) {
                return ReturnDTO.ok("提币地址已经成功添加");
            }
        } catch (Exception e) {
            return ReturnDTO.error("提币地址添加出错");
        }
        return ReturnDTO.error("提币地址已存在，请勿重复添加");
    }



    @Override
    public void BillToAccount() {
        List<WWalletBill> walletBillList = walletBillService.list(new QueryWrapper<>(WWalletBill.builder()
                .flag((byte) FlagConstant.UN_PROCESS).build()));
        walletBillList.forEach(walletBill -> {
            WOrder billorder = orderService.getOne(new QueryWrapper<>(WOrder.builder()
                    .orderId(walletBill.getOrderId())
                    .userId(walletBill.getUserId()).build()));
            if(null == billorder){
                billorder = WOrder.builder()
                        .userId(walletBill.getUserId())
                        .orderId(walletBill.getOrderId())
                        .orderType(walletBill.getDirection())
                        .coinType(walletBill.getCoinType())
                        .token(walletBill.getToken())
//                    .fee(new BigDecimal(withdrawBillParamVo.getFree()))
                        .amount(walletBill.getAmount())
                        .receiverAddress(walletBill.getReceiverAddress())
                        .type(5)
                        .build();
            }
            billorder.setType(5);
            WCoin orderWCoin = wCoinService.getOne(new QueryWrapper<>(WCoin.builder()
                    .token(walletBill.getToken())
                    .status((byte)1).build()));
            WWalletAccount orderAccount = wWalletAccountService.getAccount(walletBill.getUserId(),orderWCoin.getId().intValue());

            BigDecimal ch = (walletBill.getAmount().add(billorder.getFee())).multiply(new BigDecimal(walletBill.getDirection()));
            WWalletAccountRecord record = WWalletAccountRecord.builder()
                    .id(SNOW_FLAKE__ENTRUST.nextId())
                    .userId(orderAccount.getUserId())
                    .currencyId(orderAccount.getCurrencyId())
                    .beforeAmount(orderAccount.getTotal().add(orderAccount.getTransferFrozen()))
                    .changeAmount(ch)
                    .afterAmount(orderAccount.getTotal().add(orderAccount.getTransferFrozen()).add(ch))
                    .createTime(LocalDateTime.now())
                    .build();
            if(walletBill.getDirection()==1){
                record.setType((byte)1);
                orderAccount.setTotal(record.getAfterAmount());
            }else{
                record.setType((byte)2);
                orderAccount.setTransferFrozen(orderAccount.getTransferFrozen().add(ch));
            }
            wWalletAccountService.updateById(orderAccount);
            walletAccountRecordService.save(record);
            orderService.saveOrUpdate(billorder);
            walletBillService.update(WWalletBill.builder().flag((byte)FlagConstant.PROCESSED).build(),
                    new QueryWrapper<>(WWalletBill.builder()
                            .id(walletBill.getId())
                            .flag((byte)FlagConstant.UN_PROCESS)
                            .build()));
        });
    }

    @Override
    public void OrderToBill() {
        List<WOrder> wOrderList = orderService.list(new QueryWrapper<>(WOrder.builder()
                .type(3).build()));
        for (WOrder wOrder:wOrderList){

            WWalletBill checke = walletBillService.getOne(new QueryWrapper<>(WWalletBill.builder().orderId(wOrder.getOrderId()).build()));
            if(checke == null){
                WWalletBill Bill = WWalletBill.builder()
                        .userId(wOrder.getUserId())
                        .orderId(wOrder.getOrderId())
                        .direction(wOrder.getOrderType())
                        .coinType(wOrder.getCoinType())
                        .token(wOrder.getToken())
                        .contract(wOrder.getContract())
                        .receiverAddress(wOrder.getReceiverAddress())
                        .transferFlag((byte)FlagConstant.SKIP)
                        .amount(wOrder.getAmount().multiply(new BigDecimal(-1)))
                        .tx(wOrder.getOrderId())
                        .tradeStep("10")
                        //TODO 生产改为"20"
                        .build();
                walletBillService.save(Bill);
            }
            wOrder.setType(6);
            orderService.saveOrUpdate(wOrder);
        }
    }

    @Override
    public ReturnDTO checkparam(CoinTypeVO coinTypeVO, Long userID) {
        WCoin orderWCoin = wCoinService.getOne(new QueryWrapper<>(WCoin.builder()
                .token(coinTypeVO.getToken())
//                .isWithdraw("E")
                .status((byte)1).build()));
        WWalletAccount orderAccount = wWalletAccountService.getAccount(userID,orderWCoin.getId().intValue());
//        BigDecimal minfee = new BigDecimal(tParamConfigService.getEnableValueByKey(coinTypeVO.getToken().toLowerCase()+"MinFee"));
//        BigDecimal minquota = new BigDecimal(tParamConfigService.getEnableValueByKey(coinTypeVO.getToken().toLowerCase()+"Min"));
//
//        int i1 = orderAccount.getTotal().compareTo(minquota.add(minfee));
//        if (i1 < 0 ) {
//            return ReturnDTO.ok(BigDecimal.ZERO);
//        }
 //       return ReturnDTO.ok(orderAccount.getTotal().subtract(minfee));
        return ReturnDTO.ok(orderAccount.getTotal());
    }

}
