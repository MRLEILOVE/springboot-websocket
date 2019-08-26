package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.provider.mapper.ContentBannerMapper;
import com.jdcloud.provider.pojo.ContentBanner;
import com.jdcloud.provider.service.ContentBannerService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentBannerServiceImpl extends ServiceImpl<ContentBannerMapper, ContentBanner> implements ContentBannerService {

	@Autowired
	private AliyunConfiguration aliyunConfiguration;

	@Override
	public Page<ContentBanner> selectBannerList(Page<ContentBanner> page, ContentBanner banner) {
		page.setRecords( baseMapper.selectBannerList( page, banner ) );
		return page;
	}

	@Override
	public ContentBanner selectBannerById(Integer id) {
		return baseMapper.selectBannerById( id );
	}

	@Override
	public int updateBanner(ContentBanner banner) {
		// 图片处理
		if (StringUtils.isNotBlank( banner.getPictureBase64() )) {

			String path = ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
			String pictureUrl = aliyunConfiguration.upload( banner.getPictureBase64(),
					path + "banner_" + DateUtils.dateTimeNow( "yyMMddHHmmss" ) + "." + banner.getPictureSuffix() );
			banner.setPictureUrl( pictureUrl );
		}
		return baseMapper.updateBanner( banner );
	}

	@Override
	public int insertBanner(ContentBanner banner) {
		// 图片处理
		String path = ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
		String pictureUrl = aliyunConfiguration.upload( banner.getPictureBase64(),
				path + "banner_" + DateUtils.dateTimeNow( "yyMMddHHmmss" ) + "." + banner.getPictureSuffix() );
		banner.setPictureUrl( pictureUrl );
		return baseMapper.insertBanner( banner );
	}

	@Override
	public int deleteBannerByIds(String ids) {
		Integer[] bannerIds = ConvertUtil.toIntArray( ids );
		return baseMapper.deleteBannerByIds( bannerIds );
	}
}
