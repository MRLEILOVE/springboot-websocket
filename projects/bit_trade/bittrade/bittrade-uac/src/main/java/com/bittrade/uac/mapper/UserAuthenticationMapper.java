package com.bittrade.uac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.uac.model.dto.AuthenticationDto;
import com.bittrade.uac.model.dto.UserAuthenticationDto;
import com.bittrade.uac.model.pojo.UserAuthentication;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description:
 **/
@Repository
public interface UserAuthenticationMapper extends BaseMapper<UserAuthentication> {

    /**
     * 查询身份认证列表<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/11/22 17:45
     */
    List<AuthenticationDto> queryAuthenticationList(AuthenticationDto authenticationDto);

    /**
     * 统计数量<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/11/23 9:08
     */
    Integer queryAuthenticationCount(AuthenticationDto authenticationDto);

    /**
     * 查询所有审核失败的用户ID
     *
     * @return
     */
    Set<Long> queryRejectAuthenticationList();

    /**
     * 查询身份认证信息<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/11/30 15:03
     */
    UserAuthenticationDto selectByUserId(Long userId);

    /**
     * 根据userId更新认证信息<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/12/10 20:41
     */
    void updateByUserId(UserAuthentication authentication);
}
