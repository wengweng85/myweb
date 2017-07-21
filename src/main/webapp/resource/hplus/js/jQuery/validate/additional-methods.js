/**
 * 页面校验增加的方法
 * 
 * @author wengsh
 * @date 2012-5-25
 */
var aCity = {
	11 : "北京",
	12 : "天津",
	13 : "河北",
	14 : "山西",
	15 : "内蒙古",
	21 : "辽宁",
	22 : "吉林",
	23 : "黑龙江",
	31 : "上海",
	32 : "江苏",
	33 : "浙江",
	34 : "安徽",
	35 : "福建",
	36 : "江西",
	37 : "山东",
	41 : "河南",
	42 : "湖北",
	43 : "湖南",
	44 : "广东",
	45 : "广西",
	46 : "海南",
	50 : "重庆",
	51 : "四川",
	52 : "贵州",
	53 : "云南",
	54 : "西藏",
	61 : "陕西",
	62 : "甘肃",
	63 : "青海",
	64 : "宁夏",
	65 : "新疆",
	71 : "台湾",
	81 : "香港",
	82 : "澳门",
	91 : "国外"
}
/**
 * 身份证号码校验
 */
jQuery.validator.addMethod("idcard", function(value, element) {
	if(value){
		var iSum = 0;
		var info = "";
		if (!/^\d{17}(\d|x)$/i.test(value))
			// return "你输入的身份证长度或格式错误";
			return false;
		value = value.replace(/x$/i, "a");
		if (aCity[parseInt(value.substr(0, 2))] == null)
			// return "你的身份证地区非法";
			return false;
		sBirthday = value.substr(6, 4) + "-" + Number(value.substr(10, 2))
				+ "-" + Number(value.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))
			// return "身份证上的出生日期非法";
			return false;
		for (var i = 17; i >= 0; i--)
			iSum += (Math.pow(2, i) % 11)* parseInt(value.charAt(17 - i), 11);
		if (iSum % 11 != 1)
			// return "你输入的身份证号非法";
			return false;
		return true;
	}else{
		return true;
	}
}, "你输入的身份证号不正确");
/**
 * 日期校验形如:1985-10-15
 */
jQuery.validator.addMethod("dateCH", function(value, element) {
	if(value){
		var r = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
		if (!r.test(value))
			return false;
		r.exec(value);
		date = parseInt(RegExp.$1,10) + "-" + parseInt(RegExp.$2,10) + "-" + parseInt(RegExp.$3,10);
		var d = new Date(date.replace(/-/g, "/"));
		if (date != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))
			return false;
		return true;
	}
	return true;
}, $.format("请输入正确的日期格式:1900-01-01"));
/**
 * 日期+时间(时分)校验形如:1985-10-15 10:11
 */
jQuery.validator.addMethod("date_time", function(value, element) {
	if(value){
		var r = /^(\d{4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
		if (!r.test(value))
			return false;
		r.exec(value);
		date = parseInt(RegExp.$1,10) + "-" + parseInt(RegExp.$2,10) + "-" + parseInt(RegExp.$3,10);
		var hour=parseInt(RegExp.$4,10);
		var minute=parseInt(RegExp.$5,10);
		var d = new Date(date.replace(/-/g, "/"));
		if (date != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())){
			return false;
		}else{
			if (hour<1||hour>23||minute<0||minute>59){  
	            return false  
	        }else{
	        	return true;
	        }
		}
		return true;
	}
	return true;
}, $.format("请输入正确的时间格式:1900-01-01 10:11"));
/**
 * 日期+时间(时分秒)校验形如:1985-10-15 10:11:12
 */
jQuery.validator.addMethod("date_fulltime", function(value, element) {
	if(value){
		var r = /^(\d{4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		if (!r.test(value))
			return false;
		r.exec(value);
		date = parseInt(RegExp.$1,10) + "-" + parseInt(RegExp.$2,10) + "-" + parseInt(RegExp.$3,10);
		var hour=parseInt(RegExp.$4,10);
		var minute=parseInt(RegExp.$5,10);
		var second=parseInt(RegExp.$6,10);
		var d = new Date(date.replace(/-/g, "/"));
		if (date != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())){
			return false;
		}else{
			if (hour<1||hour>23||minute<0||minute>59||second<0||second>59){  
	            return false  
	        }else{
	        	return true;
	        }
		}
		return true;
	}
	return true;
}, $.format("请输入正确的时间格式:1900-01-01 10:11:12"));
/**
 * 时分校验形如:10:11
 */
jQuery.validator.addMethod("time", function(value, element) {
	if(value){
		var r = /^(\d{1,2}):(\d{1,2})$/;
		if (!r.test(value))
			return false;
		r.exec(value);
		var hour=parseInt(RegExp.$1,10);
		var minute=parseInt(RegExp.$2,10);
		if (hour<1||hour>23 || minute<0||minute>59){  
            return false  
        }else{
        	return true;
        }
	}
	return true;
}, $.format("请输入正确的时间格式:10:11"));
/**
 * 时分秒校验形如:10:11:12
 */
jQuery.validator.addMethod("fulltime", function(value, element) {
	if(value){
		var r = /^(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		if (!r.test(value))
			return false;
		r.exec(value);
		var hour=parseInt(RegExp.$1,10);
		var minute=parseInt(RegExp.$2,10);
		var second=parseInt(RegExp.$3,10);
		if (hour<1||hour>23 ||minute<0||minute>59||second<0||second>59){  
            return false  
        }else{
        	return true;
        }
	}
	return true;
}, $.format("请输入正确的时间格式:10:11:12"));
/**
 * 中文
 */
jQuery.validator.addMethod("chinese", function(value, element) {
	var r = /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/
	if (!r.test(value))
		return false;
	return true;
}, "请输入正确的中文");
/**
 * 颜色
 */
jQuery.validator.addMethod("color", function(value, element) {
	if(value){
		var r = /^[a-fA-F0-9]{6}$/
		if (!r.test(value))
			return false;
		return true;	
	}else{
		return true;
	}
	
}, "请输入正确的颜色编码");
/**
 * 邮编
 */
jQuery.validator.addMethod("zipcode", function(value, element) {
	if(value){
		var r = /^\d{6}$/
		if (!r.test(value))
			return false;
		return true;
	}
	else{
		return true;
	}
}, "请输入正确的邮编");
/**
 * 手机号码
 */
jQuery.validator.addMethod("mobile", function(value, element) {
	if(value){
		var r = /^1[0-9]{10}$/
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
	
}, "请输入正确的手机号码");
/**
 * 座机号码
 */
jQuery.validator.addMethod("tel", function(value, element) {
	if(value){
		var r = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入正确的座机号码");
/**
 * 电话号码
 */
jQuery.validator.addMethod("phone", function(value, element) {
	if(value){
		var r1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
		var r2 = /^1[0-9]{10}$/
		if (r1.test(value)||r2.test(value))
			return true;
		return false;
	}else{
		return true;
	}
}, "请输入正确的电话号码");
/**
 * 用户名
 */
jQuery.validator.addMethod("username", function(value, element) {
	if(value){
		var r = /^\w+$/
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
	
}, "请输入正确的由数字、26个英文字母或者下划线组成的字符串");
/**
 * 字母
 */
jQuery.validator.addMethod("letter", function(value, element) {
	if(value){
		var r = /^[A-Za-z]+$/
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入正确的字母");
/**
 * 一个中文算两个字节,检验字节长度
 * bytemaxlength
 */
jQuery.validator.addMethod("maxlength", function(value, element, params) {
	if(value){
		var r=/[^\x00-\xff]/g;
		var v=value.replace(r,'__').length;
		return (v<=params)
	}else{
		return true;
	}
},$.validator.format("请输入长度最多是 {0} 个字节的字符串(一个中文算2个字节)"));

/**
 * 一个中文1个字符检验规则
 * hanzimaxlength
 */
jQuery.validator.addMethod("hanzimaxlength", function(value, element, params) {
	if(value){
		var v=value.length;
		return (v<=params)
	}else{
		return true;
	}
},$.validator.format("请输入长度最多是 {0} 个字"));
/**
 * 一个中文算两个字节,检验字节长度
 * byteminlength
 */
jQuery.validator.addMethod("minlength", function(value, element, params) {
	if(value){
		var r=/[^\x00-\xff]/g;
		var v=value.replace(r,'__').length;
		return (v>=params)
	}else{
		return true;
	}
},$.validator.format("请输入长度最多是 {0} 个字节的字符串(一个中文算2个字节)"));
/**
 * 一个中文算两个字节,检验字节长度
 * byterangelength
 */
jQuery.validator.addMethod("rangelength", function(value, element, params) {
	if(value){
		var r=/[^\x00-\xff]/g;
		var v=value.replace(r,'__').length;
		return (v>=params[0]&&v<=params[1])
	}else{
		return true;
	}
},$.validator.format("请输入长度介于 {0} 和 {1} 个字节字符串(一个中文算2个字节)"));
/**
 * 正整数
 * plus_digits
 */
jQuery.validator.addMethod("plus_digits", function(value, element) {
	if(value){
		var r = /^[1-9][0-9]*$/
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入正确的正整数");
/**
 * 正小数
 * positive_decimal
 */
jQuery.validator.addMethod("positive_decimal", function(value, element) {
	if(value){
		var r = /^[0].[0-9]+|[1-9][0-9]*.[0-9]+$/
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入正确的小数");

/**
 * 货币金额
 * currency
 */
jQuery.validator.addMethod("currency", function(value, element) {
	if(value){
		var r = /^[0-9]*(\.[0-9]{1,2})?$/;
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入正确的金额");

/**
 * 数字
 * digit
 */
jQuery.validator.addMethod("digit", function(value, element) {
	if(value){
		var r = /^[0-9]*(\.[0-9]+)?$/;
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入正确的数字");

/**开始、结束日期比较
*/
jQuery.validator.methods.compareDate = function(value, element, param) {
	var startDate = jQuery(param).val();
	var date1 =startDate.split("-");
	var date2 =value.split("-");
	return date1[0]+date1[1]+date1[2] <= date2[0]+date2[1]+date2[2];
};
/**
 * 邮政编码
 */
jQuery.validator.addMethod("postalcode", function(value, element) {
	if(value){
		var r = /^\d{6}$/ 
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入6位数字");

/**
 * 要求输入数字字段不能为负数
 */
jQuery.validator.addMethod("positivenumber", function(value, element) {
		//var r = parseInt(value);
		if (parseInt(value)<0)
		return false;
		return true;
}, "不能输入负数");

/**
 * 组织机构代码规则
 */
jQuery.validator.addMethod("organ_code", function(value, element) {
	if(value){
		var r = /^\w{7,8}-\w{1}$/ 
		if (!r.test(value))
			return false;
		return true;
	}else{
		return true;
	}
}, "请输入类似12345678-9的组织机构代码");
function checkIDCard(id) {
 var idNum = id;
 //alert(idNum);
 var errors = new Array(
  "验证通过",
  "身份证号码位数不对",
   "身份证含有非法字符",
  "身份证号码校验错误",
  "身份证地区非法"
 );
 //身份号码位数及格式检验
 var re;
 var len = idNum.length;
 //身份证位数检验
 if (len != 15 && len != 18) {
     return errors[1];
 } else if (len == 15) {
     re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
 } else {
     re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})([0-9xX])$/);
 }

 var area = { 11: "北京", 12: "天津", 13: "河北", 14: "山西",
     15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海",
     32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西",
     37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东",
     45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州",
     53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海",
     64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门",
     91: "国外"
 }

 var idcard_array = new Array();
 idcard_array = idNum.split("");

 //地区检验
 if (area[parseInt(idNum.substr(0, 2))] == null) {
     return errors[4];
 }

 //出生日期正确性检验
 var a = idNum.match(re);

 if (a != null) {
     if (len == 15) {
         var DD = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
         var flag = DD.getYear() == a[3] && (DD.getMonth() + 1) == a[4] && DD.getDate() == a[5];
     }
     else if (len == 18) {
         var DD = new Date(a[3] + "/" + a[4] + "/" + a[5]);
         var flag = DD.getFullYear() == a[3] && (DD.getMonth() + 1) == a[4] && DD.getDate() == a[5];
     }
     if (!flag) {
         //return false;
         return "身份证出生日期不对！"; 
     }
     //检验校验位
     if (len == 18) {
         S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
            + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
            + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
            + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
            + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
            + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
            + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
            + parseInt(idcard_array[7]) * 1
            + parseInt(idcard_array[8]) * 6
            + parseInt(idcard_array[9]) * 3;

         Y = S % 11;
         M = "F";
         JYM = "10X98765432";
         M = JYM.substr(Y, 1); //判断校验位

         //检测ID的校验位
         if (M == idcard_array[17]) {
             return true;
             //return ""; 
         }
         else {
             //return false;
             return errors[3];
         }
     }

 } else {
     //return false;
     return errors[2];
 }
 return true;
}
