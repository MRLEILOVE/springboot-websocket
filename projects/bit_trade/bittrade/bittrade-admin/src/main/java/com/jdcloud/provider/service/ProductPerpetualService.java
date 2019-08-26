package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.ProductPerpetual;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author helen
 * @since 2018-11-23
 */
public interface ProductPerpetualService extends IService<ProductPerpetual> {

    /**
     * 查询永续合约产品详情
     * @param productId
     * @return
     */
    ProductPerpetual selectProductPerpetualById(Integer productId);

    /**
     * 修改永续合约产品
     * @param perpetual
     * @return
     */
    int updateProductPerpetual(ProductPerpetual perpetual);
}
