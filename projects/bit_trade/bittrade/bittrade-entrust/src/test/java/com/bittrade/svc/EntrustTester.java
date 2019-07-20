package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.svc.base.BaseTester;

public class EntrustTester extends BaseTester {

	@Autowired
	ITEntrustService es;

	@Test
	public void test() {
		TEntrust model = new TEntrust();
		model.in( TEntrust.FieldNames.STATUS, new Object[] { 1, 2 } );
		// model.in( TEntrust.FieldNames.STATUS, new Object[] { '1', 2 } );
		// model.in( TEntrust.FieldNames.STATUS, new ArrayList<Integer>() {
		// private static final long serialVersionUID = 1L;
		// {
		// add( 1 );
		// add( 2 );
		// }
		// });
		// PageDTO<TEntrust> pageDTO = es.getsByPagination( model );
		// System.out.println( "pageDTO=" + pageDTO );
		TEntrust entrust = es.getById( 1L );
		System.out.println( "entrust=" + entrust );
		TEntrust entrust2 = es.getByPK( 1 );
		System.out.println( "2 entrust2=" + entrust2 );
	}
	
	@Test
	public void add() {
	}

}
