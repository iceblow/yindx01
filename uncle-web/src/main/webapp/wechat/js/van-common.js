
var appid = "zhika_wechat_1.0";
var appkey = "24a35d8b552d3b11a7230f4db05c7e8e";
var city = "诸暨市";
var serviceCity = "诸暨市";

// 临时
//var auth = "B0397A7ACE4A37815CEF32B5D8EEC78D";
//var userId = "21";
var auth = getItem('user_auth');
var userId = getItem('user_id');
var token = "";
var qiniuUrl = "http://ofc6sbq4f.bkt.clouddn.com/";

// 地图信息
var lng = getItem('user_lng'), lat = getItem('user_lat'), city = getItem('user_city'), citycode = getItem('user_citycode');

var apiBaseUrl = "http://www.hzdaoshun.com/uncle-web/api";
// var apiBaseUrl = "http://192.168.0.10:8081/uncle-web/api";

var vipHeader = ['../img/user/vip_zero.png', '../img/user/vip_one.png', '../img/user/vip_two.png',
        '../img/user/vip_three.png', '../img/user/vip_four.png','../img/user/vip_five.png', '../img/user/vip_six.png'
    ];

/**
 * 刷新本地缓存
 */
function refreshLocal() {
	auth = getItem('user_auth');
	userId = getItem('user_id');
}

/**
 * URL加密组合
 */
function createParameter(parameter, isAuth) {
    var timestamp = getNowFormatDate();

    parameter.appid = appid;
    parameter.appkey = appkey;
    parameter.accesstime = timestamp;
    parameter.accesskey = createAccesskey(parameter, timestamp);
    if (isAuth) {
        parameter.accesstoken = auth
    }

    return parameter;
}
function createUrl(actionUrl, parameter, isAuth) {
    var timestamp = getNowFormatDate();
    var url = apiBaseUrl + actionUrl + '?appid=' + appid + '&accesstime=' + timestamp;
    if (isAuth) {
        url += '&accesstoken=' + auth
    }
    url += '&accesskey=' + createAccesskey(parameter, timestamp);
    for (var key in parameter) {
        url += '&' + key + '=' + encodeURIComponent(parameter[key])
    }
    return url;
}
// url加密
function createAccesskey(parameter, timestamp) {
    var data = [];
    for (var key in parameter) {
        data.push(key);
    }
    data.sort();

    return hex_md5(timestamp + appid + appkey + data.join(""));
}
// 时间格式化
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if(month >= 1 && month <= 9) {
        month ="0" + month;
    }
    if(strDate >= 0 && strDate <= 9) {
        strDate ="0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        +" " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();

    return currentdate;
}
/**
 * 存储相关方法
 */
function setCookie(c_name,value,expiredays) {
    expiredays = expiredays ? expiredays : 365;
    var exdate = new Date();
    exdate.setDate(exdate.getDate()+expiredays);
    document.cookie = c_name+ "=" + encodeURIComponent(value) + "; path=/" +
        ((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
}
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return decodeURIComponent(document.cookie.substring(c_start, c_end))
        }
    }
    return "";
}
function setItem(name, value) {
    window.localStorage.setItem(name, JSON.stringify(value))
}
function getItem(name) {
    return JSON.parse(window.localStorage.getItem(name) || '[]')
}
/**
 * 方法扩展
 */
Array.prototype.contains = function (obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
};
Array.prototype.removeByValue = function(val) {
    for(var i=0; i<this.length; i++) {
        if(this[i] == val) {
            this.splice(i, 1);
            break;
        }
    }
}
// Array.prototype.jsonValue= function (val) {
//     for(var i=0; i<this.length; i++){
//
//     }

// }
var formatNumber = function (n) {
    return n < 10 ? "0" + n : n;
};
var getTodayHours = function () {
    if (today.getHours() == 23) {
        return 0;
    } else {
        return today.getHours() + 1;
    }
};