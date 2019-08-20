package com.wallet.biz.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.wallet.biz.dto.PointUnSpendDto;
import com.wallet.biz.dto.ValidateAddressDto;
import com.wallet.biz.service.IJsonRpcService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
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

            log.error("JsonRpcServiceImpl.doRequest.error methodName={} mark={} ", methodName, e);

        }
        return null;
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

            log.error("JsonRpcServiceImpl.importAddress.error methodName=importaddress mark={} ",  e);
        }
    }

    @Override
    public boolean validateAddress(String address) {

        JSONArray rpcParams = new JSONArray();
        rpcParams.add(address);
        return this.doRequest("validateaddress", rpcParams, ValidateAddressDto.class).getIsvalid();
    }


    @Override
    public Object getomni_getbalance(String address, Integer id) {
        JSONArray rpcParams = new JSONArray();
        rpcParams.add(address);
        rpcParams.add(id);
        return this.doRequest("omni_getbalance", rpcParams, Object.class);
    }


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



}

