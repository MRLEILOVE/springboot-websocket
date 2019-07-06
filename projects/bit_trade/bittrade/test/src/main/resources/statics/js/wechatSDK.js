document.write("<script language=javascript src='http://res.wx.qq.com/open/js/jweixin-1.4.0.js'></script>");
/**
 * 微信SDK调用
 */
var config = {
    "isDebug": false,
    "islocal": true,
    "localTimeOut": "30",
    "timeOut": "300" // 请求超时
};

var wxUtils = (function () {
    /**
     * 获取时间戳
     * @returns {string}
     */
    function getTimeStamp() {
        var timeStr = new Date().getTime();
        var timestamp = timeStr.toString();//一定要转换字符串
        return timestamp;
    }

    /**
     * JSON转成字符串
     * @param jsonData
     * @returns {string}
     */
    function jsonToString(jsonData) {
        if (isNotEmpty(jsonData)) {
            return JSON.stringify(jsonData);
        }
        return "";
    }

    /**
     * 字符串转成JSON
     * @param strData
     * @returns {*}
     */
    function stringToJson(strData) {
        if (isNotEmpty(strData)) {
            var str = '(' + strData + ')'; //json字符串
            return eval(str);
        }
        return "";
    }

    /**
     * 判断参数是否不为空
     * @param param
     * @returns {boolean}
     */
    function isNotEmpty(param) {
        if (param == null || param == undefined || typeof(param) == "undefined" || param == "" || param == "undefined" || param == "null") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断参数是否为空
     * @param param
     * @returns {boolean}
     */
    function isEmpty(param) {
        if (param == null || param == undefined || typeof(param) == "undefined" || param == "" || param == "undefined" || param == "null") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存数据到本地缓存
     * @param key
     * @param value
     */
    function setLStorageInfo(key, value) {
        if (config.islocal) {
            var timestamp = wxUtils.getTimeStamp();//存储的时间戳
            var storageinfo = {
                "timestamp": timestamp,
                key: value
            };
            var infotxt = wxUtils.jsonToString(storageinfo);
            try {
                localStorage.setItem(key, infotxt);
            } catch (e) {
                alert("您的浏览器版本太低，或者您开启了隐身/无痕浏览模式，或者WebView组件不支持localStorage！");
            }
        } else {
            if (config.isDebug) {
                alert("未开启离线缓存模式，不支持localStorage缓存！");
            }
        }
    }

    /**
     * 获取本地缓存，如果缓存超时，清除本地缓存
     * @param key
     * @returns {*}
     */
    function getLStorageInfo(key) {
        if (config.islocal) {
            var timestamp1 = wxUtils.getTimeStamp();//存储的时间戳
            var timeout = config.localTimeOut;//离线缓存数据超时时间(分钟为单位)
            var value = null;
            try {
                value = localStorage.getItem(key);
            } catch (e) {
                alert("您的浏览器版本太低，或者您开启了隐身/无痕浏览模式，或者WebView组件不支持localStorage！");
            }
            var storageinfo = wxUtils.stringToJson(value);//转换成json对象
            var timestamp2 = storageinfo.timestamp;//获取时间戳
            var time = (timestamp1 - timestamp2) / 1000 / 60;
            var keyValue = storageinfo.key;//获取数据
            if (time > timeout) {
                wxUtils.clearLStorage(key);//清理本地离线缓存数据
                if (config.isDebug) {
                    alert(key + "【离线缓存已经超时" + Math.round(time - timeout) + "分钟,已经被清理！】");
                }
                return null;
            }
            else {
                keyValue = keyValue ? keyValue : null
                if (config.isDebug && wxUtils.isNotEmpty(keyValue)) {
                    alert("获得离线缓存数据" + key + "=" + keyValue + "【数据将在" + Math.round(timeout - time) + "分钟后失效！】");
                }
                return keyValue;
            }
        }
        else {
            if (config.isDebug) {
                alert("未开启离线缓存模式，不支持localStorage缓存！");
            }
        }
    }

    /**
     * 清除本地缓存
     * @param key
     */
    function clearLStorage(key) {
        try {
            if (config.islocal) {
                if (key === true) {
                    localStorage.clear();
                }
                else {
                    if (key) {
                        localStorage.removeItem(key);
                    }
                    else {
                        for (var pkey in localStorage) {
                            localStorage.removeItem(pkey);
                        }
                    }
                }
            } else {
                if (config.isDebug) {
                    alert("未开启离线缓存模式，不支持localStorage缓存！");
                }
            }
        }
        catch (e) {
            alert("您的浏览器版本太低，或者您开启了隐身/无痕浏览模式，或者WebView组件不支持localStorage！");
        }
    }

    return {
        version: "1.0",
        getTimeStamp: getTimeStamp,
        isNotEmpty: isNotEmpty,
        isEmpty: isEmpty,
        jsonToString: jsonToString,
        stringToJson: stringToJson,
        setLStorageInfo: setLStorageInfo,
        getLStorageInfo: getLStorageInfo,
        clearLStorage: clearLStorage
    };
})();

var JS_SDK = (function () {
    //JS接口集合
    var apiList = ['checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareQZone',
        'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem'
        /*, 'translateVoice',
        'startRecord', 'stopRecord', 'onVoiceRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice',
        'downloadVoice', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'getNetworkType',
        'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode',
        'chooseWXPay', 'openProductSpecificView', 'addCard', 'chooseCard', 'openCard' */
    ];
    var effectMin = "";

    /**
     * 入口方法 初始化JS-SDK
     * @param debug 是否开启调试模式
     */
    function init(debug) {
        console.log(" apiList   "+apiList)
        var inParam = {
            "jsApiList": apiList,// 必填，需要使用的JS接口列表
            "debug": debug
        };
        var configParam = getWXConfigParam(inParam);
        console.log("configParam==   "+configParam)
        if (debug) {
            if (wx) {
                alert("JSSDK对象存在" + wx);
            }
            else {
                alert("JSSDK对象不存在,可能没引入微信JS文件,wx=" + wx);
            }
        }
        /*所有需要使用JS-SDK的页面必须先注入配置信息，*/
        wx.config(configParam);
        //4.步骤四：通过ready接口处理成功验证
        wx.ready(function () {
            check(apiList);
        });

        //5.步骤五：通过error接口处理失败验证
        wx.error(function (res) {
            if (debug) {
                alert(res.errMsg);
            }
        });
    }

    function getWXConfigParam(inParam) {
        var debug = inParam.debug;
        var configParam = "";
        var url = "wechat/jssdk_app/confinfo";
        var JSSDKUrl = "";
        if(-1 != window.location.href.indexOf("#")) {
            JSSDKUrl = (window.location.href).split("#")[0];
        } else {
            JSSDKUrl = window.location.href;
        }
        var param = {
        	"mod": "wechat",
            "url": encodeURIComponent(JSSDKUrl)
        };
        console.log("    param .======>  "+param)
        var callBack = function (data) {
            // 请求成功时处理
            if (data.code == '0000') {
                var body = data.body;
                console.log(" body *****  " + body);
                if (body.size != 0) {
                    var jsapi_createTime = data.jsapi_createTime;
                    var currentTimestamp = wxUtils.getTimeStamp();
                    var effectTimestamp = jsapi_createTime+7000*1000 - currentTimestamp;
                    effectMin = Math.round(effectTimestamp/ 1000/ 60);
                    localStorage.setItem("effectMin", effectMin);
                    configParam = {
                        debug: debug,
                        appId: data.appId,
                        timestamp: body.timestamp,
                        nonceStr: body.nonceStr,
                        signature: body.signature,
                        jsApiList: inParam.jsApiList
                    };
                    if (config.islocal) { //启用离线缓存机制
                    	config.localTimeOut = localStorage.getItem("effectMin");
                        var signStr = wxUtils.jsonToString(configParam);
                        wxUtils.setLStorageInfo(window.location.href, signStr);//存入本地离线缓存
                    }
                }
            } else {
                alert("JSSDK验签接口调用失败！" + data.msg);
            }
        };
        
        if (config.islocal) {
        	config.localTimeOut = localStorage.getItem("effectMin");
            var jssdksign = wxUtils.getLStorageInfo(window.location.href);//获取本地离线缓存
            if (wxUtils.isEmpty(jssdksign)) {
                Ajax.ajaxRequest(url, "post", false, param, callBack);
            } else {
                var signinfo = wxUtils.stringToJson(jssdksign);//转换成json对象
                configParam = signinfo;
            }
        } else {
            Ajax.ajaxRequest(url, "post", false, param, callBack);
        }
        return configParam;
    }

    function check(jsApiList, func) {
        /**
         * 检测当前设备是否支持使用你要使用的接口
         * 一版不能使用的原因是微信版本低于6.1版本
         * @param func <function> 建议按你选择的接口单独处理问题
         */
        wx.checkJsApi({
            jsApiList: apiList,
            success: function (res) {
                if (func) {
                    func(res);
                } else {
                    for (var i = 0; i < jsApiList.length; i++) {
                        if (res["checkResult"][jsApiList[i]] == false) {
                            if (config.isDebug) {
                                alert("当前手机暂不支持：" + jsApiList[i]);
                            }
                        }
                    }
                }
            }
        });
    }
    
    /**
     * 微信支付
     */
    function chooseWXPay(jsParam, callback){
    	if (typeof jsParam == "string") {
    		jsParam = JSON.parse(jsParam);
        }
        console.log('支付')
        console.log(jsParam)
        console.log(jsParam.timeStamp)
        console.log(jsParam.nonceStr)
    	wx.chooseWXPay({

    		timestamp: jsParam.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
    		nonceStr: jsParam.nonceStr, // 支付签名随机串，不长于 32 位
    		package: jsParam.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
    		signType: jsParam.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
    		paySign: jsParam.paySign, // 支付签名
    		success: function (res) {
    			// 支付成功后的回调函数
                console.log(res)
                console.log("success")
    			callback(res);
    		}
    	});
    }

    /**分享*/
    /**分享微信*/
    function shareFriend(param, callback){
    	
    	if (typeof param == "string") {
    		param = JSON.parse(param);
        }
    	
    	wx.onMenuShareAppMessage({
    	    title: param.title, // 分享标题
    	    desc: param.desc, // 分享描述
    	    link: param.link, // 分享链接
    	    imgUrl: param.imgUrl, // 分享图标
    	    type: param.type, // 分享类型,music、video或link，不填默认为link
    	    dataUrl: param.dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
    	    success: function () { 
    	        // 用户确认分享后执行的回调函数
    	    	callback("success");
    	    },
    	    cancel: function () { 
    	        // 用户取消分享后执行的回调函数
    	    	callback("cancel");
    	    }
    	});
    	
    }
    /**分享微信朋友圈*/
    function onMenuShareTimeline(param, callback){
    	
    	if (typeof param == "string") {
    		param = JSON.parse(param);
        }
    	
    	wx.onMenuShareTimeline({
    	    title: param.desc, // 分享标题
    	    link: param.link, // 分享链接
    	    imgUrl: param.imgUrl, // 分享图标
    	    success: function () { 
    	        // 用户确认分享后执行的回调函数
    	    	callback("success");
    	    },
    	    cancel: function () { 
    	        // 用户取消分享后执行的回调函数
    	    	callback("cancel");
    	    }
    	});
    	
    }
    /**分享QQ*/
    function onMenuShareQQ(param, callback){
    	
    	if (typeof param == "string") {
    		param = JSON.parse(param);
        }
    	
    	wx.onMenuShareQQ({
    	    title: param.title, // 分享标题
    	    desc: param.desc, // 分享描述
    	    link: param.link, // 分享链接
    	    imgUrl:  param.imgUrl, // 分享图标
    	    success: function () { 
    	       // 用户确认分享后执行的回调函数
    	    	callback("success");
    	    },
    	    cancel: function () { 
    	       // 用户取消分享后执行的回调函数
    	    	callback("cancel");
    	    }
    	});
    	
    }
    
    
    /**分享QQ空间*/
    function shareQQzone(param, callback){
    	
    	if (typeof param == "string") {
    		param = JSON.parse(param);
        }
    	 wx.onMenuShareQZone({
    	      title:  param.title,
    	      desc:  param.desc,
    	      link: param.link,
    	      imgUrl: param.imgUrl,
    	      success: function (res) {
    	    	  callback("success");

    	      },
    	      cancel: function (res) {
    	    	  callback("cancel");
    	      },
    	      fail: function (res) {
    	    	  callback(res);
    	      }
    	    });
    	 
    	
    }
    
    
    /**分享到腾讯微博*/
    function shareWeibo(param, callback){
    	
    	if (typeof param == "string") {
    		param = JSON.parse(param);
        }
	    wx.onMenuShareWeibo({
	        title: param.title, // 分享标题
	        desc: param.desc, // 分享描述
	        link: param.link, // 分享链接
	        imgUrl: param.imgUrl, // 分享图标
	        success: function () { 
	           // 用户确认分享后执行的回调函数
	        	 callback("success");
	        },
	        cancel: function () { 
	            // 用户取消分享后执行的回调函数
	        	 callback("cancel");
	        }
	    });
    } 
    
    return {
        version: "0.1",
        init: init,
        check: check,
        chooseWXPay:chooseWXPay,
        shareFriend:shareFriend,
        onMenuShareTimeline:onMenuShareTimeline,
        onMenuShareQQ:onMenuShareQQ,
        shareQQzone:shareQQzone,
        shareWeibo:shareWeibo
        
    };
})();

/**
 * 原生ajax请求
 * @type {{version, ajaxRequest, getajaxHttp}}
 */
var Ajax = (function () {

    /**
     * 获取XMLHttpRequest对象
     * @returns {*}
     */
    function getajaxHttp() {
        var xmlHttp;
        try {
            // Firefox, Opera 8.0+, Safari Google Chrome
            xmlHttp = new XMLHttpRequest();
        }
        catch (e) {
            // Internet Explorer
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                    alert("您的浏览器不支持AJAX！");
                    return false;
                }
            }
        }
        return xmlHttp;
    }

    /**
     *
     * @描述：原生js发送ajax请求
     * @作者：赵成
     * @版本: 1.0
     * @创建时间: 2016-4-25 下午2:27:22
     * @param url 请求url
     * @param methodtype 请求方式 (post/get)
     * @param isAsync true(异步)/false(同步)
     * @param param 请求参数
     * @param functionName 回调方法名，不需要引号,这里只有成功的时候才调用
     * @param obj 需要在调用请求成功后回调函数中处理的数据
     * @param dataFormat 返回数据格式 true(原格式返回)/false(json格式) 默认:false
     * @param timeOutFunc 超时后的回调方法
     * @param timeOut 超时时间(毫秒级别，不传默认30000毫秒，相当于30秒)
     */
    function ajaxRequest(url, methodtype, isAsync, param, functionName, obj, dataFormat, timeOutFunc, timeOut) {
        var weChatId = wxUtils.getLStorageInfo("weChatId");
        if(weChatId) {
            param.con["weChatId"] = weChatId;
        }
        if (!timeOut) {
            timeOut = config.timeOut;
        }
        var xmlhttp = getajaxHttp();
        console.log(xmlhttp)
        console.log(weChatId)
        var isTimeout = false,
            timeFlag = 0,
            options = {
                url: url,   // string
                data: param,  // json or string
                method: methodtype,
                timeout: timeOut,
                async: isAsync,
                success: function (data) {
                    console.log(data)
                    if (functionName) {
                        functionName(data, obj);
                    }
                },
                timeOut: function (data) {
                    console.log(data)
                    if (timeOutFunc) {
                        timeOutFunc(obj);
                    } else {
                        alert("请求超时，请稍后重试！");
                    }
                },
                error: function (xmlhttp) {
                    console.log(xmlhttp)
                }
            };
        if (param) {
            if (typeof options.data == "string") {
            } else {
                options.data = JSON.stringify(param);
            }
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4) {
                if (!isTimeout && xmlhttp.status == 200) {
                    clearTimeout(timeFlag);
                    if (!dataFormat) {
                        try {
                            var resobj = JSON.parse(xmlhttp.responseText);
                            options.success(resobj);
                        } catch (e) {
                            var str = '(' + xmlhttp.responseText + ')';  //json字符串
                            options.success(eval(str));
                        }
                    }
                    else {
                        var resobj = xmlhttp.responseText;
                        options.success(resobj);
                    }
                } else {
                    clearTimeout(timeFlag);
                    options.error(xmlhttp);
                }
            }
        };

        timeFlag = setTimeout(function () {
            if (xmlhttp.readyState != 4 || xmlhttp.status != 200) {
                isTimeout = true;
                xmlhttp.abort();
                options.timeOut(xmlhttp);
                clearTimeout(timeFlag);
            }
        }, options.timeout);

        console.log('连接服务器')
        console.log(options.method.toUpperCase())
        console.log(options.url)
        console.log(options.async)
        xmlhttp.open(options.method.toUpperCase(), options.url, options.async);  //打开与服务器连接
        if (options.method.toUpperCase() == "POST") {
            xmlhttp.setRequestHeader('Content-Type', 'application/json');  //post方式要设置请求类型
            xmlhttp.send(options.data);  //发送内容到服务器
        } else {
            xmlhttp.send(null);
        }
    }

    return {
        version: "1.0",
        ajaxRequest: ajaxRequest,
        getajaxHttp: getajaxHttp
    };
})();

/**
 * 微信SDK入口
 */
window.onload = function () {

    JS_SDK.init(config.isDebug);//JSSDK接口自动授权
};