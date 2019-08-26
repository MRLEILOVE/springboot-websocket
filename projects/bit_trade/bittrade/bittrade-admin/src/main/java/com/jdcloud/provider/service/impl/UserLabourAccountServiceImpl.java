package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.provider.dto.LabourDto;
import com.jdcloud.provider.mapper.UserLabourAccountMapper;
import com.jdcloud.provider.pojo.BetaAccount;
import com.jdcloud.provider.pojo.UserLabourAccount;
import com.jdcloud.provider.pojo.vo.LabourUserVo;
import com.jdcloud.provider.pojo.vo.LabourVo;
import com.jdcloud.provider.service.BetaAccountService;
import com.jdcloud.provider.service.UserLabourAccountService;
import com.jdcloud.provider.service.UserLabourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-01-07
 */
@Service
public class UserLabourAccountServiceImpl extends ServiceImpl<UserLabourAccountMapper, UserLabourAccount> implements UserLabourAccountService {

    @Autowired
    private UserLabourService userLabourService;

    @Autowired
    private BetaAccountService betaAccountService;

    @Override
    public Page<LabourVo> selectUserLabourList(Page<LabourVo> page, LabourDto labourDto) {
        Map<String,Object> map = labourDto.getParams();
        if(map != null&&map.get("condition") != null){
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            String condition = (String) map.get("condition");
            if (pattern.matcher(condition).matches()) {
                map.put("type",1);
            } else {
                map.put("type",2);
            }
        }
        labourDto.setParams(map);
        List<LabourVo> list = baseMapper.selectUserLabourList( page, labourDto );
        for (LabourVo vo:list) {
            long labourThree = userLabourService.selectUserLabourThreeCount(vo.getUserId());
            vo.setLabourThree(labourThree);
            vo.setLabourSum(vo.getLabourThree() + vo.getLabourTwo());
            BetaAccount accountPam = new BetaAccount();
            accountPam.setUserId(vo.getUserId());
            accountPam.setProductType(BetaEnum.accountType.GENER.getCode());
            BetaAccount accountVo = betaAccountService.selectOne(new EntityWrapper<>(accountPam));
            if (accountVo != null) {
                vo.setTotalVolume(accountVo.getTotalEnter());
            }else {
                vo.setTotalVolume(BigDecimal.ZERO);
            }
            long totalAuth = userLabourService.selectUserLabourAuthCount(vo.getUserId());
            vo.setTotalAuth(totalAuth);
            long totalActive = userLabourService.selectUserLabourActiveCount(vo.getUserId());
            vo.setTotalActive(totalActive);
            long oneAuth = userLabourService.selectUserLabourOneAuthCount(vo.getUserId());
            vo.setOneAuth(oneAuth);
            long oneActive = userLabourService.selectUserLabourOneActiveCount(vo.getUserId());
            vo.setOneActive(oneActive);
        }
        page.setRecords( list );
        return page;
    }

    @Override
    public List<LabourVo> selectUserLabourExportList(LabourDto labourDto) {
        Map<String,Object> map = labourDto.getParams();
        if(map != null&&map.get("condition") != null){
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            String condition = (String) map.get("condition");
            if (pattern.matcher(condition).matches()) {
                map.put("type",1);
            } else {
                map.put("type",2);
            }
        }
        labourDto.setParams(map);
        List<LabourVo> list = baseMapper.selectUserLabourList( labourDto );
        for (LabourVo vo:list) {
            long labourThree = userLabourService.selectUserLabourThreeCount(vo.getUserId());
            vo.setLabourThree(labourThree);
            vo.setLabourSum(vo.getLabourThree() + vo.getLabourTwo());
        }
        return list;
    }


    @Override
    public BigDecimal selectUserLabourSum() {
        return baseMapper.selectUserLabourSum();
    }


    @Override
    public int updateLabourAccount(UserLabourAccount account) {
        return baseMapper.updateLabourAccount( account );
    }
    /**
     * 查询用户是否注册奖励
     * @param userId
     * @return
     */
    @Override
    public LabourUserVo selectLabourUserTWO(Long userId) {
        return baseMapper.selectLabourUserTWO(userId);
    }

}
