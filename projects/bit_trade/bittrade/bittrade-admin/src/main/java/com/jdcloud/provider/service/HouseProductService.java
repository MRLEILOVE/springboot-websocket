package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.HouseProductDto;
import com.jdcloud.provider.dto.HouseProductImgDto;
import com.jdcloud.provider.pojo.HouseProduct;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-13
 */
public interface HouseProductService extends IService<HouseProduct> {

    Page<HouseProduct> getHouseProduct(Page<HouseProduct> page, HouseProductDto houseProductDto);

    Wrapper<String> saveHouseProduct(HouseProductImgDto houseProductImgDto);

    Wrapper<String> updateHouseProduct(HouseProductImgDto houseProductImgDto);

    Wrapper<String> addHouseProduct(HouseProductImgDto houseProductImgDto);

    Wrapper<String> updatePuppy(HouseProductImgDto houseProductImgDto);

    Wrapper<String> deleteHouseProduct(Integer ids);
}
