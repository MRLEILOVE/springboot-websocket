package com.wallet.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wallet.biz.pojo.GBetaAccountRecord;
import com.wallet.biz.vo.RecordVO;
import com.wallet.biz.vo.TransferRecordVO;

/**
 * <p>
 * 我的beta资产流水表 Mapper 接口
 * </p>
 *
 * @author helen
 * @since 2019-04-15
 */
public interface GBetaAccountRecordMapper extends BaseMapper<GBetaAccountRecord> {
    /**
     * 查询beta账户划转记录
     */
    List<TransferRecordVO> transferRecord(Page<TransferRecordVO> page,@Param("userId") Long userId);

    /**
     * 通过id获取beta账户流水
     */
    RecordVO getRecordById(String id);

    /**
     * 查询beta账户记录
     * @param page 分页对象
     * @param userId 用户id
     * @param productType 币种类型
     * @param list  方向（1，4：转入beta 2，3 转出beta）
     * @return
     */
    List<RecordVO> selectRecord(Page<RecordVO> page,@Param("userId") Long userId, @Param("productType")Integer productType, @Param("list")List<Integer> list);
}
