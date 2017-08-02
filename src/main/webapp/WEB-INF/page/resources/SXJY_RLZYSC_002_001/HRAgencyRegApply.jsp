<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人力资源服务机构设立申请</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <form action="${contextpath}/hragencyapply/savedata" >
        <div id="input_content">
	        <!-- 机构基本信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>机构基本信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				        <div class="form-group">
			               <rc:textedit  property="aef104" required="true" label="机构名称" cols="2,4" validate="{required:true,chinese:true,maxlength:100,messages:{required:'机构名称不能为空'}}"  />
			               <rc:textedit  property="aab998" required="false"  label="统一社会信用代码" cols="2,3" validate="{required:true,maxlength:18,messages:{required:'统一社会信息用代码不能为空'}}"/>
			           </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				       	   <rc:select property="aef105" required="false"  label="机构状态" cols="2,2"  codetype="AAC004" />
				       	   <rc:select property="aef106" required="false"  label="机构性质" cols="2,2"  codetype="AAC005" />
				           <rc:select property="aef107" required="false"  label="隶属关系" cols="2,2"  codetype="AAC011" />
				       </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				       <rc:textedit property="aef108" required="false"  label="注册机关" cols="2,2"  />
				       <rc:textedit property="aef112" required="false" label="邮政编码" cols="2,2"  />
				       <rc:select property="aef109" required="false"  label="经营类型" cols="2,2"  codetype="AAC033"  />
				       </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:select property="aef113" required="false" label="办公场地类型" cols="2,2"  codetype="AAC024"/>
				           <rc:textedit property="aef114" required="false"  label="办公场所面积" cols="2,2" />
				       </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				            <rc:textedit property="aef111" required="false"  label="办公地址" cols="2,4" />
				       </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:textedit property="aef117" required="false" label="注册资金" cols="2,2"  />
				           <rc:textedit property="aef118" required="false" label="开户银行" cols="2,2"  />
				           <rc:textedit property="aef119" required="false" label="开户账号" cols="2,2"  />
				       </div>
			       </div>
		       </div>
	        </div>
	        <!-- 机构基本信息结束 -->
	        
	        <!-- 机构联系信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>机构联系信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				        <div class="form-group">
			               <rc:textedit  property="aef121" required="false" label="机构联系人" cols="2,2" />
			               <rc:textedit  property="aef122" required="false"  label="机构联系人电话" cols="2,2" validate="{phone:true}"/>
			               <rc:textedit property="aef123" required="false"  label="机构联系人电子邮箱" cols="2,2"  validate="{email:true}"/>
			           </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				       	   <rc:textedit property="aef115" required="false"  label="机构电话号码" cols="2,2" validate="{phone:true}"/>
				           <rc:textedit property="aef116" required="false"  label="机构传真号码" cols="2,2"/>
				           <rc:textedit property="aef124" required="false"  label="机构网站" cols="2,2"  />
				       </div>
			       </div>
		       </div>
	        </div>
	        <!-- 机构联系信息结束 -->
	         <!-- 机构法人信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>机构法人信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
				        <div class="form-group">
			               <rc:textedit  property="aef125" required="false" label="法人代表姓名" cols="2,2"  />
			               <rc:textedit  property="aef126" required="false"  label="法人代表身份证号" cols="2,3" validate="{idcard:true,maxlength:18}"/>
			           </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				       	   <rc:textedit property="aef127" required="false"  label="法人代表联系电话" cols="2,2"/>
				       	   <rc:textedit property="aef128" required="false"  label="法人代表电子邮箱" cols="2,2"/>
				       </div>
			       </div>
		       </div>
	        </div>
	        <!-- 机构法人信息结束 -->
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