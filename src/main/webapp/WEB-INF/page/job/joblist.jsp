<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://www.myweb.com/mywebtag" prefix="web" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>页面列表</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
     <!-- Data Tables -->
    <link href="<c:url value='/resource/hplus/css/plugins/dataTables/dataTables.bootstrap.css'/>" rel="stylesheet">
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
	                  <button type="button" class="btn btn-w-m btn-info" onclick="query()">查询</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="document.getElementById('query_form').reset()">重置</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="add_page()">新增</button>
	               </div>
		       </div>
	       </form>
	       </div>
        </div>
            
        <!-- 查询结果 -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询结果列表</h5>
            </div>
            <!-- 模型 -->
            <script id="tpl" type="text/x-handlebars-template" >
                <button type="button" class="btn btn-info" onclick="gotoedit('{{job_name}}')">编辑</button> 
	            <button type="button" class="btn btn-danger" onclick="pause('{{job_name}}')" >暂停</button> 
                <button type="button" class="btn btn-info" onclick="resume('{{job_name}}')" >恢复</button> 
                <button type="button" class="btn btn-danger" onclick="dd('{{job_name}}')" >删除</button> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                    <thead>
                        <tr>
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
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/handlebars-v2.0.0-min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
    
    <!-- data table extend -->
    <script src="<c:url value='/resource/hplus/js/plugins/dataTables/jquery.dataTables.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/dataTables/dataTables.bootstrap.js'/>"></script>

    <script src="<c:url value='/resource/drag/dragcommon.js'/>"></script>
    <script src="<c:url value='/resource/drag/draglist.js'/>"></script>
    <script type="text/javascript">
    var datatable;
    //页面模型数据准备
    var options={
    	//列模型	
	   	columns:[
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
	  	     { "data": null }
   	     ],
   	    //列自定义 
		columnDefs:[ {
  	         "targets": 10,
  	         "render": function ( data, type, full, meta ) {
		          var tpl = $("#tpl").html();  
		  	      //预编译模板  
		  	      var template = Handlebars.compile(tpl);  
		  	      return template(data);
  	         }
	  	  },
	  	  {
	          "targets": [0],
	          "visible": false
	      }
	    ],
	    //表格jquery selector
		datatable_selector:'.dataTables-example',
		//对应查询form
		query_form_selector:'#query_form'	   		
    };
    
    
    //初始化
    $(function(){
    	datatable=tableinit(options);
    });
    
    //查询
    function query(){
    	datatable.ajax.reload();
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
