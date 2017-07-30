<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo测试编辑页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <form action="${contextpath}/demo/savedata" >
         <!-- 人员选择 -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>人员编辑</h5>
            </div>
            <div class="ibox-content">
	            <div class="form-horizontal" >
			        <div class="form-group">
			           <rc:textediticon property="aac001" label="人员选择" cols="1,4" url="${contextpath}/demo/toselect"  callback="select_demo_by_id" title="个人信息查询框"/>
			       </div>
		       </div>
	       </div>
        </div>
        
        <div id="input_content">
	        <!-- 人员选择基本信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>基本信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				        <div class="form-group">
			               <rc:textedit  property="aac003" required="true" label="姓名" validate="{required:true,chinese:true,maxlength:10,messages:{required:'姓名不能为空'}}" cols="1,2" />
			               <rc:textedit  property="aac002" required="true"  label="身份证" cols="1,2" validate="{required:true,idcard:true,messages:{required:'身份证不能为空'}}"/>
			               <rc:select property="aac004" required="true"  label="性别" cols="1,2"  codetype="AAC004"  onchange="demo_aac004_change_test()" validate="{required:true,messages:{required:'性别不能为空'}}"/>
			               <rc:select property="aac005" required="true"  label="民族" cols="1,2"  codetype="AAC005"  onchange="demo_aac004_change_test()" validate="{required:true,messages:{required:'民族不能为空'}}"/>
			           </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:select property="aac011" required="true"  label="学历" cols="1,2"  codetype="AAC011"  validate="{required:true,messages:{required:'性别不能为空'}}"/>
				           <rc:date property="aac006" required="true"  label="出生日期" cols="1,2" validate="{required:true,messages:{required:'出生日期不能为空'}}"/>
				           <rc:select property="aac033" required="true"  label="健康状况" cols="1,2"  codetype="AAC033" validate="{required:true,messages:{required:'健康状况不能为空'}}"/>
				           <rc:select property="aac017" required="true"  label="婚姻状况" cols="1,2"  codetype="AAC017" validate="{required:true,messages:{required:'婚姻状况不能为空'}}"/>
				       </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:select property="aac024" required="true"  label="政治面貌" cols="1,2"  codetype="AAC024" validate="{required:true,messages:{required:'政治面貌不能为空'}}"/>
				           <rc:textedit property="aae006" required="true" label="联系电话" cols="1,2" validate="{required:true,phone:true,messages:{required:'联系电话不能为空'}}"/>
				           <rc:textedit property="aac067" required="true" label="移动电话" cols="1,2" validate="{required:true,mobile:true,messages:{required:'移动电话不能为空'}}"/>
				           <rc:textedit property="aae015" required="true" label="电子邮件" cols="1,2" validate="{required:true,email:true,messages:{required:'电子邮件不能为空'}}"/>
				       </div>
			       </div>
		       </div>
	        </div>
	        <!-- 人员基本信息结束 -->
	        
	        <!-- 人员附加信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>附加信息</h5>
	            </div>
	            <div class="ibox-content">
		            
		       </div>
	        </div>
	        <!-- 人员附加信息结束-->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-primary " onclick="demo_save_data()"><i class="fa fa-save"></i>&nbsp;保存</a>
	         </div>
         </div>
        </form>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    function select_demo_by_id(aac001){
    	rc.ajaxQuery("<c:url value='/demo/getDemoById'/>/"+aac001,$('#input_content'));
    }
    $(function() {
    	//验证 ajax
    	rc.validAndAjaxSubmit($("form"),demo_callback);
    })
    
    //保存页面配置信息
	function demo_save_data(){
	   $('form').submit();
	}
    
    function demo_callback(response){
    	if(response.success){
           	alert(response.message);
    	}
    	else{
    		alert(response.message);
    	}
    	
    }

    </script>
</body>
</html>