package com.jdcloud.provider.web.agnt;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AgntCountDto;
import com.jdcloud.provider.pojo.vo.AgntUserVo;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 代理商列表
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/agnt/list")
public class AgntListController extends BaseController {

	@Autowired
	private AgntUserService	agntUserService;

	private String			prefix	= "agnt/list";

	@RequiresPermissions("agnt:list:view")
	@GetMapping()
	public String user(ModelMap mmap) {
		AgntCountDto agntCountDto = agntUserService.agntStatistics();
		mmap.put( "agntCountDto", agntCountDto );
		return prefix + "/list";
	}

	/**
	 * 代理商list<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 10:43
	 */
	@RequiresPermissions("agnt:list:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AgntUserVo av) {
		Page<AgntUserVo> agntUserVoPage = agntUserService.selectAgntList( this.getPage(), av );
		return new TableDataInfo( agntUserVoPage.getRecords(), agntUserVoPage.getTotal() );
	}

	@RequiresPermissions("agnt:customer:view")
	@GetMapping("/customer/{agntId}")
	public String customerList(@PathVariable("agntId") Integer agntId, ModelMap mmap) {
		AgntCountDto agntCountDto = agntUserService.agntStatistics();
		mmap.put( "agntCountDto", agntCountDto );
		mmap.put( "agntId", agntId );
		return prefix + "/customer";
	}

	/**
	 * 代理商list导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 10:43
	 */
	@RequiresPermissions("agnt:list:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(AgntUserVo av) {
		List<AgntUserVo> agntUserVos = agntUserService.selectAgntListExport( av );
		ExcelUtil<AgntUserVo> util = new ExcelUtil<>( AgntUserVo.class );
		return util.exportExcel( agntUserVos, "AgntUser" );
	}
}