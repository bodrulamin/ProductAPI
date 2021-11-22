/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author b
 */
public class ApiResponse {

	private ApiStatus status;
	private Map<String, Object> data;
	private String msg;

	public ApiResponse() {
		this.status = ApiStatus.FAILED;
		this.data = new HashMap<String,Object>();
		this.msg = "no msg";
	}

	public ApiResponse(ApiStatus status, Map<String, Object> data, String msg) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public ApiStatus getStatus() {
		return status;
	}

	public void setStatus(ApiStatus status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



}
