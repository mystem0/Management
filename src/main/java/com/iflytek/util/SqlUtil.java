package com.iflytek.util;

import com.iflytek.pojo.Brand;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    //拼接sql
    public static StringBuilder appendSQL(Brand brand, StringBuilder sql) {
        Integer status = brand.getStatus();
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        boolean flag = true;
        if (status != null && !status.equals("")) {
            if (flag) {
                sql.append(" where status=? ");
                flag = false;
            } else {
                sql.append(" and status=? ");
            }
        }
        if (brandName != null && !brandName.equals("")) {
            if (flag) {
                sql.append(" where brand_name=? ");
                flag = false;
            } else {
                sql.append(" and brand_name=? ");
            }
        }
        if (companyName != null && !companyName.equals("")) {
            if (flag) {
                sql.append(" where company_name=? ");
                flag = false;
            } else {
                sql.append(" and company_name=? ");
            }
        }

        //sql.append("limit ?,?");
        return sql;
    }

    //设置参数
    public static PreparedStatement setParams(PreparedStatement preparedStatement, Brand brand, int currentPage, int pageSize) throws SQLException {
        Integer status = brand.getStatus();
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        boolean flag = true;
        int count = 1;
        if (status != null && !status.equals("")) {
            preparedStatement.setInt(count++, status);
        }
        if (brandName != null && !brandName.equals("")) {
            preparedStatement.setString(count++, brandName);
        }
        if (companyName != null && !companyName.equals("")) {
            preparedStatement.setString(count++, companyName);
        }
        preparedStatement.setInt(count++, (currentPage - 1) * pageSize);
        preparedStatement.setInt(count++, pageSize);
        return preparedStatement;
    }

    //重载setParams方法
    public static PreparedStatement setParams(PreparedStatement preparedStatement, Brand brand) throws SQLException {
        Integer status = brand.getStatus();
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        boolean flag = true;
        int count = 1;
        if (status != null && !status.equals("")) {
            preparedStatement.setInt(count++, status);
        }
        if (brandName != null && !brandName.equals("")) {
            preparedStatement.setString(count++, brandName);
        }
        if (companyName != null && !companyName.equals("")) {
            preparedStatement.setString(count++, companyName);
        }
        return preparedStatement;
    }
}
