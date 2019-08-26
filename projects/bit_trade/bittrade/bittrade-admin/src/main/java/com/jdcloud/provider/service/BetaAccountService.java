package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaAccountDto;
import com.jdcloud.provider.dto.BetaAccountRecordDto;
import com.jdcloud.provider.pojo.BetaAccount;
import com.jdcloud.provider.pojo.vo.BetaAccountVo;
import com.jdcloud.provider.pojo.vo.RechargeRecordVo;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 我的beta资产表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-13
 */
public interface BetaAccountService extends IService<BetaAccount> {

    Page<BetaAccountVo> betaAccountList(Page<BetaAccountVo> page, BetaAccountDto dto);
    int updateBetaAccountById(BetaAccount accountVo, Integer accountType, BigDecimal amount, Integer serialType, String remark);
    void cumulativeProfit(Long userId, BigDecimal dayEarnings);
    Wrapper<String> saveOrUpdate(BetaAccountRecordDto betaAccountRecordDto);
    List<BetaAccount> selectAllUserId();
    RechargeRecordVo selectRechargeRecord();

    /**
     * @Description: 导出列表
     * @param dto : 条件对象
     * @Author: zjun
     * @Date: 2019/8/13 15:01
     */
    List<BetaAccountVo> betaAccountExcelList(BetaAccountDto dto);


    /**
     * 打包审核返还资产
     *
     * @param accountVo
     * @param amount
     * @return
     */
    int updateBetaAccountByPack(BetaAccount accountVo, BigDecimal amount,String remark);

    /**
     * @Description: 导入资产数据
     * @Author: senghor
     * @Date: 2019/8/15 21:18
     */
    Wrapper<String> importExcel(MultipartFile file);
}
