package com.wallet.biz.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.WebUtil;
import com.wallet.biz.Exception.FlowException;
import com.wallet.biz.dto.AddressDto;
import com.wallet.biz.pojo.TWalletAddress;
import com.wallet.biz.service.TWalletAddressService;
import com.wallet.biz.vo.CoinTypeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helen
 * @since 2019-07-10
 */
@RestController
@RequestMapping(value = "/tWalletAddress",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value="TWalletAddressController",tags={"钱包地址管理"})
public class TWalletAddressController {
    @Autowired
    private TWalletAddressService walletAddressService;


    @PostMapping("addOrUpdate")
    @ApiOperation(value="添加或者更新地址", notes="添加或者更新地址")
    public ReturnDTO<String> add(@RequestBody @Validated AddressDto addressDto) throws FlowException {
         Long user_id = WebUtil.getLoginUser().getUser_id();
        if (user_id==null){
            throw new FlowException("用户未登录");
        }
        System.out.println(addressDto);
        boolean result;
        if (null!=addressDto.getId()&&addressDto.getId()>0){
            TWalletAddress walletAddress = walletAddressService.getById(addressDto.getId());
            if (null!=addressDto.getName()&&!"".equals(addressDto.getName())){
                walletAddress.setName(addressDto.getName());
            }
            if (null!=addressDto.getAddress()&&!"".equals(addressDto.getAddress())){
                walletAddress.setAddress(addressDto.getAddress());
            }
            walletAddress.setTokenType(addressDto.getTokenType());
           result = walletAddressService.updateById(walletAddress);
        }else {
            TWalletAddress walletAddress= new TWalletAddress();
            walletAddress.setUserId(user_id);
            if (null!=addressDto.getName()&&!"".equals(addressDto.getName())){
                walletAddress.setName(addressDto.getName());
            }
            walletAddress.setTokenType(addressDto.getTokenType());
            walletAddress.setAddress(addressDto.getAddress());
            result = walletAddressService.save(walletAddress);
        }
        if (result){
            return ReturnDTO.ok("成功");
        }
        return ReturnDTO.ok("系统错误");
    }

    @PostMapping("del/{id}")
    @ApiOperation(value="删除地址", notes="删除地址")
    @ApiParam(value = "地址id" ,required = true,example="1")
    public ReturnDTO<String> del(@PathVariable Integer id){
        boolean result = walletAddressService.removeById(id);
        if (result){
            return ReturnDTO.ok("成功");
        }
        return ReturnDTO.ok("系统错误");
    }


    @PostMapping("selectById/{id}")
    @ApiOperation(value="查询地址", notes="查询地址")
    @ApiParam(value = "地址id" ,required = true,example="1")
    public ReturnDTO<TWalletAddress> selectById(@PathVariable Integer id){
        TWalletAddress tWalletAddress = walletAddressService.getById(id);
        return ReturnDTO.ok(tWalletAddress);
    }

    @PostMapping("list")
    @ApiOperation(value="地址列表", notes="地址列表")
    public ReturnDTO addressList(@RequestBody CoinTypeDto coinTypeDto) throws FlowException {
        Long user_id = WebUtil.getLoginUser().getUser_id();
        if (user_id==null){
            throw new FlowException("用户未登录");
        }
        QueryWrapper<TWalletAddress>entityWrapper=new QueryWrapper<>();
        entityWrapper.eq("user_id",user_id);
        if (null!=coinTypeDto.getToken()&&""!=coinTypeDto.getToken()){
            entityWrapper.eq("token_type",coinTypeDto.getToken());
        }
        List<TWalletAddress> tWalletAddresses = walletAddressService.list(entityWrapper);
        return ReturnDTO.ok(tWalletAddresses);
    }
}

