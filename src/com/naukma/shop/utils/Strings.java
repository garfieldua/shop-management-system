package com.naukma.shop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Strings {
	private static Properties defaultProps = new Properties();
	static {
		try {
			FileInputStream in = new FileInputStream(new File("resources/strings.properties"));
			defaultProps.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return defaultProps.getProperty(key);
	}
}