package com.jdcloud.provider.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.provider.dto.ImgLogDto;
import com.jdcloud.provider.mapper.BetaImgLogMapper;
import com.jdcloud.provider.pojo.BetaImgLog;
import com.jdcloud.provider.pojo.vo.BetaImgLogVo;
import com.jdcloud.provider.service.BetaImgLogService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.date.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 覆盖图片表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-01
 */
@Service
public class BetaImgLogServiceImpl extends ServiceImpl<BetaImgLogMapper, BetaImgLog> implements BetaImgLogService {
    @Autowired
    private AliyunConfiguration aliyunConfiguration;
    /**
     * 列表
     * @param page
     * @param imgLogDto
     * @return
     */
    @Override
    public Page<BetaImgLogVo> selectImgLog(Page<BetaImgLogVo> page, ImgLogDto imgLogDto) {
        page.setRecords( baseMapper.selectImgLog(page, imgLogDto));
        return page;
    }

    /**
     * 删除(删除支付图片)
     * @param id
     * @return
     */
    @Override
    public int deleteByIds(String id) {
        Integer[] ids = ConvertUtil.toIntArray(id);
        for (Integer imgId :ids){
            BetaImgLog betaImgLog = baseMapper.selectById(imgId);
            if(betaImgLog.getPayImg()!=null){
                // 获取到 支付凭证
                String fileName = aliyunConfiguration.ossGetURL(betaImgLog.getPayImg());
                Boolean bool= aliyunConfiguration.delete_oss(fileName);
                if(!bool){
                    return 0;
                }
            }
            if(betaImgLog.getByWechatImg()!=null){
                // 获取到 覆盖的微信二维码
                String fileName = aliyunConfiguration.ossGetURL(betaImgLog.getByWechatImg());
                Boolean bool= aliyunConfiguration.delete_oss(fileName);
                if(!bool){
                    return 0;
                }
            }
            if(betaImgLog.getByAlipayImg()!=null){
                // 获取到 被覆盖的支付宝二维码
                String fileName = aliyunConfiguration.ossGetURL(betaImgLog.getByAlipayImg());
                Boolean bool= aliyunConfiguration.delete_oss(fileName);
                if(!bool){
                    return 0;
                }
            }
        }

        return baseMapper.deleteByIds(ids);
    }


    @Override
    public BetaImgLogVo selectImgLogDetails(Integer id) {
        return baseMapper.selectImgLogDetails(id);
    }

    public static void main(String[] args) {
        Long a = new Long(1446165241);
        Long b = new Long(1446165241);
        System.out.println(a==b);
        System.out.println(a.equals(b));



    }


}
