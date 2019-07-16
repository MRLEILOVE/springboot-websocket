if (typeof (WechatLoginHelper) === "undefined" || !WechatLoginHelper) {
	var WechatLoginHelper = {
		version : "1.0",
		path : ""
	};
}
var sUserAgent = navigator.userAgent.toLowerCase(); 
var IsWeixin= sUserAgent.match(/MicroMessenger/i)=="micromessenger";
var IsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
var IsAndroid = sUserAgent.match(/android/i) == "android";
var IsMobileQQ = sUserAgent.match(/\sQQ/i) == " qq"

WechatLoginHelper.login=function(SHARE_STORE_URL){
	if(IsWeixin) {
		$.ajaxSetup({
	        async: false
	    });
		var webOauth2ResData = WebLocalStorage.getLSData("webOauth2Res");
		if(WebLocalStorage.isEmpty(webOauth2ResData)){
			WechatLoginHelper.getAuthLink(SHARE_STORE_URL);
		}else{
			var webOauth2Res = JSON.parse(webOauth2ResData);
			//刷新token
			webOauth2Res = WechatLoginHelper.refresh(webOauth2Res);
			if(webOauth2Res.access_token === undefined || webOauth2Res.access_token == ''){
				WebLocalStorage.clearLSData("webOauth2Res");
				WechatLoginHelper.getAuthLink(SHARE_STORE_URL);
			}else{
				WechatLoginHelper.webLogin(webOauth2Res, SHARE_STORE_URL);
			}
		}
	}else{
		window.location.href= SHARE_STORE_URL;
	}
}
WechatLoginHelper.getAuthLink = function(SHARE_STORE_URL){
	WebLocalStorage.setLSData("webOauth2LoginSucRedirect", SHARE_STORE_URL);
	var targetSys=$("#targetSys").val()=="co"?"copartner": "mall"; 
	
	$.ajax({
	    url: "wechat/web/getAuthLink.htm",
	    aysnc: false,
	    type: "POST",
	    data:{scope:'snsapi_userinfo', state:targetSys},
	    success: function( resp){
	    	window.location.href= JSON.parse(resp).authLink;
	    }
	});
}
WechatLoginHelper.refresh = function(webOauth2Res){
	$.ajax({
	    url: "wechat/web/refresh.htm",
	    aysnc: false,
	    type: "POST",
	    data:{refreshToken:webOauth2Res.refresh_token, openId:webOauth2Res.openid},
	    success: function(response){
	    	webOauth2Res = JSON.parse(response);
	    }
	});
	return webOauth2Res;
}
WechatLoginHelper.webLogin = function(webOauth2Res, SHARE_STORE_URL){
	$.ajax({
	    url: "wechat/web/webLogin.htm",
	    aysnc: false,
	    type: "POST",
	    data:{access_token:webOauth2Res.access_token, openid:webOauth2Res.openid},
	    success: function(response){
	    	if(response == 'success'  || response == '"success"'){
				var url = SHARE_STORE_URL;
				if (url.indexOf('?') > 0) {
					url += ("&t=" + Math.random());
				} else {
					url += ("?t=" + Math.random());
				}
				window.location.href = url;
	    	}
	    }
	});
}
