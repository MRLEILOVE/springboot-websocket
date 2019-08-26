package com.jdcloud.provider.web.currencyConfig;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.dto.AccountManageConfigDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.AccountManage;
import com.jdcloud.provider.pojo.AccountManageConfig;
import com.jdcloud.provider.pojo.TransferDirection;
import com.jdcloud.provider.pojo.vo.TransferDirectionVo;
import com.jdcloud.provider.service.AccountManageService;
import com.jdcloud.provider.service.TransferDirectionService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 划转配置表 前端控制器
 * </p>
 *
 * @author c
 * @since 2019-07-25
 */
@Controller
@RequestMapping("currencyConfig/transferDirection")
public class TransferDirectionController extends BaseController {

   @Autowired
   private TransferDirectionService transferDirectionService;

    @Autowired
    private AccountManageService accountManageService;

    private String prefix = "currencyConfig/transferDirection";

    /**
     * 初始化账户划转账户
     */
    @RequiresPermissions("transferDirection:view")
    @GetMapping()
    public String accountManageInfo() {
        return prefix + "/list";
    }

    /**
     *  账户划转账户列表  法币
     */
    @RequiresPermissions("getTransferDirection:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getTransferDirection(AccountManageConfigDto dto) {
        Page<TransferDirectionVo> vo = transferDirectionService.getTransferDirection(getPage(),dto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 禁用账户
     */
    @Log(title = "禁用账户", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("updateTransferDirection:update")
    @PostMapping("/updateTransferDirection/{id}")
    @ResponseBody
    public Wrapper<String> updateTransferDirection(@PathVariable("id") Integer id) {
        TransferDirection config = transferDirectionService.selectById(id);
        if(C2cEnum.status.NORMAL.getCode().equals(config.getStatus())){
            config.setStatus(C2cEnum.status.DISABLE.getCode());
        }else {
            config.setStatus(C2cEnum.status.NORMAL.getCode());
        }
        boolean bo =  transferDirectionService.updateById(config);
        if(!bo){
            return WrapMapper.error( "修改失败" );
        }
        return WrapMapper.ok( "修改成功" );
    }

    /**
     * 划转配置初始化
     * @return
     */
    @RequiresPermissions("accountConfig:addInfo")
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Integer id, ModelMap mmap) {
        // 查询不等于当前划转账户的
        EntityWrapper entity= new EntityWrapper();
        entity.ne("id",id);
        List<AccountManage> list = accountManageService.selectList(entity);
        mmap.put("id", id);
        mmap.put("accountConfigList", list);
        return prefix + "/add";
    }


    /**
     * 保存划转配置
     */
    @Log(title = "保存账户币种配置", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("accountConfig:addAccountConfig")
    @PostMapping("/addTransferDirection")
    @ResponseBody
    public Wrapper<String> addTransferDirection(TransferDirection transferDirection) {
        return  transferDirectionService.addTransferDirection(transferDirection);
    }


    /**
     * 初始化beta账户
     */
    @RequiresPermissions("betaTransferDirection:view")
    @GetMapping("/betaTransferDirection")
    public String betaTransferDirectionInfo() {
        return "currencyConfig/betaTransferDirection/list";
    }

    /**
     * beta划转增加
     * @return
     */
    @RequiresPermissions("accountConfig:addInfo")
    @GetMapping("/addAccountManage/{id}")
    public String addAccountManage(@PathVariable("id") Integer id, ModelMap mmap) {
        // 查询不等于当前划转账户的
        EntityWrapper entity= new EntityWrapper();
        entity.ne("id",id);
        List<AccountManage> list = accountManageService.selectList(entity);
        mmap.put("id", id);
        mmap.put("accountConfigList", list);
        return "currencyConfig/betaTransferDirection/add";
    }


    /**
     * 初始化资金账户
     */
    @RequiresPermissions("capitalTransferDirection:view")
    @GetMapping("/capitalTransferDirection")
    public String capitalTransferDirectionInfo() {
        return "currencyConfig/capitalTransferDirection/list";
    }

    /**
     * 资金账户划转增加
     * @return
     */
    @RequiresPermissions("capitalAccountConfig:addInfo")
    @GetMapping("/addCapitalAccountManage/{id}")
    public String addCapitalAccountManage(@PathVariable("id") Integer id, ModelMap mmap) {
        // 查询不等于当前划转账户的
        EntityWrapper entity= new EntityWrapper();
        entity.ne("id",id);
        List<AccountManage> list = accountManageService.selectList(entity);
        mmap.put("id", id);
        mmap.put("accountConfigList", list);
        return "currencyConfig/capitalTransferDirection/add";
    }

}

