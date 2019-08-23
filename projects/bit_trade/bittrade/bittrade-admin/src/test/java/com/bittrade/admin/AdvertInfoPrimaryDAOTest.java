package com.bittrade.admin;

import com.bittrade.admin.dao.c2c.AdvertInfoPrimaryDAO;
import com.bittrade.admin.model.domain.AdvertInfoPageDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xzc
 * @date 2019-08-22 18:03
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AdminApplication.class)
public class AdvertInfoPrimaryDAOTest {

    @Autowired
    private AdvertInfoPrimaryDAO advertInfoPrimaryDAO;

    @Test
    public void findPageList() {
        List<AdvertInfoPageDo> pageList = advertInfoPrimaryDAO.findPageList(null);
        pageList.forEach(System.out::println);
    }
}