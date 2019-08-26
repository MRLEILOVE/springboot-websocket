package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.PersonalPartnerDto;
import com.jdcloud.provider.pojo.PersonalPartner;
import com.jdcloud.provider.pojo.vo.PersonalPartnerVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-09
 */
public interface PersonalPartnerService extends IService<PersonalPartner> {

    Page<PersonalPartnerVo> personalPartnerList(Page<PersonalPartnerVo> page, PersonalPartnerDto personalPartnerDto);

    int personalPartnerDeleteByIds(String ids);
}
