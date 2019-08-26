package com.jdcloud.provider.web.portrait;


import com.google.common.collect.Lists;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.pojo.vo.PortraitDataVo;
import com.jdcloud.provider.service.UserPortraitDataService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 人群画像接口控制器
 * @Author: zjun
 * @Date: 2019/8/12 15:35
 */
@Controller
@RequestMapping("/portrait")
public class PortraitController extends BaseController {

	@Autowired
	private UserPortraitDataService userPortraitDataService;

	@RequiresPermissions("portrait:view")
	@GetMapping("/view")
	public String statisticMeet(ModelMap mmap) {
		mmap.put("dateNow", DateTimeUtils.getDays());
		return "portrait/view";
	}

	/**
	 * 获取地域分布数据
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/14 19:16
	 *
	 * @param startTime : 开始时间
	 * @param endTime   : 结束时间
	 * @param groupType : 分组类型，1：省份 2：城市
	 * @return
	 */
	@RequiresPermissions("portrait:data")
	@PostMapping("/getAreaData")
	@ResponseBody
	public Wrapper getAreaData(Long startTime, Long endTime, Integer groupType) {
		List<PortraitDataVo> list = userPortraitDataService.listAreaData(startTime, endTime, groupType);
		return WrapMapper.ok(list);
	}

	/**
	 * @Description: 年龄数据
	 * @Author: zjun
	 * @Date: 2019/8/12 15:53
	 */
	@RequiresPermissions("portrait:data")
	@PostMapping("/getAgeData")
	@ResponseBody
	public Wrapper getAgeData(Long startTime, Long endTime) {
		List<Map<Integer, Integer>> ages = Lists.newArrayList();
		ages.add(new HashMap<Integer, Integer>() {{
			put(1, 19);
		}});
		ages.add(new HashMap<Integer, Integer>() {{
			put(20, 29);
		}});
		ages.add(new HashMap<Integer, Integer>() {{
			put(30, 39);
		}});
		ages.add(new HashMap<Integer, Integer>() {{
			put(40, 49);
		}});
		ages.add(new HashMap<Integer, Integer>() {{
			put(50, 200);
		}});
		ArrayList<Integer> nums = Lists.newArrayList();
		ages.forEach(age -> age.forEach((startAge, endAge) -> {
			Integer num = userPortraitDataService.listAgeData(startTime, endTime, startAge, endAge).getNum();
			nums.add(num);
		}));
		return WrapMapper.ok(nums);
	}

	/**
	 * @Description: 性别数据
	 * @Author: zjun
	 * @Date: 2019/8/12 15:53
	 */
	@RequiresPermissions("portrait:data")
	@PostMapping("/getSexData")
	@ResponseBody
	public Wrapper getSexData(Long startTime, Long endTime) {
		List<PortraitDataVo> list = userPortraitDataService.listSexData(startTime, endTime);
		return WrapMapper.ok(list);
	}

	/**
	 * @Description: 星座数据
	 * @Author: zjun
	 * @Date: 2019/8/12 15:53
	 */
	@RequiresPermissions("portrait:data")
	@PostMapping("/getConstellationData")
	@ResponseBody
	public Wrapper getConstellationData(Long startTime, Long endTime) {
		List<PortraitDataVo> list = userPortraitDataService.listConstellationData(startTime, endTime);
		return WrapMapper.ok(list);
	}

	/**
	 * @Description: 生肖数据
	 * @Author: zjun
	 * @Date: 2019/8/12 15:53
	 */
	@RequiresPermissions("portrait:data")
	@PostMapping("/getZodiacData")
	@ResponseBody
	public Wrapper getZodiacData(Long startTime, Long endTime) {
		List<PortraitDataVo> list = userPortraitDataService.listZodiacData(startTime, endTime);
		return WrapMapper.ok(list);
	}
}

