<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
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
            <form  class="form-horizontal" id="query_form" action="<c:url value='/job/querylist'/>">
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
                <table class="table table-striped table-bordered table-hover " id="table_joblist">
                    <thead>
                        <tr>
                            <th>
				                <input type="checkbox" class="checkall" />
				            </th>
                            <th>任务名称</th>
                            <th>任务执行类名称</th>
                            <th>cron表达式</th>
                            <th>下一次执行时间</th>
                            <th>上一次执行时间</th>
                            <th>执行状态</th>
                            <th>执行类型</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>描述</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    var datatable;
    //页面模型数据准备
    var options={
    	//列模型	
	   	columns:[
			 {    
				 "data":null,
				 "sClass": "text-center",
				 "render": function (data, type, full, meta ) {
				     return "<input type='checkbox' name='' class='checkchild'  value='"+data.job_name+"'/>"
		          }
			 },    
  	         { "data": "job_name" },
  	         { "data": "job_class_name" }, 
  	         { "data": "cron_expression" }, 
  	         { "data": "next_fire_time" },
  	         { "data": "pre_fire_time" },
  	         { "data": "trigger_state" },
  	         { "data": "trigger_type" },
  	         { "data": "start_time" },
	  	     { "data": "end_time" },
	  	     { "data": "description" },
	  	     { 
	  	    	"data":null,
	  	        "render": function ( data, type, full, meta ) {
			       var tpl = $("#tpl").html();  
			  	   //预编译模板  
			  	   var template = Handlebars.compile(tpl);  
			  	   return template(data);
	  	         } 
	  	     }
   	     ],
		 //对应查询form
		 query_form_selector:'query_form'	   		
    };
    
    //checkbox全选
    $(".checkall").on('click',function () {
	    var check = $(this).prop("checked");
	    $(".checkchild").prop("checked", check);
	});
    
    //批量删除
    function batchdelete(){
       if ($(".checkchild:checked").length > 0){         
    	   var result = new Array();
           (".checkchild:checked").each(function () {
               if ($(this).is(":checked")) {
                   result.push($(this).attr("value"));
               }
           });
           ids=result.join(",");
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
    
    //初始化
    $(function(){
    	datatable=$('#table_joblist').tableinit(options);
    });
    
    //查询
    function query(){
    	datatable.dt.ajax.reload();
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