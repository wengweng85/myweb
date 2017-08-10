<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo查看页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
            <table class="table table-bordered table-striped">
		            <tr>
		                 <td>姓名</td><td>${ac01.aac003}</td>
		            </tr>
		            <tr>
		                 <td>身份证号码</td><td>${ac01.aac002}</td>
		            </tr>
		            <tr>
		                 <td>性别</td><td>${ac01.aac004}</td>
		            </tr>
		            <tr>
		                 <td>民族</td><td>${ac01.aac005}</td>
		            </tr>
		            <tr>
		                 <td>学历</td><td>${ac01.aac011}</td>
		            </tr>
		            <tr>
		                 <td>出生日期</td><td>${ac01.aac006_string}</td>
		            </tr>
		            <tr>
		                 <td>政治面貌</td><td>${ac01.aac024}</td>
		            </tr>
		            <tr>
		                 <td>联系电话</td><td>${ac01.aae006}</td>
		            </tr>
		             <tr>
		                 <td>移动电话</td><td>${ac01.aac067}</td>
		            </tr>
		            <tr>
		                 <td>电子邮件</td><td>${ac01.aae015}</td>
		            </tr>
		             <tr>
		                 <td>地区选择</td><td>${ac01.aac007}</td>
		            </tr>
		            <tr>
		                 <td>备注</td><td>${ac01.aae013}</td>
		            </tr>
            </table>
		    <!-- 人员附加信息结束-->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-danger " onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;关闭</a>
	         </div>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
   
     //关闭
    function select_closeframe(){
    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    	parent.layer.close(index); //再执行关闭   
    }
  
    
    </script>
</body>
</html>