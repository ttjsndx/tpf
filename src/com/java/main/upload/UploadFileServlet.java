package com.java.main.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springside.components.ajaxupload.MonitoredDiskFileItem;
import org.springside.components.ajaxupload.MonitoredDiskFileItemFactory;
import org.springside.components.ajaxupload.UploadListener;

import com.java.util.MapUtils;
import com.java.util.StringUtils;

public class UploadFileServlet extends HttpServlet{
	
	private final static String REDIRECT_URL="/acwsui/js/jquery/fileupload/file_upload.html";
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		String para = null;
		HashMap inputData = null;
		String className = null;
		try {
			request.setCharacterEncoding("UTF-8");
			className = request.getParameter("class");
			para = request.getParameter("para");
			//para = java.net.URLDecoder.decode(java.net.URLDecoder.decode(para,"UTF-8"),"UTF-8");
			inputData = MapUtils.getHashMapByPara(para);
			//获取req中的附件信息
			ArrayList fileList = this.getMdfiList(request);
			String ip = this.getIpAddress(request);
			inputData.put("ADDR_ID", ip);
			UploadFileInterface ufi = (UploadFileInterface) this.getClass().forName(className).newInstance();
			boolean result = ufi.saveFile(inputData,fileList);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String url = request.getContextPath() + REDIRECT_URL + "?isupload=" + result
			 				+ "&class=" + className + "&para=" + java.net.URLEncoder.encode(para,"UTF-8");
			response.sendRedirect(url);
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @说明 获取req中的附件信息(req 为 包含file类型的input 的form表单提交)
	 */
	public ArrayList getMdfiList(HttpServletRequest request) throws Exception{
		UploadListener listener = new UploadListener(request, 3);
		FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);			
		ServletFileUpload upload = new ServletFileUpload(factory);
		List list = upload.parseRequest(request);
		ArrayList tempList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			MonitoredDiskFileItem mdfi = (MonitoredDiskFileItem) list.get(i);
			if (StringUtils.isNotBlank(mdfi.getName())){
				tempList.add(mdfi);
			}				
		}
		return tempList;
	}
	
	/**
	 * @date 2018-4-21
	 * @author @2wl
	 * @说明 获取请求的ip
	 */
	public static String getIpAddress(HttpServletRequest request) {  
		String Xip = request.getHeader("X-Real-IP");
		String XFor = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotBlank(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = XFor.indexOf(",");
			if(index != -1){
				return XFor.substring(0,index);
			}else{
				return XFor;
			}
		}
		XFor = Xip;
		if(StringUtils.isNotBlank(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
			return XFor;
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getRemoteAddr();
		}
		return XFor;
	}
}
