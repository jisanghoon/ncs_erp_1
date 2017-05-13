package com.dgit.ncs.setting.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.dgit.ncs.setting.Config;
import com.dgit.ncs.setting.dao.DataBaseDao;
import com.dgit.ncs.setting.jdbc.DBCon;
import com.dgit.ncs.setting.jdbc.JdbcUtil;
import com.dgit.ncs.util.MFileUtil;

public class ExportSettingService extends ServiceSetting {

	@Override
	public void initSetting() throws SQLException {

		DataBaseDao dao = DataBaseDao.getInstance();
		dao.useDB();

		MFileUtil.makeDir(Config.EXPORT_DIR);

		for (String tableName : Config.TABLE_NAME) {
			exeExportData(getFilePathForExport(tableName, false), tableName);
			MFileUtil.move(getFilePathForExport(tableName, false), getFilePathForExport(tableName, true));
		}

	}

	public void exeExportData(String tablePath, String tableName) {

		String sql = String.format(
				"select * into outfile '%s' "
						+ "character set 'UTF8' "
						+ "fields TERMINATED by ',' "
						+ "LINES TERMINATED by '\n' from %s",
				tablePath, tableName);

		Statement stmt = null;
		try {

			Connection con = DBCon.getConnection();
			stmt = con.createStatement();
			stmt.executeQuery(sql);
			System.out.printf(Config.LOG_SPACE, "Export Table", tableName, stmt.getUpdateCount() + " Rows " + "Success!");

		} catch (SQLException e) {
			System.out.printf(Config.LOG_SPACE, "ERROR", e.getErrorCode(), e.getMessage());

		} finally {
			JdbcUtil.close(stmt);
		}
	}
}
