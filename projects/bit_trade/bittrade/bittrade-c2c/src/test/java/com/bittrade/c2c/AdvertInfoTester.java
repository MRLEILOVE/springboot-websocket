package com.bittrade.c2c;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bittrade.c2c.service.impl.TAdvertInfoServiceImpl;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.QueryAdvertVO;
import com.core.common.DTO.PageDTO;
import com.core.web.constant.entity.LoginUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { C2CApplication.class })
public class AdvertInfoTester extends com.core.framework.BaseTest {

	@Autowired
	TAdvertInfoServiceImpl advertInfoServiceImpl;
	
	@Test
	public void test() {
		System.out.println("advertInfoServiceImpl=" + advertInfoServiceImpl);
		PageDTO<TAdvertInfoDTO> pageDTO = new PageDTO<TAdvertInfoDTO>(1, 2);
		QueryAdvertVO queryAdvertVO = new QueryAdvertVO();
		queryAdvertVO.setAdvertType(TAdvertInfoDTO.AdvertTypeEnum.BUY.getCode());
		queryAdvertVO.setOnlyShowCanTransaction(true);
		LoginUser loginUser = new LoginUser();
		advertInfoServiceImpl.listAdverts(pageDTO, queryAdvertVO, loginUser);
		System.out.println("pageDTO=" + pageDTO);
		System.out.println("pageDTO.getData()=" + pageDTO.getData());
	}
	
	@Test
	public void testWithModel() {
		System.out.println("advertInfoServiceImpl=" + advertInfoServiceImpl);
		TAdvertInfo queryAdvertInfo = new TAdvertInfo();
		queryAdvertInfo.setCoinId(1L);
		queryAdvertInfo.eq(TAdvertInfo.FieldNames.COIN_ID, 1);
		queryAdvertInfo.ge(TAdvertInfo.FieldNames.TYPE, 0);
		PageDTO<TAdvertInfo> pageDTO = advertInfoServiceImpl.getsByPage(queryAdvertInfo, 1, 3);
		System.out.println("pageDTO=" + pageDTO);
		System.out.println("pageDTO.getData()=" + pageDTO.getData());
	}

}
