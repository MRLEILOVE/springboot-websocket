package com.walletbiz.web;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.walletbiz.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.WebUtil;
import com.walletbiz.Exception.FlowException;
import com.walletbiz.dto.JudgmentDto;
import com.walletbiz.dto.PageDto;
import com.walletbiz.dto.WalletDto;
import com.walletbiz.enumer.CoinType;
import com.walletbiz.pojo.TAccountConfig;
import com.walletbiz.pojo.TOrder;
import com.walletbiz.pojo.TWalletFundAccount;
import com.walletbiz.pojo.WUserWallet;
import com.walletbiz.pojo.WUserWalletBill;
import com.walletbiz.pojo.WWithdrawWalletBill;
import com.walletbiz.service.ICreateAddressFactory;
import com.walletbiz.service.IJsonRpcService;
import com.walletbiz.service.ITAccountConfigService;
import com.walletbiz.service.ITOrderService;
import com.walletbiz.service.ITwalletFundAccountService;
import com.walletbiz.service.IWUserWalletBillService;
import com.walletbiz.service.IWWithdrawWalletBillService;
import com.walletbiz.service.IwalletChainService;
import com.walletbiz.service.ParameterConfigService;
import com.walletbiz.service.WUserWalletService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户钱包账单 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
@RestController
@RequestMapping(value = "wallet",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value="TWalletAddressController",tags={"钱包业务"})
public class WalletController {
    @Autowired
    private IWUserWalletBillService userWalletBillService;
    @Autowired
    private IWWithdrawWalletBillService withdrawWalletBillService;
    @Autowired
    private ITOrderService orderService;
    @Autowired
    private ICreateAddressFactory createAddressFactory;
    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private ITwalletFundAccountService iTwalletFundAccountService;
    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private WUserWalletService wUserWalletService;
    @Autowired
    private ITAccountConfigService accountConfigService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private IwalletChainService walletChainService;



    @Value("${jdcloud.aliyun.uploadPath}")
    private String uploadPath;

    private String url = "http://localhost:19999/create";

    @PostMapping("tibiFee")
    @ApiOperation(value = "提币费率", notes = "提币费率")
    public ReturnDTO<String> tibiFee() {
        return walletChainService.showfee();
    }

    @PostMapping("confirmTibi")
    @ApiOperation(value = "确定提币", notes = "确定提币")
    public ReturnDTO<String> confirmTibi(@RequestBody @Validated WithDrawParamVo withDrawParamVo) {
        //判断该币种是否可以提币
        QueryWrapper<TAccountConfig> wrapper = new QueryWrapper<>();
        wrapper.eq("keyword",withDrawParamVo.getToken());
        TAccountConfig tAccountConfig = accountConfigService.getOne(wrapper);
        if(null == tAccountConfig || tAccountConfig.getDrawCoin() != 1){
            return ReturnDTO.error("该币种暂时不允许提币");
        }

        return orderService.confirmTibi(withDrawParamVo);
    }


    @PostMapping("chongbi")
    @ApiOperation(value = "充币")
    public String chongbi() throws  FlowException {
        Long userId = WebUtil.getLoginUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        CreateAddressParamDto paramDto = new CreateAddressParamDto();
        paramDto.setUserId(userId);
        String s = createAddressFactory.create(paramDto);
        System.out.println(s);
        if (s.contains("BTC_TOKEN")) {
            s = s.replaceAll("BTC_TOKEN", "USDT");
        }
        return s;
    }

    @PostMapping("qrCode")
    @ApiOperation(value = "二维码", notes = "二维码")
    public ReturnDTO<String> qrCode(@RequestBody @Validated CoinTypeDto coinTypeDto) throws FlowException, FileNotFoundException {
        Long userId = WebUtil.getLoginUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        QueryWrapper<WUserWallet>entityWrapper=new QueryWrapper<>();
        if(CoinType.USDT.toString().equals(coinTypeDto.getToken())){
            coinTypeDto.setToken("BTC_TOKEN");
        }
        entityWrapper.eq("user_id",userId).eq("coin_type",coinTypeDto.getToken());
        WUserWallet wUserWallet1 = wUserWalletService.getOne(entityWrapper);
        if (null==wUserWallet1){
            return ReturnDTO.error("该用户还没有钱包地址");
        }
        if (null!=coinTypeDto.getUrl()&&!"".equals(coinTypeDto.getUrl())){
            wUserWallet1.setCodeQr(coinTypeDto.getUrl());
            boolean b = wUserWalletService.updateById(wUserWallet1);
            if (b){
                return ReturnDTO.ok("success");
            }
            return ReturnDTO.error("error");
        }
        if (null==wUserWallet1.getCodeQr()||"".equals(wUserWallet1.getCodeQr())){
            return ReturnDTO.error("该用户还没有二维码");
        }
        return ReturnDTO.ok(wUserWallet1.getCodeQr());
    }


    @PostMapping("record")
    @ApiOperation(value = "充提币记录", notes = "充提币记录")
    public ReturnDTO<PageVo<CoinRecordVo>> rechargeRecord(@RequestBody WalletDto walletDto) throws FlowException {
        Long user_id = WebUtil.getLoginUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }

        QueryWrapper<WUserWalletBill> chong = new QueryWrapper<>();
        chong.eq("user_id", user_id).eq("flag",2).orderBy(true, false, "create_time");
        QueryWrapper<WWithdrawWalletBill> ti = new QueryWrapper<>();
        ti.eq("user_id", user_id).eq("flag",2).orderBy(true, false, "create_time");
        if (null != walletDto.getToken() && !"".equals(walletDto.getToken())) {
            ti.eq("token", walletDto.getToken());
            chong.eq("token", walletDto.getToken());
        }
        List<CoinRecordVo> voList = new ArrayList<>();
        long pages = 0L;//page1.getPages() + page2.getPages();
        long totals = 0L;//page1.getTotal() + page2.getTotal();

        if ("recharge".equals(walletDto.getType())) {
            Page<WUserWalletBill> chongPage = new Page<>(walletDto.getCurrent(), walletDto.getSize() / 2);
            IPage<WUserWalletBill> page1 = userWalletBillService.page(chongPage, chong);
            List<WUserWalletBill> chongList = page1.getRecords();
            for (WUserWalletBill userWalletBill : chongList) {
                CoinRecordVo coinRecordVo = new CoinRecordVo();
                coinRecordVo.setToken(userWalletBill.getToken());
                coinRecordVo.setAmount(userWalletBill.getAmount().toPlainString());
                coinRecordVo.setReceiverAddress(userWalletBill.getReceiverAddress());
                coinRecordVo.setCreateTime(userWalletBill.getCreateTime());
                coinRecordVo.setDirection(userWalletBill.getDirection());
                voList.add(coinRecordVo);
            }
            pages += page1.getPages();
            totals += page1.getTotal();
        }

        if ("withdraw".equals(walletDto.getType())) {
            Page<WWithdrawWalletBill> tiPage = new Page<>(walletDto.getCurrent(), walletDto.getSize() / 2);
            IPage<WWithdrawWalletBill> page2 = withdrawWalletBillService.page(tiPage, ti);
            List<WWithdrawWalletBill> tiList = page2.getRecords();
            for (WWithdrawWalletBill wWithdrawWalletBill : tiList) {
                CoinRecordVo coinRecordVo = new CoinRecordVo();
                coinRecordVo.setToken(wWithdrawWalletBill.getToken());
                coinRecordVo.setAmount(wWithdrawWalletBill.getAmount().toPlainString());
                coinRecordVo.setReceiverAddress(wWithdrawWalletBill.getReceiverAddress());
                coinRecordVo.setCreateTime(wWithdrawWalletBill.getCreateTime());
                coinRecordVo.setDirection(wWithdrawWalletBill.getDirection());
                voList.add(coinRecordVo);
            }
            pages += page2.getPages();
            totals += page2.getTotal();
        }

        Collections.sort(voList, new Comparator<CoinRecordVo>() {
            @Override
            public int compare(CoinRecordVo o1, CoinRecordVo o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });

        PageVo<CoinRecordVo> page = new PageVo<>();
        page.setRecords(voList);
        page.setCurrent(walletDto.getCurrent());
        page.setSize(walletDto.getSize());
        page.setTotal(totals);
        page.setPages(pages);
        return ReturnDTO.ok(page);
    }

    @GetMapping("detail")
    @ApiOperation(value = "查询当前用户的币种余额", notes = "查询当前用户的币种余额")
    public ReturnDTO<List<AssetsVO>> detail() throws FlowException {
        Long user_id = WebUtil.getLoginUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }
        List<AssetsVO> assetsVOs = iTwalletFundAccountService.detail(user_id);
        return ReturnDTO.ok(assetsVOs);
    }

    @GetMapping("conversionTotal")
    @ApiOperation(value = "总资金折合", notes = "总资金折合")
    public ReturnDTO<ConversionVo> totalConversion() throws FlowException {
        Long user_id = WebUtil.getLoginUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }
        ConversionVo conversionVo = iTwalletFundAccountService.totalConversion(user_id);
        return ReturnDTO.ok(conversionVo);
    }

    @PostMapping("auditStatus")
    @ApiOperation(value = "审核记录", notes = "审核记录")
    public ReturnDTO<IPage<TOrder>> auditStatus(@RequestBody PageDto pageDto) throws FlowException {
        Long user_id = WebUtil.getLoginUser().getUser_id();
        if (user_id == null) {
            throw new FlowException("用户未登录");
        }
        QueryWrapper<TOrder> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("user_id", user_id).orderBy(true, false, "create_time");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(-1);
        entityWrapper.in("type", list);//审核通过的记录不显示，只显示审核中、被拒绝的记录
        Page<TOrder> page = new Page<>(pageDto.getCurrent(), pageDto.getSize());
        IPage<TOrder> page1 = orderService.page(page, entityWrapper);
        return ReturnDTO.ok(page1);
    }

    @PostMapping("tibiMaxMin")
    @ApiOperation(value = "最大最小提币数量", notes = "最大最小提币数量")
    public ReturnDTO<Map<String, Object>> maxMinTibi() {
        return walletChainService.showmaxMin();
    }

    @PostMapping("jugment")
    @ApiOperation(value = "额度判断", notes = "额度判断")
    public ReturnDTO<String> judgment(@RequestBody @Validated JudgmentDto withDrawParamVo) {
        return walletChainService.checkparam(withDrawParamVo);
    }
}



  /*  @PostMapping("coinSelect")
    @ApiOperation(value="币种选择", notes="币种选择")
    public Object coinSelect(){
        QueryWrapper<TCurrency>queryWrapper=new QueryWrapper<>();
        queryWrapper.select("name");
        List<TCurrency> list = currencyService.list(queryWrapper);

        List<String>list1=new ArrayList<>();
        for(TCurrency currency:list){
            list1.add(currency.getName());
        }
        list1.sort(new Comparator<String>() {
            //重点是这个函数
            public int compare(String o1, String o2) {
                //忽略掉大小写后,进行字符串的比较
                String s1 = o1.toLowerCase();
                String s2 = o2.toLowerCase();
                return s1.compareTo(s2);//从a-z的排
                //return s2.compareTo(s1);//从z-a的排
            }
        });
        return list;
    }
*/