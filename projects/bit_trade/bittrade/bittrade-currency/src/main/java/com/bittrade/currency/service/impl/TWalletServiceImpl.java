package com.bittrade.currency.service.impl;

import java.util.List;

import com.bittrade.pojo.vo.UserWalletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletServiceImpl;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.QueryWalletVO;
import com.bittrade.pojo.vo.TWalletVO;

import redis.clients.jedis.JedisCluster;

/**
 * <p>
 * 虚拟币钱包表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Service
public class TWalletServiceImpl extends DefaultTWalletServiceImpl<ITWalletDAO, TWallet, TWalletDTO, TWalletVO> implements ITWalletService {
    @Autowired
    private ITWalletDAO walletDAO;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 查询用户的币币账户
     */
    @Override
    public CoinAccountVO queryCoinAccountByUserId(Integer userId) {
        CoinAccountVO resultVO = new CoinAccountVO();
        Double usdtTotal = 0.00;//usdt总数量
        Double CNY   = 0.00;//人民币总数量

        //查询用户钱包
        List<QueryWalletVO> walletVOS = walletDAO.queryByUserId(userId);
        if(walletVOS != null && walletVOS.size() > 0){
            //计算转换成usdt的总数量
            for (QueryWalletVO vo : walletVOS) {
                if("USDT".equals(vo.getCurrencyName())){
                    usdtTotal = usdtTotal + vo.getTotal();//本身就是usdt的话，直接累加就可以了
                }else {
                    //#TODO 查找该币与usdt的兑换率 ，现在暂时先写个假的汇率
                    Double rate = 0.5;
                    usdtTotal = usdtTotal + (rate * vo.getTotal());
                }
            }
            //计算折合人民币的数量
            String rateKey = jedisCluster.get("USD_TO_CNY_RATE_KEY");
            if(rateKey != null){
                Integer cnyRateKey = Integer.parseInt(rateKey);
                CNY = cnyRateKey * usdtTotal;
            }

            resultVO.setTotal(usdtTotal);
            resultVO.setCNY(CNY);
            resultVO.setWalletVOS(walletVOS);
        }else{
            resultVO.setCNY(0.00);
            resultVO.setTotal(0.00);
        }
        return resultVO;
    }

    /**
     * 查询用户钱包
     * @param userId 用户id
     * @param currencyTradeId 交易对id
     */
    @Override
    public UserWalletVO queryUserWallet(Integer userId, Integer currencyTradeId) {
        return walletDAO.queryUserWallet(userId,currencyTradeId);
    }
}
