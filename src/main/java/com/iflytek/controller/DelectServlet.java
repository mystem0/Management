package com.iflytek.controller;

import com.alibaba.fastjson.JSON;
import com.iflytek.pojo.Brand;
import com.iflytek.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//批量删除数据
@WebServlet(name = "DelectServlet", value = "/DelectServlet")
public class DelectServlet extends HttpServlet {
    BrandService brandService = new BrandService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);

        brandService.delectall(ids);
        response.getWriter().write("t");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
