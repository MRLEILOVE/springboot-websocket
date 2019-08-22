package com.admin.bittrade.service.c2c;

import com.admin.bittrade.vo.PageParam;
import com.admin.bittrade.vo.c2c.AdvertInfoScreeningParameterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.model.TAdvertInfo;

/**
 * @author xzc
 * @date 2019-08-22 11:05
 * @description
 */
public interface AdvertInfoService extends IService<TAdvertInfo> {

    /**
     * 按条件分页查询
     *
     * @param pageParam
     * @param parameterVo
     * @return
     */
    IPage<TAdvertInfo> findList(PageParam pageParam, AdvertInfoScreeningParameterVo parameterVo);
}
