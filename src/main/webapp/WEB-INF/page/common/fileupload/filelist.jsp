<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文件列表</title>
    <!-- css引入 -->
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div>
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
			            <rc:hidden property="file_bus_id" value="${filerecord.file_bus_id}"/>
			            <rc:select property="file_bus_type" label="业务类型" codetype="FILE_BUS_TYPE" value="${filerecord.file_bus_type}"  />
			            
			            <rc:select property="filename" label="文件名" codetype="FILENAME"   />
			            <!-- 
			            <rc:textedit property="file_name" label="文件名"/>
			            -->
			            <div class="col-sm-6" align="right">
		                   <a class="btn btn-info" onclick="file_query()"><i class="fa fa-search"></i>&nbsp;查询</a>
		                   <a class="btn btn-success" onclick="file_upload()"><i class="fa fa-upload"></i>&nbsp;文件上传</a>
		                   <a class="btn btn-success" onclick="img_upload()"><i class="fa fa-upload"></i>&nbsp;图片上传</a>
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
                <a class="link" onclick="file_download_by_id('{{file_uuid}}')"><i class="fa fa-download"></i>&nbsp;下载</a> 
                <a class="link" onclick="file_delete_by_id('{{file_uuid}}')" ><i class="fa fa-remove"></i>&nbsp;删除</a> 
            </script>
            
            <!-- toolbar -->
            <div id="toolbar" class="btn-group">
				 <button id="btn_delete" type="button" class="btn btn-danger" onclick="file_bat_delete()">
				 <span class="glyphicon glyphicon-remove" aria-hidden="false"></span>批量删除
				 </button>
			</div>
			
            <div class="ibox-content">
			    <table id="filetable" data-url="<c:url value='/common/fileload/getFileList'/>" 
			          data-click-to-select="false"
                      data-toolbar="#toolbar"
                      data-show-export="true"
                      data-page-size="10" >
			    <thead>
				    <tr>
				        <th data-checkbox="true" ></th>
				        <th data-formatter="file_indexFormatter">序号</th>
	                    <th data-field="file_name" >文件名</th>
	                    <th data-field="file_length" >文件大小(byte)</th>
	                    <th data-field="file_type" >文件类型</th>
	                    <th data-field="file_bus_type" >业务类型</th>
	                    <th data-formatter="file_jobnameFormatter">操作</th>
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
    var file_options={
    	formid:'query_form'
    }
    //初始化
    $(function(){
    	//$('.collapse-link').click();
    	$('#filetable').inittable(file_options);
    });
  
    
    //用户表格监听,双击 
    $('#filetable').on('dbl-click-row.bs.table', function (e, row, $element) {
    	file_view_by_id(row.aac001)
    });  
    //操作编辑
    function file_jobnameFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//预编译模板  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
    }
    
    function file_indexFormatter(value, row, index) {
        return index+1;
    }
    
    //查询
    function file_query(){
    	$('#filetable').refreshtable();
    	//$('.collapse-link').click();
    }
    
    //删除数据
    function file_delete_by_id(file_uuid){
   	  if(file_uuid){
   		layer.confirm('确定删除此文件吗？', function(index){
   			var url= "<c:url value='/common/fileload/deletebyid/'/>"+file_uuid;
   			rc.ajax(url, null,function (response) {
   				if(response.success){
   					$('#filetable').refreshtable();
   				}else{
   					alert(response.message);
   				}
   			});
   		});
   	  }else{
   		layer.alert('请先选择你要删除的数据');
   	  }
    }
    
    
    //下载 
    function file_download_by_id(file_uuid){
    	var url="<c:url value='/common/fileload/download'/>/"+file_uuid;
    	window.open(url);
    }
    
    //批量删除
    function file_bat_delete(){
   		var selections=$('#filetable').getAllTableSelections();
   	    //选中的值
   	    var selectnodes='';
   	    if(selections.length>0){
   	    	layer.confirm('确定批量删除这些数据吗？', function(index){
   	    	   for(i=0;i<selections.length;i++){
   	   	     	   var item=selections[i];
   	   	     	   selectnodes+=item.file_uuid+',';
   	   	       }
   	   		   rc.ajax("<c:url value='/common/fileload/batdelete'/>", {selectnodes:selectnodes},function (response) {
   	   		    	alert(response.message);
   	   		    	$('#filetable').refreshtable();
   	   		   }); 
   	    	});
    	  }else{
    		layer.alert('请先选择你要删除的数据');
    	  }
    }
    
    //文件上传
    function file_upload(){
    	layer.open({
	   		  type: 2,
	   		  title: '文件上传',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['30%', '40%'],
	   		  content: "<c:url value='/common/fileload/toFileUpload'/>?file_bus_id="+$('#file_bus_id').val()+"&file_bus_type="+$('#file_bus_type').val() //iframe的url
   		});
    } 
    
    function img_upload(){
    	layer.open({
	   		  type: 2,
	   		  title: '图片上传',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['60%', '90%'],
	   		  content: "<c:url value='/common/fileload/toImgUpload'/>?file_bus_id="+$('#file_bus_id').val()+"&file_bus_type="+$('#file_bus_type').val() //iframe的url
   		});
    } 
    
    </script>
</body>
</html>