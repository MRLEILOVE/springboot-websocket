

package com.jdcloud.provider.web.userWallet;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.dto.WUserWalletBillDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.PersonalCard;
import com.jdcloud.provider.pojo.WUserWalletBill;
import com.jdcloud.provider.pojo.vo.WUserWalletBillVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.IWUserWalletBillService;
import com.jdcloud.provider.service.PersonalCardService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//冲币
@Controller
@RequestMapping("userWallet/recharge1")
public class Recharge1Controller extends BaseController {

    @Autowired
    private AccountConfigService accountConfigService;
    @Autowired
    private PersonalCardService personalCardService;
    @Autowired
    private IWUserWalletBillService userWalletBillService;
    private String prefix = "userWallet/recharge";


    @RequiresPermissions("newWithDrawInfo:view")
    @GetMapping()
    public String newWithDrawInfo(ModelMap mmap) {
        // 查询充币有哪些
        EntityWrapper entity = new EntityWrapper();
        entity.eq("top_up_coin", C2cEnum.topUpCoin.YES.getCode());
        entity.orderBy("sort", false);
        List<AccountConfig> list = accountConfigService.selectList(entity);
        mmap.put("list", list);
        return prefix + "/list";
    }

    /**
     * 充币记录
     *
     * @param
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getNewWithDraw(WUserWalletBillDto wUserWalletBillDto) {
        Page<WUserWalletBillVo> page = userWalletBillService.getNewWithDraw(getPage(), wUserWalletBillDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 详情
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("monitor:operlog:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        WUserWalletBill bill = userWalletBillService.selectById(id);

        EntityWrapper entity = new EntityWrapper();
        entity.eq("user_id", bill.getUserId());
        PersonalCard card = personalCardService.selectOne(entity);
//        AccountConfig config = accountConfigService.selectById(bill.getToken());
        WUserWalletBillVo vo = new WUserWalletBillVo();
        if(card !=null){
            vo.setName(card.getName());
            vo.setPhone(card.getPhone());
        }else{
            vo.setName("公司账户");
            vo.setPhone("");
        }
        vo.setToken(bill.getToken());
        BeanUtils.copyProperties(bill, vo);
        mmap.put("operLog", vo);
        return prefix + "/detail";
    }


}


