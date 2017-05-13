package com.dgit.ncs.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dgit.ncs.setting.Config;
import com.dgit.ncs.setting.jdbc.DBCon;
import com.dgit.ncs.setting.jdbc.JdbcUtil;

public class TableDao {
	private PreparedStatement pstmt;

	private static TableDao instance = new TableDao();

	private TableDao() {
	}

	public static TableDao getInstance() {
		return instance;
	}

	public void createTable(String sql) {
		Connection con = DBCon.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.printf(Config.LOG_SPACE, "CREATE TABLE", sql.substring(13, sql.indexOf("(")), "Success!");
		} catch (SQLException e) {
			System.err.printf(Config.LOG_SPACE, "CREATE TABLE", sql.substring(13, sql.indexOf("(")), "Fail!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

	}

}
