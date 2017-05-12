package com.digit.ncs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.digit.ncs.setting.dao.DataBaseDao;
import com.digit.ncs.setting.jdbc.DBCon;

public class DataBaseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void aTestDBConnection() {
		//fail("Not yet implemented");
		DBCon.getConnection();
	}

	@Test
	public void bTestDBExists() {
		//fail("Not yet implemented");
		DataBaseDao.getInstance().createDatabase();
	}

	@Test
	public void cTestEmployeeTableExists() {
		//fail("Not yet implemented");
		
	}

	@Test
	public void dTestDepartmentTableExists() {
		//fail("Not yet implemented");
		
	}

	@Test
	public void eTestTitleTableExists() {
		//fail("Not yet implemented");
		
	}

}
