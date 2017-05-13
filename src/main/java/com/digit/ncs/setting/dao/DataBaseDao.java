package com.digit.ncs.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.digit.ncs.setting.Config;
import com.digit.ncs.setting.jdbc.DBCon;
import com.digit.ncs.setting.jdbc.JdbcUtil;

public class DataBaseDao {
	private PreparedStatement pstmt;

	private static DataBaseDao instance = new DataBaseDao();

	private DataBaseDao() {
	}

	public static DataBaseDao getInstance() {
		return instance;
	}

	public void createDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("CREATE DATABASE " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "CREATE DATABASE", Config.DB_NAME, "Success!");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1007) {
				System.err.printf(Config.LOG_SPACE, "DATABASE", Config.DB_NAME, "EXIST!");
				dropDatabase();
				createDatabase();
			}

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void dropDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("DROP DATABASE IF EXISTS " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "DROP DATABASE", Config.DB_NAME, "Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "DROP DATABASE", Config.DB_NAME, "Fail!");
			e.printStackTrace();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void selectUseDatabase() throws SQLException {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("USE " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "USE DATABASE", Config.DB_NAME, "Selected Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "USE DATABASE", Config.DB_NAME, "Selected Fail!");
			e.printStackTrace();
			throw new SQLException();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void setForeignKeyCheck(int isCheck) {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS = ?");
			pstmt.setInt(1, isCheck);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, Config.DB_NAME, "SET FOREIGN_KEY_CHECKS",
					(isCheck == 0 ? "'False'" : "'True'") + "Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, Config.DB_NAME, "SET FOREIGN_KEY_CHECKS",
					(isCheck == 0 ? "'False'" : "'True'") + "Fail!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

}
