(function (root, factory) {
	root.JsBridge = factory(root);
}(typeof window == "undefined" ? this : window, function (win) {
	if (!win.document) { return {};}


	var doc = win.document,
	title = doc.title,
	ua = navigator.userAgent.toLowerCase(),
	platform = navigator.platform.toLowerCase(),
	isMacorWin = !(!platform.match("mac") && !platform.match("win")),
	isandroid = -1 != ua.indexOf("android"),
	isphoneorpad = -1 != ua.indexOf("iphone") || -1 != ua.indexOf("ipad"),
	JsBridge = {
		usable: false,
		init: function (bridge) {
			/*注册提供给native的接口*/
			bridge.registerHandler("exam",function(message, responseCallback){

				var result=document.getElementById("result");
				result.innerHTML = 'native传递的数据:'+JSON.stringify(message);
				responseCallback({
					status: "1",
					msg: "ok",
					values:{
						msg: "js回调native"
					}
				});
			});

			bridge.registerHandler("exam1",function(message, responseCallback){

				var result=document.getElementById("result");
				result.innerHTML = 'native传递的数据:'+JSON.stringify(message);
				responseCallback({
					status: "1",
					msg: "ok",
					values:{
						cityName:message.cityName,
						cityProvince: message.cityProvince
					}
				});
			});

			bridge.registerHandler("exam2",function(message, responseCallback){

				var result=document.getElementById("result");
				result.innerHTML = 'native传递的数据:'+JSON.stringify(message);
				responseCallback({
					status: "1",
					msg: "ok",
					values:{
						city:{
							cityName:message.cityName,
							cityProvince: message.cityProvince
						}

					}
				});
			});

			bridge.registerHandler("exam3",function(message, responseCallback){

				var result=document.getElementById("result");
				result.innerHTML = 'native传递的数据:'+JSON.stringify(message);
				responseCallback({
					status: "1",
					msg: "ok",
					values:{
						cityName:'北京',
						cityProvince: '北京'

					}
				});
			});

			bridge.registerHandler("exam4",function(message, responseCallback){

				var result=document.getElementById("result");
				result.innerHTML = 'native传递的数据:'+JSON.stringify(message);
				responseCallback({
					status: "1",
					msg: "ok",
					values:{

					}
				});
			});

			return this;
		},
		checkUsable: function (methodName, params, cb) {
			var _this = this;
			if (!window._JSNativeBridge) {
				//JS not be injected success
				cb({
					status: "-1",
					msg: "window._JSNativeBridge is undefined"
				}, {});
				return;
			}

			try {
                    window._JSNativeBridge._doSendRequest(methodName, params, cb);
			} catch (e) {
				cb({status: "-1", msg: e},{});
			}
		},

		test: function(params,cb){
			this.checkUsable("test",{
				"msg": "js发送的请求"
			},cb);
		},

		test1: function(params,cb){
			this.checkUsable("test1",{
				"age": 10,
				"name":"wangwu"
			},cb);
		},

		test2: function(params,cb){
			this.checkUsable("test2",{
				person:{
					"age": 10,
					"name":"wangwu"
				}

			},cb);
		},

		test3: function(params,cb){
			this.checkUsable("test3",{
				"jiguan":"北京",
				person:{
					"age": 10,
					"name":"wangwu"
				}

			},cb);
		},
		test4: function(params,cb){
			this.checkUsable("test4",{

			},cb);
		}


	};

	if (window._JSNativeBridge) {
		console.log("native injection js success!");
		window._JSNativeBridge.protocol.scheme = 'niu';
		window._JSNativeBridge.protocol.host = 'receive_msg';
		window._JSNativeBridge.debug = true;
		JsBridge.init(window._JSNativeBridge);
	} else {
		console.log("native injection js wrong!");
		document.addEventListener(
			'JsBridgeInit',
			function(event) {
				console.log('------------------bridge');
                window._JSNativeBridge.protocol.scheme = 'niu';
		        window._JSNativeBridge.protocol.host = 'receive_msg';
		        event.bridge.debug = true;
				JsBridge.init(event.bridge);
			},
			false
			);
	}

	return JsBridge;
}));