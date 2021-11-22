/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import utility.Dao;
import com.model.ApiResponse;
import com.google.gson.Gson;
import com.model.ApiStatus;
import com.model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ProductListController", urlPatterns = {"/plist"})
public class ProductListController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ApiResponse apires = new ApiResponse();

		Gson gson = new Gson();
		List<Product> plist = new ArrayList<>();
		try {
			Map data = new HashMap<String, Object>();
			plist = Dao.getProducts();
			data.put("products", plist);
			apires.setData(data);
			apires.setStatus(ApiStatus.SUCCESS);
			apires.setMsg("Products got successfully");

		} catch (Exception ex) {
			apires.setStatus(ApiStatus.FAILED);
			apires.setMsg("Failed to retrieve Products");
			apires.setMsg(ex.getMessage());
		}

		resp.getWriter().print(gson.toJson(apires));
	}

}
