package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.ContentBanner;

public interface ContentBannerService extends IService<ContentBanner> {


    Page<ContentBanner> selectBannerList(Page<ContentBanner> page, ContentBanner banner);

    ContentBanner selectBannerById(Integer id);

    int updateBanner(ContentBanner banner);

    int insertBanner(ContentBanner banner);

    int deleteBannerByIds(String ids);
}
