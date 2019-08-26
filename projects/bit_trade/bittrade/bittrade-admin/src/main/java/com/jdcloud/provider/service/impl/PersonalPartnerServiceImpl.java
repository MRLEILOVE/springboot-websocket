package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.PersonalPartnerDto;
import com.jdcloud.provider.mapper.PersonalPartnerMapper;
import com.jdcloud.provider.pojo.PersonalPartner;
import com.jdcloud.provider.pojo.vo.PersonalPartnerVo;
import com.jdcloud.provider.service.PersonalPartnerService;
import com.jdcloud.util.Convert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-09
 */
@Service
public class PersonalPartnerServiceImpl extends ServiceImpl<PersonalPartnerMapper, PersonalPartner> implements PersonalPartnerService {

    @Resource
    private PersonalPartnerMapper personalPartnerMapper;

    /**
     * 查询列表
     * @param page
     * @param personalPartnerDto
     * @return
     */
    @Override
    public Page<PersonalPartnerVo> personalPartnerList(Page<PersonalPartnerVo> page, PersonalPartnerDto personalPartnerDto) {
        page.setRecords( personalPartnerMapper.personalPartnerList(page, personalPartnerDto ) );
        return page;
    }

    /**
     * 删除列表
     * @return
     */
    @Override
    public int personalPartnerDeleteByIds(String ids) {
        Integer[] Ids = ConvertUtil.toIntArray(ids);
        return baseMapper.personalPartnerDeleteByIds(Ids );
    }


}



