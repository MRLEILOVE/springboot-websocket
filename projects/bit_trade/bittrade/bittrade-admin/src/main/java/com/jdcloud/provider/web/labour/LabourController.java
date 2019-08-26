package com.jdcloud.provider.web.labour;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.LabourDto;
import com.jdcloud.provider.pojo.vo.LabourVo;
import com.jdcloud.provider.service.UserLabourAccountService;
import com.jdcloud.provider.service.UserLabourService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微合约持仓表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/labour")
public class LabourController extends BaseController {

	private String			prefix	= "labour";

	@Autowired
	private UserLabourAccountService userLabourAccountService;

	@Autowired
	private UserLabourService userLabourService;

	@RequiresPermissions("labour:view")
	@GetMapping()
	public String labour(ModelMap mmap) {
		Map<String,Object> map = new HashMap<>();
		Integer labourNum = userLabourService.selectCount(new EntityWrapper<>(null));
		BigDecimal labourMon = userLabourAccountService.selectUserLabourSum();
		map.put("labourNum",labourNum);
		map.put("labourMon",labourMon);
		mmap.put( "map", map );
		return prefix + "/labour";
	}

	@RequiresPermissions("labour:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LabourDto labourDto) {
		Page<LabourVo> labours = userLabourAccountService.selectUserLabourList( getPage(), labourDto );
		return new TableDataInfo( labours.getRecords(), labours.getTotal() );
	}
	@RequiresPermissions("labour:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(LabourDto labourDto) {
		List<LabourVo> labours = userLabourAccountService.selectUserLabourExportList(labourDto);
		ExcelUtil<LabourVo> util = new ExcelUtil<LabourVo>( LabourVo.class );
		return util.exportExcel( labours, "labours" );
	}

}