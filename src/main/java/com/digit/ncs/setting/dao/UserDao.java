package com.digit.ncs.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.digit.ncs.setting.Config;
import com.digit.ncs.setting.jdbc.DBCon;
import com.digit.ncs.setting.jdbc.JdbcUtil;

public class UserDao {
	private static UserDao instance = new UserDao();

	private UserDao() {
	}

	public static UserDao getInstance() {
		return instance;
	}

	public void initUser() {
		createUser();
		grantUser();
		// createUserAndGrant();
	}

	private void createUser() {
		String sql = "create user ? identified by ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.setString(2, Config.PJT_PASSWD);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "Create User", Config.PJT_USER, "Success!");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1396) {
				System.err.printf(Config.LOG_SPACE, "User", Config.PJT_USER, "EXIST!");
				dropUser();
				createUser();
			} else System.err.printf(Config.LOG_SPACE, "Create User", Config.PJT_USER, "Fail!");
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	/* 계정 삭제 */
	public void dropUser() {
		String sql = "drop user ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.executeUpdate();
			System.out.printf(Config.LOG_SPACE, "Drop User", Config.PJT_USER, "Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "Drop User", Config.PJT_USER, "Fail!");

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	/*
	 * 계정에 대한 권한 Config.DB_NAME에 해당하는 데이터베이스만 select, insert update, delete권한 만
	 * 부여
	 */
	public void grantUser() {
		String sql = "grant select, insert, update, delete on " + Config.DB_NAME + ".* to ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.executeUpdate();
			System.out.printf(Config.LOG_SPACE, "Grant User", Config.PJT_USER, "Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "Grant User", Config.PJT_USER, "Fail!");
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	/*
	 * 계정추가및 해당 권한을 원샷~~!!
	 * 
	 */
	private void createUserAndGrant() {
		String sql = "grant select, insert, update, delete on " + Config.DB_NAME + ".* to ? identified by ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.setString(2, Config.PJT_PASSWD);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "Create User", Config.PJT_USER, "Success!");
			System.out.printf(Config.LOG_SPACE, "Grant User", Config.PJT_USER, "Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "Create User", Config.PJT_USER, "Fail!");
			System.err.printf(Config.LOG_SPACE, "Grant User", Config.PJT_USER, "Fail!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
