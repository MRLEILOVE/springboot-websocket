package com.jdcloud.provider.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.AccountConfigDto;
import com.jdcloud.provider.mapper.AccountConfigMapper;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.StringUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-20
 */
@Service
public class AccountConfigServiceImpl extends ServiceImpl<AccountConfigMapper, AccountConfig> implements AccountConfigService {

    @Value("${jdcloud.aliyun.uploadPath}")
    private String uploadPath;
    @Autowired
    private AliyunConfiguration aliyunConfiguration;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Page<AccountConfig> selectAccountConfigPageList(Page<AccountConfig> page, AccountConfigDto accountConfigDto) {
        EntityWrapper entityWrapper = new EntityWrapper();

        if (accountConfigDto.getTopUpCoin() != null){
            entityWrapper.eq("top_up_coin", accountConfigDto.getTopUpCoin());
        }
        if (accountConfigDto.getDrawCoin() != null){
            entityWrapper.eq("draw_coin", accountConfigDto.getDrawCoin());
        }
        if (!StringUtil.isBlank(accountConfigDto.getKeyword())) {
            entityWrapper.like("name", accountConfigDto.getKeyword()).or()
                    .like("keyword", accountConfigDto.getKeyword()).or()
                    .like("value", accountConfigDto.getKeyword());
        }
        entityWrapper.orderBy("sort", true);
        return page.setRecords(baseMapper.selectPage(page, entityWrapper));
    }

    @Override
    public Wrapper<String> saveOrUpdate(AccountConfig accountConfig) {
        // 图片处理
        if (org.apache.commons.lang3.StringUtils.isNotBlank(accountConfig.getPictureBase64())) {
            String path = uploadPath + ConstantEnum.AliyunConstant.ACCOUNT_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload(accountConfig.getPictureBase64(),
                    path + "account_" + accountConfig.getKeyword() + "_" + accountConfig.getAccountType() + "." + accountConfig.getPictureSuffix());
            accountConfig.setIcon(pictureUrl);
        }
        if (accountConfig.getId() == null) {
            accountConfig.setCreateTime(new Date());
            baseMapper.insert(accountConfig);
        } else {
            accountConfig.setUpdateTime(new Date());
            baseMapper.updateById(accountConfig);
        }
        List<AccountConfig> accountConfigList =baseMapper.selectList(null);
        JSONArray jsonArray = new JSONArray();
        for (AccountConfig accountConfigVO : accountConfigList) {
            JSONObject json = new JSONObject(accountConfigVO);
            jsonArray.add(json);
            // 保存单个
            redisTemplate.set(RedisKeyUtil.ACCOUNT_CONFIG_ID + accountConfigVO.getId(), json);
            redisTemplate.set(RedisKeyUtil.ACCOUNT_CONFIG_TYPE + accountConfig.getAccountType(), json);
        }
        // 保存所有
        redisTemplate.set(RedisKeyUtil.ACCOUNT_CONFIG_ID + "all", jsonArray);
        return WrapMapper.ok("保存成功");
    }

    @Override
    public int deleteByIds(String idStr) {
        Integer[] ids = ConvertUtil.toIntArray(idStr);
        return baseMapper.deleteByIds(ids);
    }
}
