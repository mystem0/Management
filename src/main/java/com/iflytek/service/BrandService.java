package com.iflytek.service;

import com.iflytek.dao.BrandDao;
import com.iflytek.pojo.Brand;

import java.util.List;

/**
 * 连接pojo层和dao层
 */
public class BrandService {
    private BrandDao dao = new BrandDao();

    public List<Brand> selectAll(int currentPage, int pageSize) {
        return dao.selectAll(currentPage, pageSize);
    }

    public int getAllCount() {
        return dao.getAllCount();
    }

    //条件查询
    public List<Brand> selectWhere(int currentPage, int pageSize, Brand brand) {
        return dao.selectWhere(currentPage, pageSize, brand);
    }

    //条件查询count
    public int getWhereCount(Brand brand) {
        return dao.getWhereCount(brand);
    }

    //添加数据
    public void add(Brand brand) {
        dao.add(brand);
    }

    public void updata(Brand brand) {
        dao.updata(brand);
    }

    public void del(Brand brand) {
        dao.del(brand);
    }

    public void delectall(int[] ids) {
        dao.delectall(ids);
    }
}
