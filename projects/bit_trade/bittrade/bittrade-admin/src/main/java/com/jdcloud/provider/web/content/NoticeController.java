package com.jdcloud.provider.web.content;

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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 活动管理 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/content/notice")
public class NoticeController extends BaseController {

	private String			prefix	= "content/notice";

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleClassifyService articleClassifyService;

	@RequiresPermissions("content:notice:view")
	@GetMapping()
	public String user(ModelMap mmap) {
		EntityWrapper<ArticleClassify> entity= new EntityWrapper<>();
		Map<String,Object> map = new HashMap<>();
		entity.in("type","1,5");
		mmap.put( "classify", articleClassifyService.selectList(entity));
		return prefix + "/notice";
	}

	@RequiresPermissions("content:notice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Article article) {
		article.setType(1);
		Page<Article> articles = articleService.selectArticleList( getPage(), article );
		return new TableDataInfo( articles.getRecords(), articles.getTotal() );
	}
	@GetMapping("/edit/{articleId}")
	public String edit(@PathVariable("articleId") Long articleId, ModelMap mmap) {
		mmap.put( "article", articleService.selectById(articleId));
		EntityWrapper<ArticleClassify> entity= new EntityWrapper<>();
		Map<String,Object> map = new HashMap<>();
		entity.in("type","1,5");
		mmap.put( "classify", articleClassifyService.selectList(entity));
		return prefix + "/edit";
	}

	@GetMapping("/add")
	public String add( ModelMap mmap) {
		EntityWrapper<ArticleClassify> entity= new EntityWrapper<>();
		Map<String,Object> map = new HashMap<>();
		entity.in("type","1,5");
		mmap.put( "classify", articleClassifyService.selectList(entity));
		return prefix + "/add";
	}

	@RequiresPermissions("content:notice:edit")
	@PostMapping("/edit")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> editSave(Article article) {
		ArticleClassify ify = articleClassifyService.selectById(article.getClassifyId());
		article.setType(ify.getType());
		article.setUpdateTime(new Date());
		return toAjax( articleService.updateArticleById(article) );

	}

	@RequiresPermissions("content:notice:add")
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> addSave(Article article) {
		ArticleClassify ify = articleClassifyService.selectById(article.getClassifyId());
		article.setType(ify.getType());
		article.setCreateTime(new Date());
		article.setPublishTime(new Date());
		article.setShortText(article.getTitle());
		article.setPublished(true);
		return toAjax( articleService.insertArticle(article) );
	}

	@RequiresPermissions("content:notice:published")
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

	@RequiresPermissions("content:notice:deleteById")
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
}