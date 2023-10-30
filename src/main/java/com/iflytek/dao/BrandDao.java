package com.iflytek.dao;

import com.iflytek.pojo.Brand;
import com.iflytek.service.BrandService;
import com.iflytek.util.DButil;
import com.iflytek.util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接数据库，建立和关闭连接池，执行sql语句
 */
public class BrandDao {
    /*
     * 全查询
     * */
    public List<Brand> selectAll(int currentPage, int pageSize) {
        List<Brand> brandList = new ArrayList<>();
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from tb_brand limit ?,?");
            preparedStatement.setInt(1, (currentPage - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setBrandName(resultSet.getString("brand_name"));
                brand.setCompanyName(resultSet.getString("company_name"));
                brand.setOrdered(resultSet.getInt("ordered"));
                brand.setStatus(resultSet.getInt("status"));
                brandList.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return brandList;
    }

    /*
    全查询总数
     */
    public int getAllCount() {
        int count = 0;
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select count(*) from tb_brand");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    //条件查询
    public List<Brand> selectWhere(int currentPage, int pageSize, Brand brand) {
        List<Brand> brandList = new ArrayList<>();
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //拼接sql
        StringBuilder sql = new StringBuilder("select * from tb_brand ");
        StringBuilder stringBuilder = SqlUtil.appendSQL(brand, sql);
        stringBuilder.append(" limit ?,?");
        System.out.println(stringBuilder + "**");
        try {
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            //设置参数
            PreparedStatement preparedStatement1 = SqlUtil.setParams(preparedStatement, brand, currentPage, pageSize);
            resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                Brand brand1 = new Brand();
                brand1.setId(resultSet.getInt("id"));
                brand1.setBrandName(resultSet.getString("brand_name"));
                brand1.setStatus(resultSet.getInt("status"));
                brand1.setCompanyName(resultSet.getString("company_name"));
                brand1.setOrdered(resultSet.getInt("ordered"));
                brandList.add(brand1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return brandList;
    }

    //条件查询count
    public int getWhereCount(Brand brand) {
        int count = 0;
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //拼接sql
        StringBuilder sql = new StringBuilder("select count(*) from tb_brand ");
        StringBuilder stringBuilder = SqlUtil.appendSQL(brand, sql);
        System.out.println(stringBuilder + "**");
        try {
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            //设置参数
            PreparedStatement preparedStatement1 = SqlUtil.setParams(preparedStatement, brand);
            resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    //添加数据
    public void add(Brand brand) {
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet=null;
        try {
            preparedStatement = connection.prepareStatement("insert into tb_brand" + "(id,company_name,brand_name,ordered,description,status)" + "values(null,?,?,?,?,?)");
            preparedStatement.setString(1, brand.getBrandName());
            preparedStatement.setString(2, brand.getCompanyName());
            preparedStatement.setInt(3, brand.getOrdered());
            preparedStatement.setString(4, brand.getDescription());
            preparedStatement.setInt(5, brand.getStatus());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
                //resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //修改数据
    public void updata(Brand brand) {
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet=null;
        try {
            preparedStatement = connection.prepareStatement("update tb_brand set brand_name=?,company_name=?,ordered=?,description=?,status=? where id = ?");
            preparedStatement.setString(1, brand.getBrandName());
            preparedStatement.setString(2, brand.getCompanyName());
            preparedStatement.setInt(3, brand.getOrdered());
            preparedStatement.setString(4, brand.getDescription());
            preparedStatement.setInt(5, brand.getStatus());
            preparedStatement.setInt(6, brand.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
                //resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //删除一条数据
    public void del(Brand brand) {
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet=null;
        try {
            preparedStatement = connection.prepareStatement("delete from tb_brand where id=?");
            preparedStatement.setInt(1, brand.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
                //resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //批量删除
    public void delectall(int[] ids) {
        Connection connection = DButil.getConnection();
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet=null;
        try {
            preparedStatement = connection.prepareStatement("delete from tb_brand where id=?");
            for (int id : ids) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
                //resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
