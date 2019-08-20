package com.wallet.biz.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.CPersonalAccount;
import com.wallet.biz.vo.PersonalAccountVO;

public interface PersonalAccountMapper extends BaseMapper<CPersonalAccount> {
    /**
     * 法币账户入账
     */
    Integer inMoney(CPersonalAccount cPersonalAccount);

    /**
     * 法币账户出账
     */
    Integer outMoney(CPersonalAccount cPersonalAccount);

    /**
     * 冻结法币账户金额
     * @param id id
     * @param num 数量
     * @param version 版本号
     */
    Integer modifyTransferFrozen(@Param("id") Integer id,@Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     *  释放划转解冻
     * @param id id
     * @param num 数量
     * @param version 版本号
     */
    Integer decreaseFreeze(@Param("id") Integer id,@Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     * 获取用户的法币钱包
     * @param userId 用户id
     * @return
     */
    List<PersonalAccountVO> getByUserId(@Param("userId") Long userId);
}
