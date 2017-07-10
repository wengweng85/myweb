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
                <h1 class="logo-name">W</h1>
            </div>
            <h3>欢迎使用</h3>
            <form class="m-t" role="form"  method="post" action="" >
                <div class="form-group">
                    <input type="text" id="username" name="username"  class="form-control" placeholder="用户名" >
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password"  oncopy="" class="form-control" placeholder="密码" >
                </div>
                <button type="button" class="btn btn-w-m btn-info full-width" onclick="save()">登 录</button>
            </form>
        </div>
    </div>
    <script src="${homeModule}/resource/hplus/js/jquery.min.js"></script>
    <script src="${homeModule}/resource/hplus/js/bootstrap.min.js"></script>
    <script src="${homeModule}/resource/hplus/js/plugins/layer/layer.min.js"></script>
    <script src="${homeModule}/resource/drag/dragcommon.js"></script>
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
            	}
            },
            error : function(response) {
                layer.msg(response.message);
            }
        });
	}
	
</script>

</html>
