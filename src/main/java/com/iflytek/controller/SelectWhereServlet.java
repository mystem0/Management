package com.iflytek.controller;

import com.alibaba.fastjson.JSON;
import com.iflytek.pojo.Brand;
import com.iflytek.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/*
条件查询
 */
@WebServlet(name = "SelectWhereServlet", value = "/SelectWhereServlet")
public class SelectWhereServlet extends HttpServlet {
    private BrandService brandService = new BrandService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //获取brand相关的信息
        String brandString = request.getReader().readLine();
        Brand brand = JSON.parseObject(brandString, Brand.class);
        //
        List<Brand> brandList = brandService.selectWhere(currentPage, pageSize, brand);
        int count = brandService.getWhereCount(brand);
        HashMap<String, Object> map = new HashMap<>();
        map.put("brandList", brandList);
        map.put("totalCount", count);
        //把对象转换成json字符串
        response.getWriter().write(JSON.toJSONString(map));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //获取brand相关的信息
        String brandString = request.getReader().readLine();
        Brand brand = JSON.parseObject(brandString, Brand.class);
        //
        List<Brand> brandList = brandService.selectWhere(currentPage, pageSize, brand);
        int count = brandService.getWhereCount(brand);
        HashMap<String, Object> map = new HashMap<>();
        map.put("brandList", brandList);
        map.put("totalCount", count);
        //把对象转换成json字符串
        response.getWriter().write(JSON.toJSONString(map));
    }
}
