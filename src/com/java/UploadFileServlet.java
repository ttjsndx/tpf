package com.java;

import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadFileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		
		String para = null;
		HashMap inputData = null;
		String className = null;
		try {
			request.setCharacterEncoding("UTF-8");
			className = request.getParameter("para");
			para = request.getParameter("className");
			para = java.net.URLDecoder.decode(java.net.URLDecoder.decode(para,"UTF-8"),"UTF-8");
			inputData = MapUtils
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
