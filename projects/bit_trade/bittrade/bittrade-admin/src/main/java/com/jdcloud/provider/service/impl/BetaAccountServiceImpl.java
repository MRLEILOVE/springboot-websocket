package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.ContractEnum;
import com.jdcloud.core.utils.RequestUtil;
import com.jdcloud.provider.dto.*;
import com.jdcloud.provider.mapper.BetaAccountMapper;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.BetaAccountVo;
import com.jdcloud.provider.pojo.vo.RechargeRecordVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.provider.utils.ImportExcelUtil;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.http.IpUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * <p>
 * 我的beta资产表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-13
 */
@Slf4j
@Service
public class BetaAccountServiceImpl extends ServiceImpl<BetaAccountMapper, BetaAccount> implements BetaAccountService {

    @Autowired
    private BetaAccountRecordService betaAccountRecordService;
    @Resource
    private BetaAccountMapper betaAccountMapper;
    @Autowired
    private BetaAccountService betaAccountService;
    @Autowired
    private AccountConfigService accountConfigService;
    @Autowired
    private AccountUpRecordService accountUpRecordService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private BetaAccountImportRecordService betaAccountImportRecordService;

    @Override
    public Page<BetaAccountVo> betaAccountList(Page<BetaAccountVo> page, BetaAccountDto dto) {
        page.setRecords(betaAccountMapper.betaAccountList(page, dto));
        return page;
    }

    /**
     * 变更资产
     *
     * @param accountVo
     * @param accountType
     * @param amount
     * @param serialType
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBetaAccountById(BetaAccount accountVo, Integer accountType, BigDecimal amount, Integer serialType, String remark) {
        BigDecimal afterAmount = accountVo.getBalance();
        if (BigDecimal.ZERO.compareTo(amount) > 0) {

            throw new RuntimeException("操作金额不能为负数");
        }
        if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
            afterAmount = afterAmount.add(amount);
            accountVo.setTotalEnter(accountVo.getTotalEnter().add(amount));
        } else {
            amount = BigDecimal.ZERO.subtract(amount);
            afterAmount = afterAmount.add(amount);
            accountVo.setTotalExit(accountVo.getTotalExit().add(amount));
        }
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        BetaAccountRecord record = new BetaAccountRecord();
        record.setUserId(accountVo.getUserId());
        record.setBeforeAmount(accountVo.getBalance());
        record.setAmount(amount);
        record.setAfterAmount(afterAmount);
        record.setCreateTime(new Date());
        record.setBetaAccountId(accountVo.getId());
        record.setRemark(remark);
        record.setRecordNumber("BR" + DateTimeUtils.getCurrentFullDateTime() + sleep);
        boolean row = betaAccountRecordService.insert(record);
        if (!row) {
            throw new RuntimeException("操作过于频繁，请稍后再试");
        }
        accountVo.setBalance(afterAmount);
        accountVo.setUpdateTime(new Date());
        baseMapper.updateBetaAccountById(accountVo);

        BigDecimal parentAmount = amount;
        // 开启线程去处理
//        taskExecutor.execute(() -> {
//            // 修改父级统计数据
//            if (accountType == BetaEnum.accountType.BETA.getCode()) {
//                userStatisticService.updateParentUserStatisticScore(accountVo.getUserId(), parentAmount, serialType);
//            }
//            if (accountType == BetaEnum.accountType.TOTAL_EARNINGS.getCode()) {
//                userStatisticService.updateParentUserStatisticTotal(accountVo.getUserId(), parentAmount, serialType);
//            }
//        });
        return 1;
    }

    /**
     * 添加用户的累计收益
     *
     * @param userId
     * @param dayEarnings
     * @return
     */
    @Override
    public void cumulativeProfit(Long userId, BigDecimal dayEarnings) {
        BetaAccount betaAccount = new BetaAccount();
        betaAccount.setUserId(userId);
        betaAccount.setProductType(BetaEnum.accountType.TOTAL_EARNINGS.getCode());
        betaAccount = betaAccountService.selectOne(new EntityWrapper<>(betaAccount));
        updateBetaAccountById(betaAccount,
                BetaEnum.accountType.TOTAL_EARNINGS.getCode(), dayEarnings,
                BetaEnum.serialType.SERIAL_ADD.getCode(), "后台拒绝放狗，用户累计收益增加1天");
    }


    /**
     * 充值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<String> saveOrUpdate(BetaAccountRecordDto betaAccountRecordDto) {
//        SysUser currentUser = ShiroUtils.getUser();
//        System.out.println(currentUser.toString());
        if (BigDecimal.ZERO.compareTo(betaAccountRecordDto.getPrice()) > 0) {
            return WrapMapper.error("操作的金额不能为负数！");
        }
        BetaAccount betaAccount = betaAccountService.selectById(betaAccountRecordDto.getId());
        if (betaAccount == null) {
            return WrapMapper.error("用户资产不存在！");
        }
        // 判断配置中是否配置可以充币
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("account_type", betaAccount.getProductType());
        AccountConfig accountConfig = accountConfigService.selectOne(entityWrapper);
        if (accountConfig.getTopUpCoin() != 1) {
            return WrapMapper.error("该钱包类型未开放充币权限");
        }
        // 记录充币记录
        AccountUpRecord accountUpRecord = new AccountUpRecord();
        accountUpRecord.setCreateTime(new Date());
        accountUpRecord.setUserId(betaAccount.getUserId());
        accountUpRecord.setBeforeAmount(betaAccount.getBalance());
        accountUpRecord.setAmount(betaAccountRecordDto.getPrice());
        accountUpRecord.setAfterAmount(betaAccount.getBalance().add(betaAccountRecordDto.getPrice()));
        accountUpRecord.setAccountId(betaAccount.getId().longValue());
        accountUpRecord.setSysUserId(ShiroUtils.getUserId());
        accountUpRecord.setProductType(betaAccount.getProductType());
        accountUpRecord.setIp(IpUtils.getIpAddr(RequestUtil.getRequest()));
        accountUpRecordService.insert(accountUpRecord);
        int flag = updateBetaAccountById(betaAccount,
                betaAccount.getProductType(), betaAccountRecordDto.getPrice(),
                BetaEnum.serialType.SERIAL_ADD.getCode(), "管理后台充值");
        if (flag == 1) {
            return WrapMapper.ok("充值成功");
        } else {
            return WrapMapper.ok("充值异常");
        }
    }

    /**
     * @Description: 查询所有人的微分和累积收益的钱包
     * @Author: senghor
     * @Date: 2019/5/25 0025 18:05
     */
    @Override
    public List<BetaAccount> selectAllUserId() {
        return baseMapper.selectAllUserId();
    }

    /**
     * 查询充值记录
     *
     * @return
     */
    @Override
    public RechargeRecordVo selectRechargeRecord() {
        RechargeRecordVo vo = new RechargeRecordVo();
        IndexDataDto dataDto = userAccountService.userMonitor();
        BigDecimal registerBeta = parameterConfigService.getBigDecimalValue("registerBeta");
        if (dataDto != null) {
            BigDecimal bi = new BigDecimal(dataDto.getTodayRegisterCount());
            BigDecimal total = bi.multiply(registerBeta);
            vo.setRegistrationGift(total);
        }
        // 获取今日消耗β通证
        SumRechargeDto sumRechargeDto = new SumRechargeDto();
        sumRechargeDto.setStartTime(DateTimeUtils.getDays());
        sumRechargeDto.setEndTime(DateTimeUtils.getEndTime(new Date()));
        sumRechargeDto.setProductType(BetaEnum.accountType.BETA.getCode());
        BigDecimal be = userAccountService.sumRecharge(sumRechargeDto);
        if (be != null) {
            vo.setConsume(be);
        }
        return vo;
    }

    @Override
    public List<BetaAccountVo> betaAccountExcelList(BetaAccountDto dto) {
        return betaAccountMapper.betaAccountExcelList(dto);
    }


    /**
     * 打包审核返还资产
     *
     * @param accountVo
     * @param amount
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBetaAccountByPack(BetaAccount accountVo, BigDecimal amount, String remark) {
        BigDecimal afterAmount = accountVo.getBalance();
        if (BigDecimal.ZERO.compareTo(amount) > 0) {

            throw new RuntimeException("操作金额不能为负数");
        }
        afterAmount = afterAmount.add(amount);
        accountVo.setTotalExit(accountVo.getTotalExit().add(amount));
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        BetaAccountRecord record = new BetaAccountRecord();
        record.setUserId(accountVo.getUserId());
        record.setBeforeAmount(accountVo.getBalance());
        record.setAmount(amount);
        record.setAfterAmount(afterAmount);
        record.setCreateTime(new Date());
        record.setBetaAccountId(accountVo.getId());
        record.setRemark(remark);
        record.setRecordNumber("BR" + DateTimeUtils.getCurrentFullDateTime() + sleep);
        boolean row = betaAccountRecordService.insert(record);
        if (!row) {
            throw new RuntimeException("操作过于频繁，请稍后再试");
        }
        accountVo.setBalance(afterAmount);
        accountVo.setUpdateTime(new Date());
        baseMapper.updateBetaAccountById(accountVo);
        return 1;
    }

    /**
     * @param file
     * @Description: 导入资产数据
     * @Author: senghor
     * @Date: 2019/8/15 21:18
     */
    @Override
    public Wrapper<String> importExcel(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (file.isEmpty()) {
            return WrapMapper.ok("导入失败,文件不存在!");
        }
        InputStream in = null;
        List<List<Object>> listob = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
        } catch (Exception e) {
            return WrapMapper.ok("请上传execl文件");
        }

        List<List<Object>> nowListob = listob;
        List<String> errorlist = new ArrayList<String>();
        List<String> succeedlist = new ArrayList<String>();
        if (listob != null && listob.size() > 0) {
            int row = 1;
            EntityWrapper entityWrapper;
            for (List<Object> excelList : nowListob) {
                row++;
                BetaAccountImportRecord betaAccountImportRecord = new BetaAccountImportRecord();
                betaAccountImportRecord.setPhone(excelList.get(0).toString());
                betaAccountImportRecord.setType(excelList.get(1).toString());
                betaAccountImportRecord.setAmount(excelList.get(2).toString());
                betaAccountImportRecord.setRemark(excelList.get(3).toString());
                betaAccountImportRecord.setImportStauts(BetaEnum.importStauts.defeated.getCode());
                betaAccountImportRecordService.insert(betaAccountImportRecord);
                int id = betaAccountImportRecord.getId();
                if ("无".equals(excelList.get(0))) {
                    errorlist.add("第" + row + "行用户手机号不能为空");
                    continue;
                }
                if ("无".equals(excelList.get(1))) {
                    errorlist.add("第" + row + "行币种类型不能为空");
                    continue;
                }
                if ("无".equals(excelList.get(2))) {
                    errorlist.add("第" + row + "行奖励金额不能为空");
                    continue;
                }
                try {
                    String phone = excelList.get(0).toString().replace(".00", "");// 用户手机号
                    Integer type = Integer.valueOf(excelList.get(1).toString());// 币种类型
                    BigDecimal amount = new BigDecimal(excelList.get(2).toString());// 奖励金额
                    String remark = "奖励";// 奖励备注
                    if (!"无".equals(excelList.get(3))) {
                        remark = excelList.get(3).toString();// 奖励备注
                    }
                    BetaAccount betaAccount = baseMapper.selectPhoneType(phone, type);
                    int flag = updateBetaAccountById(betaAccount, type, amount,
                            BetaEnum.serialType.SERIAL_ADD.getCode(), remark);
                    if (flag == 1) {
                        succeedlist.add("第" + row + "行导入成功");
                        betaAccountImportRecord = new BetaAccountImportRecord();
                        betaAccountImportRecord.setId(id);
                        betaAccountImportRecord.setImportStauts(BetaEnum.importStauts.succeed.getCode());
                        betaAccountImportRecordService.updateById(betaAccountImportRecord);
                    } else {
                        errorlist.add("第" + row + "行导入失败");
                    }
                } catch (Exception e) {
                    log.error("第" + row + "行未知错误" + e);
                    errorlist.add("第" + row + "行导入失败,未知错误" + e);
                }
            }
        }
//            });
        return WrapMapper.ok("导入成功</br>异常信息:" + errorlist.toString());
    }
}
