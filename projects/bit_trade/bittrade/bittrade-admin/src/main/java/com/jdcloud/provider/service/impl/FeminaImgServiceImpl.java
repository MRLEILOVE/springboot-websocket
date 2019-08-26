package com.jdcloud.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.pojo.FeminaImg;
import com.jdcloud.provider.mapper.FeminaImgMapper;
import com.jdcloud.provider.pojo.vo.FeminaImgVo;
import com.jdcloud.provider.service.FeminaImgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.util.date.DateUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import com.jdcloud.util.zookeeper.SnowflakeIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-29
 */
@Service
public class FeminaImgServiceImpl extends ServiceImpl<FeminaImgMapper, FeminaImg> implements FeminaImgService {


    @Value("${jdcloud.aliyun.uploadPath}")
    private String uploadPath;
    @Autowired
    private AliyunConfiguration aliyunConfiguration;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Wrapper<String> saveOrUpdate(FeminaImgVo feminaImgVo) {
        String path = uploadPath + ConstantEnum.AliyunConstant.BETAHEADER_PATH.getCode()+ "betaHeader_";
        if(StringUtils.isNotBlank(feminaImgVo.getPictureBase64StandbyImg())){
            String random= SnowflakeIdGenerator.instance.nextId().toString();
            String standbyImg = aliyunConfiguration.upload( feminaImgVo.getPictureBase64StandbyImg(),
                    path + DateUtils.dateTimeNow("yyMMddHHmmssSSS")+ "_"+random.substring(random.length()-4)+"." + feminaImgVo.getPictureSuffixStandbyImg() );
            feminaImgVo.setStandbyImg(standbyImg);
        }
        if(StringUtils.isNotBlank(feminaImgVo.getPictureBase64RunImg())){
            String random= SnowflakeIdGenerator.instance.nextId().toString();
            String runImg = aliyunConfiguration.upload( feminaImgVo.getPictureBase64RunImg(),
                    path + DateUtils.dateTimeNow("yyMMddHHmmssSSS")+ "_"+random.substring(random.length()-4)+ "." + feminaImgVo.getPictureSuffixRunImg() );
            feminaImgVo.setRunImg(runImg);
        }
        if(StringUtils.isNotBlank(feminaImgVo.getPictureBase64ExcavateImg())){
            String random= SnowflakeIdGenerator.instance.nextId().toString();
            String excavateImg = aliyunConfiguration.upload( feminaImgVo.getPictureBase64ExcavateImg(),
                    path + DateUtils.dateTimeNow("yyMMddHHmmssSSS")+ "_"+random.substring(random.length()-4)+ "." + feminaImgVo.getPictureSuffixExcavateImg() );
            feminaImgVo.setExcavateImg(excavateImg);
        }
        if(StringUtils.isNotBlank(feminaImgVo.getPictureBase64SleepImg())){
            String random= SnowflakeIdGenerator.instance.nextId().toString();
            String sleepImg = aliyunConfiguration.upload( feminaImgVo.getPictureBase64SleepImg(),
                    path + DateUtils.dateTimeNow("yyMMddHHmmssSSS")+ "_"+random.substring(random.length()-4)+ "." + feminaImgVo.getPictureSuffixSleepImg() );
            feminaImgVo.setSleepImg(sleepImg);
        }
        FeminaImg feminaImg=new FeminaImg();
        BeanUtil.copyProperties(feminaImgVo,feminaImg);
        if(feminaImg.getId()==null){
            baseMapper.insert(feminaImg);
        }else {
            baseMapper.updateById(feminaImg);
        }
        JSONObject json = new JSONObject(baseMapper.selectById(feminaImg));
        redisTemplate.set(RedisKeyUtil.BETAFEMINA_IMG, json);
        return WrapMapper.ok("保存成功");
    }
}
