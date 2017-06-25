package com.zbkblog.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhangbokang on 2017/6/23.
 */
public class Web {
    static {
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = Web.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("/properties/application.properties");
            properties.load(inputStream);
            jsLoadDomain = properties.getProperty("jsLoadDomain");
        } catch (Exception e) {
            e.printStackTrace();
            jsLoadDomain = "localhost";
        }
    }
    //web中用的一些常量
    public static String jsLoadDomain;
}
