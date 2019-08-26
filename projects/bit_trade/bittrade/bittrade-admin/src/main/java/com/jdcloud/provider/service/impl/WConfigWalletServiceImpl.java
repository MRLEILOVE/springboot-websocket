package com.jdcloud.provider.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.mapper.WConfigWalletMapper;
import com.jdcloud.provider.pojo.WConfigWallet;
import com.jdcloud.provider.service.WConfigWalletService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.web.base.BaseEntity;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 配置钱包 服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-07-17
 */
@Service
public class WConfigWalletServiceImpl extends ServiceImpl<WConfigWalletMapper, WConfigWallet> implements WConfigWalletService {


	/**
	 * 保存热钱包
	 *
	 * @param wConfigWallet
	 * @return
	 */
	@Override
	public Wrapper<String> saveOrUpdate(WConfigWallet wConfigWallet) {
		return null;
	}

	@Override
	public Page<WConfigWallet> listHotAddress(Page<WConfigWallet> page, BaseEntity baseEntity) {
		String beginTime = baseEntity.getParams().get("beginTime") + "";
		String endTime = baseEntity.getParams().get("endTime") + "";
        return page.setRecords(baseMapper.selectPage(
                new RowBounds(page.getOffset(), page.getLimit()),
                new EntityWrapper<WConfigWallet>()
                        .ge(StringUtils.isNotBlank(beginTime),"create_time", beginTime)
                        .le(StringUtils.isNotBlank(endTime),"create_time", endTime)
                )
        );
    }
}
