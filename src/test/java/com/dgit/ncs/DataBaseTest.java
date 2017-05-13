package com.dgit.ncs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.dgit.ncs.setting.Config;
import com.dgit.ncs.setting.dao.DataBaseDao;
import com.dgit.ncs.setting.dao.TableDao;
import com.dgit.ncs.setting.jdbc.DBCon;
import com.dgit.ncs.setting.jdbc.JdbcUtil;

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

		DataBaseDao.getInstance().createDB();
		try {
			DataBaseDao.getInstance().useDB();
			System.out.println("\nJUnit TEST:");
			System.out.printf(Config.LOG_SPACE, "DATABASE", Config.DB_NAME, "EXIST");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "DATABASE", Config.DB_NAME, "NOT EXIST");
			throw new SQLException();
		}
		System.out.println("---------------------------------------------------------------------");
	}

	@Test
	public void cTestEmployeeTableExists() throws SQLException {

		DataBaseDao.getInstance().setFKeyCheck(0);

		TableDao dao = TableDao.getInstance();
		dao.createTable(Config.CREATE_SQL[2]);

		DataBaseDao.getInstance().setFKeyCheck(1);

		// EmployeeTable
		tableExist(Config.TABLE_NAME[2]);
		System.out.println("---------------------------------------------------------------------");
	}

	@Test
	public void dTestDepartmentTableExists() throws SQLException {
		TableDao dao = TableDao.getInstance();
		dao.createTable(Config.CREATE_SQL[1]);

		// DepartmentTable
		tableExist(Config.TABLE_NAME[1]);
		System.out.println("---------------------------------------------------------------------");
	}

	@Test
	public void eTestTitleTableExists() throws SQLException {
		TableDao dao = TableDao.getInstance();
		dao.createTable(Config.CREATE_SQL[0]);

		// TitleTable
		tableExist(Config.TABLE_NAME[0]);
		System.out.println("---------------------------------------------------------------------");
	}

	private void tableExist(String tblName) throws SQLException {
		System.out.println("\nJUnit TEST:");
		String sql =

				"SELECT 1 AS flag FROM information_schema.tables "
						+ "WHERE TABLE_NAME = '"
						+ tblName
						+ "' AND TABLE_SCHEMA = '"
						+ Config.DB_NAME
						+ "'";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getInt("flag") == 1) {

					Assert.assertEquals(1, rs.getInt("flag"));
					System.out.printf(Config.LOG_SPACE, "TABLE", tblName, "EXIST");

				} else throw new SQLException();

			} else throw new SQLException();

		} catch (SQLSyntaxErrorException e) {
			System.err.printf(Config.LOG_SPACE, "ERROR", "SQL For TABLE EXIST", sql);
			throw new SQLSyntaxErrorException();

		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "TABLE", tblName, "NOT EXIST");
			throw new SQLException();

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

		}
	}

}
