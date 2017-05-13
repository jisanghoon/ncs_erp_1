package com.digit.ncs.setting;

public class Config {
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String DB_NAME = "ncs_erp_jsh";
	public static final String PJT_USER = "user_ncs";
	public static final String PJT_PASSWD = "user_ncs";

	public static final String[] TABLE_NAME = {"title","department", "employee"};
	
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
	public static final String MYSQL_EXPORT_PATH = "C:\\ProgramData\\MySQL\\MySQL Server 5.6\\Uploads\\";
	public static final String LOG_SPACE="%-25s %-25s %s %n";
	
	public static final String[] CREATE_SQL ={
			
			"CREATE TABLE title ("
			+ "tcode INT(4)  NOT NULL,	"
			+ "tname VARCHAR(10) null,	"
			+ "primary key (tcode))",
			
			"CREATE TABLE department ("
			+ "`dcode` INT(11)  NOT NULL,"
			+ "`dname` CHAR(10) NOT NULL, "
			+ "`floor` INT(11)  NULL,"
			+ "primary key (dcode)) ",
			
			"CREATE TABLE employee ("
			+ "`eno`      INT(11)     NOT NULL, "
			+ "`ename`    VARCHAR(20) NOT NULL,	"
			+ "`salary`   INT(11)     NULL, "
			+ "`dno`      INT(11)     NULL, "
			+ "`gender`   TINYINT(1)  NULL, "
			+ "`joindate` DATE        NULL, "
			+ "`title` 	  INT(11)     NULL, "
			+ "primary key (eno) ,"
			+ "FOREIGN KEY (dno) REFERENCES department (dcode),"
			+ "FOREIGN KEY (title) REFERENCES title (tcode))"
	};
}
