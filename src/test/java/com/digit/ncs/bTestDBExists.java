package com.digit.ncs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.digit.ncs.setting.dao.DataBaseDao;

public class bTestDBExists {
	DataBaseDao dbDAO;
	@Before
	public void setUp() throws Exception {
		dbDAO=DataBaseDao.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateDatabase() {
		//fail("Not yet implemented");
		dbDAO.createDatabase();
		
	}

	@Test
	public void testDropDatabase() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSelectUseDatabase() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetForeignKeyCheck() {
		//fail("Not yet implemented");
	}

}
