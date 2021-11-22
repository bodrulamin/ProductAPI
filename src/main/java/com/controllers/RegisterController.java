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
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author b
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder("");

		BufferedReader bf = req.getReader();

		String line = "";
		while ((line = bf.readLine()) != null) {
			sb.append(line);

		}

		ApiResponse ar = new ApiResponse(ApiStatus.FAILED, new HashMap<String, Object>(), "no msg");

		User user = gson.fromJson(sb.toString(), User.class);

		String msg = "";
		try {
			msg = Dao.registerUser(user);
			ar.setStatus(ApiStatus.SUCCESS);

		} catch (ClassNotFoundException | SQLException ex) {
			ar.setStatus(ApiStatus.FAILED);
			System.out.println("exxxxxxx");
			msg = ex.getMessage();
		}
		ar.setMsg(msg);

		resp.getWriter().print(gson.toJson(ar));
		System.out.println(sb.toString());

	}

}
