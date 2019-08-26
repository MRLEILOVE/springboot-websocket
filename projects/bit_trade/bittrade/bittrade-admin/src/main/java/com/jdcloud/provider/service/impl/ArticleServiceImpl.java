package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.config.properties.JdcloudProperties;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.provider.mapper.ArticleMapper;
import com.jdcloud.provider.pojo.Article;
import com.jdcloud.provider.service.ArticleService;
import com.jdcloud.util.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-12-04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

	@Autowired
	private AliyunConfiguration	aliyunConfiguration;

	@Autowired
	private JdcloudProperties	jdcloudProperties;

	@Value("${jdcloud.aliyun.uploadPath}")
	private String				uploadPath;

	@Override
	public Page<Article> selectArticleList(Page<Article> page, Article article) {
		Map<String,Object> map = article.getParams();
		map.put("selectTime",new Date());
		article.setParams(map);
		page.setRecords( baseMapper.selectArticleList( page, article ) );
		return page;
	}

	@Override
	public Article selectArticleById(Long articleId) {
		return baseMapper.selectArticleById( articleId );
	}

	@Override
	public int updateArticleById(Article article) {
		// 图片处理
		if (StringUtils.isNotBlank( article.getPictureBase64() )) {
			String path = uploadPath + ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
			String pictureUrl = aliyunConfiguration.upload( article.getPictureBase64(),
					path + "article_banner_" + DateUtils.dateTimeNow( "yyMMddHHmmss" ) + "." + article.getPictureSuffix() );
			article.setImageUrl( pictureUrl );
		}
		return baseMapper.updateById( article );
	}

	@Override
	public int insertArticle(Article article) {
		// 图片处理
		if (StringUtils.isNotBlank( article.getPictureBase64() )) {
			String path = uploadPath + ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
			String pictureUrl = aliyunConfiguration.upload( article.getPictureBase64(),
					path + "article_banner_" + DateUtils.dateTimeNow( "yyMMddHHmmss" ) + "." + article.getPictureSuffix() );
			article.setImageUrl( pictureUrl );
		}
		return baseMapper.insert( article );
	}

	@Override
	public String uploadImage(MultipartFile mfile) throws IOException {
		String suffix = mfile.getOriginalFilename();
		InputStream file =mfile.getInputStream();
		// 图片处理
		if (file !=null) {
			String path = uploadPath + ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
			String pictureUrl = aliyunConfiguration.uploadFileInputStream( file, path + "article_" + DateUtils.dateTimeNow( "yyMMddHHmmss" ) + suffix );
			return pictureUrl;
		}
		return null;
	}
}
