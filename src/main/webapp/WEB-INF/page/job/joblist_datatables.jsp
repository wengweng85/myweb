<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ҳ���б�</title>
    <rc:csshead/>
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
	                  <a class="btn btn-info" onclick="query()">��ѯ</a>
	                  <a class="btn btn-info" onclick="document.getElementById('query_form').reset()">����</a>
	                  <a class="btn btn-info" onclick="add_page()">����</a>
	               </div>
		       </div>
	       </form>
	       </div>
        </div>
            
        <!-- ��ѯ��� -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����б�</h5>
                <div class="ibox-tools">
                    <a onclick="batchdelete()" class="btn btn-danger btn-xs">����ɾ��</a>
                </div>
            </div>
            <!-- ģ�� -->
            <script id="tpl" type="text/x-handlebars-template" >
                <a  class="btn btn-info" onclick="gotoedit('{{job_name}}')">�༭</a> 
                {{#equals trigger_state 'WAITING'}}
	                 <a class="btn btn-danger" onclick="pause('{{job_name}}')" >��ͣ</a>
                {{/equals}} 
                {{#equals trigger_state 'PAUSED'}}
                     <a  class="btn btn-info" onclick="resume('{{job_name}}')" >�ָ�</a>
                {{/equals}} 
                <a  class="btn btn-danger" onclick="dd('{{job_name}}')" >ɾ��</a> 
            </script>
            <div class="ibox-content">
                <table class="table table-striped table-bordered table-hover " id="table_joblist">
                    <thead>
                        <tr>
                            <th>
				                <input type="checkbox" class="checkall" />
				            </th>
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
    <rc:jsfooter/>
    <script type="text/javascript">
    var datatable;
    //ҳ��ģ������׼��
    var options={
    	//��ģ��	
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
			  	   //Ԥ����ģ��  
			  	   var template = Handlebars.compile(tpl);  
			  	   return template(data);
	  	         } 
	  	     }
   	     ],
		 //��Ӧ��ѯform
		 query_form_selector:'query_form'	   		
    };
    
    //checkboxȫѡ
    $(".checkall").on('click',function () {
	    var check = $(this).prop("checked");
	    $(".checkchild").prop("checked", check);
	});
    
    //����ɾ��
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
		   layer.alert("������ѡ��һ����¼");                
		   return;
	   }
    }	 
    
    //��ʼ��
    $(function(){
    	datatable=$('#table_joblist').tableinit(options);
    });
    
    //��ѯ
    function query(){
    	datatable.dt.ajax.reload();
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