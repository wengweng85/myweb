<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>页面列表</title>
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
            <form  class="form-horizontal" id="query_form" >
		        <div class="form-group">
		           <label class="col-sm-1 control-label">名称</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_name" class="form-control">
		           </div>
		           <label class="col-sm-1 control-label">描述</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_describe" class="form-control"> 
		           </div>
	               <div class="col-sm-4">
	                  <a class="btn btn-info" onclick="query()">查询</a>
	                  <a class="btn btn-info" onclick="document.getElementById('query_form').reset()">重置</a>
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
                <a  class="btn btn-info" onclick="gotoedit('{{job_name}}')">编辑</a> 
                {{#equals trigger_state 'WAITING'}}
	                 <a class="btn btn-danger" onclick="pause('{{job_name}}')" >暂停</a>
                {{/equals}} 
                {{#equals trigger_state 'PAUSED'}}
                     <a  class="btn btn-info" onclick="resume('{{job_name}}')" >恢复</a>
                {{/equals}} 
                <a  class="btn btn-danger" onclick="dd('{{job_name}}')" >删除</a> 
            </script>
            <div class="ibox-content">
			    <table id="jobtable" data-url="<c:url value='/job/querylist'/>" >
			    <thead>
				    <tr>
				        <th data-checkbox="true" ></th>
				        <th data-formatter="indexFormatter">序号</th>
	                    <th data-field="job_class_name" >任务执行类名称</th>
	                    <th data-field="cron_expression" >cron表达式</th>
	                    <th data-field="next_fire_time" >下一次执行时间</th>
	                    <th data-field="pre_fire_time" >上一次执行时间</th>
	                    <th data-field="trigger_state" data-sortable="true">执行状态</th>
	                    <th data-field="trigger_type" >执行类型</th>
	                    <th data-field="start_time" >开始时间</th>
	                    <th data-field="end_time" >结束时间</th>
	                    <th data-field="description" >描述</th>
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
    var options={
    	formid:'query_form'
    }
    //初始化
    $(function(){
    	$('#jobtable').inittable(options);
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
    	$('#jobtable').refreshtable();
    }
    
    //数据编辑
    function edit(id){
    	var url = "<c:url value='/job/gotoedit'/>";
    	openwindow('editwindow',url+'/'+id); 
    }
     
    //暂停
    function pause(id){
    	var url = "<c:url value='/job/pause'/>"+"/"+id;
    	ajax(url,"确定暂停此任务吗")
    }
  
    //恢复
    function resume(id){
    	var url = "<c:url value='/job/resume'/>"+"/"+id;
    	ajax(url,"确定恢复此任务吗")
    }
    
    //删除
    function dd(id){
    	var url = "<c:url value='/job/delete'/>"+"/"+id;
    	ajax(url,"确定删除此任务吗")
    }
    
    
    //批量删除
    function batchdelete(){
       var selections=$('#jobtable').getAllTableSelections();
       //选中的值
       var ids='';
       if(selections.length>0){
		   for(i=0;i<selections.length;i++){
        	   var item=selections[i];
        	   ids+=item.job_name+',';
           }
    	   console.log(ids);
    	   rc.ajax("<c:url value='/job/batchdelete'/>", {ids:ids},function (response) {
		    	alert(response.message);
		    	if(response.success){
		    		query();
				}
		   });  
       }else{
    	   layer.alert("请至少选中一条记录");                
		   return;
       }
    }	
    
    
    function ajax(url,tip){
    	layer.confirm(tip,function(){
        	$.ajax({
                type : "get",
                url : url,
                dataType : "json",
                success:function(response,textStatus){
                	layer.msg(response.message);
                	query();
                },
                error : function() {
                    layer.msg('发生错误了');
                }
            });
    	})
    }
    //新增
    function add_page(){
    	layer.open({
	   		  type: 2,
	   		  title: '新增页面',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '90%'],
	   		  content: "<c:url value='/job/toadd'/>" //iframe的url
   		});
    }    

    </script>
</body>
</html>