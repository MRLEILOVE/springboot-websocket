package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.RecordSmsLogDto;
import com.jdcloud.provider.pojo.vo.RecordSmsLogVo;
import com.jdcloud.provider.service.RecordSmsLogService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 记录抢狗短信 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-05-16
 */
@Controller
@RequestMapping("/recordSmsLog")
public class RecordSmsLogController extends BaseController {

    @Autowired
    private RecordSmsLogService recordSmsLogService;

    private String prefix = "analyze/recordSmsLog";

    @RequiresPermissions("recordSmsLog:view")
    @GetMapping()
    public String betaOrderInfo(ModelMap mmap) {
        return prefix + "/list";
    }

    /**
     * 领养短信分析列表
     */
    @RequiresPermissions("recordSmsLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo recordSmsLogListPage(RecordSmsLogDto recordSmsLogDto) {
        Page<RecordSmsLogVo> vo = recordSmsLogService.selectRecordSmsLogPageList(getPage(), recordSmsLogDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }


    /**
     * 非领养短信初始化
     */
    @RequiresPermissions("recordSmsLog:view")
    @GetMapping("/noAdoptSMSList")
    public String NoAdoptSMSInfo(ModelMap mmap) {
        return prefix + "/noAdoptSMSList";
    }

    /**
     * 非领养短信列表
     */
    @RequiresPermissions("noAdoptSMSList:list")
    @PostMapping("/noAdoptSMSList")
    @ResponseBody
    public TableDataInfo noAdoptSMSList(RecordSmsLogDto recordSmsLogDto) {
        Page<RecordSmsLogVo> vo = recordSmsLogService.selectNoAdoptSMSList(getPage(), recordSmsLogDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }


}

