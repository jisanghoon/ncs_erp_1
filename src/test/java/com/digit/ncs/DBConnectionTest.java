package com.digit.ncs;

import org.junit.Test;

import com.digit.ncs.setting.jdbc.DBCon;

public class DBConnectionTest {

	@Test
	public void testGetConnection() {
		DBCon.getConnection();
	}

	@Test
	public void testCloseConnection() {
		DBCon.closeConnection();
	}

}
