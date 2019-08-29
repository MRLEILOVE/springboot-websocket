package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.model.dto.*;
import com.bittrade.uac.model.enums.MessageTemplateEnum;
import com.bittrade.uac.model.enums.SendTypeEnum;
import com.bittrade.uac.model.enums.UserEnum;
import com.bittrade.uac.model.vo.*;
import com.bittrade.uac.service.PersonalAccountService;
import com.bittrade.uac.service.RecordLogService;
import com.bittrade.uac.service.UacUserService;
import com.core.web.constant.entity.LoginUser;
import com.core.web.tool.WebUtil;
import com.google.common.base.Preconditions;
import com.bittrade.uac.mapper.UserMapper;
import com.bittrade.uac.model.pojo.User;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.bittrade.uac.model.enums.SendTypeEnum.FIND_PWD;
import static com.bittrade.uac.model.enums.SendTypeEnum.REGISTER;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description:
 **/
@Service
public class UacUserServiceImpl extends ServiceImpl<UserMapper, User> implements UacUserService {

    @Autowired
    private UacUserService uacUserService;
    @Autowired
    private C5SmsService c5SmsService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RecordLogService recordLogService;
    @Autowired
    private PersonalAccountService personalAccountService;
    /**
     * 正则表达式匹配
     */
    private static final Pattern compile = Pattern.compile("^[-\\+]?[\\d]*$");

    @Override
    public boolean hasMobile(String mobile) {
        User user = new User();
        user.setTelePhone(mobile);
        int count = uacUserService.count(Wrappers.lambdaQuery(user));
        return count > 0;
    }

    @Override
    public void sendSms(SendSmsVo sendSmsVo) {
        Integer sendType = sendSmsVo.getSendType();
        String phoneNumber = sendSmsVo.getPhoneNumber();
        String areaCode = sendSmsVo.getAreaCode();

        SendTypeEnum sendTypeEnum = SendTypeEnum.getEnumByCode(sendType);
        Preconditions.checkArgument(Objects.nonNull(phoneNumber), "手机号码有误！phone:" + phoneNumber);
        Preconditions.checkArgument(Objects.nonNull(sendTypeEnum), "不允许发送此类型的短信,type:" + sendType);

        MessageTemplateEnum msTemplate = null;
        switch (sendTypeEnum) {
            case REGISTER:
                //验证手机号是否重复
                if (this.hasMobile(phoneNumber)) {
                    throw new RuntimeException("手机号码已存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            case FIND_PWD:
                //验证是否存在此手机号
                if (!this.hasMobile(phoneNumber)) {
                    throw new RuntimeException("手机号码不存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            case CHANGE_PAY_PWD:
                //验证是否存在此手机号
                if (!this.hasMobile(phoneNumber)) {
                    throw new RuntimeException("手机号码不存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            case CHANGE_PAYMENT_INFORMATION:
                //验证是否存在此手机号
                if (!this.hasMobile(phoneNumber)) {
                    throw new RuntimeException("手机号码不存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            default:
                throw new RuntimeException("不允许发送此类型的短信！");

        }
        //验证码存储key
        String redisKey = sendTypeEnum.getValue() + ":" + phoneNumber;

        // 间隔时间之内不允许发送多次
        Preconditions.checkArgument(!redisTemplate.hasKey(redisKey + "_interval"), "获取验证码过于频繁，请稍后再试！");

        String code = (String) redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(code)) {
            code = RandomStringUtils.randomNumeric(6);
        }

        // 记录两次发短信的间接
        redisTemplate.opsForValue().set(redisKey + "_interval", code, 120);
        // 记录KEY
        redisTemplate.opsForValue().set(redisKey, code, 600);
        //删除验证 验证码错误次数
        redisTemplate.delete(redisKey + "_error_count");

        String content = String.format(msTemplate.getLocation(), code);
        // 发送短信
        SendSmsDto sendSmsDto = new SendSmsDto();
        sendSmsDto.setAreaCode(areaCode);
        sendSmsDto.setPhoneNumber(phoneNumber);
        sendSmsDto.setContent(content);
        c5SmsService.send(sendSmsDto);
        //保存日志
        recordLogService.save(sendSmsDto, msTemplate);
    }


    @Override
    public void sendEmail(SendEmailVo emailVo) {
        String email = emailVo.getEmail();
        Integer sendType = emailVo.getSendType();

        SendTypeEnum sendTypeEnum = SendTypeEnum.getEnumByCode(sendType);
        Preconditions.checkArgument(Objects.nonNull(email), "邮箱有误！phone:" + email);
        Preconditions.checkArgument(Objects.nonNull(sendTypeEnum), "不允许发送此类型的短信,type:" + sendType);

        MessageTemplateEnum msTemplate = null;
        switch (sendTypeEnum) {
            case REGISTER:
                //验证手机号是否重复
                if (this.hasEmail(email)) {
                    throw new RuntimeException("邮箱已存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            case FIND_PWD:
                //验证是否存在此手机号
                if (!this.hasEmail(email)) {
                    throw new RuntimeException("邮箱不存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            case CHANGE_PAY_PWD:
                //验证是否存在此手机号
                if (!this.hasEmail(email)) {
                    throw new RuntimeException("邮箱不存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            case CHANGE_PAYMENT_INFORMATION:
                //验证是否存在此手机号
                if (!this.hasEmail(email)) {
                    throw new RuntimeException("邮箱不存在！");
                }
                msTemplate = MessageTemplateEnum.USER_RECEIVING_TEMPLATE;
                break;
            default:
                throw new RuntimeException("不允许发送此类型的邮件！");
        }

        //验证码存储key
        String redisKey = sendTypeEnum.getValue() + ":" + email;

        // 间隔时间之内不允许发送多次
        Preconditions.checkArgument(redisTemplate.hasKey(redisKey + "_interval"), "获取验证码过于频繁，请稍后再试！");

        String code = (String) redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(code)) {
            code = RandomStringUtils.randomNumeric(6);
        }

        // 记录两次发短信的间接
        redisTemplate.opsForValue().set(redisKey + "_interval", code, 120);
        // 记录KEY
        redisTemplate.opsForValue().set(redisKey, code, 600);
        //删除验证 验证码错误次数
        redisTemplate.delete(redisKey + "_error_count");

        String content = String.format(msTemplate.getLocation(), code);
        // 发送邮件
        SendEmailDto emailDto = new SendEmailDto();
        emailDto.setEmail(email);
        emailDto.setContent(content);
        mailService.send(emailDto);
        //保存日志
        recordLogService.save(emailDto, msTemplate);
    }

    /**
     * 校验验证码
     *
     * @param equipmentCode 设备码
     * @param code          验证码
     * @param checkType     验证类型
     * @return
     */
    private void checkCode(String equipmentCode, String code, SendTypeEnum checkType) {
        Preconditions.checkArgument(StringUtils.isNotBlank(equipmentCode), "设备码无效！");
        Preconditions.checkArgument(StringUtils.isNotBlank(code), "验证码无效！");
        Preconditions.checkArgument(Objects.nonNull(checkType), "验证类型无效！");

        String redisKey = checkType.getValue() + ":" + equipmentCode;
        Preconditions.checkArgument(redisTemplate.hasKey(redisKey), "验证码无效！");

        String sendCode = redisTemplate.opsForValue().get("redisKey").toString();
        //如果失败次数超过五次 则从新获取验证码
        if (!StringUtils.equals(code, sendCode)) {
            Integer count = (Integer) redisTemplate.opsForValue().get(redisKey + "_error_count");
            if (Objects.isNull(count)) {
                count = 1;
            }
            if (count >= 5) {
                redisTemplate.delete(redisKey);
                redisTemplate.delete(redisKey + "_error_count");
                redisTemplate.delete(redisKey + "_interval");
            } else {
                redisTemplate.opsForValue().increment(redisKey + "_error_count", count + 1);
            }
            throw new RuntimeException("验证码输错" + count + "次，超过" + 5 + "次，请重新获取！");
        } else {
            redisTemplate.delete(redisKey);
            redisTemplate.delete(redisKey + "_error_count");
            redisTemplate.delete(redisKey + "_interval");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterVo registerVo) {
        //校验参数
        this.checkRegisterParam(registerVo);
        BCryptPasswordEncoder be = new BCryptPasswordEncoder();
        String password = registerVo.getPassword();
        String areaCode = registerVo.getAreaCode();
        String email = registerVo.getEmail();
        String mobile = registerVo.getMobile();
        Integer registerType = registerVo.getRegisterType();
        Date now = new Date();
        String loginName = 0 == registerType.intValue() ? mobile : email;
        Long userId = this.getUserId();
        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPassword(be.encode(password));
        if (0 == registerType.intValue()) {
            user.setPhoneAreaCode(areaCode);
            user.setTelePhone(mobile);
            user.setIsTelValidate(UserEnum.ValidateStatus.VALIDATE_Y.getCode());
            user.setTelValidateTime(now);
        } else if (1 == registerType.intValue()) {
            user.setUserEmail(email);
            user.setIsMailValidate(UserEnum.ValidateStatus.VALIDATE_Y.getCode());
            user.setMailValidateTime(now);
        }
        user.setCreateTime(now);
        user.setId(userId);
        uacUserService.save(user);
        // 注册成，创建C2C账户
        personalAccountService.create(userId);
    }

    @Override
    public boolean hasEmail(String email) {
        User user = new User();
        user.setUserEmail(email);
        int count = uacUserService.count(Wrappers.lambdaQuery(user));
        return count > 0;
    }

    /**
     * 验证注册参数
     *
     * @param registerVo
     */
    private void checkRegisterParam(UserRegisterVo registerVo) {
        //注册类型
        Integer registerType = registerVo.getRegisterType();
        String password = registerVo.getPassword();
        Preconditions.checkArgument(Objects.nonNull(registerType), "注册类型无效！");
        Preconditions.checkArgument(Objects.nonNull(password), "密码无效！");
        String code = registerVo.getCode();
        String equipmentCode = "";
        if (0 == registerType.intValue()) {
            //手机号注册
            String areaCode = registerVo.getAreaCode();
            String mobile = registerVo.getMobile();
            Preconditions.checkArgument(Objects.nonNull(areaCode), "手机号区域码无效！");
            Preconditions.checkArgument(Objects.nonNull(mobile), "手机号码无效！");
            Preconditions.checkArgument(!this.hasMobile(mobile), "手机号码已注册！");
            equipmentCode = mobile;
        } else if (1 == registerType.intValue()) {
            //邮箱注册
            String email = registerVo.getEmail();
            Preconditions.checkArgument(Objects.nonNull(email), "注册邮箱无效！");
            Preconditions.checkArgument(!this.hasEmail(email), "邮箱号已注册！");
            equipmentCode = email;

        } else {
            Preconditions.checkArgument(false, "注册类型无效！");
        }
        this.checkCode(equipmentCode, code, REGISTER);
    }

    @Override
    public void findPwd(UserFindPwdVo findPwdVo) {
        this.checkFindPwdParam(findPwdVo);
        String password = findPwdVo.getPassword();
        String loginName = findPwdVo.getLoginName();
        User condition = new User();
        condition.setLoginName(loginName);
        User user = uacUserService.getOne(Wrappers.lambdaQuery(condition));
        BCryptPasswordEncoder be = new BCryptPasswordEncoder();
        user.setLoginPassword(be.encode(password));
        uacUserService.update(Wrappers.update(user));
    }

    /**
     * 验证找回密码实体参数
     *
     * @param findPwdVo
     */
    private void checkFindPwdParam(UserFindPwdVo findPwdVo) {
        String loginName = findPwdVo.getLoginName();
        String password = findPwdVo.getPassword();
        String code = findPwdVo.getCode();
        Preconditions.checkArgument(Objects.nonNull(loginName), "不存在此帐号！");
        Preconditions.checkArgument(Objects.nonNull(password), "密码无效！");

        User condition = new User();
        condition.setLoginName(loginName);
        User user = uacUserService.getOne(Wrappers.lambdaQuery(condition));

        Preconditions.checkArgument(Objects.nonNull(user), "不存在此帐号！");

        //验证登录名:如果手机号绑定 优先发送短信验证码 ，如果没有手机号 则发送邮箱验证码
        String equipmentCode = Objects.isNull(user.getTelePhone()) ? user.getUserEmail() : user.getTelePhone();
        //校验验证码
        this.checkCode(equipmentCode, code, FIND_PWD);
    }

    @Override
    public void updatePassword(ChangePasswordDto changePasswordDto) {
        LoginUser loginUser = WebUtil.getLoginUser();
        Long userId = loginUser.getUser_id();
        User user = uacUserService.getById(userId);
        BCryptPasswordEncoder be = new BCryptPasswordEncoder();
        if (be.matches(changePasswordDto.getOldPass(), user.getLoginPassword())) {
            user.setLoginPassword(be.encode(changePasswordDto.getNewPass()));
            uacUserService.updateById(user);
        } else {
            throw new RuntimeException("密码错误");
        }
    }

    @Override
    public void sendRetrieve(String loginName) {
        User condition = new User();
        condition.setLoginName(loginName);
        User user = uacUserService.getOne(Wrappers.lambdaQuery(condition));
        Preconditions.checkArgument(Objects.nonNull(user), "不存在此帐号！");

        String mobile = user.getTelePhone();
        String userEmail = user.getUserEmail();
        //验证登录名:如果手机号绑定 优先发送短信验证码 ，如果没有手机号 则发送邮箱验证码
        if (StringUtils.isNotBlank(mobile)) {
            SendSmsVo sendSmsVo = new SendSmsVo();
            sendSmsVo.setAreaCode(user.getPhoneAreaCode());
            sendSmsVo.setPhoneNumber(mobile);
            sendSmsVo.setSendType(FIND_PWD.getCode());
            uacUserService.sendSms(sendSmsVo);
        } else if (StringUtils.isNoneBlank(userEmail)) {
            SendEmailVo sendEmailVo = new SendEmailVo();
            sendEmailVo.setEmail(userEmail);
            sendEmailVo.setSendType(FIND_PWD.getCode());
            uacUserService.sendEmail(sendEmailVo);
        } else {
            throw new RuntimeException("账户存在异常，没绑定手机号码，没绑定邮箱！loginName =" + loginName);
        }
    }


    @Override
    public void checkCode(CheckCodeVo checkCodeVo) {
        String equipmentCode = checkCodeVo.getEquipmentCode();
        String code = checkCodeVo.getCode();
        Integer checkType = checkCodeVo.getCheckType();
        SendTypeEnum sendTypeEnum = SendTypeEnum.getEnumByCode(checkType);
        this.checkCode(equipmentCode, code, sendTypeEnum);
    }

    /**
     * gen用户ID
     *
     * @return
     */
    public Long getUserId() {
        long incrId = redisTemplate.opsForValue().increment("KEY_USER_ID");
        Long uuid = NumberUtils.createLong(RandomStringUtils.randomNumeric(10));
        return incrId + uuid;
    }

    /**
     * 校验推荐码格式
     *
     * @param recommendCode
     * @return
     */
    public Boolean verificationCode(String recommendCode) {
        return recommendCode.length() != 6 || !compile.matcher(recommendCode).matches();
    }

}