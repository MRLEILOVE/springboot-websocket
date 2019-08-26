package com.jdcloud.provider.web.content;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.Article;
import com.jdcloud.provider.pojo.ArticleClassify;
import com.jdcloud.provider.service.ArticleClassifyService;
import com.jdcloud.provider.service.ArticleService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * <p>
 * 活动管理 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/content/article")
public class ArticleController extends BaseController {

	private String			prefix	= "content/article";

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleClassifyService articleClassifyService;

	@RequiresPermissions("content:article:view")
	@GetMapping()
	public String article(ModelMap mmap) {
		ArticleClassify ify= new ArticleClassify();
		ify.setType(4);
		mmap.put( "classify", articleClassifyService.selectList(new EntityWrapper<>(ify)));
		return prefix + "/article";
	}

	@RequiresPermissions("content:article:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Article article) {
		article.setType(4);
		Page<Article> articles = articleService.selectArticleList( getPage(), article );
		return new TableDataInfo( articles.getRecords(), articles.getTotal() );
	}

	@GetMapping("/edit/{articleId}")
	public String edit(@PathVariable("articleId") Long articleId, ModelMap mmap) {
		mmap.put( "article", articleService.selectById(articleId));
		ArticleClassify ify= new ArticleClassify();
		ify.setType(4);
		mmap.put( "classify", articleClassifyService.selectList(new EntityWrapper<>(ify)));
		return prefix + "/edit";
	}

	@GetMapping("/add")
	public String add( ModelMap mmap) {
		ArticleClassify ify= new ArticleClassify();
		ify.setType(4);
		mmap.put( "classify", articleClassifyService.selectList(new EntityWrapper<>(ify)));
		return prefix + "/add";
	}

	@RequiresPermissions("content:article:edit")
	@PostMapping("/edit")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> editSave(Article article) {
		return toAjax( articleService.updateArticleById(article) );
	}

	@RequiresPermissions("content:article:add")
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> addSave(Article article) {
		article.setType(4);
		article.setCreateTime(new Date());
		return toAjax( articleService.insertArticle(article) );
	}

	@RequiresPermissions("content:article:published")
	@PostMapping("/published")
	@ResponseBody
	public Wrapper published(Long articleId) {
		try {
			Article article = articleService.selectById(articleId);
			if (article == null){
				return WrapMapper.error("文章不存在！");
			}
			if (article.getPublished()) {
				article.setPublished(false);
			} else {
				article.setPublished(true);
			}
			articleService.updateById(article);
			return WrapMapper.ok();
		} catch (Exception e) {
			return WrapMapper.error("文章操作失败！");
		}
	}

	@RequiresPermissions("content:article:deleteById")
	@PostMapping("/deleteById")
	@ResponseBody
	public Wrapper deleteById(Long articleId) {
		try {
			articleService.deleteById(articleId);
			return WrapMapper.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return WrapMapper.error("文章删除失败！");
		}
	}

	@PostMapping("/uploadImage")
	@ResponseBody
	public JSONObject uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) {
		JSONObject json =new JSONObject();
        JSONObject data =new JSONObject();
		json.put("msg","");
		try {
			String src = articleService.uploadImage(file);
			if (src == null) {
				json.put("code", 1);
			} else {
				json.put("code",0);
                data.put("src",src);
                data.put("title",src);
			}
            json.put("data",data);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg","");
			json.put("data","");
			return json;
		}
	}
}