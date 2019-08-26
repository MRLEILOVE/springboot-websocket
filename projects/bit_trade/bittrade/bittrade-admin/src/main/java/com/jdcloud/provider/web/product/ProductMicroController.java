package com.jdcloud.provider.web.product;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ProductMicroDto;
import com.jdcloud.provider.pojo.ProductMicro;
import com.jdcloud.provider.service.ProductMicroService;
import com.jdcloud.provider.utils.BeanUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 微合约持仓表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/product/micro")
public class ProductMicroController extends BaseController {

	private String			prefix	= "product/micro";

	@Autowired
	private ProductMicroService productMicroService;

	@RequiresPermissions("product:micro:view")
	@GetMapping()
	public String user() {
		return prefix + "/micro";
	}

	@RequiresPermissions("product:micro:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProductMicro micro) {
		Page<ProductMicro> micros = productMicroService.selectPage(getPage());
		return new TableDataInfo( micros.getRecords(), micros.getTotal() );
	}

	@GetMapping("/edit/{productId}")
	public String edit(@PathVariable("productId") Integer productId, ModelMap mmap) {
		mmap.put( "micro", productMicroService.selectProductMicroById( productId ) );
		return prefix + "/edit";
	}

	@RequiresPermissions("product:micro:edit")
	@PostMapping("/edit")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> editSave(ProductMicroDto microDto) {
		ProductMicro micro = new ProductMicro();
		BeanUtils.copyBeanProp(micro,microDto);
		return toAjax( productMicroService.updateProductMicro( micro ) );
	}
}