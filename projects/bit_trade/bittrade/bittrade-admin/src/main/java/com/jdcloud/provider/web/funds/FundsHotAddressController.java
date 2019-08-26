
package com.jdcloud.provider.web.funds;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.CoinTypeDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.TWalletFundAccountRecord;
import com.jdcloud.provider.pojo.WConfigWallet;
import com.jdcloud.provider.service.IJsonRpcService;
import com.jdcloud.provider.service.WConfigWalletService;
import com.jdcloud.provider.utils.AesUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.provider.web.base.BaseEntity;
import com.jdcloud.util.RandomUtil;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bitcoinj.core.*;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 热钱包控制器
 */
@Controller
@RequestMapping("/funds/hotAddress")
public class FundsHotAddressController extends BaseController {

    private String prefix = "funds/hotAddress";

    @Autowired
    private IJsonRpcService jsonRpcService;

    @Autowired
    private WConfigWalletService wConfigWalletService;

    @Autowired
    private NetworkParameters networkParameters;



    @RequiresPermissions("newWithDrawInfo:view")
    @GetMapping()
    public String newWithDrawInfo() {
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BaseEntity baseEntity) {
        Page<WConfigWallet> page = wConfigWalletService.listHotAddress(getPage(), baseEntity);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }


    @RequiresPermissions("addWithDrawInfo:view")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Wrapper createAddress(CoinTypeDto coinTypeDto) throws Exception {
        ECKey key = new ECKey();
        WConfigWallet wConfigWallet = new WConfigWallet();
        wConfigWallet.setAddress(key.toAddress(networkParameters).toBase58());
       // wConfigWallet.setKeystore(AesUtils.aesEncrypt(key.getPrivateKeyAsWiF(networkParameters), "1234567812345678")); //dev
        wConfigWallet.setKeystore(AesUtil.aesEncrypt(key.getPrivateKeyAsWiF(networkParameters), "1000002072188457"));
        wConfigWallet.setValid("D");
        wConfigWallet.setWalletType(coinTypeDto.getWalletType());
        wConfigWallet.setCoinType("暂无");
        String s = key.toAddress(networkParameters).toBase58();
        jsonRpcService.importAddress(s);
        boolean b = jsonRpcService.validateAddress(s);
        System.out.println(b);
        boolean insert = wConfigWalletService.insert(wConfigWallet);
        if (insert){
            return WrapMapper.ok("success");
        }
        return WrapMapper.error("error");
    }


    @RequestMapping("/update/{id}/{accountId}")
    @ResponseBody
    public Wrapper updateAddress(@PathVariable Integer id,@PathVariable Integer accountId){
        EntityWrapper<WConfigWallet>entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("valid","E");
        if (1==accountId){
            entityWrapper.eq("coin_type","BTC").eq("wallet_type","withdraw");
        }else if(2==accountId){
            entityWrapper.eq("coin_type","BTC").eq("wallet_type","fee");
        }else if(3==accountId){
            entityWrapper.eq("coin_type","BTC_TOKEN").eq("wallet_type","withdraw");
        }else if(4==accountId){
            entityWrapper.eq("coin_type","BTC_TOKEN").eq("wallet_type","fee");
        }
        List<WConfigWallet>list=new ArrayList<>();
        WConfigWallet wConfigWallet = wConfigWalletService.selectById(id);
        WConfigWallet wConfigWallet1 = wConfigWalletService.selectOne(entityWrapper);
        WConfigWallet wConfigWallet2=wConfigWallet1;
        wConfigWallet.setValid("E");
        wConfigWallet.setWalletType(wConfigWallet1.getWalletType());
        wConfigWallet.setCoinType(wConfigWallet1.getCoinType());
        wConfigWallet1.setCoinType("暂无");
        wConfigWallet1.setValid("D");
        wConfigWallet1 .setWalletType(wConfigWallet2.getWalletType());
        list.add(wConfigWallet);
        list.add(wConfigWallet1);
        boolean b = wConfigWalletService.updateBatchById(list);
        if (b){
            return WrapMapper.ok("success");
        }
        return WrapMapper.error("error");
    }

    /**
     * 初始化热钱包编辑页面
     *     C
     * 2019-7-30
     */
    @RequiresPermissions("fundsHotAddress:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        WConfigWallet wailet = wConfigWalletService.selectById(id);
        mmap.put("accountConfig", wailet);
        return prefix + "/edit";
    }


    /**
     * C
     * 2019-7-30
     * 保存热钱包
     */
    @RequiresPermissions("fundsHotAddress:saveOrUpdate")
    @PostMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(WConfigWallet WConfigWallet) {
        return wConfigWalletService.saveOrUpdate(WConfigWallet);
    }


    /**
     *
     * 设置有效无效热钱包
     */
    @RequestMapping("/updates/{id}")
    @ResponseBody
    public Wrapper updates(@PathVariable Integer id){
        WConfigWallet wailet =   wConfigWalletService.selectById(id);
        if(null ==wailet){
            return WrapMapper.error("热钱包不存在！");
        }
        //是否有效：D无效E有效
        if(wailet.getValid().equals("D")){
            wailet.setValid("E");
        }else{
            wailet.setValid("D");
        }
        boolean bo =   wConfigWalletService.updateById(wailet);
        if(!bo){
            return WrapMapper.error("修改热钱包失败！");
        }
        return WrapMapper.ok("钱包状态设置成功");
    }
}


