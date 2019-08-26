package com.jdcloud.provider.web.personal;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.PersonalCardAuthDto;
import com.jdcloud.provider.dto.PersonalCardDto;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.pojo.PersonalCard;
import com.jdcloud.provider.pojo.PersonalCardRecord;
import com.jdcloud.provider.pojo.vo.PersonalCardVo;
import com.jdcloud.provider.service.PersonalCardRecordService;
import com.jdcloud.provider.service.PersonalCardService;
import com.jdcloud.provider.utils.ExcelUtil;
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

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户收款信息表 前端控制器
 * </p>
 *
 * @author helen
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/personal/personalCard")
public class PersonalCardController extends BaseController {

    private String prefix = "personal/personalCard";

    @Autowired
    private PersonalCardService personalCardService;
    @Autowired
    private PersonalCardRecordService recordService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequiresPermissions("personal:personalCard:view")
    @GetMapping()
    public String personalCard() {
        return prefix + "/personalCard";
    }

    /**
     * 查询所有待审核
     *
     * @return
     */
    @GetMapping("toBeAuditedInfo")
    public String toBeAuditeList() {
        return prefix + "/toBeAuditeList";
    }

    /**
     * 查询身份审核列表
     * 2019-7-15 已处理实名
     * C
     */
    @RequiresPermissions("personal:personalCard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PersonalCardDto personalCardDto) {
        personalCardDto.setAuth(BetaEnum.authStauts.toBeAudited.getCode());
        Page<PersonalCardVo> orderUserListPage = personalCardService.personalCardListPageListNew(getPage(), personalCardDto);
        return new TableDataInfo(orderUserListPage.getRecords(), orderUserListPage.getTotal());
    }

    /**
     * 收付款历史 初始化
     * C
     * 2019-7-14
     */
    @RequiresPermissions("personalCardRecord:view")
    @GetMapping("/recordInfo/{userId}")
    public String recordInfo(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("userId", userId);
        return prefix + "/recordList";
    }

    /**
     * 收款历史列表（用户id）
     * C
     * 2019-7-14
     */
    @RequiresPermissions("personalCardRecord:list")
    @PostMapping("/recordList")
    @ResponseBody
    public TableDataInfo recordList(Long userId) {
        EntityWrapper entity = new EntityWrapper();
        entity.eq("user_id", userId);
        Page<PersonalCardRecord> recordPage = recordService.selectPage(getPage(), entity);
        return new TableDataInfo(recordPage.getRecords(), recordPage.getTotal());
    }


    /**
     * 查询待审核的列表
     */
    @PostMapping("/authStautsList")
    @ResponseBody
    public TableDataInfo authStautsList(PersonalCardDto personalCardDto) {
        if (personalCardDto.getAuthStauts() == null) {
            personalCardDto.setAuthStauts(BetaEnum.authStauts.toBeAudited.getCode());
        }
        Page<PersonalCardVo> orderUserListPage = personalCardService.personalCardListPageList(getPage(), personalCardDto);
        return new TableDataInfo(orderUserListPage.getRecords(), orderUserListPage.getTotal());
    }

    /**
     * 导出EXCL文档
     */
    @Log(title = "身份认证导出", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("personal:personalCard:export")
    @PostMapping("/export")
    @ResponseBody
    public Wrapper<String> export(PersonalCardDto personalCardDto) throws IOException {
        List<PersonalCardVo> list = personalCardService.personalCardListExcelList(personalCardDto);
        ExcelUtil<PersonalCardVo> util = new ExcelUtil<PersonalCardVo>(PersonalCardVo.class);
        return util.exportExcel(list, "personalCardExcelList");
    }


    /**
     * 身份认证详情
     *
     * @param userId
     * @param mmap
     * @return
     */
    @RequiresPermissions("personal:personalCard:detail")
    @GetMapping("/detail/{userId}")
    public String detail(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("personalCard", personalCardService.selectDetail(userId));
        return prefix + "/edit";
    }

    /**
     * 认证审核
     */
    @Log(title = "认证审核", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("personal:personalCard:audit")
    @PostMapping("/audit")
    @ResponseBody
    public Wrapper audit(PersonalCardAuthDto personalCardAuthDto) {
        return personalCardService.updateByIdPersonalCard(personalCardAuthDto);
    }

    /**
     * 升级用户身份
     */
    @Log(title = "升级用户", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("personal:personalCard:updateCurrentIdentity")
    @RequestMapping("/updateCurrentIdentity")
    @ResponseBody
    public Wrapper updateCurrentIdentity(PersonalCardAuthDto personalCardAuthDto) {
        Wrapper bo = personalCardService.updateCurrentIdentity(personalCardAuthDto);
        return bo;
    }

    /**
     * @Description: 修改用户概率
     * @Author: senghor
     * @Date: 2019/8/13 16:45
     */
    @Log(title = "修改用户概率", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("personal:personalCard:updateMPNnum")
    @RequestMapping("/updateMPNnum")
    @ResponseBody
    public Wrapper updateMPNnum(PersonalCardAuthDto personalCardAuthDto) {
        Boolean updateMPN = personalCardService.updateMPNnum(personalCardAuthDto);
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("user_id", personalCardAuthDto.getUserId());
        PersonalCard personalCard = personalCardService.selectOne(entityWrapper);
        redisTemplate.set(RedisKeyUtil.PERSONAL_CARD_USERID + personalCard.getUserId(), new JSONObject(personalCard));
        return WrapMapper.ok(updateMPN);
    }

    /**
     * 校正推广收益
     *
     * @return
     */
    @RequiresPermissions("personal:personalCard:checkPushEarnings")
    @PostMapping("/checkPushEarnings")
    @ResponseBody
    public Wrapper checkPushEarnings() {
        return personalCardService.checkPushEarnings();
    }

    // 查询推荐好友校正的人数
    @RequiresPermissions("personal:personalCard:checkNumber")
    @GetMapping("/check")
    public String check(ModelMap mmap) {
        mmap.put("checkSum", personalCardService.recommendEarningsSum(BetaEnum.authStauts.success.getCode()));
        return prefix + "/check";
    }

    /**
     * @Description: 刷新缓存中的实名认证数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @RequiresPermissions("personal:personalCard:refreshRedis")
    @PostMapping("/refreshRedis")
    @ResponseBody
    public Wrapper<String> refreshRedis() {
        return personalCardService.refreshRedis();
    }


    /**
     * 修改老用户身份标识
     * c
     * 2019-8-8
     */
    @Log(title = "修改老用户身份标识", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("personal:updateOldFlag")
    @RequestMapping("/updateOldFlag/{id}")
    @ResponseBody
    public Wrapper updateOldFlag(@PathVariable("id") Integer id) {
        PersonalCard card = personalCardService.selectById(id);
        if (null == card) {
            return WrapMapper.error("用户不存在！");
        }
        if (card.getOldFlag().equals(BetaEnum.getOldFlags.NEW_USER.getCode())) {
            card.setOldFlag(BetaEnum.getOldFlags.AGED_USER.getCode());
        } else {
            card.setOldFlag(BetaEnum.getOldFlags.NEW_USER.getCode());
        }
        boolean bo = personalCardService.updateById(card);
        if (!bo) {
            return WrapMapper.error("修改失败！");
        }
        redisTemplate.set(RedisKeyUtil.PERSONAL_CARD_USERID + card.getUserId(), new JSONObject(card));
        return WrapMapper.ok("修改成功");
    }

    /**
     * @Description: 批量修改用户概率
     * @Author: senghor
     * @Date: 2019/8/13 19:36
     */
    @RequiresPermissions("personal:personalCard:updateMPNnumList")
    @PostMapping("/updateMPNnumList")
    @ResponseBody
    public Wrapper updateMPNnumList(PersonalCardDto personalCardDto) {
        personalCardDto.setAuth(BetaEnum.authStauts.toBeAudited.getCode());
        return WrapMapper.ok(personalCardService.updateMPNnumList(personalCardDto));
    }

}

