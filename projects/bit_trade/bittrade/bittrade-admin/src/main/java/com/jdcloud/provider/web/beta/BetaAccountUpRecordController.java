package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountUpRecordDto;
import com.jdcloud.provider.dto.BetaAccountDto;
import com.jdcloud.provider.pojo.AccountUpRecord;
import com.jdcloud.provider.pojo.vo.AccountUpRecordVo;
import com.jdcloud.provider.pojo.vo.BetaAccountVo;
import com.jdcloud.provider.service.AccountUpRecordService;
import com.jdcloud.provider.service.BetaAccountRecordService;
import com.jdcloud.provider.service.BetaAccountService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 充币记录 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-05-29
 */
@Controller
@RequestMapping("/beta/accountUpRecord")
public class BetaAccountUpRecordController extends BaseController {
    @Autowired
    private AccountUpRecordService accountUpRecordService;
    private String prefix = "beta/accountUpRecord";

    @RequiresPermissions("accountUpRecord:view")
    @GetMapping()
    public String betaAccount() {
        return prefix + "/list";
    }

    /**
     * 列表
     */
    @RequiresPermissions("accountUpRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaAccountList(AccountUpRecordDto dto) {
        Page<AccountUpRecordVo> list = accountUpRecordService.selectAccountUpRecordList(getPage(), dto);
        return new TableDataInfo(list.getRecords(), list.getTotal());
    }
}

