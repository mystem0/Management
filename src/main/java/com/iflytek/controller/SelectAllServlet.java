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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

//查询所有
@WebServlet(name = "SelectAllServlet", value = "/SelectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService brandService = new BrandService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    //参数都封装在request对象里了
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        //服务端
        PrintWriter writer = response.getWriter();
        writer.write("hello");*/
        response.setContentType("text/json;charset=utf-8");
        //获取参数currentPage  pageSize
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//查询数据库tb_brand表封装成json返回调用者
        List<Brand> brandList = brandService.selectAll(currentPage, pageSize);//select * from tb_brand limit 0,5
        //查询总条数
        int count = brandService.getAllCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("brandList", brandList);
        map.put("totalCount", count);
        //把对象转换成json字符串
        response.getWriter().write(JSON.toJSONString(map));
    }
}
