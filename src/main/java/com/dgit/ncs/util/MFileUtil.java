package com.dgit.ncs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dgit.ncs.setting.Config;

public class MFileUtil {

	// 파일이동
	public static void move(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			// 복사한뒤 원본파일을 삭제함
			delete(inFileName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 파일삭제
	public static void delete(String fileFullPath) {
		File file = new File(fileFullPath);
		if (file.exists()) {
			file.delete();
		}
	}

	public static void makeDir(String exportDir) {
		File backupDir = new File(exportDir);
		if (backupDir.exists()) {
			backupDir.delete();
			backupDir.mkdir();
		} else {
			backupDir.mkdir();
		}
		System.out.printf(Config.LOG_SPACE, "Make Dir", Config.EXPORT_DIR, "Success!");
	}
}
