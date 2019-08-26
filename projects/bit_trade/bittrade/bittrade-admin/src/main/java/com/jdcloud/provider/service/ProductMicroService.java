package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.ProductMicro;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author helen
 * @since 2018-11-27
 */
public interface ProductMicroService extends IService<ProductMicro> {

    /**
     * 通过ID查询微交易产品信息
     * @param productId
     * @return
     */
    ProductMicro selectProductMicroById(Integer productId);

    /**
     * 编辑微交易产品信息
     * @param micro
     * @return
     */
    int updateProductMicro(ProductMicro micro);
}
