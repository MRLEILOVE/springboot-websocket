package com.walletbiz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walletbiz.mapper.PersonalCardMapper;
import com.walletbiz.pojo.PersonalCard;
import com.walletbiz.service.PersonalCardService;

import lombok.extern.slf4j.Slf4j;


/**
 * <p>
 * 用户收款信息表 服务实现类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
@Service
@Slf4j
public class PersonalCardServiceImpl extends ServiceImpl<PersonalCardMapper, PersonalCard> implements PersonalCardService {


}
