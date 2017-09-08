<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link href="${homeModule}/resource/hplus/css/bootstrap.min.css" rel="stylesheet">
    <link href="${homeModule}/resource/hplus/css/font-awesome.min.css" rel="stylesheet">
    <link href="${homeModule}/resource/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${homeModule}/resource/hplus/css/style.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h3 >陕西人力资源市场管理系统</h3>
            </div>
            <form class="m-t" role="form"  method="post" action="" >
            <input type="hidden" id="publicKeyExponent" name="publicKeyExponent" value="${publicKeyExponent}"/>
	        <input type="hidden" id="publicKeyModulus" name="publicKeyModulus" value="${publicKeyModulus}"/>
                <div class="form-group">
                    <input type="text" id="username" name="username"  class="form-control" placeholder="用户名" >
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password"  oncopy="" class="form-control" placeholder="密码" >
                </div>
                <button type="button" class="btn btn-w-m btn-info full-width m-b" onclick="save()">登 录</button>
                <p class="text-muted text-center">
                <a href="#">
                    <small>忘记密码了？</small>
                </a>
                |
                <a href="${homeModule}register">注册一个新账号</a>
            </p>
            </form>  
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

	function save(){
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
                layer.msg(response.message);
                $('#password').val('');
            }
        });
	}
	
	
	function view(){
	    $.ajax({
            type : "post",
            url : "/sxrlzy//ViewComplaintsReportHistoryInfo",
            dataType : "json",
            data: param,  //传入组装的参数
            success:function(response,textStatus){
            	layer.msg(response.message);
            	if(response.success){
            	    window.location.href="/sxrlzy/";
            	}
            },
            error : function(response) {
                layer.msg(response.message);
            }
        });
	}
	
</script>

</html>
