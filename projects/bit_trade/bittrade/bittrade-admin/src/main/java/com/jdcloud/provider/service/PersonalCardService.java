package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.PersonalCardAuthDto;
import com.jdcloud.provider.dto.PersonalCardDto;
import com.jdcloud.provider.pojo.PersonalCard;
import com.jdcloud.provider.pojo.vo.PersonalCardVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户收款信息表 服务类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
public interface PersonalCardService extends IService<PersonalCard> {

    Page<PersonalCardVo>  personalCardListPageList(Page<PersonalCardVo> page,PersonalCardDto personalCardDto);

    Page<PersonalCardVo>  personalCardListPageListNew(Page<PersonalCardVo> page,PersonalCardDto personalCardDto);

    List<PersonalCardVo> personalCardListExcelList(PersonalCardDto personalCardDto);

    PersonalCard selectDetail(Long userId);

    /**
     * @Description: 实名认证审核
     * @Author: senghor
     * @Date: 2019/6/9 20:08
     */
    Wrapper updateByIdPersonalCard(PersonalCardAuthDto personalCardAuthDto);

    Wrapper updateCurrentIdentity(PersonalCardAuthDto personalCardAuthDto);

    Wrapper checkPushEarnings();

    List<PersonalCard> selectCheckPush(Integer type);

    Integer recommendEarningsSum(Integer type);

    /**
     * @Description: 刷新缓存中的预约数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    Wrapper<String> refreshRedis();

    Boolean updateMPNnum(PersonalCardAuthDto personalCardAuthDto);

    Integer updateMPNnumList(PersonalCardDto personalCardDto);
}
