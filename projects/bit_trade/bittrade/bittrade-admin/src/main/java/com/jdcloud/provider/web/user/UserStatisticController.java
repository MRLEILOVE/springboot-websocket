package com.jdcloud.provider.web.user;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.UserStatisticDto;
import com.jdcloud.provider.pojo.vo.UserStatisticVo;
import com.jdcloud.provider.service.UserStatisticService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 *
 * @author ourblue
 * @since 2019-05-24
 */
@Controller
@RequestMapping("user/userStatistic")
public class UserStatisticController extends BaseController {

    @Autowired
    private UserStatisticService userStatisticService;

    private String prefix = "user/userStatistic";


    @RequiresPermissions("userStatistic:view")
    @GetMapping()
    public String userStatisticInfo() {
        return prefix + "/list";
    }

    /**
     * 列表
     *
     * @param userStatisticDto
     * @return
     */
    @RequiresPermissions("userStatistic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserStatisticDto userStatisticDto) {
        Page<UserStatisticVo> users = userStatisticService.selectUserList(getPage(), userStatisticDto);
        return new TableDataInfo(users.getRecords(), users.getTotal());
    }

    /**
     * 详情
     */
    @RequiresPermissions("userStatistic:details")
    @GetMapping("/edit/{id}")
    public String details(@PathVariable("id") Integer id, ModelMap mmap) {
        UserStatisticVo vo = userStatisticService.selectAil(id);
        mmap.put("vo", vo);
        return prefix + "/edit";
    }

    @RequiresPermissions("userStatistic:list")
    @GetMapping("/lowerListInfo/{userId}")
    public String userStatisticLowerListInfo(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("userId",userId);
        return prefix + "/lowerList";
    }
    /**
     * 下级列表
     *
     * @param userStatisticDto
     * @return
     */
    @RequiresPermissions("userStatistic:list")
    @PostMapping("/lowerList")
    @ResponseBody
    public TableDataInfo lowerList(UserStatisticDto userStatisticDto) {
        Page<UserStatisticVo> users = userStatisticService.selectUserList(getPage(), userStatisticDto);
        return new TableDataInfo(users.getRecords(), users.getTotal());
    }
}

