<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo测试查询页面</title>
    <!-- css引入 -->
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- 查询条件开始 -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询条件</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
	            <form class="form-horizontal" id="query_form" >
			        <div class="form-group">
                        <rc:textedit property="aac002" label="身份证" cols="1,2"/>
			            <rc:textedit property="aac003" label="姓名" cols="1,2"/>
			            <rc:select property="aac004" label="性别" cols="1,2" codetype="AAC004" multiple="true" />
			            <rc:select property="aac011" label="学历" cols="1,2" codetype="AAC011" multiple="true"/>
			       </div>
			       <div class="hr-line-dashed"></div>
			       <div class="form-group">
			            <rc:date property="aac006_begin" label="出生日期(开始)" cols="1,2"/>
			            <rc:date property="aac006_end" label="出生日期(结束)" cols="1,2"/>
			            <rc:textedit property="aae009" label="经办机构" cols="1,2"/>
			           <div class="col-sm-3" align="right">
		                  <a class="btn btn-info" onclick="demo_query()"><i class="fa fa-search"></i>&nbsp;查询</a>
		                  <a class="btn btn-info" onclick="rc.clean($('query_form'))"><i class="fa fa-refresh"></i>&nbsp;重置</a>
		                  <a class="btn btn-info" onclick="demo_add()"><i class="fa fa-plus"></i>&nbsp;新增</a>
		               </div>
			       </div>
		       </form>
	       </div>
        </div>
        <!-- 查询条件结束 -->
            
        <!-- 查询结果开始 -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询结果列表</h5>
                <div class="ibox-tools">
                </div>
            </div>
            <!-- 模型 -->
            <script id="tpl" type="text/x-handlebars-template" >
                <a class="btn btn-info" ><i class="fa fa-edit"></i>&nbsp;编辑</a> 
                <a class="btn btn-danger" onclick="demo_delete_by_id('{{aac001}}')" ><i class="fa fa-remove"></i>&nbsp;删除</a> 
            </script>
            
            <!-- toolbar -->
            <div id="toolbar" class="btn-group">
				 <button id="btn_delete" type="button" class="btn btn-default" onclick="demo_bat_delete()">
				 <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
				 </button>
			</div>
			
            <div class="ibox-content">
			    <table id="ac01table" data-url="<c:url value='/demo/getAc01List'/>" 
			          data-click-to-select="false"
                      data-toolbar="#toolbar"
                      data-page-size="10" >
			    <thead>
				    <tr>
				        <th data-checkbox="true" ></th>
				        <th data-formatter="demo_indexFormatter">序号</th>
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
	                    <th data-field="aae010" >经办人</th>
	                    <th data-field="aae009" >经办机构</th>
	                    <th data-field="aae036" >经办时间</th>
	                    <th data-field="aab800" >户籍所在省</th>
	                    <th data-formatter="demo_jobnameFormatter">操作</th>
				    </tr>
			    </thead>
			    </table>
            </div>
        </div>
       <!-- 查询结果结束 -->
    </div>
    <!-- javascript引入 -->
    <rc:jsfooter/>
    <script type="text/javascript">
    var demo_options={
    	formid:'query_form'
    }
    //初始化
    $(function(){
    	//$('.collapse-link').click();
    	$('#ac01table').inittable(demo_options);
    });
  
    
    //用户表格监听 
    $('#ac01table').on('click-row.bs.table', function (e, row, $element) {
    });  
    //操作编辑
    function demo_jobnameFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//预编译模板  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
    }
    
    function demo_indexFormatter(value, row, index) {
        return index+1;
    }
    
    //查询
    function demo_query(){
    	$('#ac01table').refreshtable();
    	//$('.collapse-link').click();
    }
    
    //删除数据
    function demo_delete_by_id(aac001){
   	  if(aac001){
   		layer.confirm('确定删除要此用户数据吗？', function(index){
   			var url= "<c:url value='/demo/deletebyid/'/>"+aac001;
   			rc.ajax(url, null,function (response) {
   				if(response.success){
   					$('#ac01table').refreshtable();
   				}else{
   					alert(response.message);
   				}
   			});
   		});
   	  }else{
   		layer.alert('请先选择你要删除的数据');
   	  }
    }
    
    
    //批量删除
    function demo_bat_delete(){
   		var selections=$('#ac01table').getAllTableSelections();
   	    //选中的值
   	    var selectnodes='';
   	    if(selections.length>0){
   			   for(i=0;i<selections.length;i++){
   	     	   var item=selections[i];
   	     	   selectnodes+=item.aac001+',';
   	       }
   		   rc.ajax("<c:url value='/demo/batdelete'/>", {selectnodes:selectnodes},function (response) {
   		    	alert(response.message);
   		    	$('#ac01table').refreshtable();
   		   }); 
   	    }else{
   	 	   layer.alert("请至少选中一条记录");                
   			   return;
   	    }
    }
    </script>
</body>
</html>