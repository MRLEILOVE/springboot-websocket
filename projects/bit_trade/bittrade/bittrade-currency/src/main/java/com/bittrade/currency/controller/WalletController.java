package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.currency.api.service.IWUserWalletBillService;
import com.bittrade.currency.api.service.IWUserWalletService;
import com.bittrade.currency.api.service.IWWithdrawWalletBillService;
import com.bittrade.pojo.model.WUserWalletBill;
import com.bittrade.pojo.model.WWithdrawWalletBill;

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
public class WalletController {
    @Autowired
    private IWUserWalletBillService userWalletBillService;
    @Autowired
    private IWWithdrawWalletBillService withdrawWalletBillService;
    @Autowired
    private IWUserWalletService userWalletService;
    @Autowired
    private ITCurrencyService currencyService;
    @Autowired
    private ITWalletService walletService;


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

    @PostMapping("recharge")
    @ApiOperation(value="充币", notes="充币")
    @ApiImplicitParam(name = "RechargeVo", value = "对象RechargeVo", required = true, dataType = "String")
    public Object recharge(@RequestBody RechargeVo vo, Model model) throws IOException {
       QueryWrapper<WUserWallet>queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("user_id","userid").eq("coin_type",vo.getName());
       queryWrapper.select("address","qr");
        WUserWallet one = userWalletService.getOne(queryWrapper);
        String content =one.getAddress();
        new SimpleQrcodeGenerator().generate(content).toFile("F:\\AodaCat_default.png");
        OutputStream out = null;
        try {
            out = new FileOutputStream("F:\\AodaCat_default.png");
            new SimpleQrcodeGenerator().generate(content).toStream(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            IOUtils.closeQuietly(out);
        }

        return one;
    }*/

    @PostMapping("rechargeRecord/{currentPage}/{pageSize}")
    @ApiOperation(value="充币记录", notes="冲币记录")
    public List<WUserWalletBill> rechargeRecord(Model model,@PathVariable  Integer currentPage,@PathVariable Integer pageSize){
        QueryWrapper<WUserWalletBill>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",5).orderByDesc("update_time");
        queryWrapper.select("amount","update_time");
        IPage<WUserWalletBill> page = userWalletBillService.page(new Page<>(currentPage,pageSize),queryWrapper);
        List<WUserWalletBill> records = page.getRecords();
        model.addAttribute("records",records);
        return records;
    }

    @PostMapping("extractRecord/{currentPage}/{pageSize}")
    @ApiOperation(value="提币记录", notes="提币记录")
    public List<WWithdrawWalletBill> extractRecord(Model model,@PathVariable  Integer currentPage,@PathVariable Integer pageSize){
        QueryWrapper<WWithdrawWalletBill>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",5).orderByDesc("update_time");
        queryWrapper.select("amount","update_time");
        IPage<WWithdrawWalletBill> page = withdrawWalletBillService.page(new Page<>(currentPage, pageSize));
        List<WWithdrawWalletBill> records = page.getRecords();
        model.addAttribute("records",records);
        return records;
    }

}
