package com.digit.ncs;

import org.junit.Test;	

import com.digit.ncs.setting.jdbc.DBCon;

public class aTestDBConnection {

	@Test
	public void testGetInstance() {
		DBCon.getConnection();
	}
	
	@Test
	public void testCloseConnection() {
		DBCon.closeConnection();
	}

}
