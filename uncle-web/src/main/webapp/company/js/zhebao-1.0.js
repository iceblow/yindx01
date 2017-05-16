var ua = navigator.userAgent.toLowerCase();
var zhebao = {
 
    //��ȡҳ���Ƿ��״̬
	receiveCoupon:function (success, fail,couponid) {
		receiveCouponSuccess = success;
		receiveCouponFail = fail;
      
        if (/iphone|ipad|ipod/.test(ua)) {

           //JsModelCoupon("receiveCouponSuccess","receiveCouponFail",couponid);
           
           window.webkit.messageHandlers.receiveCoupon.postMessage(couponid);

            
        }else if (/android/.test(ua)) {
        	//alert(window.title);
            alert("111111"+couponid);
			var result = window.JSBrigeInterface.receiveCoupon(couponid);
            success(result);
        } else {
            success(111);
        }
    },
    
    receiveCouponByInput:function (success, fail) {
		receiveCouponSuccess = success;
		receiveCouponFail = fail;
		var couponid = $("#couponid").val()
      
        if (/iphone|ipad|ipod/.test(ua)) {

           //JsModelCoupon("receiveCouponSuccess","receiveCouponFail",couponid);

           window.webkit.messageHandlers.receiveCoupon.postMessage(couponid);
        }else if (/android/.test(ua)) {
        	//alert(window.title);
            alert("111111"+couponid);
			var result = window.JSBrigeInterface.receiveCoupon(couponid);
            success(result);
        } else {
            success(111);
        }
    }
	
/*	 //��ȡ�豸ID
    getDeviceInfo:function (success, fail) {
        getDeviceInfoSuccess = success;
        getDeviceInfoFail = fail;
        
        if (/iphone|ipad|ipod/.test(ua)) {

           JsModeldeviceInfo("getDeviceInfoSuccess","getDeviceInfoFail");

            
        }else if (/android/.test(ua)) {
        	
			var result = window.JSBrigeInterface.getDeviceInfo();
            success(result);
        } else {
            success(111);
        }
    },
	
	 //��ȡ�û�״̬
    getUserState:function (success, fail) {
        getUserStateSuccess = success;
        getUserStateFail = fail;
        
        if (/iphone|ipad|ipod/.test(ua)) {
        
           JsModeluserState("getUserStateSuccess","getUserStateFail");

            
        }else if (/android/.test(ua)) {
        	
			var result = window.JSBrigeInterface.getUserState();
            success(result);
        } else {
            success(111);
        }
    }*/


}

var receiveCouponSuccess, receiveCouponFail;
/*var getDeviceInfoSuccess,getDeviceInfoFail;
var getUserStateSuccess,getUserStateFail;*/