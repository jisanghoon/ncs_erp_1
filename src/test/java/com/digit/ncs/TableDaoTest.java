package com.digit.ncs;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.digit.ncs.setting.Config;
import com.digit.ncs.setting.dao.DataBaseDao;
import com.digit.ncs.setting.dao.TableDao;

public class TableDaoTest {
	TableDao dao;

	@Before
	public void setUp() throws Exception {
		DataBaseDao.getInstance().selectUseDatabase();
		dao = TableDao.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateTable() {
/*		String[] TableSQL = Config.CREATE_SQL;

		for (int i = 0; i < TableSQL.length; i++) {
			try {
				dao.createTable(TableSQL[i]);
			} catch (SQLException e) {
				fail("Can't Creat " + Config.TABLE_NAME[i] + " Table");
			}
		}*/
	}

}
