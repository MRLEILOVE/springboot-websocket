package com.jdcloud.provider.web.product;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.Config;
import com.jdcloud.provider.pojo.AcceptDealer;
import com.jdcloud.provider.service.AcceptDealerService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 代理商管理
 * <p>
 *
 * @author yongheng
 * @since 2018/12/1
 */
@Controller
@RequestMapping(value = "/product/acceptDealer")
public class AcceptDealerController extends BaseController {

	private String				prefix	= "product/acceptDealer";

	@Autowired
	private AcceptDealerService	acceptDealerService;

	@RequiresPermissions("product:acceptDealer:view")
	@GetMapping()
	public String acceptDealer() {
		return prefix + "/list";
	}

	/**
	 * 参数设置列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:01
	 */
	@RequiresPermissions("product:acceptDealer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Config config) {
		Page<AcceptDealer> page = acceptDealerService.selectAcceptDealerList( getPage(), config );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@RequiresPermissions("product:acceptDealer:add")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("product:acceptDealer:saveOrUpdate")
	@PostMapping(value = "/saveOrUpdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> saveOrUpdate(AcceptDealer acceptDealer) {
		return acceptDealerService.saveOrUpdate( acceptDealer, this.getUser() );
	}

	@RequiresPermissions("product:acceptDealer:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AcceptDealer acceptDealer = acceptDealerService.selectById( id );
		mmap.put( "acceptDealer", acceptDealer );
		return prefix + "/edit";
	}

	/**
	 * 删除操作<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 18:07
	 */
	@RequiresPermissions("product:acceptDealer:del")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return acceptDealerService.deleteByIds( ids );
	}
}