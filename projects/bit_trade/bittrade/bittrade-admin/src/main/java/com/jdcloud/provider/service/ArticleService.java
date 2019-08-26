package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.Article;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author helen
 * @since 2018-12-04
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询活动中心列表
     * @param page
     * @param article
     * @return
     */
    Page<Article> selectArticleList(Page<Article> page, Article article);

    /**
     * 查询文章
     * @param articleId
     * @return
     */
    Article selectArticleById(Long articleId);

    /**
     * 修改文章
     * @param article
     * @return
     */
    int updateArticleById(Article article);

    /**
     * 新增文章
     * @param article
     * @return
     */
    int insertArticle(Article article);

    /**
     * 上传图片
     * @return
     */
    String uploadImage(MultipartFile file) throws IOException;

}
