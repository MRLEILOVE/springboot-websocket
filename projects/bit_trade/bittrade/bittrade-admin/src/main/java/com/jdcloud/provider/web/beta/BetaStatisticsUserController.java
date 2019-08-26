package com.jdcloud.provider.web.beta;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.dto.BetaOrderDto;
import com.jdcloud.provider.dto.BetaStatisticsUserDto;
import com.jdcloud.provider.dto.OrderEntrustPageDto;
import com.jdcloud.provider.pojo.BetaStatisticsUser;
import com.jdcloud.provider.pojo.vo.OrderEntrustVo;
import com.jdcloud.provider.pojo.vo.RegisterEChartsVo;
import com.jdcloud.provider.service.BetaStatisticsUserService;
import com.jdcloud.provider.service.HouseService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.provider.web.base.BaseEntity;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 统计用户注册人数表 前端控制器
 * </p>
 *
 * @author
 * @since 2019-07-06
 */
@Controller
@RequestMapping("beta/betaStatisticsUser")
public class BetaStatisticsUserController extends BaseController {

    @Autowired
    private BetaStatisticsUserService betaStatisticsUserService;
    @Autowired
    private HouseService houseService;

    /**
     * 统计注册人数初始化
     *
     * @param mmap
     * @return
     */
    @RequiresPermissions("echartsInfo:view")
    @GetMapping("/echarts")
    public String echartsInfo(ModelMap mmap) {
        mmap.put("nowDate", DateUtils.formatDate(new Date()));
        return "echarts";
    }


    /**
     * 统计今日抢狗数
     *
     * @param mmap
     * @return
     */
    @RequiresPermissions("countDayDogInfO:view")
    @GetMapping("/countDayDogInfO")
    public String countDayDogInfO(ModelMap mmap) {
        mmap.put("nowDate", DateUtils.formatDate(DateUtils.offsetDay(new Date(), -7)));
        mmap.put("maxDate", DateUtils.formatDate(new Date()));
        return "countDayDog";
    }


    /**
     * @Description: 统计注册人数
     * @Author: C
     * @Date: 2019/5/27
     */
    @RequestMapping("/getRegisterEChartsNew")
    @ResponseBody
    public Wrapper getRegisterEChartsNew(String time) {
        JSONObject jsonObject = new JSONObject();
        EntityWrapper entity = new EntityWrapper();
        Date nowDate;
        if (StringUtils.isNotBlank(time)) {
            nowDate = DateUtils.parse(time);
            time = DateUtils.formatDate(nowDate);
            entity.ge("create_date", time);
        } else {
            nowDate = DateTimeUtils.getDays(-7);
            // 默认查询七天
            entity.ge("create_date", nowDate);
        }
        entity.eq("statistic_type", BetaEnum.statisticType.REGISTEREDCODEBYDAY.getCode());
        entity.orderBy("file_created_date", true);
        List<BetaStatisticsUser> list = betaStatisticsUserService.selectList(entity);

        Long between = DateUtils.between(nowDate, new Date(), DateUnit.DAY);
        int showRow = between.intValue();
        String[] date = new String[showRow];
        Integer[] registerSum = new Integer[showRow];
        Integer[] authSum = new Integer[showRow];
        for (int i = 0; i < showRow; i++) {
            String formatDate= DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i-1)), "yyyy年MM月dd日");
            date[i]=DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i)), "yyyy年MM月dd日");
            registerSum[i] = 0;
            authSum[i] = 0;
            for (int j = 0; j < list.size(); j++) {
                String dateTime = DateUtils.format(list.get(j).getFileCreatedDate(), "yyyy年MM月dd日");
                if (formatDate.equals(dateTime)) {
                    registerSum[i] = list.get(j).getRegistering();
                    authSum[i] = list.get(j).getRealnameNumber();
                }
            }
        }
        jsonObject.put("date", date);
        jsonObject.put("registerSum", registerSum);
        jsonObject.put("authSum", authSum);
        return WrapMapper.ok(jsonObject);
    }


    /**
     * @Description: 统计今日抢狗人数
     * @Author: C
     * @Date: 2019/5/27
     */
    @RequestMapping("/getcountDayDog")
    @ResponseBody
    public Wrapper getcountDayDog(String time) {
        JSONObject jsonObject = new JSONObject();
        EntityWrapper entity = new EntityWrapper();
        Date nowDate;
        if (StringUtils.isNotBlank(time)) {
            nowDate = DateUtils.parse(time);
            time = DateUtils.formatDate(nowDate);
            entity.ge("create_date", time);
        } else {
            nowDate = DateTimeUtils.getDays(-7);
            // 默认查询七天
            entity.ge("create_date", nowDate);
        }
        entity.eq("statistic_type", BetaEnum.statisticType.GRABTHEDOG.getCode());
        entity.orderBy("file_created_date", true);
        List<BetaStatisticsUser> list = betaStatisticsUserService.selectList(entity);

        Long between = DateUtils.between(nowDate, new Date(), DateUnit.DAY);
        int showRow = between.intValue();
        String[] date = new String[showRow];
        Integer[] registerSum = new Integer[showRow];
        Integer[] authSum = new Integer[showRow];
        Integer[] buyPeiceSum = new Integer[showRow];
        Integer[] dayFirstBuy = new Integer[showRow];

        for (int i = 0; i < between; i++) {
            date[i] = DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i)), "yyyy年MM月dd日");
            registerSum[i] = 0;
            authSum[i] = 0;
            buyPeiceSum[i] = 0;
            dayFirstBuy[i] = 0;
            for (int j = 0; j < list.size(); j++) {
                String dateTime = DateUtils.format(list.get(j).getFileCreatedDate(), "yyyy年MM月dd日");
                if (date[i].equals(dateTime)) {
                    date[i] = dateTime;
                    registerSum[i] = list.get(j).getBuyUserSum();
                    authSum[i] = list.get(j).getSellUserSum();
                    buyPeiceSum[i] = list.get(j).getBuyPeiceSum().intValue();
                    dayFirstBuy[i] = list.get(j).getDayFirstBuy();
                }
            }
        }
        jsonObject.put("date", date);
        jsonObject.put("registerSum", registerSum);
        jsonObject.put("authSum", authSum);
        jsonObject.put("buyPeiceSum", buyPeiceSum);
        jsonObject.put("dayFirstBuy", dayFirstBuy);
        return WrapMapper.ok(jsonObject);
    }


    private String prefix = "statistics";

    /**
     * 初始化今日注册实名人数列表
     */
    @RequiresPermissions("registeredInfo:view")
    @GetMapping("/registeredInfo")
    public String registeredInfo(){
        return prefix+"/registeredList";
    }

    /**
     * 列表
     * @param
     * @return
     */
    @RequiresPermissions("getRegistereList:list")
    @RequestMapping("/registeredList")
    @ResponseBody
    public TableDataInfo getRegistereList(BetaStatisticsUserDto dto) {
        Page<BetaStatisticsUser> vo =betaStatisticsUserService.getRegistereList(getPage(),dto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 导出注册实名统计
     */
    @Log(title = "实名注册统计", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("registered:export")
    @RequestMapping("/export")
    @ResponseBody
    public Wrapper export(RegisterEChartsVo vo) {
        List<BetaStatisticsUser> betaStatisticsUser =betaStatisticsUserService.registeredcodeList(vo);
        ExcelUtil<BetaStatisticsUser> util = new ExcelUtil<BetaStatisticsUser>(BetaStatisticsUser.class);
        return util.exportExcel(betaStatisticsUser, "实名注册统计");
    }


    /**
     * 初始化抢狗数
     */
    @RequiresPermissions("grabTheDogListInfo:view")
    @GetMapping("/grabTheDogListInfo")
    public String grabTheDogInfo(){
        return prefix+"/grabTheDogList";
    }


    /**
     * 初始化持仓统计初始化
     */
    @RequiresPermissions("positionCountInfo:view")
    @GetMapping("/positionCountInfo")
    public String positionCountInfo(){
        return prefix+"/positionCount";
    }

    /**
     * 统计狗窝人数初始化
     *
     * @param mmap
     * @return
     */
    @RequiresPermissions("houseCountInfo:view")
    @GetMapping("/houseCountInfo")
    public String houseCountInfo(ModelMap mmap) {
        mmap.put("nowDate", DateUtils.formatDate(new Date()));
        mmap.put("sumHouseNum",houseService.selectCount(null));
        return prefix+"/houseCountInfo";
    }

    /**
     * @Description: 统计狗窝人数
     * @param time :
     * @Author: zjun
     * @Date: 2019/8/7 11:55
     */
    @RequestMapping("/houseCount")
    @ResponseBody
    public Wrapper houseCount(String time) {
        JSONObject jsonObject = new JSONObject();
        EntityWrapper entity = new EntityWrapper();
        Date nowDate;
        if (StringUtils.isNotBlank(time)) {
            nowDate = DateUtils.parse(time);
            time = DateUtils.formatDate(nowDate);
            entity.ge("create_date", time);
        } else {
            nowDate = DateTimeUtils.getDays(-7);
            // 默认查询七天
            entity.ge("create_date", nowDate);
        }
        entity.eq("statistic_type", BetaEnum.statisticType.HOUSE.getCode());
        entity.orderBy("file_created_date", true);
        List<BetaStatisticsUser> list = betaStatisticsUserService.selectList(entity);

        Long between = DateUtils.between(nowDate, new Date(), DateUnit.DAY);
        int showRow = between.intValue();
        String[] date = new String[showRow];
        Integer[] houseNum = new Integer[showRow];
        for (int i = 0; i < showRow; i++) {
            String formatDate= DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i-1)), "yyyy年MM月dd日");
            date[i]=DateUtils.format(DateUtils.offsetDay(new Date(), -(between.intValue() - i)), "yyyy年MM月dd日");
            houseNum[i] = 0;
            for (int j = 0; j < list.size(); j++) {
                String dateTime = DateUtils.format(list.get(j).getFileCreatedDate(), "yyyy年MM月dd日");
                if (formatDate.equals(dateTime)) {
                    houseNum[i] = list.get(j).getHouseNum();
                }
            }
        }
        jsonObject.put("date", date);
        jsonObject.put("houseNum", houseNum);
        return WrapMapper.ok(jsonObject);
    }
}

