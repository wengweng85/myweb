<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码值树查询条件</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div id="input_content">
	        <!-- 代码信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				       <div class="form-group">
			               <rc:textedit  property="q_code_value" cols="2,4"  label="代码值"   />
			               <rc:textedit  property="q_code_name" cols="2,4"  label="代码中文"  />
			           </div>
			       </div>
		       </div>
	        </div>
	        <!-- 代码信息结束 -->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-primary " onclick="query()"><i class="fa fa-save"></i>&nbsp;查询</a>
	              <a class="btn btn-primary " onclick="reset()"><i class="fa fa-refresh"></i>&nbsp;重置</a>
	              <a class="btn btn-danger " onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;关闭</a>
	         </div>
         </div>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    $(function() {
    	$('#q_code_value').val(parent.$('#q_code_value').val());
    	$('#q_code_name').val(parent.$('#q_code_name').val());
    })
    
    function query(){
    	parent.$('#q_code_value').val($('#q_code_value').val());
    	parent.$('#q_code_name').val($('#q_code_name').val());
    	parent.code_value_query_detail_callback();
    	select_closeframe();
    }
    
    //重置
    function reset(){
    	$('#q_code_value').val('');
    	$('#q_code_name').val('');
    	query();
    }
    
     //关闭
    function select_closeframe(){
    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    	parent.layer.close(index); //再执行关闭   
    }
    </script>
</body>
</html>