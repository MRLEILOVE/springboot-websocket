package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.config.properties.JdcloudProperties;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.provider.dto.BannerDto;
import com.jdcloud.provider.mapper.BannerMapper;
import com.jdcloud.provider.pojo.Banner;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.BannerService;
import com.jdcloud.util.date.DateUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yongheng
 * @since 2018-12-07
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

	@Autowired
	private AliyunConfiguration	aliyunConfiguration;

	@Autowired
	private JdcloudProperties	jdcloudProperties;

	@Value("${jdcloud.aliyun.uploadPath}")
	private String				uploadPath;

	/**
	 * banner列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/7 18:53
	 */
	@Override
	public Page<BannerDto> selectBannerList(BannerDto bannerDto) {
		List<BannerDto> list = baseMapper.selectBannerList( bannerDto );
		Integer count = baseMapper.selectBannerCount( bannerDto );
		Page<BannerDto> page = new Page<>();
		page.setRecords( list );
		page.setSize( bannerDto.getSize() );
		page.setCurrent( bannerDto.getCurrent() );
		page.setTotal( count );
		return page;
	}

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/7 20:17
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> saveOrUpdate(BannerDto bannerDto, SysUser user) {

		Banner banner = new Banner();
		if (bannerDto.getId() != null) {
			banner = baseMapper.selectById( bannerDto.getId() );
		}

		banner.setTitle( bannerDto.getTitle() );
		banner.setAppJumpUrl( bannerDto.getAppJumpUrl() );
		banner.setJumpUrl( bannerDto.getJumpUrl() );
		banner.setSort( bannerDto.getSort() );
		banner.setStartTime( bannerDto.getStartTime() );
		banner.setCloseTime( bannerDto.getCloseTime() );
		banner.setPictureUrl( bannerDto.getPictureUrl() );
		banner.setStatus( bannerDto.getStatus() == null ? ConstantEnum.BannerState.NORMAL_DOC.getCode() : bannerDto.getStatus() );
		banner.setUpdater( user.getUserName() );
		banner.setUpdateTime( new Date() );

		// 图片处理
		if (StringUtils.isNotBlank( bannerDto.getPictureBase64() )) {
			String path = uploadPath + ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
			String pictureUrl = aliyunConfiguration.upload( bannerDto.getPictureBase64(),
					path + "banner_" + DateUtils.dateTimeNow( "yyMMddHHmmss" ) + "." + bannerDto.getPictureSuffix() );
			banner.setPictureUrl( pictureUrl );
		}

		if (bannerDto.getId() == null) {
			// 保存
			banner.setCreater( user.getUserName() );
			banner.setCreateTime( new Date() );
			baseMapper.insert( banner );
		} else {
			// 更新
			if ((GlobalConstant.Number.ONE_1 + "").equals( bannerDto.getType() )) {
				baseMapper.updateById( banner );
			} else {
				baseMapper.updateAllColumnById( banner );
			}
		}
		return WrapMapper.ok();
	}
}
