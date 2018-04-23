/**
 * 创建者: wuwl
 * 创建日期: 2018-04-08
 * 文件说明: 手机端 页面拍照保存
 */

/*
 * 页面全局变量 AsPhoto
 */
var takePhotoJson ={
	'IS_TAKE_PHOTO':0,
	'UPLOAD_CLASS':'com.java.main.upload.impl.UploadPhotoImpl',
	'PARA':''
};

/*
 * 页面初始化方法
 */
function doinit(){
	initPara();
	initStyle();
	//checkDate(); //校验数据,若通过则加载拍照上传功能
	initAction();
	changeAlert();
}

/*
 * 初始化上传配置数据
 */
function initPara(){
	let taskID = getQueryString("a")||"";
	let longitude = getQueryString("b")||"";
	let latitude = getQueryString("c")||"";
	let capture = getQueryString("d")||"";
	takePhotoJson.PARA = "BO_SUPERVISOR_TASK_ID=" + taskID + ",LONGITUDE="+ longitude
						+ ",LATITUDE=" + latitude + ",CAPTURE=" + capture;
	takePhotoJson.BO_SUPERVISOR_TASK_ID = 	taskID;
	takePhotoJson.LONGITUDE = 	longitude;
	takePhotoJson.LATITUDE = 	latitude;
	takePhotoJson.CAPTURE = 	capture;	
}

/*
 * 校验数据,若通过则加载拍照上传功能
 */
function checkDate(){
	ACWS.ajax('zjmcpm/commonmold/create/service/ProjectJZW/CheckData', takePhotoJson, 
		function(outputData){
			var msg = "";
			if(!outputData.IS_NORMAL){
				msg = "非法二维码！";
			}else if(!outputData.IS_CURRDATE){
				msg = "二维码已过期！";
			}
			if(msg != ""){
				alert(msg);
			}else{
				initAction();
			}
		}
	);
}

/*
 * 初始化点击事件
 */
function initAction(){
	$("#TAKE_PHOTO").click(function(){
		$("#file_head").click();
	});
	$("#UPLOAD").click(function(){
		doUploadPhoto();
	});
}

/*
 * 上传照片
 */
function doUploadPhoto(){
	if(takePhotoJson.IS_TAKE_PHOTO == 0){
		alert("请先自拍！");
		return;
	}else if(takePhotoJson.IS_TAKE_PHOTO == 1){
		alert("请勿重复上传！");
		return;
	}
	$(".pagemask").show();
	document.forms[0].method='post';
	document.forms[0].enctype='multipart/form-data';
	document.forms[0].action = "/tpf/xr.file.upload?class=" + takePhotoJson.UPLOAD_CLASS
						+ "&para=" + takePhotoJson.PARA;
	document.forms[0].target = "frameFile";
	document.forms[0].submit();
	doListenUpload();
}

/*
 * 监听文档上传完成
 */
function doListenUpload(){
	var iframe = document.getElementById("frameFile");
	if (iframe.attachEvent){ 
		iframe.attachEvent("onload", function(){
			doAfterUpload();
		}); 
	} else { 
		iframe.onload = function(){
			doAfterUpload();			
		}; 
	}
}

/*
 * 上传完成后调用
 */
function doAfterUpload(){
	takePhotoJson.IS_TAKE_PHOTO = 1;
	$(".pagemask").hide();
	alert("上传完成！");
}

/*
 * 初始化样式
 */
function initStyle(){
	var clientHeight = document.documentElement.clientHeight;
	var clientWidth = document.documentElement.clientWidth;
	$(".div_top").css('height',clientHeight - 210 - 5); 
	var m_width = $("#ACTION").width();
	$("#UPLOAD").css('width', m_width - 210 - 5);
	$(".div_top_2").css('height',clientHeight - 210 - 5 - 100 - 20);

	//loading图片居中
	$(".loading_pic").css('left', (clientWidth - 100) / 2);
	$(".loading_pic").css('top', (clientHeight - 100) / 2);
}

/*
 * 展示拍照所得图片
 */
function setImagePreview(){
	var preview;
	var file_head = document.getElementById("file_head");
	var picture = file_head.value||"";
	if (!picture.match(/.jpg|.gif|.png|.bmp/i)){
		alert("图片格式不正确！");
		return;
	}
	if (preview = document.getElementById("preview"), file_head.files && file_head.files[0]) {
		console.log(file_head.files[0]);
		preview.style.display = "block";
	    preview.style.width = "auto";
	    preview.style.maxWidth = "90%";
	    preview.style.height = "90%";
	    preview.src = window.navigator.userAgent.indexOf("Chrome") >= 1 || window.navigator.userAgent.indexOf("Safari") >= 1 ? window.webkitURL.createObjectURL(file_head.files[0]) : window.URL.createObjectURL(file_head.files[0]);
		takePhotoJson.IS_TAKE_PHOTO = 2;
	}
}