<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<form enctype="multipart/form-data" method="post" style="width:100%" action="/xr.file.upload?class=com.huaxin.util.file.upload.FileAsPhotoService&para=BO_SUPERVISOR_TASK_ID=,LONGITUDE=,LATITUDE=,CAPTURE=" target="frameFile">
		<iframe id="frameFile" name="frameFile" style="display: none"></iframe>
		<input type="file" name="file_head" id="file_head" onchange="setImagePreview();" accept="image/*" capture="camera">
		<input type="submit" value="提交">
	</form>
  </body>
</html>
