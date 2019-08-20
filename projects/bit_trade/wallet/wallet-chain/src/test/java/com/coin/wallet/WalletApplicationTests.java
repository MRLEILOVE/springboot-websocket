//package com.coin.wallet;
//
//import com.alibaba.fastjson.JSON;
//import com.coin.wallet.constant.DirectionConstant;
//import com.coin.wallet.constant.FlagConstant;
//import com.coin.wallet.constant.TradeStepConstant;
//import com.coin.wallet.dto.BlockInfoDto;
//import com.coin.wallet.dto.RawtransactionDto;
//import com.coin.wallet.dto.VoutDto;
//import com.coin.wallet.entity.CoinConfig;
//import com.coin.wallet.entity.UserWallet;
//import com.coin.wallet.entity.UserWalletBill;
//import com.coin.wallet.service.ICreateAddressFactory;
//import com.coin.wallet.service.IJsonRpcService;
//import com.coin.wallet.utils.AesUtils;
//import com.coin.wallet.utils.IdUtils;
//import org.bitcoinj.core.ECKey;
//import org.bitcoinj.core.NetworkParameters;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.EthBlock;
//import org.web3j.protocol.core.methods.response.EthTransaction;
//import org.web3j.protocol.core.methods.response.Transaction;
//import org.web3j.protocol.http.HttpService;
//
//import java.math.BigInteger;
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class WalletApplicationTests {
//
//    @Autowired
//    ICreateAddressFactory createAddressService;
//
//    @Autowired
//    private IJsonRpcService jsonRpcService;
//
//    @Autowired
//    private NetworkParameters networkParameters;
//    @Test
//    public void contextLoads() throws Exception {
//        Web3j web3j = Web3j.build(new HttpService(
//                "https://mainnet.infura.io/v3/b210e2816eef4c568362e4463fde8002"));
//        CoinConfig coinConfig=new CoinConfig();
//        BigInteger oldBlock = coinConfig.getScanBlock();
//                //当前区块最新高度
//        BigInteger currentBlock = web3j.ethBlockNumber().send().getBlockNumber();
//        String oldBlockHash ="";
//
//        while (currentBlock.compareTo(oldBlock) > 0) {
//            oldBlock = oldBlock.add(BigInteger.ONE);
//            //oldBlockHash = jsonRpcService.getBlockHash(oldBlock);
//            EthBlock.Block block = web3j.ethGetBlockByHash(oldBlockHash, true).send().getBlock();
//
//           // BlockInfoDto blockInfoDto = jsonRpcService.getBlockInfo(oldBlockHash);
//            List<EthBlock.TransactionResult> transactions = block.getTransactions();
//            for (EthBlock.TransactionResult result:transactions) {
//               // RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(txId);
//                Optional<Transaction> transaction = web3j.ethGetTransactionByHash(result.toString()).send().getTransaction();
//                EthTransaction send = web3j.ethGetTransactionByHash(result.toString()).send();
//
//
//            }
//            List<VoutDto> voutDtoList = rawtransactionDto.getVout();
//            for (VoutDto voutDto : voutDtoList) {
//                if (voutDto.getScriptPubKey() != null
//                        && voutDto.getScriptPubKey().getAddresses() != null
//                        && voutDto.getScriptPubKey().getAddresses().get(0) != null) {
//
//                    UserWallet userWallet = userWalletService.cacheUserWallet(coinConfig.getCoinType(), voutDto.getScriptPubKey().getAddresses().get(0));
//
//                    if (userWallet == null) {
//                        continue;
//                    }
//                    try {
//                        userWalletBillService.save(
//                                UserWalletBill.builder()
//                                        .userId(userWallet.getUserId())
//                                        .orderId(IdUtils.nextId())
//                                        .direction(DirectionConstant.IN)
//                                        .coinType(coinConfig.getCoinType())
//                                        .token(coinConfig.getToken())
//                                        .contract(coinConfig.getContract())
//                                        .senderAddress("")
//                                        .receiverAddress(userWallet.getAddress())
//                                        .amount(voutDto.getValue())
//                                        .block(block)
//                                        .tx(rawtransactionDto.getTxid())
//                                        .tradeStep(TradeStepConstant.PACKAGED)
//                                        .flag(FlagConstant.INIT)
//                                        .build());
//                    } catch (DuplicateKeyException e) {
//                        log.warn("RechargeStrategyBTC.handleIn.转入。param={}", JSON.toJSONString(rawtransactionDto), e);
//                    }
//
//                }
//
//
//          /*  List<String> txList = blockInfoDto.getTx();
//            for (String tx : txList) {
//                handleTxId(coinConfig, tx, blockInfoDto.getHeight());
//            }*/
//
//            coinConfigService.updateById(CoinConfig.builder()
//                    .id(coinConfig.getId())
//                    .scanBlock(oldBlock).build());
//        }
//    }
//
//    }
//
//    private void handleTxId(CoinConfig coinConfig, String txId, BigInteger block) {
//        //查询交易详情
//        RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(txId);
//
//        //判断是否为BTC交易,如果不是跳过
//        if (!isBTCTrade(rawtransactionDto)) {
//            return;
//        }
//
//        handle(coinConfig, rawtransactionDto, block);
//    }
//
//    private void handle(CoinConfig coinConfig, RawtransactionDto rawtransactionDto, BigInteger block) {
//
//        List<VoutDto> voutDtoList = rawtransactionDto.getVout();
//        for (VoutDto voutDto : voutDtoList) {
//            if (voutDto.getScriptPubKey() != null
//                    && voutDto.getScriptPubKey().getAddresses() != null
//                    && voutDto.getScriptPubKey().getAddresses().get(0) != null) {
//
//                UserWallet userWallet = userWalletService.cacheUserWallet(coinConfig.getCoinType(), voutDto.getScriptPubKey().getAddresses().get(0));
//
//                if (userWallet == null) {
//                    continue;
//                }
//                try {
//                    userWalletBillService.save(
//                            UserWalletBill.builder()
//                                    .userId(userWallet.getUserId())
//                                    .orderId(IdUtils.nextId())
//                                    .direction(DirectionConstant.IN)
//                                    .coinType(coinConfig.getCoinType())
//                                    .token(coinConfig.getToken())
//                                    .contract(coinConfig.getContract())
//                                    .senderAddress("")
//                                    .receiverAddress(userWallet.getAddress())
//                                    .amount(voutDto.getValue())
//                                    .block(block)
//                                    .tx(rawtransactionDto.getTxid())
//                                    .tradeStep(TradeStepConstant.PACKAGED)
//                                    .flag(FlagConstant.INIT)
//                                    .build());
//                } catch (DuplicateKeyException e) {
//                    log.warn("RechargeStrategyBTC.handleIn.转入。param={}", JSON.toJSONString(rawtransactionDto), e);
//                }
//
//            }
//        }
//    }
//
//}
