<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>api测试页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div id="input_content">
	        <!-- 人员选择基本信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>基本信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				       <!-- 注意textarea框不要放到form-group中 -->
					   <rc:textarea label="请求json串" property="json"    rows="3"  />
					   <rc:textarea label="返回json串" property="json_resutn"    rows="3"/>
			       </div>
		       </div>
	        </div>
	        <!-- 人员基本信息结束 -->
	        
	        <!-- 人员附加信息开始 -->
	        
	        <!-- 人员附加信息结束-->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-primary " onclick="api_test()"><i class="fa fa-save"></i>&nbsp;测试</a>
	              <a class="btn btn-danger " onclick="closeframe()"><i class="fa fa-remove"></i>&nbsp;关闭</a>
	         </div>
         </div>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    
    //api_test
    function api_test(){
    	 rc.ajax("<c:url value='/api/common/'/>", {json:$('#json').val()},function (response) {
 		    console.log(typeof response);
 		    console.log(JSON.parse(response));
 		    var res=JSON.parse(response)
 		    $('#json_resutn').val(res.name)
   		 }); 
    }
    
     //关闭
    function select_closeframe(){
    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    	parent.layer.close(index); //再执行关闭   
    }
  
    function demo_callback(response){
    	if(response.success){
           	alert(response.message);
           	parent.demo_query();
           	select_closeframe();
    	}
    	else{
    		alert(response.message);
    	}
    }
    
    </script>
</body>
</html>