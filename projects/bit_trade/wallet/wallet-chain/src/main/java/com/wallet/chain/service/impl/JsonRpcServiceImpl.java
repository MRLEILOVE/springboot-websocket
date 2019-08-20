package com.wallet.chain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.wallet.chain.dto.*;
import com.wallet.chain.exception.CommonException;
import com.wallet.chain.service.IJsonRpcService;
import com.wallet.chain.utils.IdUtils;
import com.wallet.chain.utils.OkHttpUtils;

import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.script.Script;
import org.spongycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JsonRpcServiceImpl implements IJsonRpcService {


    private String AUTHORIZATION;

    private JsonRpcHttpClient CLIENT;

    @Value("${btc.point.url}")
    private String pointUrl;
    @Value("${btc.point.user}")
    private String pointUser;
    @Value("${btc.point.password}")
    private String pointPassword;
    @Value("${btc.fee_rate.url}")
    private String feeRateUrl;

    @PostConstruct
    public void init() {
        this.AUTHORIZATION = "Basic " + Base64Utils.encodeToString((pointUser + ":" + pointPassword).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Authorization", this.AUTHORIZATION);
        URL url = null;
        try {
            url = new URL(pointUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException("地址错误");
        }
        this.CLIENT = new JsonRpcHttpClient(url, headers);
    }

    /**
     * 接口调用
     *
     * @param methodName
     * @param params
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T doRequest(String methodName, JSONArray params, Class<T> clazz) {

        try {
            if (String.class.equals(clazz)) {
                return (T) this.CLIENT.invoke(methodName, params, Object.class).toString();
            }
            return this.CLIENT.invoke(methodName, params, clazz);
        } catch (Throwable e) {
            String mark = IdUtils.nextId();
            log.error("JsonRpcServiceImpl.doRequest.error methodName={} mark={} ", methodName, mark, e);
            throw CommonException.FAILURE(mark + " " + e.getMessage());
        }
    }

    @Override
    public BigInteger getLastBlock() {
        return this.doRequest("getblockcount", new JSONArray(), BigInteger.class);
    }

    @Override
    public BigDecimal getTokenBalance(String address, String contract) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(address);
        rpcParams.add(Integer.valueOf(contract));
        TokenBalanceDto tokenBalanceDto = this.doRequest("omni_getbalance", rpcParams, TokenBalanceDto.class);
        return tokenBalanceDto.getBalance();
    }

    @Override
    public List<String> listTokenBlockTransactions(BigInteger block) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(block);
        JSONArray jsonArray = this.doRequest("omni_listblocktransactions", rpcParams, JSONArray.class);
        return jsonArray.toJavaList(String.class);
    }

    @Override
    public OmniTransactionResultDto getTokenTransaction(String txId) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(txId);
        return this.doRequest("omni_gettransaction", rpcParams, OmniTransactionResultDto.class);
    }

    @Override
    public List<UTXO> getPointUnspent(String address) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(0);
        rpcParams.add(9999999);
        JSONArray addressArray = new JSONArray();
        addressArray.add(address);
        rpcParams.add(addressArray);

        JSONArray jsonArray = this.doRequest("listunspent", rpcParams, JSONArray.class);
        List<PointUnSpendDto> pointUnSpendDtoList = jsonArray.toJavaList(PointUnSpendDto.class);
        List<UTXO> list = new ArrayList<>();
        pointUnSpendDtoList.forEach(pointUnSpendDto -> {
            list.add(new UTXO(
                    Sha256Hash.wrap(pointUnSpendDto.getTxid()),
                    pointUnSpendDto.getVout(),
                    Coin.valueOf(pointUnSpendDto.getAmount().multiply(BigDecimal.TEN.pow(8)).longValue()),
                    0,
                    false,
                    new Script(Hex.decode(pointUnSpendDto.getScriptPubKey()))
            ));
        });
        return list;
    }

    /**
     * 获取费率
     *
     * @return
     */
    @Override
    public FeeRateDto getFeeRate() {

        try {
            String result = OkHttpUtils.get(feeRateUrl);
            return JSON.parseObject(result, FeeRateDto.class);
        } catch (IOException e) {
            log.warn("JsonRpcServiceImpl.getFeeRate.获取交易费率异常", e);
            return new FeeRateDto();
        }
    }

    @Override
    public String sendRawTransaction(String sign) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(sign);
        return this.doRequest("sendrawtransaction", rpcParams, String.class);
    }

    @Override
    public void importAddress(String address) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(address);
        rpcParams.add(address);
        rpcParams.add(false);

        try {
            this.CLIENT.invoke("importaddress", rpcParams, Object.class);
        } catch (Throwable e) {
            String mark = IdUtils.nextId();
            log.error("JsonRpcServiceImpl.importAddress.error methodName=importaddress mark={} ", mark, e);
            throw CommonException.FAILURE(mark + " " + e.getMessage());
        }
    }

    /**
     * 只能是节点上管理的钱包
     *
     * @param address
     * @return
     */
    @Override
    public BigDecimal getBalance(String address) {

        JSONArray rpcParams = new JSONArray();
        rpcParams.add(0);
        rpcParams.add(9999999);
        JSONArray addressArray = new JSONArray();
        addressArray.add(address);
        rpcParams.add(addressArray);

        JSONArray jsonArray = this.doRequest("listunspent", rpcParams, JSONArray.class);
        List<PointUnSpendDto> pointUnSpendDtoList = jsonArray.toJavaList(PointUnSpendDto.class);

        BigDecimal amount = BigDecimal.ZERO;
        for (PointUnSpendDto unSpendDto : pointUnSpendDtoList) {
            amount = unSpendDto.getAmount().add(amount);
        }
        return amount;
    }

    @Override
    public DecoderawtransactionDto decodeRawTransaction(String sign) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(sign);
        return this.doRequest("decoderawtransaction", rpcParams, DecoderawtransactionDto.class);
    }

    /**
     * 校验地址是否有效
     *
     * @param address
     * @return
     */
    @Override
    public boolean validateAddress(String address) {

        JSONArray rpcParams = new JSONArray();
        rpcParams.add(address);
        return this.doRequest("validateaddress", rpcParams, ValidateAddressDto.class).getIsvalid();
    }

    @Override
    public String getBlockHash(BigInteger block) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(block);
        return this.doRequest("getblockhash", rpcParams, String.class);
    }

    @Override
    public BlockInfoDto getBlockInfo(String hash) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(hash);
        return this.doRequest("getblock", rpcParams, BlockInfoDto.class);
    }

    /**
     * 查询交易信息：从块[BuffHASH]止，获取最近的全部交易【不包括blockHash的交易】
     *
     * @param blockHash
     * @return
     */
    @Override
    public ListSinceBlockDto listSinceBlock(String blockHash) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(blockHash);
        return this.doRequest("listsinceblock", rpcParams, ListSinceBlockDto.class);
    }

    @Override
    public RawtransactionDto getRawTransaction(String txHash) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(txHash);
        rpcParams.add(2);
        return this.doRequest("getrawtransaction", rpcParams, RawtransactionDto.class);
    }
}

