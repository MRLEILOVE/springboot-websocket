package com.jdcloud.provider.web.product;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ProductPerpetualDto;
import com.jdcloud.provider.pojo.ProductPerpetual;
import com.jdcloud.provider.service.ProductPerpetualService;
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
@RequestMapping("/product/perpetual")
public class ProductPerpetualController extends BaseController {

	private String			prefix	= "product/perpetual";

	@Autowired
	private ProductPerpetualService productPerpetualService;

	@RequiresPermissions("product:perpetual:view")
	@GetMapping()
	public String user() {
		return prefix + "/perpetual";
	}

	@RequiresPermissions("product:perpetual:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProductPerpetual perpetual) {
		Page<ProductPerpetual> perpetuals = productPerpetualService.selectPage(getPage());
		return new TableDataInfo( perpetuals.getRecords(), perpetuals.getTotal() );
	}

	@GetMapping("/edit/{productId}")
	public String edit(@PathVariable("productId") Integer productId, ModelMap mmap) {
		mmap.put( "perpetual", productPerpetualService.selectProductPerpetualById( productId ) );
		return prefix + "/edit";
	}

	@RequiresPermissions("product:perpetual:edit")
	@PostMapping("/edit")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> editSave(ProductPerpetualDto perpetualDto) {
		ProductPerpetual perpetual = new ProductPerpetual();
		BeanUtils.copyBeanProp(perpetual,perpetualDto);
		return toAjax( productPerpetualService.updateProductPerpetual( perpetual ) );
	}

}