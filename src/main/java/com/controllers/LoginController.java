/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import utility.Dao;
import com.model.ApiStatus;
import com.model.ApiResponse;
import com.model.User;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author b
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder("");
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		Gson gson = new Gson();
		User user = gson.fromJson(sb.toString(), User.class);
		if (user == null){
			user = new User();
		}
		
		ApiResponse ar = new ApiResponse();
		try {
			User dbUser = Dao.getUser(user.getUsername());
			if(user.getPassword().equals(dbUser.getPassword())){
				ar.setStatus(ApiStatus.SUCCESS);
				ar.setMsg("Login Successfull");
			}
		} catch (Exception ex) {
			ar.setMsg(ex.getMessage());
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(gson.toJson(ar));
		System.out.println(sb.toString());
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("get method is not implemented. Thank you");
	}
	
}
