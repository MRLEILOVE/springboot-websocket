package com.bittrade.svc;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.svc.base.BaseTester;

public class EntrustTester extends BaseTester {

	@Autowired
	ITEntrustService es;

	@Test
	public void test() {
		TEntrust entrustQuery = new TEntrust();
		entrustQuery.in( TEntrust.FieldNames.STATUS, new Object[] { EntrustStatusEnumer.UNFINISH.getCode(), EntrustStatusEnumer.PART_FINISH.getCode() } );
		// entrustQuery.in( TEntrust.FieldNames.STATUS, new Object[] { '1', 2 } );
		// entrustQuery.in( TEntrust.FieldNames.STATUS, new ArrayList<Integer>() {
		// private static final long serialVersionUID = 1L;
		// {
		// add( 1 );
		// add( 2 );
		// }
		// });
		// PageDTO<TEntrust> pageDTO = es.getsByPagination( entrustQuery );
		// System.out.println( "pageDTO=" + pageDTO );
		
		List<TEntrust> list_ent = es.getsBy( entrustQuery );
		System.out.println( "list_ent.size()=" + list_ent.size() );
		
//		TEntrust entrust = es.getById( 1L );
//		System.out.println( "entrust=" + entrust );
//		TEntrust entrust2 = es.getByPK( 1 );
//		System.out.println( "2 entrust2=" + entrust2 );
	}
	
	@Test
	public void add() {
	}
	
	@Test
	public void getsByPage() {
		this.es.testByPage();
	}
	
	@Test
	public void testByDTO() {
		this.es.testByDTO();
	}

}
