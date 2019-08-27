package com.bittrade.c2c.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.pojo.model.TChatRecordLog;

/**
 * @author xzc
 * @date 2019-08-19 20:30
 * @description
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public interface ChatRecordLogDAO extends BaseMapper<TChatRecordLog> {


}
