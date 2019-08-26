package com.jdcloud.provider.web.statistic;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.pojo.BetaFreezeAccountStat;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.ComboReserveMeet;
import com.jdcloud.provider.pojo.vo.StatisticMeetVo;
import com.jdcloud.provider.service.BetaFreezeAccountStatService;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.service.ComboReserveMeetService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 统计接口控制器
 * @Author: senghor
 * @Date: 2019/7/30 18:30
 */
@Controller
@RequestMapping("statistic")
public class StatisticController extends BaseController {

    @Autowired
    private ComboGroupService comboGroupService;
    @Autowired
    private ComboReserveMeetService comboReserveMeetService;
    @Autowired
    private BetaFreezeAccountStatService betaFreezeAccountStatService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequiresPermissions("statistic:meet:view")
    @GetMapping("/meet/view")
    public String statisticMeet(ModelMap mmap) {
        mmap.put("dateNow", DateTimeUtils.getDays());
        return "statistics/meet";
    }

    /**
     * @Description: 点击人数占比和预约人数占比
     * @Author: senghor
     * @Date: 2019/7/30 18:31
     */
    @RequiresPermissions("statistic:meet:data")
    @PostMapping("/meet/data")
    @ResponseBody
    public Wrapper meet(String time) {
        JSONArray jsonArray = (JSONArray) redisTemplate.get(RedisKeyUtil.COMBO_GROUP_ID + "all");

        JSONObject jsonObject = new JSONObject();
        String[] titles = new String[jsonArray.size()];
        JSONArray reserveArr = new JSONArray();
        JSONArray adoptionArr = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            titles[i] = "套餐" + jsonArray.getJSONObject(i).get("id");
        }

        Date startDate = DateTimeUtils.getDays();
        if (StringUtils.isNotBlank(time)) {
            startDate = DateUtils.parseDate(time);
        }
        Date endDate = DateTimeUtils.getEndTime(startDate);
        List<StatisticMeetVo> reserveList = comboGroupService.selectStatisticReserve(startDate, endDate);
        JSONObject reserveJson = new JSONObject();
        if (reserveList.size() > 0) {
            for (int i = 0; i < reserveList.size(); i++) {
                reserveJson = new JSONObject();
                reserveJson.put("name", "套餐" + reserveList.get(i).getComboId());
                reserveJson.put("value", reserveList.get(i).getCountSum());
                reserveArr.add(reserveJson);
            }
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                reserveJson = new JSONObject();
                reserveJson.put("name", "套餐" + jsonArray.getJSONObject(i).get("id"));
                reserveJson.put("value", 0);
                reserveArr.add(reserveJson);
            }
        }
        List<StatisticMeetVo> adoptionList = comboGroupService.selectStatisticClick(startDate, endDate);
        JSONObject adoptionJson = new JSONObject();
        if (adoptionList.size() > 0) {
            for (int i = 0; i < adoptionList.size(); i++) {
                adoptionJson = new JSONObject();
                adoptionJson.put("name", "套餐" + adoptionList.get(i).getComboId());
                adoptionJson.put("value", adoptionList.get(i).getCountSum());
                adoptionArr.add(adoptionJson);
            }
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                adoptionJson = new JSONObject();
                adoptionJson.put("name", "套餐" + jsonArray.getJSONObject(i).get("id"));
                adoptionJson.put("value", 0);
                adoptionArr.add(adoptionJson);
            }
        }
        jsonObject.put("title", titles);
        jsonObject.put("startDate", DateUtils.formatDate(new Date()));
        if (StringUtils.isNotBlank(time)) {
            jsonObject.put("startDate", time);
        }
        jsonObject.put("adoption", adoptionArr);
        jsonObject.put("reserve", reserveArr);
        return WrapMapper.ok(jsonObject);
    }

    /**
     * @Description: 预约点击人数页面
     * @Author: senghor
     * @Date: 2019/8/1 14:50
     */
    @RequiresPermissions("statistic:reserve:view")
    @GetMapping("/reserve/view")
    public String statisticReserve(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        mmap.put("nowDate", DateUtils.formatDate(DateTimeUtils.getDays(-7)));
        for (int i = 0; i < list.size(); i++) {
            ComboGroup comboGroup = list.get(i);
            comboGroup.setEarningsRatio(BigDecimalUtil.turnDown(NumberUtil.mul(comboGroup.getEarningsRatio(), 100), 2));
            list.set(i, comboGroup);
        }
        mmap.put("list", list);
        return "statistics/reserve/reserve";
    }

    /**
     * @Description: 点击人数占比和预约人数占比折线图
     * @Author: senghor
     * @Date: 2019/7/30 18:31
     */
    @RequiresPermissions("statistic:reserve:data")
    @PostMapping("/reserve/data")
    @ResponseBody
    public Wrapper statisticReserve(String startTime, String endTime, String comboId) {
        Date nowDate;
        Date endDate = new Date();
        EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtils.isNotBlank(comboId)) {
            entityWrapper.eq("combo_id", comboId);
        }
        if (StringUtils.isNotBlank(endTime)) {
            if (StringUtils.isBlank(startTime)) {
                return WrapMapper.error("请选择要查询的开始时间");
            }
            endDate = DateUtils.offsetDay(DateUtils.parse(endTime), 1);
            entityWrapper.le("count_day", endDate);
        }
        if (StringUtils.isNotBlank(startTime)) {
            nowDate = DateUtils.parse(startTime);
            entityWrapper.ge("count_day", DateUtils.parse(startTime));
        } else {
            nowDate = DateTimeUtils.getDays(-7);
            entityWrapper.ge("count_day", nowDate);
        }
        JSONObject jsonObject = new JSONObject();

        // 展示的数据范围
        Long between;
        if (StringUtils.isBlank(endTime)) {
            between = DateUtils.between(nowDate, new Date(), DateUnit.DAY);
        } else {
            between = DateUtils.between(nowDate, endDate, DateUnit.DAY);
        }
        int showRow = between.intValue();
        String[] title = new String[showRow];
        Integer[] data1 = new Integer[showRow];
        Integer[] data2 = new Integer[showRow];
        if (StringUtils.isBlank(comboId)) {
            entityWrapper.setSqlSelect("id AS id,create_time AS createTime,update_time AS updateTime,SUM(adoption_sum) AS adoptionSum,SUM(reserve_sum) AS reserveSum,combo_id AS comboId,count_day AS countDay");
            entityWrapper.groupBy("count_day");
        }
        entityWrapper.orderBy("count_day", true);
        List<ComboReserveMeet> comboReserveMeets = comboReserveMeetService.selectList(entityWrapper);
        for (int i = 0; i < between; i++) {
            title[i] = DateUtils.format(DateUtils.offsetDay(endDate, -(between.intValue() - i)), "yyyy-MM-dd");
            data1[i] = 0;
            data2[i] = 0;
            for (int j = 0; j < comboReserveMeets.size(); j++) {
                String dateTime = DateUtils.format(comboReserveMeets.get(j).getCountDay(), "yyyy-MM-dd");
                if (title[i].equals(dateTime)) {
                    title[i] = dateTime;
                    data1[i] = comboReserveMeets.get(j).getAdoptionSum();
                    data2[i] = comboReserveMeets.get(j).getReserveSum();
                }
            }
        }

        jsonObject.put("title", title);
        jsonObject.put("data1", data1);
        jsonObject.put("data2", data2);
        jsonObject.put("nowDate", DateUtils.formatDate(nowDate));
        return WrapMapper.ok(jsonObject);
    }


    /**
     * @Description: 冻结资产统计页面
     * @Author: senghor
     * @Date: 2019/8/12 16:27
     */
    @RequiresPermissions("statistic:freeze:view")
    @GetMapping("/freeze/view")
    public String statisticFreeze(ModelMap mmap) {
        mmap.put("dateNow", DateTimeUtils.getDays());
        return "statistics/freeze/freeze";
    }

    /**
     * @Description: 冻结资产统计页面
     * @Author: senghor
     * @Date: 2019/8/12 16:27
     */
    @RequiresPermissions("statistic:freeze:data")
    @PostMapping("/freeze/data")
    @ResponseBody
    public Wrapper freeze(String time) {
        Date nowDate;
        Date endDate = new Date();
        EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtils.isNotBlank(time)) {
            nowDate = DateUtils.parse(time);
        } else {
            nowDate = DateTimeUtils.getDays(-7);
        }
        entityWrapper.ge("count_day", nowDate);
        List<BetaFreezeAccountStat> betaFreezeAccountStats = betaFreezeAccountStatService.selectList(entityWrapper);
        JSONObject jsonObject = new JSONObject();

        // 展示的数据范围
        Long between = DateUtils.between(nowDate, endDate, DateUnit.DAY);
        int showRow = between.intValue();
        String[] date = new String[showRow];
        BigDecimal[] freezeBanlenceSums = new BigDecimal[showRow];
        BigDecimal[] sellBanlenceSums = new BigDecimal[showRow];
        BigDecimal[] unfreezeSums = new BigDecimal[showRow];
        for (int i = 0; i < showRow; i++) {
            String formatDate = DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i - 1)), "MM-dd");
            date[i] = DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i)), "MM-dd");
            freezeBanlenceSums[i] = BigDecimal.ZERO;
            sellBanlenceSums[i] = BigDecimal.ZERO;
            unfreezeSums[i] = BigDecimal.ZERO;
            for (int j = 0; j < betaFreezeAccountStats.size(); j++) {
                String dateTime = DateUtils.format(betaFreezeAccountStats.get(j).getCountDay(), "MM-dd");
                if (formatDate.equals(dateTime)) {
                    freezeBanlenceSums[i] = betaFreezeAccountStats.get(j).getFreezeBanlenceSum();
                    sellBanlenceSums[i] = betaFreezeAccountStats.get(j).getSellBanlenceSum();
                    unfreezeSums[i] = betaFreezeAccountStats.get(j).getUnfreezeSum();
                }
            }
        }

        jsonObject.put("date", date);
        jsonObject.put("freezeBanlenceSums", freezeBanlenceSums);
        jsonObject.put("sellBanlenceSums", sellBanlenceSums);
        jsonObject.put("unfreezeSums", unfreezeSums);
        return WrapMapper.ok(jsonObject);
    }

}

