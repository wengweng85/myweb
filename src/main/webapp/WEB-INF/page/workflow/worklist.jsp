<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://www.myweb.com/mywebtag" prefix="web" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ҳ���б�</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
     <!-- Data Tables -->
    <link href="<c:url value='/resource/hplus/css/plugins/dataTables/dataTables.bootstrap.css'/>" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- ��ѯ���� -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����</h5>
            </div>
            <div class="ibox-content">
            <form  class="form-horizontal" id="query_form" action="<c:url value='/job/querylist'/>">
		        <div class="form-group">
		           <label class="col-sm-1 control-label">����</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_name" class="form-control">
		           </div>
		           <label class="col-sm-1 control-label">����</label>
		           <div class="col-sm-2">
		               <input type="text" name="page_describe" class="form-control"> 
		           </div>
	               <div class="col-sm-4">
	                  <button type="button" class="btn btn-w-m btn-info" onclick="query()">��ѯ</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="document.getElementById('query_form').reset()">����</button>
	                  <button type="button" class="btn btn-w-m btn-info" onclick="add_page()">����</button>
	               </div>
		       </div>
	       </form>
	       </div>
        </div>
            
        <!-- ��ѯ��� -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����б�</h5>
            </div>
            <!-- ģ�� -->
            <script id="tpl" type="text/x-handlebars-template" >
                <button type="button" class="btn btn-info" onclick="gotoedit('{{job_name}}')">�༭</button> 
	            <button type="button" class="btn btn-danger" onclick="pause('{{job_name}}')" >��ͣ</button> 
                <button type="button" class="btn btn-info" onclick="resume('{{job_name}}')" >�ָ�</button> 
                <button type="button" class="btn btn-danger" onclick="dd('{{job_name}}')" >ɾ��</button> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                    <thead>
                        <tr>
                            <th>��������</th>
                            <th>����ִ��������</th>
                            <th>cron���ʽ</th>
                            <th>��һ��ִ��ʱ��</th>
                            <th>��һ��ִ��ʱ��</th>
                            <th>ִ��״̬</th>
                            <th>ִ������</th>
                            <th>��ʼʱ��</th>
                            <th>����ʱ��</th>
                            <th>����</th>
                            <th>����</th>
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
    //ҳ��ģ������׼��
    var options={
    	//��ģ��	
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
   	    //���Զ��� 
		columnDefs:[ {
  	         "targets": 10,
  	         "render": function ( data, type, full, meta ) {
		          var tpl = $("#tpl").html();  
		  	      //Ԥ����ģ��  
		  	      var template = Handlebars.compile(tpl);  
		  	      return template(data);
  	         }
	  	  },
	  	  {
	          "targets": [0],
	          "visible": false
	      }
	    ],
	    //���jquery selector
		datatable_selector:'.dataTables-example',
		//��Ӧ��ѯform
		query_form_selector:'#query_form'	   		
    };
    
    
    //��ʼ��
    $(function(){
    	datatable=tableinit(options);
    });
    
    //��ѯ
    function query(){
    	datatable.ajax.reload();
    }
    
	
    //���ݱ༭
    function edit(id){
    	var url = "<c:url value='/job/gotoedit'/>";
    	openwindow('editwindow',url+'/'+id); 
    }
     
    //��ͣ
    function pause(id){
    	var url = "<c:url value='/job/pause'/>"+"/"+id;
    	ajax(url,"ȷ����ͣ��������")
    }
  
    //�ָ�
    function resume(id){
    	var url = "<c:url value='/job/resume'/>"+"/"+id;
    	ajax(url,"ȷ���ָ���������")
    }
    
    //ɾ��
    function dd(id){
    	var url = "<c:url value='/job/delete'/>"+"/"+id;
    	ajax(url,"ȷ��ɾ����������")
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
                    layer.msg('����������');
                }
            });
    	})
    }
    //����
    function add_page(){
    	layer.open({
	   		  type: 2,
	   		  title: '����ҳ��',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '90%'],
	   		  content: "<c:url value='/job/toadd'/>" //iframe��url
   		});
    }    
    
    </script>
</body>

</html>
