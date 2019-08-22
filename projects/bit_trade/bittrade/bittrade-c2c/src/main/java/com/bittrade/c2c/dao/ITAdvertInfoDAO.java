package com.bittrade.c2c.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.DAO.IDefaultTAdvertInfoDAO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.AdvertUserVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface ITAdvertInfoDAO extends IDefaultTAdvertInfoDAO {

	IPage<AdvertUserVO> listAdvertsUsers(@Param("page") Page<AdvertUserVO> page, @Param("coinId") Long coinId, @Param("user_id") Long user_id);
}
