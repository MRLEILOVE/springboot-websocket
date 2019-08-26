package com.jdcloud.provider.web.personal;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountRecordDto;
import com.jdcloud.provider.dto.PersonalAccountDto;
import com.jdcloud.provider.pojo.PersonalAccount;
import com.jdcloud.provider.pojo.vo.PersonalAccountListVo;
import com.jdcloud.provider.pojo.vo.PersonalAccountRecordVo;
import com.jdcloud.provider.service.PersonalAccountRecordService;
import com.jdcloud.provider.service.PersonalAccountService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 我的资产表 前端控制器
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
@Controller
@RequestMapping("/personalAccount")
public class PersonalAccountController extends BaseController {

    @Autowired
    private PersonalAccountService personalAccountService;
    @Autowired
    PersonalAccountRecordService personalAccountRecordService;

    private String prefix = "personalAccount";

    @RequiresPermissions("personalAccount:view")
    @GetMapping()
    public String orderUser() {
        return prefix + "/list";
    }

    /**
     * 列表
     */
    @RequiresPermissions("personalAccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectPersonalAccountListPage(PersonalAccountDto dto) {
        Page<PersonalAccountListVo> vo = personalAccountService.selectPersonalAccountListPage(getPage(), dto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }


    /**
     * 资产流水列表
     */
//  @RequiresPermissions("accountRecord:view")
    @GetMapping("/accountRecordInfo/{id}")
    public String accountRecordInfo(@PathVariable("id") Integer id, ModelMap mmap) {
        PersonalAccount account = personalAccountService.selectById(id);
        mmap.put("vo",account);
        return prefix + "/accountList";
    }

    /**
     * 资产流水
     */
//  @RequiresPermissions("accountList:list")
    @PostMapping("/accountList")
    @ResponseBody
    public TableDataInfo accountList(AccountRecordDto accountRecordDto) {
        Page<PersonalAccountRecordVo> vo = personalAccountRecordService.getAccountList(getPage(),accountRecordDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

}

