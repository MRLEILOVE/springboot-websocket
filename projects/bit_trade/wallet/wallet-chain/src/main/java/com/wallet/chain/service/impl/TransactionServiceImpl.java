package com.wallet.chain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wallet.chain.constant.WalletTypeConstant;
import com.wallet.chain.dto.CalculationFeeDto;
import com.wallet.chain.dto.TransferDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.ConfigWallet;
import com.wallet.chain.entity.UserWallet;
import com.wallet.chain.entity.WalletBill;
import com.wallet.chain.enums.CoinTypeEnum;
import com.wallet.chain.service.ConfigWalletService;
import com.wallet.chain.service.IJsonRpcService;
import com.wallet.chain.service.ITransactionService;
import com.wallet.chain.utils.AesUtils;
import com.wallet.chain.utils.BtcUtils;
import com.wallet.chain.utils.ValidatorUtils;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.*;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.spongycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private ConfigWalletService configWalletService;
    @Autowired
    private NetworkParameters networkParameters;
    @Value("${btc.config-wallet.encrypt-key}")
    private String encryptKey;
    @Value("${btc.user-wallet.encrypt-key}")
    private String userWalletEncryptKey;

    @Override
    public TransferDto withdraw(CoinConfig coinConfig, WalletBill WalletBill) {

        ConfigWallet withdrawWallet = configWalletService.getOne(new QueryWrapper<>(ConfigWallet.builder()
                .coinType(coinConfig.getCoinType())
                .walletType(WalletTypeConstant.WITHDRAW)
                .build()));
        System.out.println(withdrawWallet);
        ValidatorUtils.isNullThrow(withdrawWallet, "btc代币提币钱包不存在");
        //签名
        TransferDto transferDto = btcTokenSign(coinConfig, withdrawWallet.getAddress(),
                AesUtils.aesDecrypt(withdrawWallet.getKeystore(), encryptKey),
                WalletBill.getReceiverAddress(),
                WalletBill.getAmount());

        //广播交易
        if(transferDto!=null){
            jsonRpcService.sendRawTransaction(transferDto.getSign());
        }
        return transferDto;
    }

    @Override
    public TransferDto btcTokenCollection(CoinConfig coinConfig, UserWallet userWallet, ConfigWallet feeWallet, BigDecimal amount) {
        //签名
        TransferDto transferDto = btcTokenSign(coinConfig,
                userWallet.getAddress(),
                AesUtils.aesDecrypt(userWallet.getPrivateKey(), userWalletEncryptKey),
                feeWallet.getAddress(),
                AesUtils.aesDecrypt(feeWallet.getKeystore(), encryptKey),
              //  AesUtils.aesDecrypt(feeWallet.getKeystore(), userWalletEncryptKey),
                coinConfig.getBossAddress(),
                amount);

        //广播交易
        // update w_user_wallet_bill set flag=2 where id = xx;

        if(transferDto!=null){
            jsonRpcService.sendRawTransaction(transferDto.getSign());
        }
        return transferDto;
    }

    @Override
    public TransferDto btcCollection(CoinConfig coinConfig, UserWallet userWallet, ConfigWallet feeWallet) {
        //签名
        TransferDto transferDto = btcCollectionSign(coinConfig,
                userWallet.getAddress(),
                AesUtils.aesDecrypt(userWallet.getPrivateKey(), userWalletEncryptKey),
                feeWallet.getAddress(),
                AesUtils.aesDecrypt(feeWallet.getKeystore(), encryptKey));

        //广播交易
        if(transferDto!=null){
            jsonRpcService.sendRawTransaction(transferDto.getSign());
        }
        return transferDto;
    }

    public TransferDto btcCollectionSign(CoinConfig coinConfig, String fromAddress, String fromPrivateKey, String feeAddress, String feePrivateKey) {

        List<UTXO> unSpendList = jsonRpcService.getPointUnspent(fromAddress);
        if (unSpendList == null || unSpendList.size() == 0) {
            return null;
        }

        long longAmount = 0L;
        for (UTXO utxo : unSpendList) {
            longAmount += utxo.getValue().getValue();
        }

        List<UTXO> willUse = new ArrayList<>();//将被使用的未花费

        List<UTXO> feeUnSpendList = jsonRpcService.getPointUnspent(feeAddress);
        willUse.addAll(unSpendList);
        willUse.addAll(feeUnSpendList);

        return sign(coinConfig, willUse, fromAddress, fromPrivateKey, feeAddress, feePrivateKey,
                coinConfig.getBossAddress(), feeAddress, new BigDecimal(longAmount / 100000000L));
    }

    public TransferDto btcTokenSign(CoinConfig coinConfig, String fromAddress, String fromPrivateKey, String toAddress, BigDecimal amount) {
        System.out.println(fromAddress);
        List<UTXO> willUse = jsonRpcService.getPointUnspent(fromAddress);
        ValidatorUtils.listIsEmptyThrow(willUse, "提币钱包btc不足" + fromAddress);

        return sign(coinConfig, willUse, fromAddress, fromPrivateKey, fromAddress, fromPrivateKey,
                toAddress, fromAddress, amount);
    }

    public TransferDto btcTokenSign(CoinConfig coinConfig, String fromAddress, String fromPrivateKey, String feeAddress, String feePrivateKey,
                                    String toAddress, BigDecimal amount) {

        List<UTXO> withdrawUnSpendList = jsonRpcService.getPointUnspent(fromAddress);
        ValidatorUtils.listIsEmptyThrow(withdrawUnSpendList, "提币钱包btc不足" + fromAddress);

        List<UTXO> willUse = new ArrayList<>();//将被使用的未花费

        List<UTXO> feeUnSpendList = jsonRpcService.getPointUnspent(feeAddress);
        willUse.add(withdrawUnSpendList.get(0));
        willUse.addAll(feeUnSpendList);

        return sign(coinConfig, willUse, fromAddress, fromPrivateKey, feeAddress, feePrivateKey,
                toAddress, feeAddress, amount);
    }

    public TransferDto sign(CoinConfig coinConfig, List<UTXO> willUse, String fromAddress, String fromPrivateKey, String feeAddress, String feePrivateKey,
                            String toAddress, String changeAddress, BigDecimal amount) {
        Transaction tran = new Transaction(networkParameters);


        //计算未花费和手续费
        BigDecimal btcAmount = CoinTypeEnum.BTC.getCoinType().equalsIgnoreCase(coinConfig.getCoinType()) ? amount : new BigDecimal("0.00000546");
        long longBtcAmount = BigDecimal.TEN.pow(8).multiply(btcAmount).longValue();
        CalculationFeeDto calculationFeeDto = BtcUtils.calculationFee(willUse, jsonRpcService.getFeeRate().getHalfHourFee(), btcAmount);
        List<UTXO> needUtxo = calculationFeeDto.getSenderUnSpend();

        long surplusAmount = calculationFeeDto.getUtxoSum() - (calculationFeeDto.getFee() + longBtcAmount);
        //余额判断
        ValidatorUtils.isTrueThrow(surplusAmount < 0, "提币钱包或手续费钱包btc不足");

        if (surplusAmount > 0) {
            tran.addOutput(Coin.valueOf(surplusAmount), Address.fromBase58(networkParameters, changeAddress));
        }

        tran.addOutput(Coin.valueOf(longBtcAmount), Address.fromBase58(networkParameters, toAddress));

        //构建usdt的输出脚本 注意这里的金额是要乘10的8次方
        if (CoinTypeEnum.BTC_TOKEN.getCoinType().equalsIgnoreCase(coinConfig.getCoinType())) {
            String usdtHex = BtcUtils.handleUSDTHex(amount, coinConfig.getContract());
            tran.addOutput(Coin.valueOf(0L), new Script(Utils.HEX.decode(usdtHex)));
        }

        //签名
        int length = needUtxo.size();
        UTXO utxo;
        for (int i = 0; i < length; i++) {
            utxo = needUtxo.get(i);
            TransactionInput transactionInput = tran.addInput(utxo.getHash(), utxo.getIndex(), utxo.getScript());
            String pK;
            if (utxo.getScript().getToAddress(networkParameters).toBase58().equalsIgnoreCase(fromAddress)) {
                pK = fromPrivateKey;
            } else {
                pK = feePrivateKey;
            }
            ECKey ecKey = DumpedPrivateKey.fromBase58(networkParameters, pK).getKey();
            Sha256Hash hash = tran.hashForSignature(i, utxo.getScript(), Transaction.SigHash.ALL, true);
            ECKey.ECDSASignature ecSig = ecKey.sign(hash);
            TransactionSignature txSig = new TransactionSignature(ecSig, Transaction.SigHash.ALL, true);

            transactionInput.setScriptSig(ScriptBuilder.createInputScript(txSig, ecKey));
        }

        //这是签名之后的原始交易，直接去广播就行了
        String signedHex = Hex.toHexString(tran.bitcoinSerialize());
        //这是交易的hash
        String txHash = Hex.toHexString(Utils.reverseBytes(Sha256Hash.hash(Sha256Hash.hash(tran.bitcoinSerialize()))));
        return TransferDto.builder().sign(signedHex).tx(txHash).senderAddress(fromAddress).build();
    }

}
