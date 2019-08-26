package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaMaleAddDto;
import com.jdcloud.provider.dto.BetaMaleDto;
import com.jdcloud.provider.pojo.BetaMale;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.BetaMaleVo;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 贝塔狗---公狗表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-10
 */
public interface BetaMaleService extends IService<BetaMale> {

    Page<BetaMaleVo> selectBetaMaleListPage(Page<BetaMaleVo> page, BetaMaleDto betaMaleDto);

    Wrapper<String> saveOrUpdate(BetaMaleAddDto betaMaleAddDto);

    BetaMaleVo selectBetaMale(Integer id);

    Wrapper<String> updateEditRecovery(BetaMaleAddDto betaMaleAddDto);

    BetaMaleOrderVo orderListDetails(Integer id);

    int deleteByIds(String ids);

    Wrapper<String> importExcel(MultipartFile file);

    Wrapper<String> updatePresellTime(BetaMaleAddDto betaMaleAddDto);

    /**
     * @Description: 刷新缓存中的剩余公狗数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    Wrapper<String> refreshRedis();

    Wrapper<String> deleteNew(Integer id);

    /**
     * @Description: 设置公狗优先级
     * @Author: senghor
     * @Date: 2019/8/17 23:34
     */
    Wrapper<String> importExcelWaitSum(MultipartFile fileWaitsum);

    /**
     * @Description: 清空之前所有数据的优先级
     * @Author: senghor
     * @Date: 2019/8/18 0:28
     */
    void clearWaitSum();
}
