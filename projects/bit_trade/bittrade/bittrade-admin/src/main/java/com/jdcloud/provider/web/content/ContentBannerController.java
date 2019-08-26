package com.jdcloud.provider.web.content;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BannerDto;
import com.jdcloud.provider.pojo.Banner;
import com.jdcloud.provider.service.BannerService;
import com.jdcloud.provider.service.ContentBannerService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.date.DateUtils;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content/banner")
public class ContentBannerController extends BaseController {

	private String					prefix	= "content/banner";

	@Autowired
	private ContentBannerService	contentBannerService;

	@Autowired
	private BannerService			bannerService;

	@RequiresPermissions("content:banner:view")
	@GetMapping()
	public String user() {
		return prefix + "/banner";
	}

	/**
	 * banner列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/8 12:00
	 */
	@RequiresPermissions("content:banner:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BannerDto bannerDto) {
		this.paramsHandle( bannerDto );
		Page<BannerDto> page = bannerService.selectBannerList( bannerDto );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@RequiresPermissions("content:banner:add")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * banner保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/8 12:00
	 */
	@RequiresPermissions("content:banner:saveOrUpdate")
	@PostMapping("/saveOrUpdate")
	@ResponseBody
	public Wrapper<String> saveOrUpdate(BannerDto bannerDto) {
		return bannerService.saveOrUpdate( bannerDto, this.getUser() );
	}

	@RequiresPermissions("content:banner:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer bannerId, ModelMap mmap) {
		Banner banner = bannerService.selectById( bannerId );
		BannerDto bannerDto = new BannerDto();
		BeanUtils.copyProperties( banner, bannerDto );
		bannerDto.setStartTimeStr(bannerDto.getStartTime() == null? null: DateUtils.parseDateToStr( "yyyy-MM-dd HH:mm:ss", bannerDto.getStartTime() ) );
		bannerDto.setCloseTimeStr(bannerDto.getCloseTime() == null? null: DateUtils.parseDateToStr( "yyyy-MM-dd HH:mm:ss", bannerDto.getCloseTime() ) );
		mmap.put( "banner", bannerDto );
		return prefix + "/edit";
	}

	/**
	 * banner删除<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/8 12:00
	 */
	@RequiresPermissions("content:banner:remove")
	@PostMapping("/remove")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return toAjax( contentBannerService.deleteBannerByIds( ids ) );
	}

}
