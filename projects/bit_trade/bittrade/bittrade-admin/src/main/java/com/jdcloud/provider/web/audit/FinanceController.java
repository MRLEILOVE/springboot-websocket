package com.jdcloud.provider.web.audit;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.constant.MessageConstant;
import com.jdcloud.base.dto.SmsMessageDto;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.AuditEnum;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.core.message.SmsService;
import com.jdcloud.provider.dto.TOrderDto;
import com.jdcloud.provider.dto.TotalDto;
import com.jdcloud.provider.dto.WithDrawParamDto;
import com.jdcloud.provider.model.dto.UserDto;
import com.jdcloud.provider.model.service.UacUserFeignApi;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.TOrderVo;
import com.jdcloud.provider.pojo.vo.TotalVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.StringUtils;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/audit/finance")
public class FinanceController extends BaseController {

    private String					prefix	= "audit/withdraw";

    @Autowired
    private ITOrderService orderService;

    @Resource
    private UacUserFeignApi uacUserFeignApi;

    @Resource
    private RecordSmsLogService recordSmsLogService;

    @Value("${withdraw.url}")
    private String withdrawUrl;
//    private String dev="http://192.168.1.16:19999/withdraw"; //dev
//    private String test="http://192.168.1.61:19999/withdraw"; //tset
//    private String prod="http://192.168.1.222:19999/withdraw";

    @Autowired
    private IJsonRpcService jsonRpcService;



    @Autowired
    private WConfigWalletService wConfigWalletService;




    @RequiresPermissions("financeInfo:view")
    @GetMapping()
    public String newWithDrawInfo() {
        return prefix + "/finance";
    }
    @Autowired
    private SmsService smsService;

    /**
     * 审核列表
     * @param
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getHouseProduct(TOrderDto tOrderDto) {
        /*  Page<TOrder> vo = orderService.selectPage(getPage());*/
//        EntityWrapper<TOrder>entityWrapper=new EntityWrapper<>();
//        entityWrapper.eq("type",AuditEnum.orderType.CSAUDITED.getCode());
//        Page<TOrder> tOrderPage = orderService.selectPage(getPage(), entityWrapper);
//        return new TableDataInfo(tOrderPage.getRecords(), tOrderPage.getTotal());
        tOrderDto.setType(AuditEnum.orderType.CSAUDITED.getCode().toString());
        Page<TOrderVo> page = orderService.getCustomerList(getPage(), tOrderDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }



    /**
     * 财务审核列表
     * @param
     * @return
     */
    @PostMapping("/getFinanceList")
    @ResponseBody
    public TableDataInfo getFinanceList(TOrderDto tOrderDto) {
        tOrderDto.setType(AuditEnum.orderType.CSAUDITED.getCode().toString());
        Page<TOrderVo> page = orderService.getFinanceList(getPage(), tOrderDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 财务审核
     * @param id
     * @return
     * @throws Exception
     */
    @RequiresPermissions("finance:audit")
    @PostMapping("/audit/{id}")
    @ResponseBody
    @Log(title = "财务审核",buinessType = AnnotationEnum.BusinessType.UPDATE)
    public Wrapper audit(@PathVariable Long id) throws Exception {
        TOrder tOrder = orderService.selectById(id);
        if (!tOrder.getType().equals(AuditEnum.orderType.CSAUDITED.getCode())){
            WrapMapper.error("审核异常");
        }
        tOrder.setType(AuditEnum.orderType.FINANCEAUDITED.getCode());
        WithDrawParamDto withDrawParamDto=new WithDrawParamDto();
        if ("BTC".equals(tOrder.getToken())){
            withDrawParamDto.setCoinType("BTC");
        }else if("USDT".equals(tOrder.getToken())){
            withDrawParamDto.setCoinType("BTC_TOKEN");
        }
        withDrawParamDto.setOrderId(tOrder.getOrderId());
        BigDecimal amount = tOrder.getAmount();
        withDrawParamDto.setAmount(amount);
        withDrawParamDto.setReceiverAddress(tOrder.getReceiverAddress());
        withDrawParamDto.setUserId(tOrder.getUserId());
        withDrawParamDto.setToken(tOrder.getToken());
        RestTemplate restTemplate = new RestTemplate();
        String a = restTemplate.postForObject(withdrawUrl, withDrawParamDto, String.class);
        System.out.println(a);
        JSONObject jsonObject = JSONObject.parseObject(a);
        Integer code= (Integer) jsonObject.get("code");
        if (code!=200){
            throw new Exception("方法调用异常");
        }

        boolean b = orderService.updateById(tOrder);
        if (b){
            Wrapper<UserDto> userDtoWrapper = uacUserFeignApi.queryFuserById(tOrder.getUserId());
            UserDto userDto=userDtoWrapper.getResult();
            SmsMessageDto sm = new SmsMessageDto();
            sm.setAreaCode(userDto.getPhoneAreaCode());
            sm.setMoblie(userDto.getTelePhone());
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String currentDateString = format.format(new Date());
            sm.setParamSingle("now",currentDateString);
            sm.setParamSingle("cointype",tOrder.getToken());
            sm.setParamSingle("amount",tOrder.getAmount().toString());
            sm.setParamSingle("orderId",tOrder.getOrderId());
            smsService.sendSms(sm,MessageConstant.SmsTempletEnum.WITHDRAW_TEMPLATE);
            RecordSmsLog smsLog = new RecordSmsLog();
            smsLog.setCreateTime(new Date());
            smsLog.setType(12L);
            smsLog.setPhone(userDto.getTelePhone());
            smsLog.setContent( StringUtil.mosaicTemplate(sm,MessageConstant.SmsTempletEnum.WITHDRAW_TEMPLATE.getLocation()));
            recordSmsLogService.insert(smsLog);
        }
        return WrapMapper.ok("成功");
    }


    /**
     * 客服拒绝
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/refuse/{id}")
    @ResponseBody
    @Log(title = "客服拒绝",buinessType = AnnotationEnum.BusinessType.UPDATE)
    public Wrapper refuse(@PathVariable Integer id) throws Exception {
        return orderService.refuse(id);
    }


    /**
     *财务拒绝
     * C
     */
    @Log(title = "财务拒绝", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @PostMapping("/financialRejection/{id}")
    @ResponseBody
    public Wrapper financialRejection(@PathVariable Integer id) throws Exception {
        return orderService.financialRejection(id);
    }

    /**
     * 资金总额
     * @return
     */
    @RequestMapping("/total")
    @ResponseBody
    public Wrapper total()  {
        EntityWrapper<WConfigWallet>entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("valid","E");
        List<WConfigWallet> list = wConfigWalletService.selectList(entityWrapper);
        List<TotalDto>list1=new ArrayList<>();
        for (WConfigWallet wConfigWallet:list){
            TotalDto totalDto=new TotalDto();
            totalDto.setCoinType(wConfigWallet.getCoinType());
            totalDto.setWalletType(wConfigWallet.getWalletType());
            BigDecimal btcBalance= jsonRpcService.getBalance(wConfigWallet.getAddress());
            BigDecimal usdtBalance = jsonRpcService.getTokenBalance(wConfigWallet.getAddress(),"31");
            totalDto.setBtcBalance(btcBalance);
            totalDto.setUsdtBalance(usdtBalance);
            totalDto.setAddress(wConfigWallet.getAddress());
            list1.add(totalDto);
        }
        return WrapMapper.ok(list1);
    }


    /**
     * 资金总额初始化
     * @return
     *
     */
    @RequiresPermissions("totalNew:view")
    @GetMapping("/totalInfo")
    public String totalNewInfo() {
        return "audit/total/list";
    }

    /**
     * 资金总额
     * @return
     */
    @RequestMapping("/totalNew")
    @ResponseBody
    public TableDataInfo  totalNew(TotalDto dto){
        Page<TotalVo> page = orderService.getTotal(getPage(),dto);
        return new TableDataInfo(page.getRecords(),page.getTotal());
    }



}
