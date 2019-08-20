package com.wallet.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.mapper.PersonalCardMapper;
import com.wallet.biz.pojo.PersonalCard;
import com.wallet.biz.service.PersonalCardService;

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
