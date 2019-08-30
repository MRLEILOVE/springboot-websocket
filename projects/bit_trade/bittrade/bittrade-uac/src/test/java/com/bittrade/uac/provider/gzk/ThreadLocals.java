package com.bittrade.uac.provider.gzk;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadLocals {
	
	private static ThreadLocal<Connection> tc = new ThreadLocal<Connection>();
	
	public static Connection openConnection() throws SQLException {
		Connection connection = tc.get();
		if(connection == null) {
			tc.set(  null );
		}
		return (Connection) connection;
	}
	
	public static void closeConnection() throws SQLException {
		openConnection().close();
		tc.remove();
	}

}
