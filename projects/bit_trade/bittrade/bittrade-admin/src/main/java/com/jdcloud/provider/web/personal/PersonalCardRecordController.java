package com.jdcloud.provider.web.personal;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.pojo.PersonalCardRecord;
import com.jdcloud.provider.pojo.vo.PersonalCardRecordVo;
import com.jdcloud.provider.service.PersonalCardRecordService;
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
 * 用户收款信息表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-14
 */
@Controller
@RequestMapping("/personal/personalCardRecord")
public class PersonalCardRecordController extends BaseController {

    private String prefix = "personal/personalCard";

    @Autowired
    private PersonalCardRecordService recordService;

    /**
     * 初始化收款列表
     * @return
     */
    @RequiresPermissions("recordListInfo:view")
    @GetMapping()
    public String recordListInfo() {
        return prefix + "/recordListPage";
    }

    /**
     * 收款记录列表_新增加列表
     * @param
     * @return
     */
    @RequiresPermissions("getrecordList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getrecordList(ActionDto actionDto) {
        Page<PersonalCardRecordVo> recordPage = recordService.getrecordList(getPage(),actionDto);
        return new TableDataInfo(recordPage.getRecords(), recordPage.getTotal());
    }
}

