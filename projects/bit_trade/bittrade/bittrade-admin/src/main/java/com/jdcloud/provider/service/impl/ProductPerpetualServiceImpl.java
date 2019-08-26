package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.ProductPerpetualMapper;
import com.jdcloud.provider.pojo.ProductPerpetual;
import com.jdcloud.provider.service.ProductPerpetualService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-11-23
 */
@Service
public class ProductPerpetualServiceImpl extends ServiceImpl<ProductPerpetualMapper, ProductPerpetual> implements ProductPerpetualService {

    @Override
    public ProductPerpetual selectProductPerpetualById(Integer productId) {
        return baseMapper.selectProductPerpetualById( productId );
    }

    @Override
    public int updateProductPerpetual(ProductPerpetual perpetual) {
        perpetual.setServiceFee(perpetual.getServiceFee().divide(new BigDecimal("100")));
        perpetual.setCloseFee(perpetual.getCloseFee().divide(new BigDecimal("100")));
        return baseMapper.updateProductPerpetual( perpetual );
    }
}
