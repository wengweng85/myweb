<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>页面列表</title>
    <!-- css头文件  -->
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
            <form  class="form-horizontal" id="query_form" action="<c:url value='/drag/querylist'/>">
		        <div class="form-group">
		           <label class="col-sm-1 control-label">名称</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_name" class="form-control">
		           </div>
		           <label class="col-sm-1 control-label">描述</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_describe" class="form-control"> 
		           </div>
		           <div class="col-sm-1">
		               <rc:select name="aac004" codetype="PERMTYPE" id="aac004" value="${v}"/>
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
            <!-- 模型 tpl  -->
            <script id="tpl" type="text/x-handlebars-template" >
                <button type="button" class="btn btn-info" onclick="gotoedit('{{id}}')">编辑</button> 
	            <button type="button" class="btn btn-info" onclick="edit('{{id}}')">页面可视化编辑</button>
	            <button type="button" class="btn btn-info" onclick="view('{{id}}')">页面预览</button>  
	            <button type="button" class="btn btn-danger" onclick="dd('{{id}}')" >删除</button> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>说明</th>
                            <th>数据</th>
                            <th>时间</th>
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
    <rc:jsfooter />
    <script type="text/javascript">
    var datatable;
    //页面模型数据准备
    var options={
    	//列模型	
	   	columns:[
  	         { "data": "id" },
  	         { "data": "page_name" },
  	         { "data": "page_describe" },
  	         { "data": "serialized_data" },
  	         { "data": "designtime" },
  	         { "data": null }
   	     ],
   	    //列自定义 
		columnDefs:[ {
  	         "targets": 5,
  	         "render": function ( data, type, full, meta ) {
		          var tpl = $("#tpl").html();  
		  	      //预编译模板  
		  	      var template = Handlebars.compile(tpl);  
		  	      return template(data);
  	         }
	  	  },
	  	  {
	          "targets": [0],
	          "visible": true
	      }
	    ],
	    //表格jquery selector
		datatable_selector:'.dataTables-example',
		//对应查询form
		query_form_selector:'#query_form'	   		
    };
    
    
    //初始化
    $(function(){
    	datatable=rc.tableinit(options);
    });
    
    //查询
    function query(){
    	datatable.ajax.reload();
    }
    
    
    //跳转至编辑页面
    function gotoedit(id){
    	layer.open({
   		  type: 2,
   		  title: '编辑页面',
   		  shadeClose: true,
   		  shade: 0.8,
   		  area: ['50%', '90%'],
   		  content: "<c:url value='/drag/toEdit'/>"+"/"+id //iframe的url
   		});
    }
	
    //数据编辑
    function edit(id){
    	var url = "<c:url value='/drag/gotoedit'/>";
    	openwindow('editwindow',url+'/'+id); 
    }
     
    //删除
    function dd(id){
    	layer.confirm('确定删除此页面配置吗',function(){
    		var url = "<c:url value='/drag/delete'/>"+"/"+id;
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
    
    //预览
    function view(id){
    	var url = "<c:url value='/drag/view'/>";
    	openwindow('viewwindow',url+'/'+id);
    }
    //打开新页面
    function openwindow(windoowname,url){
        //window.open(url,windoowname,'width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
         window.open(url,windoowname);
    }
    
    //新增
    function add_page(){
    	layer.open({
	   		  type: 2,
	   		  title: '新增页面',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '90%'],
	   		  content: "<c:url value='/drag/toadd'/>" //iframe的url
   		});
    }    
    
    </script>
</body>

</html>
