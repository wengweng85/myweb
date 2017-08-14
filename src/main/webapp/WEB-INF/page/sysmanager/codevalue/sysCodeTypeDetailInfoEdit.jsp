<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码值明细结点编辑页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <form action="${contextpath}/codetype/saveOrUpdateCodeTypeDetail" >
        <div id="input_content">
	        <!-- 代码信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>代码值信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
		                <div class="form-group">
			               <rc:textedit  property="par_code_value" cols="2,4" value="${codevalue.par_code_value}" readonly="true"  label="上级代码值"   />
			               <rc:textedit  property="par_code_name" cols="2,4"  value="${codevalue.par_code_name}"  readonly="true"  label="上级代码名称" />
			           </div>
			           <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:hidden property="code_seq" value="${codevalue.code_seq}"/>
				           <rc:textedit  property="code_type" cols="2,4" value="${codevalue.code_type}" readonly="true"  label="代码类型"   />
			               <rc:textedit  property="code_value" cols="2,4" value="${codevalue.code_value}" readonly="true"  label="代码值"   />
			           </div>
			           <div class="hr-line-dashed"></div>
			           <div class="form-group">
			               <rc:textedit  property="code_name" cols="2,4"  value="${codevalue.code_name}"  required="true"  label="代码中文"  validate="{required:true,messages:{required:'代码中文不能为空'}}"/>
			               <rc:textedit  property="code_describe" cols="2,4"  value="${codevalue.code_describe}" required="false" label="代码中文描述"  />
			           </div>
			       </div>
		       </div>
	        </div>
	        <!-- 代码信息结束 -->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-primary " onclick="demo_save_data()"><i class="fa fa-save"></i>&nbsp;保存</a>
	              <a class="btn btn-danger " onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;关闭</a>
	         </div>
         </div>
        </form>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    $(function() {
    	//验证 ajax
    	rc.validAndAjaxSubmit($("form"),demo_callback);
    })
    
    //保存页面配置信息
	function demo_save_data(){
	   $('form').submit();
	}
    
     //关闭
    function select_closeframe(){
    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    	parent.layer.close(index); //再执行关闭   
    }
  
    function demo_callback(response){
    	if(response.success){
           	//alert(response.message);
           	parent.treenode_detail_edit_callback(response.obj);
           	select_closeframe();
    	}
    	else{
    		alert(response.message);
    	}
    }
    
    </script>
</body>
</html>