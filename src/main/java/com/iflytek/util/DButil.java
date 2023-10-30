package com.iflytek.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

//连接池
public class DButil {
    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            properties.load(DButil.class.getClassLoader().getResourceAsStream("db.properties.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(DButil.class.getClassLoader().getResourceAsStream("db.properties.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
