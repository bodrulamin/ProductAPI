/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import com.model.Product;
import com.google.gson.Gson;
import com.model.ApiResponse;
import com.model.ApiStatus;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Dao;
import static utility.Utility.getStringBuilder;

@WebServlet(name = "ProductController", urlPatterns = {"/product"})

public class ProductController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		ApiResponse apires = new ApiResponse();
		StringBuilder sb = getStringBuilder(req);

		Product product = gson.fromJson(sb.toString(), Product.class);
		if (product == null) {
			apires.setStatus(ApiStatus.FAILED);
			apires.setMsg("Product info is not provided");
			resp.setContentType("application/json");
			resp.getWriter().print(gson.toJson(apires));
			return;

		}

		try {
			if (Dao.addProduct(product)) {
				apires.setMsg("Product Added Succesfully");
				apires.setStatus(ApiStatus.SUCCESS);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			apires.setMsg(ex.getMessage());
			apires.setStatus(ApiStatus.FAILED);
		}

		resp.setContentType("application/json");
		resp.getWriter().print(gson.toJson(apires));

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
	
						Gson gson = new Gson();
		
		ApiResponse apires = new ApiResponse();
		
	

		try {
			if (Dao.deleteProduct(id)) {
				apires.setMsg("Product deleted Succesfully");
				System.out.println("deleted");
				apires.setStatus(ApiStatus.SUCCESS);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			apires.setMsg(ex.getMessage());
			apires.setStatus(ApiStatus.FAILED);
		}

		resp.setContentType("application/json");
		resp.getWriter().print(gson.toJson(apires));		
	}

	
	

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		
		ApiResponse apires = new ApiResponse();
		StringBuilder sb = getStringBuilder(req);
 	System.out.println(sb.toString());
		Product product = gson.fromJson(sb.toString(), Product.class);
		if (product == null) {
			apires.setStatus(ApiStatus.FAILED);
			apires.setMsg("Product info is not provided");
			resp.setContentType("application/json");
			resp.getWriter().print(gson.toJson(apires));
			return;

		}

		try {
			if (Dao.updateProduct(product)) {
				apires.setMsg("Product Updated Succesfully");
				System.out.println("aded");
				apires.setStatus(ApiStatus.SUCCESS);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			apires.setMsg(ex.getMessage());
			apires.setStatus(ApiStatus.FAILED);
		}

		resp.setContentType("application/json");
		resp.getWriter().print(gson.toJson(apires));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Product p = new Product();
		p.setName("mehedii");
		System.out.println(p);

		resp.setContentType("application/json");
		resp.getWriter().print(gson.toJson(p));
	}

}
