
package com.jdcloud.provider.web.funds;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.CoinTypeDto;
import com.jdcloud.provider.pojo.WCoinConfig;
import com.jdcloud.provider.service.IJsonRpcService;
import com.jdcloud.provider.service.WCoinConfigService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funds/coldAddress")
public class FundsColdAddressController extends BaseController {

    private String prefix = "funds/coldAddress";

    @Autowired
    private IJsonRpcService jsonRpcService;

    @Autowired
    private WCoinConfigService wCoinConfigService;

    @Autowired
    private NetworkParameters networkParameters;



    @RequiresPermissions("newWithDrawInfo:view")
    @GetMapping()
    public String newWithDrawInfo() {
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        // Page<TWalletFundAccountRecord> page = iTwalletFundAccountRecordService.selectPage(getPage());
        //return new TableDataInfo(page.getRecords(), page.getTotal());
        Page<WCoinConfig> page = wCoinConfigService.selectPage(getPage());
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }
    @RequiresPermissions("fundsColdAddress:view")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Wrapper createAddress(CoinTypeDto coinTypeDto) {
        WCoinConfig wConfigWallet = new WCoinConfig();
        wConfigWallet.setCoinType(coinTypeDto.getCoinType());
        boolean b = jsonRpcService.validateAddress(coinTypeDto.getAddress());
        if (!b) {
            return WrapMapper.error("该地址不是正确的btc地址");
        }
        wConfigWallet.setBossAddress(coinTypeDto.getAddress());
        wConfigWallet.setValid("D");
        boolean insert = wCoinConfigService.insert(wConfigWallet);
        if (insert) {
            return WrapMapper.ok("success");
        }
        /*  userWalletService.save(userWallet);*/
        return WrapMapper.error("error");
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public Wrapper updateAddress(@PathVariable Long id){
        WCoinConfig wCoinConfig = wCoinConfigService.selectById(id);
        EntityWrapper<WCoinConfig>main=new EntityWrapper<>();
        main.eq("valid","E").eq("coin_type",wCoinConfig.getCoinType());
        WCoinConfig wCoinConfig1 = wCoinConfigService.selectOne(main);
        wCoinConfig1 .setValid("D");
        boolean b = wCoinConfigService.updateById(wCoinConfig1);
        if (b){
        BeanUtils.copyProperties(wCoinConfig1,wCoinConfig);
            wCoinConfig.setValid("E");
            wCoinConfig.setId(id);
            System.out.println(wCoinConfig);
            wCoinConfigService.updateById(wCoinConfig);
            return WrapMapper.ok("success");
        }
        return WrapMapper.error("error");
    }

    /**
     * 详情
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        WCoinConfig dto = wCoinConfigService.selectById(id);
        mmap.put("dto", dto);
        return prefix + "/edit";
    }

    /**
     * 修改冷钱包地址
     * @param wCoinConfig
     * @return
     */
    @RequestMapping("/updateAddress")
    @ResponseBody
    public Wrapper updateAddress(WCoinConfig wCoinConfig){
        WCoinConfig coinConfig =  wCoinConfigService.selectById(wCoinConfig.getId());
        if(null == coinConfig){
            return WrapMapper.error("修改冷钱包失败");
        }
        coinConfig.setBossAddress(wCoinConfig.getBossAddress());
        boolean  bo = wCoinConfigService.updateById(coinConfig);
        if(!bo){
            return WrapMapper.error("修改失败");
        }
        return WrapMapper.ok("修改成功");
    }

    /**
     * 删除冷钱包地址
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/8 19:00
     *
     * @param id 钱包id
     * @return 删除结果
     */
    @PostMapping("/removeAddress/{id}")
    @ResponseBody
    public Wrapper removeAddress(@PathVariable("id") Long id) {
        boolean result = wCoinConfigService.deleteById(id);
        return result ? WrapMapper.ok() : WrapMapper.error("删除失败");
    }
}


