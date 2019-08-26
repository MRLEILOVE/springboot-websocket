package com.jdcloud.provider.service;

import com.jdcloud.provider.pojo.FeminaImg;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.FeminaImgVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-29
 */
public interface FeminaImgService extends IService<FeminaImg> {

    /**
     * @Description: 新增或修改
     * @param feminaImgVo :
     * @Author: zjun
     * @Date: 2019/7/30 0:31
     */
    Wrapper<String> saveOrUpdate(FeminaImgVo feminaImgVo);
}
