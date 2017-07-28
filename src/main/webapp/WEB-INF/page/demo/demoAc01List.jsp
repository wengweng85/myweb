<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo测试页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- 查询条件 -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询条件</h5>
            </div>
            <div class="ibox-content">
	            <form class="form-horizontal" id="query_form" >
			        <div class="form-group">
			           <label class="col-sm-1 control-label">身份证</label>
			           <div class="col-sm-2">
			               <rc:textedit property="aac002" onchange="aac002_change_test()" />
			           </div>
			           <label class="col-sm-1 control-label">姓名</label>
			           <div class="col-sm-2">
			               <rc:textedit property="aac003"/>
			           </div>
			            <label class="col-sm-1 control-label">性别</label>
			           <div class="col-sm-2">
			               <rc:select property="aac004" codetype="AAC004" onchange="layer.msg('性别onchange,值为'+this.value)"/>
			           </div>
			           <label class="col-sm-1 control-label">学历</label>
			           <div class="col-sm-2">
			               <rc:select property="aac011" codetype="AAC011"/>
			           </div>
			       </div>
			       <div class="hr-line-dashed"></div>
			       <div class="form-group">
			           <label class="col-sm-1 control-label">出生日期(开始)</label>
			           <div class="col-sm-2">
			               <rc:date property="aac006_begin"/>
			           </div>
			           <label class="col-sm-1 control-label">出生日期(结束)</label>
			           <div class="col-sm-2">
			               <rc:date property="aac006_end"/>
			           </div>
			           <label class="col-sm-1 control-label">经办机构</label>
			           <div class="col-sm-2">
			               <rc:textedit property="aae009"/>
			           </div>
			           <div class="col-sm-3" align="right">
		                  <a class="btn btn-info" onclick="query()"  >查询</a>
		                  <a class="btn btn-info" onclick="rc.clean($('query_form'))">重置</a>
		                  <a class="btn btn-info" onclick="add_page()">新增</a>
		               </div>
			       </div>
		       </form>
	       </div>
        </div>
            
        <!-- 查询结果 -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询结果列表</h5>
                <div class="ibox-tools">
                    <a onclick="batchdelete()" class="btn btn-danger btn-xs">批量删除</a>
                </div>
            </div>
            <!-- 模型 -->
            <script id="tpl" type="text/x-handlebars-template" >
                <a  class="btn btn-info" onclick="gotoedit('{{aac001}}')">编辑</a> 
                <a  class="btn btn-danger" onclick="dd('{{aac001}}')" >删除</a> 
            </script>
            <div class="ibox-content">
			    <table id="ac01table" data-url="<c:url value='/demo/getAc01List'/>" 
			          data-click-to-select="true"
                      data-single-select="true"
                      data-page-size="10" >
			    <thead>
				    <tr>
				        <th data-checkbox="true" ></th>
				        <th data-formatter="indexFormatter">序号</th>
	                    <th data-field="aac002" >身份证号码</th>
	                    <th data-field="aac003" >姓名</th>
	                    <th data-field="aac004" >性别</th>
	                    <th data-field="aac005" >民族</th>
	                    <th data-field="aac006" >出生日期</th>
	                    <th data-field="aac033" >健康状况</th>
	                    <th data-field="aac017" >婚姻状况</th>
	                    <th data-field="aac024" >政治面貌</th>
	                    <th data-field="aac011" >学历</th>
	                    <th data-field="aae006" >联系电话</th>
	                    <th data-field="aae009" >经办机构名</th>
	                    <th data-formatter="jobnameFormatter">操作</th>
				    </tr>
			    </thead>
			    </table>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    
    function aac002_change_test(){
    	$aac002=$('#aac002').val();
    	layer.msg('身份证onchange,值为'+$aac002)
    }
    var options={
    	formid:'query_form'
    }
    //初始化
    $(function(){
    	$('#ac01table').inittable(options);
    });
  
    //操作编辑
    function jobnameFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//预编译模板  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
    }
    
    function indexFormatter(value, row, index) {
        return index+1;
    }
    
    //查询
    function query(){
    	$('#ac01table').refreshtable();
    }

    </script>
</body>
</html>