package com.zbkblog.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by zhangbokang on 2017/6/23.
 */
public class Web {
    static {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("properties/application.properties");
            properties.load(fileInputStream);
            jsLoadDomain = properties.getProperty("jsLoadDomain");
        } catch (Exception e) {
            e.printStackTrace();
            jsLoadDomain = "localhost:8080";
        }
    }
    //web中用的一些常量
    public static String jsLoadDomain;
}
