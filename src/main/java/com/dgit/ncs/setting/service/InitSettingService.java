package com.dgit.ncs.setting.service;

import java.sql.SQLException;

import com.dgit.ncs.setting.Config;
import com.dgit.ncs.setting.dao.DataBaseDao;
import com.dgit.ncs.setting.dao.TableDao;
import com.dgit.ncs.setting.dao.UserDao;

public class InitSettingService extends ServiceSetting {

	public void initSetting() throws SQLException {

		createDataBase(); // 데이터베이스를 생성
		createTable(); // 해당 데이터베이스에서 테이블 생성
		createUser(); // 해당 데이터베이스 사용자 추가
	}

	private void createDataBase() throws SQLException {

		DataBaseDao dao = DataBaseDao.getInstance();
		dao.createDB();
		dao.useDB();
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
