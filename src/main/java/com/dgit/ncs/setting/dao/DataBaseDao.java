package com.dgit.ncs.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dgit.ncs.setting.Config;
import com.dgit.ncs.setting.jdbc.DBCon;
import com.dgit.ncs.setting.jdbc.JdbcUtil;

public class DataBaseDao {
	private PreparedStatement pstmt;

	private static DataBaseDao instance = new DataBaseDao();

	private DataBaseDao() {
	}

	public static DataBaseDao getInstance() {
		return instance;
	}

	public void createDB() {

		try {

			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("CREATE DATABASE " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "CREATE DATABASE", Config.DB_NAME, "Success!");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1007) {
				dropDB();
				createDB();
			}

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void dropDB() {

		try {

			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("DROP DATABASE IF EXISTS " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "DROP DATABASE IF EXISTS", Config.DB_NAME, "Success!");

		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "DROP DATABASE", Config.DB_NAME, "Fail!");
			e.printStackTrace();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void useDB() throws SQLException {

		try {

			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("USE " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "USE ", Config.DB_NAME, "Success!");

		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "USE ", Config.DB_NAME, "Fail!");
			e.printStackTrace();
			throw new SQLException();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public void setFKeyCheck(int isCheck) {
		try {

			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS = ?");
			pstmt.setInt(1, isCheck);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "SET FOREIGN_KEY_CHECKS = " + isCheck, Config.DB_NAME, "Success!");

		} catch (SQLException e) {
			System.out.printf(Config.LOG_SPACE, "SET FOREIGN_KEY_CHECKS = " + isCheck, Config.DB_NAME, "Fail!");
			e.printStackTrace();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

}
