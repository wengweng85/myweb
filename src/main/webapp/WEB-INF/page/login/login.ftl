<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="viewport" content="initial-scale=1.0, width=device-width" />
<title>陕西省人力资源管理信息系统</title>

<link href="${homeModule}/resource/hplus/css/bootstrap.min.css" rel="stylesheet">
<link href="${homeModule}/resource/hplus/css/font-awesome.min.css" rel="stylesheet">
    
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

a{color:#333;text-decoration:none; } //对全站有链接的文字颜色样式为color:#333;并立即无下划线text-decoration:none;
a:hover {color:#CC3300;text-decoration:none;}//对鼠标放到超链接上文字颜色样式变为color:#CC3300;并文字
</style>

</head>
<body>
	<!--中间 center-->
	<div id="center_right">
	<div id="text-title"><a>登录</a></div>
	<div id="text">
	<input type="hidden" id="publicKeyExponent" name="publicKeyExponent" value="${publicKeyExponent}"/>
	<input type="hidden" id="publicKeyModulus" name="publicKeyModulus" value="${publicKeyModulus}"/>
	<table border="0" align="left" class="maintext">
	<tr>
		<td >
		用户名：
		</td>
		<td>
		<input name="username" id="username" type="text" tabindex="1">
		</td>
	</tr>
	<tr>
		<td>
		密&nbsp;&nbsp;&nbsp;码：
		</td>
		<td>
		<input name="password" id="password"  type="password"   tabindex="2">
		</td>
	</tr>
	<tr>
		<td>
		验证码:
		</td>
		<td>
		<input name="verifycode"  id="verifycode" type="text" size="8" class="btn2" tabindex="3">
		<img id="verifiy_img" onclick="_reload_verfy_code()" src="${homeModule}/verifycode/create" style="width: 65px;height: 28px"/>
		</td>
	</tr>
 	<tr>
		<td colspan="2" align="center">
			<table>
				<tr>
					<td><button style="width:155px;" class="btn  btn-success btn-xs" onclick="login()">登录</button></td>
					<td>&nbsp;</td>
					<td><a style="width:155px;" target="_blank" href="${homeModule}register" class="btn  btn-primary btn-xs" >注册</a></td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
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
        
    });
	function keyEnter(e){
	    var currKey=0,e=e||event;
	    currKey=e.keyCode||e.which||e.charCode;
		if(currKey == 13) { 
			login();
		} 
	} 
	
	
	document.onkeydown =keyEnter; 

	function login(){
	    var username=$('#username').val();
    	if(!username){
    		layer.msg('用户名不能不空');
    		$('#username').focus();
    		return ;
    	}
      	var password=$('#password').val();
    	if(!password){
    		layer.msg('密码不能不空');
    		$('#password').focus();
    		return ;
    	} 
    	var verifycode=$('#verifycode').val();
    	if(!verifycode){
    		layer.msg('验证码不能不空');
    		$('#verifycode').focus();
    		return ;
    	} 
    	
    	RSAUtils.setMaxDigits(200);  
		var key = new RSAUtils.getKeyPair($('#publicKeyExponent').val(), "", $('#publicKeyModulus').val());  
		var encrypedPwd = RSAUtils.encryptedString(key,$('#password').val().split("").reverse().join("")); 
		$('#password').val(encrypedPwd); 
	    
	    var param = {username:username,password:$('#password').val(),verifycode:verifycode}
	    $.ajax({
            type : "post",
            url : "${homeModule}/login",
            dataType : "json",
            data: param,  //传入组装的参数
            success:function(response,textStatus){
            	if(response.success){
            	    window.location.href="${homeModule}";
            	}else{
            	    layer.msg(response.message);
            	    $('#password').val('');
            	    _reload_verfy_code();
            	}
            },
            error : function(response) {
                layer.msg(response.message);
                $('#password').val('');
            }
        });
	}
	
	function _reload_verfy_code(){
	    $('#verifiy_img').attr('src','${homeModule}/verifycode/create?d='+new Date());
	}
	
	//如果当前登录页面不是最顶层页面、跳出
	if (window != top){
	   top.location.href = location.href;  
	}  
</script>

</html>