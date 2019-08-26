package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.ContractEnum;
import com.jdcloud.base.enums.ErrorCodeEnum;
import com.jdcloud.base.exception.BusinessException;
import com.jdcloud.provider.dto.PersonalAccountDto;
import com.jdcloud.provider.mapper.PersonalAccountMapper;
import com.jdcloud.provider.pojo.PersonalAccount;
import com.jdcloud.provider.pojo.PersonalAccountRecord;
import com.jdcloud.provider.pojo.vo.PersonalAccountListVo;
import com.jdcloud.provider.service.PersonalAccountRecordService;
import com.jdcloud.provider.service.PersonalAccountService;
import com.jdcloud.util.date.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * 我的资产表 服务实现类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
@Service
@Slf4j
public class PersonalAccountServiceImpl extends ServiceImpl<PersonalAccountMapper, PersonalAccount> implements PersonalAccountService {

    @Resource
    private PersonalAccountMapper personalAccountMapper;
    @Autowired
    private PersonalAccountRecordService personalAccountRecordService;
    @Autowired
    private PersonalAccountService personalAccountService;
    /**
     * 更新用户资产
     * @param account
     */
    @Override
    public void updatePersonalAccount(PersonalAccount account) {
        account.setUpdateTime(new Date());
        baseMapper.updatePersonalAccount(account);
    }

    /**
     * 更新用户资产_新
     *
     * @param account
     */
    @Override
    public boolean updatePersonalAccountNew(PersonalAccount account) {
        account.setUpdateTime(new Date());
        boolean bo = baseMapper.updatePersonalAccountNew(account);
        return bo;
    }

    @Override
    public Page<PersonalAccountListVo> selectPersonalAccountListPage(Page<PersonalAccountListVo> page, PersonalAccountDto dto) {
        page.setRecords( personalAccountMapper.selectPersonalAccountListPage(page, dto ) );
        return page;
    }

    /**
     * 只增加流水
     */
    @Override
    @Transactional
    public void personalAccountAdd(Long userId, Integer productType, BigDecimal registerBitt, Integer serialType, String remark, Integer entrustId) {
        PersonalAccount accountPam = new PersonalAccount();
        accountPam.setUserId(userId);
        accountPam.setProductType(productType);
        PersonalAccount accountVo = baseMapper.selectOne(accountPam);
        BigDecimal afterAmount = accountVo.getBalance();
        if (BigDecimal.ZERO.compareTo(registerBitt) > 0) {
            log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + registerBitt);
            throw new RuntimeException("操作金额不能为负数");
        }
        if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
            afterAmount = afterAmount.add(registerBitt);
        } else {
            registerBitt = BigDecimal.ZERO.subtract(registerBitt);
            afterAmount = afterAmount.add(registerBitt);
        }

        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        PersonalAccountRecord record = new PersonalAccountRecord();

        record.setEntrustId(entrustId);
        record.setUserId(accountVo.getUserId());
        record.setBeforeAmount(accountVo.getBalance());
        record.setAmount(registerBitt);
        record.setAfterAmount(afterAmount);
        record.setCreateTime(new Date());
        record.setPeraccountId(accountVo.getId());
        record.setRemark(remark);
        record.setProductType(productType);
        record.setRecordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep);
        boolean row = personalAccountRecordService.insert(record);
        if (!row) {
            log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + row);
            throw new RuntimeException("操作过于频繁，请稍后再试");
        }
    }



    /**
     * 增加事务 走新接口
     *
     * @param userId       用户id
     * @param productType  交易类型
     * @param registerBitt 交易数量
     * @param serialType
     * @param remark       备注
     * @param entrustId    订单id
     *                     c
     *                     2019-6-24
     */
    @Override
    @Transactional
    public void personalAccountAddNew(Long userId, Integer productType, BigDecimal registerBitt, Integer serialType, String remark, Integer entrustId) {
        PersonalAccount accountPam = new PersonalAccount();
        accountPam.setUserId(userId);
        accountPam.setProductType(productType);
        accountPam = baseMapper.selectOne(accountPam);
        // 策略：创建订单 不生成流水只减少余额，增加冻结资金
        BigDecimal afterAmount = accountPam.getBalance().add(accountPam.getUsedMargin());
        if (BigDecimal.ZERO.compareTo(registerBitt) > 0) {
            log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + registerBitt);
            throw new RuntimeException("操作金额不能为负数");
        }
        PersonalAccount personalAccountA = new PersonalAccount();
        if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
            afterAmount = afterAmount.add(registerBitt);
            //修改A的总入金
            BigDecimal countTotalEnterB = accountPam.getTotalEnter().add(registerBitt);
            personalAccountA.setTotalEnter(countTotalEnterB);
            //修改A的资产余额
            BigDecimal countBalance = accountPam.getBalance().add(registerBitt);
            personalAccountA.setBalance(countBalance);
            personalAccountA.setVersion(accountPam.getVersion());
            personalAccountA.setId(accountPam.getId());
            boolean row = personalAccountService.updatePersonalAccountNew(personalAccountA);
            if (!row) {
                log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + row);
                throw new BusinessException(ErrorCodeEnum.BIZERROR2);
            }
        } else {
            registerBitt = BigDecimal.ZERO.subtract(registerBitt);
            afterAmount = afterAmount.add(registerBitt);

            // 增加总出金 减少冻结资金
            BigDecimal countMargin = accountPam.getUsedMargin().add(registerBitt);
            personalAccountA.setUsedMargin(countMargin);
            BigDecimal countTotalExitA = accountPam.getTotalExit().add(registerBitt);
            personalAccountA.setTotalExit(countTotalExitA);
            personalAccountA.setId(accountPam.getId());
            personalAccountA.setVersion(accountPam.getVersion());
            boolean row = personalAccountService.updatePersonalAccountNew(personalAccountA);
            if (!row) {
                log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + row);
                throw new BusinessException(ErrorCodeEnum.BIZERROR2);
            }
        }
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        PersonalAccountRecord record = new PersonalAccountRecord();

        record.setEntrustId(entrustId);
        record.setUserId(accountPam.getUserId());
        record.setBeforeAmount(accountPam.getBalance().add(accountPam.getUsedMargin())); // 交易前金额
        record.setAmount(registerBitt);
        record.setAfterAmount(afterAmount);
        record.setCreateTime(new Date());
        record.setPeraccountId(accountPam.getId());
        record.setRemark(remark);
        record.setProductType(productType);
        record.setRecordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep);
        boolean row = personalAccountRecordService.insert(record);
        if (!row) {
            log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + row);
            throw new BusinessException(ErrorCodeEnum.BIZERROR2);
        }
    }


    /**
     * 取消放币接口_新的 只增加流水
     *        增加一个 entrustId
     *        增加到流水中
     * @param userId
     * @param productType
     * @param registerBitt
     * @param serialType
     * @param remark
     */
    @Override
    @Transactional
    public void updatePersonalAccountNew(Long userId, Integer productType, BigDecimal registerBitt, Integer serialType,
                                         String remark,Integer entrustId) {
        PersonalAccount accountPam = new PersonalAccount();
        accountPam.setUserId(userId);
        accountPam.setProductType(productType);
        PersonalAccount accountVo = baseMapper.selectOne(accountPam);
        BigDecimal afterAmount = accountVo.getBalance();
        if (BigDecimal.ZERO.compareTo(registerBitt) > 0) {
            log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + registerBitt);
            throw new RuntimeException("操作金额不能为负数");
        }
        if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
            afterAmount = afterAmount.add(registerBitt);
            accountVo.setTotalEnter(accountVo.getTotalEnter().add(registerBitt));
        } else {
            registerBitt = BigDecimal.ZERO.subtract(registerBitt);
            afterAmount = afterAmount.add(registerBitt);
            accountVo.setTotalExit(accountVo.getTotalExit().add(registerBitt));
        }
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        PersonalAccountRecord record = new PersonalAccountRecord();

        record.setEntrustId(entrustId);
        record.setUserId(accountVo.getUserId());
        record.setBeforeAmount(accountVo.getBalance());
        record.setAmount(registerBitt);
        record.setAfterAmount(afterAmount);
        record.setCreateTime(new Date());
        record.setPeraccountId(accountVo.getId());
        record.setRemark(remark);
        record.setProductType(productType);
        record.setRecordNumber("BI" + DateTimeUtils.getCurrentFullDateTime() + sleep);
        boolean row = personalAccountRecordService.insert(record);
        if (!row) {
            log.error("BETAACCOUNTSERVICEIMPL.UPDATEBETAACCOUNTBYID.INSERT.RESULT.ROW=" + row);
            throw new RuntimeException("操作过于频繁，请稍后再试");
        }
        accountVo.setBalance(afterAmount);
        accountVo.setUpdateTime(new Date());
        baseMapper.updatePersonalAccount(accountVo);
    }


}
