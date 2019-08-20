package com.wallet.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wallet.biz.pojo.CPersonalAccountRecord;
import com.wallet.biz.vo.RecordVO;
import com.wallet.biz.vo.TransferRecordVO;

/**
 * <p>
 * 我的资产流水表 Mapper 接口
 * </p>
 *
 * @author helen
 * @since 2019-03-05
 */
public interface PersonalAccountRecordMapper extends BaseMapper<CPersonalAccountRecord> {

    /**
     * 查询法币账户划转记录
     */
    List<TransferRecordVO> transferRecord(Page<TransferRecordVO> page, @Param("userId") Long userId);

    /**
     * 通过id获取资金账户流水
     */
    RecordVO getRecordById(@Param("id") String id);

    /**
     * 查询记录
     * @param page 分页对象
     * @param userId 用户id
     * @param productType 币种
     * @param list 方向（1、3划出法币账户  2、4转入法币账户）
     * @return
     */
    List<RecordVO> selectRecord(Page<RecordVO> page, @Param("userId") Long userId,@Param("productType")  Integer productType,@Param("list")  List<Integer> list);
}
