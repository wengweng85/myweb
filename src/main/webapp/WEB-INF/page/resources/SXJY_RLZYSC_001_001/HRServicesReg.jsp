<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>人力资源服务机构登记</title>
<rc:csshead />
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<form action="${contextpath}/demo/savedata">
			<div class="form-group" style="text-align: right;">
				<a class="btn btn-primary " onclick="demo_save_data()"><i
					class="fa fa-save"></i>&nbsp;保存</a>
			</div>
			<div id="input_content">
				<!-- 基本信息-->
				<div class="ibox ">
					<div class="ibox-title">
						<h5>基本信息</h5>
					</div>
					<div class="ibox-content">
						<div class="form-horizontal">
							<div class="form-group">
								<rc:textedit property="aef104" required="true" label="机构名称"
									validate="{required:true,chinese:true,maxlength:200,messages:{required:'机构名称不能为空'}}"
									cols="2,2" />
								<rc:textedit property="aab998" required="true" label="统一社会信用代码"
									cols="2,2"
									validate="{required:true,idcard:true,maxlength:18,messages:{required:'统一社会信用代码不能为空'}}" />
								<rc:select property="aef105"  required="true" label="机构状态"
									cols="2,2" codetype="AEF105"
									validate="{required:true,maxlength:20,messages:{required:'机构状态不能为空'}}" />
								
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
							    <rc:select property="AEF106" required="true" label="机构性质"
									cols="2,2" codetype="AEF106"
									validate="{required:true,messages:{required:'机构性质不能为空'}}" />
								<rc:select property="aef107" required="true" label="隶属关系"
									cols="2,2" codetype="AEF107"
									validate="{required:true,messages:{required:'隶属关系不能为空'}}" />
								<rc:date property="aef108" required="true" label="注册机关"
									cols="2,2"
									validate="{required:true,messages:{required:'注册机关不能为空'}}" />
								
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
							   <rc:select property="aef109" required="true" label="经营类型"
									cols="2,2" codetype="AEF109"
									validate="{required:true,messages:{required:'经营类型不能为空'}}" />
								<rc:select property="aef113" required="true" label="办公场地类型"
									cols="2,2" codetype="AEF113"
									validate="{required:true,messages:{required:'办公场地类型不能为空'}}" />
								<rc:textedit property="aef111"  label="办公地址"
									cols="2,2" />
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<rc:textedit property="aef112" required="true" label="邮政编码"
									cols="2,2"
									validate="{required:true,phone:true,messages:{required:'邮政编码不能为空'}}" />
								<rc:textedit property="aef114" label="办公场所面积" cols="2,2" />	 
								<rc:textedit property="aef117"  label="注册资金" cols="2,2"  />
									
									
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<rc:textedit property="aef131" required="true" label="许可证编号"
									cols="2,2"
									validate="{required:true,messages:{required:'许可证编号不能为空'}}" />
								<rc:textedit property="aef118"  label="开户银行"
									cols="2,2"  />
									
								<rc:textedit property="aef119" required="true" label="开户账号"
									cols="2,2"
									validate="{required:true,mobile:true,messages:{required:'开户账号不能为空'}}" />
								
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
							  <rc:textedit property="aef110"  label="经营范围"
									cols="2,10" value="求职、用人登记；职业介绍、职业指导；收集、发布职业供求、培训信息；为家庭用工提供中介服务；互联网职业信息服务；法律、法规和政策咨询"
									 />
							</div>
                             
						</div>
					</div>
				</div>
				<!-- 机构信息 -->
				<div class="ibox ">
					<div class="ibox-title">
						<h5>机构信息</h5>
					</div>
					<div class="ibox-content">
						<div class="form-horizontal">
							<div class="form-group">
								<rc:textedit property="aef121" required="true" label="机构联系人"
									validate="{required:true,chinese:true,maxlength:10,messages:{required:'机构联系人姓名不能为空'}}"
									cols="2,2" />
								<rc:textedit property="aef122" required="true" label="机构联系人电话"
									cols="2,2"
									validate="{required:true,idcard:true,messages:{required:'机构联系人电话不能为空'}}" />
								<rc:select property="aef123"  label="机构联系人电子邮箱"
									cols="2,2" />
							
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group" >
								<rc:select property="aac011" required="true" label="机构电话号码"
									cols="2,2" codetype="AAC011"
									validate="{required:true,messages:{required:'性别不能为空'}}" />
								<rc:textedit property="aac003"  label="机构传真号码" 
									validate="{required:true,chinese:true,maxlength:10,messages:{required:'姓名不能为空'}}"
									cols="2,2" />
								<rc:textedit property="aac002"  label="机构网站"
									cols="2,2"
									validate="{required:true,idcard:true,messages:{required:'身份证不能为空'}}" />
							</div>
						</div>
					</div>


					<!-- 法人信息 -->
					<div class="ibox ">
						<div class="ibox-title">
							<h5>法人信息</h5>
						</div>
						<div class="ibox-content">
							<div class="form-horizontal">
								<div class="form-group">
									<rc:textedit property="aac003" required="true" label="法人代表姓名"
										validate="{required:true,chinese:true,maxlength:10,messages:{required:'姓名不能为空'}}"
										cols="2,2" />
									<rc:textedit property="aac002" required="true" label="法人代表身份证号"
										cols="2,2"
										validate="{required:true,idcard:true,messages:{required:'身份证不能为空'}}" />
									<rc:select property="aac004" required="true" label="法人代表联系电话"
										cols="2,2" codetype="AAC004"
										onchange="demo_aac004_change_test()"
										validate="{required:true,messages:{required:'性别不能为空'}}" />
								</div>
								<div class="hr-line-dashed"></div>
							    <div class="form-group" >
							   	<rc:select property="aac005" required="true" label="法人代表电子邮箱"
										cols="2,2" codetype="AAC005"
										onchange="demo_aac004_change_test()"
										validate="{required:true,messages:{required:'民族不能为空'}}" />
								</div>
							</div>
						</div>
					</div>
		</form>
	</div>
	<rc:jsfooter />
	<script type="text/javascript">
		function select_demo_by_id(aac001) {
			rc.ajaxQuery("<c:url value='/demo/getDemoById'/>/" + aac001,
					$('#input_content'));
		}
		$(function() {
			//验证 ajax
			rc.validAndAjaxSubmit($("form"), demo_callback);
		})

		//保存页面配置信息
		function demo_save_data() {
			$('form').submit();
		}

		function demo_callback(response) {
			if (response.success) {
				alert(response.message);
			} else {
				alert(response.message);
			}

		}
	</script>
</body>
</html>