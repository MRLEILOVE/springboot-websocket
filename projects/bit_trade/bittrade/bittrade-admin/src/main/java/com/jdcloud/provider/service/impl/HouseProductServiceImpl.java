package com.jdcloud.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.base.enums.HouseEnum;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.provider.dto.HouseProductDto;
import com.jdcloud.provider.dto.HouseProductImgDto;
import com.jdcloud.provider.mapper.HouseProductMapper;
import com.jdcloud.provider.pojo.House;
import com.jdcloud.provider.pojo.HouseProduct;
import com.jdcloud.provider.service.HouseProductService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import com.jdcloud.util.zookeeper.SnowflakeIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-13
 */
@Service
public class HouseProductServiceImpl extends ServiceImpl<HouseProductMapper, HouseProduct> implements HouseProductService {

    @Value("${jdcloud.aliyun.uploadPath}")
    private String uploadPath;

    @Autowired
    private AliyunConfiguration aliyunConfiguration;

    /**
     * 商品列表
     *
     * @param page
     * @param houseProductDto
     * @return
     */
    @Override
    public Page<HouseProduct> getHouseProduct(Page<HouseProduct> page, HouseProductDto houseProductDto) {
        page.setRecords(baseMapper.getHouseProduct(page, houseProductDto));
        return page;
    }

    /**
     * 增加商品
     *
     * @param
     * @return
     */
    @Override
    public Wrapper<String> saveHouseProduct(HouseProductImgDto houseProductImgDto) {
        // 增加商品：大狗狗粮只有一种
//        EntityWrapper entity = new EntityWrapper();
//        entity.eq("status",HouseEnum.houseProductStatus.sell.getCode());
//        entity.eq("type",HouseEnum.productType.BIGDOGFOOD.getCode());
//        entity.eq("be_suitable",HouseEnum.beSuitable.BITCH.getCode());
//        entity.eq("show_position",HouseEnum.showPosition.ARTICLE.getCode());
//        Integer count = baseMapper.selectCount(entity);
//        if(count>= GlobalConstant.Number.ONE_1){
//            return WrapMapper.error("母狗狗粮已经存在！");
//        }
        HouseProduct houseproduct = new HouseProduct();
        // 图片处理
        if (StringUtils.isNotBlank(houseProductImgDto.getPictureBase64())) {
            String path = uploadPath + ConstantEnum.AliyunConstant.HOUSEPRODUCT_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload(houseProductImgDto.getPictureBase64(),
                    path + "houseProduct_" + houseProductImgDto.getPrice() + "_" + System.currentTimeMillis() + "."
                            + houseProductImgDto.getPictureSuffix());
            //  houseproduct.setImage(pictureUrl);
            houseProductImgDto.setImage(pictureUrl);
        }

        // 图片处理
        if (StringUtils.isNotBlank(houseProductImgDto.getSceneryPictureBase64())) {
            String path = uploadPath + ConstantEnum.AliyunConstant.HOUSEPRODUCT_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload(houseProductImgDto.getSceneryPictureBase64(),
                    path + "houseProduct_" + houseProductImgDto.getPrice() + "_" + System.currentTimeMillis() +""+ SnowflakeIdGenerator.instance.nextId()+"."
                            + houseProductImgDto.getSceneryPictureSuffix());
            houseProductImgDto.setSceneryImg(pictureUrl);
        }
        // 为空判断
        if (houseProductImgDto.getStockCount() == null) {
            houseProductImgDto.setStockCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        if (houseProductImgDto.getTotalCount() == null) {
            houseProductImgDto.setTotalCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        if (houseProductImgDto.getBuyLimit() == null) {
            houseProductImgDto.setBuyLimit(HouseEnum.LimitQuantity.BACKGROUND.getCode());
        }
        if (houseProductImgDto.getOverlayLimit() == null) {
            houseProductImgDto.setOverlayLimit(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        //五大属性为空判断
        if (houseProductImgDto.getPower() == null) {
            houseProductImgDto.setPower(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getAgility() == null) {
            houseProductImgDto.setAgility(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getStamina() == null) {
            houseProductImgDto.setStamina(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getIntelligence() == null) {
            houseProductImgDto.setIntelligence(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getCharm() == null) {
            houseProductImgDto.setCharm(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        BeanUtil.copyProperties(houseProductImgDto, houseproduct);
        houseproduct.setSpeedUpTime(houseProductImgDto.getAccelerationTime());
        if(HouseEnum.giveCurrencyStatus.NO.getCode().equals(houseproduct.getGiveCurrencyStatus())){
            houseproduct.setAccountConfigId(0);
            houseproduct.setAccountNum(BigDecimal.ZERO);
        }
        int row = baseMapper.insert(houseproduct);
        if (row == 0) {
            return WrapMapper.ok("新增商品失败！");
        }
        return WrapMapper.ok("新增商品成功");
    }

    /**
     * 修改商品
     *
     * @param houseProductImgDto
     * @return
     */
    @Override
    @Transactional
    public Wrapper<String> updateHouseProduct(HouseProductImgDto houseProductImgDto) {
        HouseProduct houseproduct = baseMapper.selectById(houseProductImgDto.getId());
        if (houseproduct != null) {
            // 图片处理
            if (StringUtils.isNotBlank(houseProductImgDto.getPictureBase64())) {
                String path = uploadPath + ConstantEnum.AliyunConstant.HOUSEPRODUCT_PATH.getCode();
                String pictureUrl = aliyunConfiguration.upload(houseProductImgDto.getPictureBase64(),
                        path + "houseProduct_" + houseProductImgDto.getPrice() + "_" + System.currentTimeMillis()
                                + "." + houseProductImgDto.getPictureSuffix());
                houseProductImgDto.setImage(pictureUrl);
            }
            // 图片处理
            if (StringUtils.isNotBlank(houseProductImgDto.getSceneryPictureBase64())) {
                String path = uploadPath + ConstantEnum.AliyunConstant.HOUSEPRODUCT_PATH.getCode();
                String pictureUrl = aliyunConfiguration.upload(houseProductImgDto.getSceneryPictureBase64(),
                        path + "houseProduct_" + houseProductImgDto.getPrice() + "_" + System.currentTimeMillis() +""+ SnowflakeIdGenerator.instance.nextId()+"."
                                + houseProductImgDto.getSceneryPictureSuffix());
                houseProductImgDto.setSceneryImg(pictureUrl);
            }
            // 为空判断
            if (houseProductImgDto.getStockCount() == null) {
                houseProductImgDto.setStockCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
            }
            if (houseProductImgDto.getTotalCount() == null) {
                houseProductImgDto.setTotalCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
            }
            if (houseProductImgDto.getBuyLimit() == null) {
                houseProductImgDto.setBuyLimit(HouseEnum.LimitQuantity.BACKGROUND.getCode());
            }
            if (houseProductImgDto.getOverlayLimit() == null) {
                houseProductImgDto.setOverlayLimit(HouseEnum.LimitQuantity.UNLIMITED.getCode());
            }
            //五大属性为空判断
            if (houseProductImgDto.getPower() == null) {
                houseProductImgDto.setPower(HouseEnum.LimitQuantity.DEFAULT.getCode());
            }
            if (houseProductImgDto.getAgility() == null) {
                houseProductImgDto.setAgility(HouseEnum.LimitQuantity.DEFAULT.getCode());
            }
            if (houseProductImgDto.getStamina() == null) {
                houseProductImgDto.setStamina(HouseEnum.LimitQuantity.DEFAULT.getCode());
            }
            if (houseProductImgDto.getIntelligence() == null) {
                houseProductImgDto.setIntelligence(HouseEnum.LimitQuantity.DEFAULT.getCode());
            }
            if (houseProductImgDto.getCharm() == null) {
                houseProductImgDto.setCharm(HouseEnum.LimitQuantity.DEFAULT.getCode());
            }
            BeanUtil.copyProperties(houseProductImgDto, houseproduct);
            houseproduct.setSpeedUpTime(houseProductImgDto.getAccelerationTime());
            if(HouseEnum.giveCurrencyStatus.NO.getCode().equals(houseproduct.getGiveCurrencyStatus())){
                houseproduct.setAccountConfigId(0);
                houseproduct.setAccountNum(BigDecimal.ZERO);
            }
            int row = baseMapper.updateById(houseproduct);
            if (row == 0) {
                return WrapMapper.error("修改商品失败！");
            }
        }
        return WrapMapper.ok("修改商品成功");
    }


    /**
     * 增加小狗商品
     * @param houseProductImgDto
     * @return
     */
    @Override
    public Wrapper<String> addHouseProduct(HouseProductImgDto houseProductImgDto) {
        HouseProduct houseproduct = new HouseProduct();
        // 图片处理
        if (StringUtils.isNotBlank(houseProductImgDto.getPictureBase64())) {
            String path = uploadPath + ConstantEnum.AliyunConstant.HOUSEPRODUCT_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload(houseProductImgDto.getPictureBase64(),
                    path + "houseProduct_" + houseProductImgDto.getPrice() + "_" + System.currentTimeMillis() + "."
                            + houseProductImgDto.getPictureSuffix());
            houseProductImgDto.setImage(pictureUrl);
        }
        // 为空判断
        if (houseProductImgDto.getStockCount() == null) {
            houseProductImgDto.setStockCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        if (houseProductImgDto.getTotalCount() == null) {
            houseProductImgDto.setTotalCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        if (houseProductImgDto.getBuyLimit() == null) {
            houseProductImgDto.setBuyLimit(HouseEnum.LimitQuantity.BACKGROUND.getCode());
        }
        if (houseProductImgDto.getOverlayLimit() == null) {
            houseProductImgDto.setOverlayLimit(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        //五大属性为空判断
        if (houseProductImgDto.getPower() == null) {
            houseProductImgDto.setPower(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getAgility() == null) {
            houseProductImgDto.setAgility(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getStamina() == null) {
            houseProductImgDto.setStamina(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getIntelligence() == null) {
            houseProductImgDto.setIntelligence(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getCharm() == null) {
            houseProductImgDto.setCharm(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        BeanUtil.copyProperties(houseProductImgDto, houseproduct);
        houseproduct.setSpeedUpTime(houseProductImgDto.getAccelerationTime());
        if(HouseEnum.giveCurrencyStatus.NO.getCode().equals(houseproduct.getGiveCurrencyStatus())){
            houseproduct.setAccountConfigId(0);
            houseproduct.setAccountNum(BigDecimal.ZERO);
        }
        Integer row = baseMapper.insert(houseproduct);
        if (row != 1) {
            return WrapMapper.error("增加失败");
        }
        return WrapMapper.ok("增加成功");
    }


    /**
     * 修改小狗商品
     *
     * @param houseProductImgDto
     * @return
     */
    @Override
    public Wrapper<String> updatePuppy(HouseProductImgDto houseProductImgDto) {
        HouseProduct houseproduct = baseMapper.selectById(houseProductImgDto.getId());
        houseproduct.setSpeedUpTime(houseProductImgDto.getAccelerationTime());
        // 图片处理
        if (StringUtils.isNotBlank(houseProductImgDto.getPictureBase64())) {
            String path = uploadPath + ConstantEnum.AliyunConstant.HOUSEPRODUCT_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload(houseProductImgDto.getPictureBase64(),
                    path + "houseProduct_" + houseProductImgDto.getPrice() + "_" + System.currentTimeMillis() + "."
                            + houseProductImgDto.getPictureSuffix());
            houseProductImgDto.setImage(pictureUrl);
        }
        // 为空判断
        if (houseProductImgDto.getStockCount() == null) {
            houseProductImgDto.setStockCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        if (houseProductImgDto.getTotalCount() == null) {
            houseProductImgDto.setTotalCount(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        if (houseProductImgDto.getBuyLimit() == null) {
            houseProductImgDto.setBuyLimit(HouseEnum.LimitQuantity.BACKGROUND.getCode());
        }
        if (houseProductImgDto.getOverlayLimit() == null) {
            houseProductImgDto.setOverlayLimit(HouseEnum.LimitQuantity.UNLIMITED.getCode());
        }
        //五大属性为空判断
        if (houseProductImgDto.getPower() == null) {
            houseProductImgDto.setPower(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getAgility() == null) {
            houseProductImgDto.setAgility(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getStamina() == null) {
            houseProductImgDto.setStamina(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getIntelligence() == null) {
            houseProductImgDto.setIntelligence(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        if (houseProductImgDto.getCharm() == null) {
            houseProductImgDto.setCharm(HouseEnum.LimitQuantity.DEFAULT.getCode());
        }
        BeanUtil.copyProperties(houseProductImgDto, houseproduct);
        houseproduct.setSpeedUpTime(houseProductImgDto.getAccelerationTime());
        if(HouseEnum.giveCurrencyStatus.NO.getCode().equals(houseproduct.getGiveCurrencyStatus())){
            houseproduct.setAccountConfigId(0);
            houseproduct.setAccountNum(BigDecimal.ZERO);
        }
        baseMapper.updateById(houseproduct);
        return WrapMapper.ok("修改成功");
    }


    /**
     * 删除商品
     *
     * @param ids
     * @return
     */
    @Override
    public Wrapper<String> deleteHouseProduct(Integer ids) {
        HouseProduct houseProduct = baseMapper.selectById(ids);
        if (houseProduct == null) {
            return WrapMapper.error("商品获取失败！");
        }
        if(houseProduct.getStatus().equals(HouseEnum.houseProductStatus.DELETE.getCode())){
            Integer row = baseMapper.deleteById(houseProduct.getId());
            if (row == 1) {
                return WrapMapper.ok("删除成功");
            }
        }
        return WrapMapper.error("删除的状态有误，必须先进行逻辑删除！！");
    }
}
