package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.provider.mapper.BetaHeaderMapper;
import com.jdcloud.provider.pojo.BetaHeader;
import com.jdcloud.provider.pojo.vo.BetaHeaderVo;
import com.jdcloud.provider.service.BetaHeaderService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.date.DateUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * beta狗头像表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-11
 */
@Service
public class BetaHeaderServiceImpl extends ServiceImpl<BetaHeaderMapper, BetaHeader> implements BetaHeaderService {

    @Resource
    private BetaHeaderMapper betaHeaderMapper;

    @Value("${jdcloud.aliyun.uploadPath}")
    private String				uploadPath;

    @Autowired
    private AliyunConfiguration aliyunConfiguration;

    @Override
    public Page<BetaHeader> betaHeaderPageList(Page<BetaHeader> page) {
        page.setRecords(betaHeaderMapper.betaHeaderPageList(page));
        return page;
    }

    @Override
    public int deletebetaHeaderByIds(String ids) {
        Integer[] Ids = ConvertUtil.toIntArray(ids);
        return betaHeaderMapper.deletebetaHeaderByIds(Ids);
    }

    @Override
    public Wrapper<String> saveOrUpdate(BetaHeaderVo vo) {
        BetaHeader header = new BetaHeader ();
        if(StringUtils.isNotBlank(vo.getPictureBase64())){
            String path = uploadPath + ConstantEnum.AliyunConstant.BETAHEADER_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload( vo.getPictureBase64(),
                    path + "betaHeader_" + DateUtils.dateTimeNow("yyMMddHHmmss")+ "." + vo.getPictureSuffix() );
            header.setHeadUrl(pictureUrl);
        }
        if(vo.getId()!=null){
            header.setUpdateTime(new Date());
            header.setId(vo.getId());
            betaHeaderMapper.updateById(header);
        }
        if(vo.getId()==null){
            header.setCreateTime(new Date());
            betaHeaderMapper.insert(header);
        }
        return WrapMapper.ok();
    }
}
