/*package com.digit.ncs;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.digit.ncs.setting.dao.DataBaseDao;

public class DataBaseDaoTest {
	DataBaseDao dao;

	@Before
	public void setUp() throws Exception {
		dao = DataBaseDao.getInstance();
	}
	
	@Test
	public void testGetInstance() {
		dao = DataBaseDao.getInstance();
		Assert.assertNotNull(dao);
	}

	@Test
	public void testCreateDatabase() {
		try{
			dao.createDatabase();	
		}catch(SQLException e){
			fail("Can't Creat DB");
		}
	}

	@Test
	public void testSelectUseDatabase() {
		try{
			dao.selectUseDatabase();	
		}catch(SQLException e){
			fail("Can't Use DB");
		}
	}

	@Test
	public void testDropDatabase() {
		try{
			dao.dropDatabase();	
		}catch(SQLException e){
			fail("Can't Drop DB");
		}
	}

}
*/