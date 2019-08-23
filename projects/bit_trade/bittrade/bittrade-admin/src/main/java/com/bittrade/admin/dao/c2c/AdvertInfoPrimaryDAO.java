package com.bittrade.admin.dao.c2c;

import com.bittrade.admin.model.domain.AdvertInfoConditionDo;
import com.bittrade.admin.model.domain.AdvertInfoPageDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzc
 * @date 2019-08-22 17:01
 * @description 广告主表联查
 */
@Repository
public interface AdvertInfoPrimaryDAO {


    /**
     * @param conditionDo
     * @return
     */
    List<AdvertInfoPageDo> findPageList(AdvertInfoConditionDo conditionDo);

}
