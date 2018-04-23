/**
 * @date 2018-4-21
 * @author @2wl
 * @说明 前端页面基础js
 */

/**
 * 将json转换成特定格式的string ,为了方便往后台传输数据
 * @param {} jso
 */
function jsonToStrForAjax(jso){
	let str = "para=";
	if(!jso){
		console.log("兄弟你传入参数有问题！");
		return str;
	}	
	for(let i in jso){
		str += i + "=" + jso[i];
	}
	return str;
}

/**
 * 重写alert 方法（去除手机端提醒时自带的ip地址，IE不适用）
 */
function changeAlert(){
	window.alert = function(name){
		try{
			var iframe = document.createElement("IFRAME");
			iframe.style.display="none";
			iframe.setAttribute("src", 'data:text/plain,');
			document.documentElement.appendChild(iframe);
			window.frames[0].window.alert(name);
			iframe.parentNode.removeChild(iframe);
		}catch(e){

		}
	} 
}

/**
 * 获取url 上的参数
 */
function getQueryString(queryStringName){
	let returnValue = "";
	let URLString = new String(document.location);
	let serachLocation = -1;
	let queryStringLength = queryStringName.length;
	do{
  		serachLocation = URLString.indexOf( queryStringName + "\=" );
  		if (serachLocation != -1){
   			if ((URLString.charAt(serachLocation-1) == '?') || (URLString.charAt(serachLocation-1) == '&')){
    			URLString = URLString.substr(serachLocation);
    			break;
   			}
   			URLString = URLString.substr(serachLocation + queryStringLength+1);
  		}
 	}
 	while (serachLocation != -1)
 	if (serachLocation != -1){
  		let seperatorLocation = URLString.indexOf("&");
  		if (seperatorLocation == -1){
   			returnValue = URLString.substr(queryStringLength+1);
  		}else{
   			returnValue = URLString.substring(queryStringLength + 1,seperatorLocation);
  		}
 	}
 	return returnValue;
}

