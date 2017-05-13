package com.digit.ncs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.digit.ncs.setting.Config;
import com.digit.ncs.setting.dao.DataBaseDao;
import com.digit.ncs.setting.dao.TableDao;
import com.digit.ncs.setting.jdbc.DBCon;
import com.digit.ncs.setting.jdbc.JdbcUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataBaseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void aTestDBConnection() {
		Assert.assertNotNull(DBCon.getConnection());
	}

	@Test
	public void bTestDBExists() throws SQLException {

		DataBaseDao.getInstance().createDatabase();
		DataBaseDao.getInstance().selectUseDatabase();
		System.out.println();
	}

	@Test
	public void cTestEmployeeTableExists() {

		DataBaseDao.getInstance().setForeignKeyCheck(0);

		TableDao dao = TableDao.getInstance();
		dao.createTable(Config.CREATE_SQL[2]);

		DataBaseDao.getInstance().setForeignKeyCheck(1);

		tableExist(Config.TABLE_NAME[2]);
	}



	@Test
	public void dTestDepartmentTableExists() {

		TableDao dao = TableDao.getInstance();
		dao.createTable(Config.CREATE_SQL[1]);

		tableExist(Config.TABLE_NAME[1]);
	}

	@Test
	public void eTestTitleTableExists() {

		TableDao dao = TableDao.getInstance();
		dao.createTable(Config.CREATE_SQL[0]);

		tableExist(Config.TABLE_NAME[0]);
	}
	
	private void tableExist(String tblName) {

		String sql =

				"SELECT 1 AS flag "
						+ "FROM information_schema.tables "
						+ "WHERE "
						+ "TABLE_NAME = '"
						+ tblName
						+ "' AND TABLE_SCHEMA = 'ncs_erp_jsh'";

		Connection con = DBCon.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Assert.assertEquals(1, rs.getInt("flag"));
				
				System.out.println(
						rs.getInt("flag") == 1 ? "TABLE " + tblName + " EXISTS" : "TABLE " + tblName + " NOT EXISTS");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

}
