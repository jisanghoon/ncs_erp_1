package com.dgit.ncs.setting.service;

import java.sql.SQLException;

import com.dgit.ncs.setting.Config;

public abstract class ServiceSetting {

	protected static String getFilePathForImport(String tableName, boolean isImport) {
		StringBuilder sb = new StringBuilder();
		sb.append(isImport ? Config.IMPORT_DIR : Config.MYSQL_EXPORT_PATH).append(tableName).append(".txt");
		return sb.toString().replace("\\", "/");
	}

	protected static String getFilePathForExport(String tableName, boolean isImport) {
		StringBuilder sb = new StringBuilder();
		sb.append(isImport ? Config.EXPORT_DIR : Config.MYSQL_EXPORT_PATH).append(tableName).append(".txt");
		return sb.toString().replace("\\", "/");
	}

	public abstract void initSetting() throws SQLException;
}
