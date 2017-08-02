<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="viewport" content="initial-scale=1.0, width=device-width" />
<title>陕西省人力资源管理信息系统</title>
<style type="text/css">
body{
    background:url(resource/hplus/img/loginDialog/background.jpg) no-repeat 0 0 scroll;
}
#text-title{
	position: absolute;left: 40px;top:30px;color:#4e4e4e;font-size:24px;font-family:'Microsoft YaHei';
	width:319px;height:60px;border-bottom:2px solid #6c6c6d;
}
.maintext{
position: absolute;left: 40px;top:120px;font-family:'Microsoft YaHei';
color:#4e4e4e;font-size:18px;
}
.maintext tr{
height: 50px;
}
/*核心中间部分内容*/
#center_right{
 float:right;width:390px; height:400px; background:url(resource/hplus/img/loginDialog/text_backbg.png) no-repeat;
 top: 250px;position: fixed;right: 10%;
}
#foot{
 width:100%; position: fixed;top:720px;
}
#foot_bady{
text-align:center;
}
#foot_bady span{ 
  font-size:14px; color:black; font-family:'宋体';
}
.btn{ 
  width:241px; border:1px #B2B2B2 solid; height:30px; line-height: 30px;
}
.btn2{ 
  width:120px; border:1px #B2B2B2 solid; height:30px; line-height: 30px;
}
.login a {
	position: absolute;
    left: 45%;
    top: 20%;
}
.login{
	position: absolute;
	left:0px;
    top: 165px;
    font-family:'Microsoft YaHei';
	color:#ebf0f1;font-size:18px;
}
.verifycode{
 	position: absolute;
    width: 80px;
    height: 30px;
    top: 118px;
    left: 200px;
    text-align:center;
}
.verifycode img{
	vertical-align:middle;
}
</style>

</head>
<body>
	<!--中间 center-->
	<div id="center_right">
	<div id="text-title"><a>登录</a></div>
	<div id="text">
	<form name="form" method="post"  >
	<input type="hidden" id="publicKeyExponent" name="publicKeyExponent" value="${publicKeyExponent}"/>
	<input type="hidden" id="publicKeyModulus" name="publicKeyModulus" value="${publicKeyModulus}"/>
	<table border="0" align="left" class="maintext">
	<tr>
		<td >
		用户名：
		</td>
		<td>
		<input name="username" id="username" type="text" class="btn" tabindex="1">
		</td>
	</tr>
	<tr>
		<td>
		密&nbsp;&nbsp;&nbsp;码：
		</td>
		<td>
		<input name="password" id="password"  type="password" class="btn"  tabindex="2">
		</td>
	</tr>
	<tr>
		<td>
		验证码:
		</td>
		<td>
		<input name="verifycode" type="text" size="8" class="btn2" tabindex="3">
		<div class="verifycode">
		<img src="${homeModule}/verifycode/create" style="width: 65px;height: 28px"/>
		</div>
		</td>
	</tr>
 	<tr>
		<td colspan="2" align="center">
		<div class="login">
		  <input name="submitbtn" onclick="login()" type="image" src="resource/hplus/img/loginDialog/button.png" tabindex="4">
		</div>
		</td>
	</tr>
	</table>
	</form>
	</div>
	</div>
	<div id="foot">
	<div id="foot_bady">
	<span>技术支持：浙江网新恩普软件有限公司<br><br>总机：0571-88911222 客服热线：0571-88933535</span>
	</div>
	</div>
	<script src="${homeModule}/resource/hplus/js/jQuery/all/jquery.js"></script>
    <script src="${homeModule}/resource/hplus/js/bootstrap.min.js"></script>
    <script src="${homeModule}/resource/hplus/js/plugins/layer/layer.min.js"></script>
    <script src="${homeModule}/resource/hplus/js/rc.all-2.0.js"></script>
    <script src="${homeModule}/resource/hplus/js/RSA.js"></script>
</body>
<script type="text/javascript">
    $(function(){
        $("#username").focus();
    });
	function keyEnter(e){
	    var currKey=0,e=e||event;
	    currKey=e.keyCode||e.which||e.charCode;
		if(currKey == 13) { 
			save();
		} 
	} 
	document.onkeydown =keyEnter; 

	function login(){
	    var username=$('#username').val();
    	if(!username){
    		alert('用户名不能不空');
    		$('#username').focus();
    		return ;
    	}
      	var password=$('#password').val();
    	if(!password){
    		alert('密码不能不空');
    		$('#password').focus();
    		return ;
    	} 
    	
    	RSAUtils.setMaxDigits(200);  
		var key = new RSAUtils.getKeyPair($('#publicKeyExponent').val(), "", $('#publicKeyModulus').val());  
		var encrypedPwd = RSAUtils.encryptedString(key,$('#password').val().split("").reverse().join("")); 
		$('#password').val(encrypedPwd); 
	    
	    var param = $('form').serializeObject();
	    $.ajax({
            type : "post",
            url : "${homeModule}/login",
            dataType : "json",
            data: param,  //传入组装的参数
            success:function(response,textStatus){
            	layer.msg(response.message);
            	if(response.success){
            	    window.location.href="${homeModule}";
            	}else{
            	    $('#password').val('');
            	}
            },
            error : function(response) {
                alert(response.message);
                $('#password').val('');
            }
        });
	}
	
</script>

</html>