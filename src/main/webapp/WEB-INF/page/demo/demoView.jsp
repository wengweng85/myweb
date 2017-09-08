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
            <table class="table table-bordered table-striped xedittable ">
                    <rc:hidden  property="aac001" value="${ac01.aac001}"/>
		            <tr>
		                 <td><strong>姓名</strong></td><td>${ac01.aac003} </td>
		                 <td><strong>身份证号码</strong></td><td>${ac01.aac002}</td>
		            </tr>
		            <tr>
		                 <td><strong>性别</strong></td><td><a href="#" id="aac004" data-type="select" data-source="[{value: 1, text: '男'},{value: 2, text: '女'}]" data-title="性别">${ac01.aac004}</a></td>
		                 <td><strong>民族</strong></td><td><a href="#" id="aac005" data-type="select" data-source="[{value: 1, text: '男'},{value: 2, text: '女'}]" data-title="性别">${ac01.aac005}</a></td>
		            </tr>
		            <tr>
		                 <td><strong>学历</strong></td><td><a href="#" id="aac011" data-type="select" data-source="[{value: 1, text: '男'},{value: 2, text: '女'}]" data-title="性别">${ac01.aac011}</a></td>
		                 <td><strong>出生日期</strong></td><td><a href="#" id="aac006" data-type="date" data-title="出生日期">${ac01.aac006_string}</td>
		            </tr>
		            <tr>
		                 <td><strong>政治面貌</strong></td><td><a href="#" id="aac024" data-type="text" data-title="政治面貌">${ac01.aac024}</a> </td>
		                 <td><strong>联系电话</strong></td><td><a href="#" id="aae006" data-type="text" data-title="联系电话">${ac01.aae006}</a> </td>
		            </tr>
		            <tr>
		                 <td><strong>移动电话</strong></td><td><a href="#" id="aac067" data-type="text" data-title="移动电话">${ac01.aac067}</a> </td>
		                 <td><strong>电子邮件</strong></td><td><a href="#" id="aae015" data-type="text" data-title="电子邮件">${ac01.aae015}</a> </td>
		            </tr>
		            <tr>
		                 <td><strong>地区选择</strong></td><td><a href="#" id="aac007" data-type="text" data-title="地区选择">${ac01.aac007}</a> </td>
		                 <td><strong>备注</strong></td><td><a href="#" id="aae013" data-type="text" data-title="地区选择">${ac01.aae013}</a></td>
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
   
     
    $(function () {
    	$('.xedittable a').each(function(){
    		$(this).editable({
    			pk: $('#aac001').val(),
    			url: function(params) {
	   			    var url=contextPath+'/demo/updateDataByXedit';
	   			    var param={};
	   			    param.aac001=params.pk;
	   			    param[params.name]=params.value;
		   			rc.ajax(url, param,function (response) {
		   				console.log(response)
		   			});
    			}
    		});
    	})
    });
    
  //删除数据
    function demo_save_data(){
   	   alert('demo_save_data');
    }
    
    </script>
</body>
</html>