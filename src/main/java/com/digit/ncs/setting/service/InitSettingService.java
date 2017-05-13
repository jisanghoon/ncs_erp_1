package com.digit.ncs.setting.service;

import java.sql.SQLException;

import com.digit.ncs.setting.Config;
import com.digit.ncs.setting.dao.DataBaseDao;
import com.digit.ncs.setting.dao.TableDao;
import com.digit.ncs.setting.dao.UserDao;

public class InitSettingService extends ServiceSetting {

	public void initSetting() throws SQLException {

		createDataBase(); // 데이터베이스를 생성
		createTable(); // 해당 데이터베이스에서 테이블 생성
		createUser(); // 해당 데이터베이스 사용자 추가
	}

	private void createDataBase() throws SQLException {

		DataBaseDao dao = DataBaseDao.getInstance();
		dao.createDatabase();
		dao.selectUseDatabase();
	}

	private void createTable() {

		TableDao dao = TableDao.getInstance();
		for (int i = 0; i < Config.TABLE_NAME.length; i++) {
			dao.createTable(Config.CREATE_SQL[i]);
		}
	}

	private void createUser() {
		UserDao.getInstance().initUser();
	}

}
