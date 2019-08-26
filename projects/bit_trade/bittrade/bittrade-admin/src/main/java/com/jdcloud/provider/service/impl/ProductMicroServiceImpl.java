package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.ProductMicroMapper;
import com.jdcloud.provider.pojo.ProductMicro;
import com.jdcloud.provider.service.ProductMicroService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-11-27
 */
@Service
public class ProductMicroServiceImpl extends ServiceImpl<ProductMicroMapper, ProductMicro> implements ProductMicroService {

    @Override
    public ProductMicro selectProductMicroById(Integer productId) {
        return baseMapper.selectProductMicroById( productId );
    }

    @Override
    public int updateProductMicro(ProductMicro micro) {
        micro.setServiceFee(micro.getServiceFee().divide(new BigDecimal("100")));
        micro.setProfitPercent(micro.getProfitPercent().divide(new BigDecimal("100")));
        return baseMapper.updateProductMicro(micro);
    }
}
