<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
     <title>新增加及编辑页面</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/plugins/codemirror/codemirror.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/plugins/codemirror/ambiance.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
</head>
<body >
    <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12 b-r">
                            <div class="form-group">
                                <input type="hidden" id="id" name="id" class="form-control" value="${design.id}">
                            </div>
                            <div class="form-group">
                                <label>名称</label>
                                <input type="name" id="page_name"  name="page_name" placeholder="名称" class="form-control" value="${design.name}" aria-required="true" aria-invalid="false" class="valid">
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input type="describe" id="page_describe"  name="page_describe" placeholder="描述" class="form-control" value="${design.describe}">
                            </div>
                             <div class="form-group">
                                <label>数据参考写法</label>
                                <textarea id="sample_serialized_data" readonly="readonly" >[{ "x": 0, "y": 0,"width": 12, "height": 12,"dataurl":"http://127.0.0.1:8080/myweb/resource/hplus/graph_echarts.html"} ]</textarea>
                            </div>
                            <div class="form-group">
                                <label>页面数据</label>
                                <textarea id="serialized_data" name="serialized_data">${design.serialized_data}</textarea>
                            </div>
                         <div class="form-group" style="text-align: right;">
                              <button class="btn btn-primary " onclick="savePage()" type="submit">保存内容</button>
                              <button class="btn btn-danger "  onclick="cc()">关闭</button>
                         </div>
                        </div>
                    </div>
                </div>
             </div>
         </div>
        </div>
    </div>   
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/codemirror/codemirror.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/codemirror/mode/javascript/javascript.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/validate/jquery.validate.min.js'/>"></script>
    <script>
      var editor_sample; 
      var editor_one;    
      $(document).ready(function(){
   	    editor_sample=CodeMirror.fromTextArea(
				document.getElementById("sample_serialized_data"),
		            {
						lineNumbers:true,
						theme:"ambiance"
					}
				);
   	    editor_sample.setSize('auto','100px');
   	    
        editor_one=CodeMirror.fromTextArea(
		document.getElementById("serialized_data"),
            {
				lineNumbers:true,
				theme:"ambiance"
			}
		);
        editor_one.setSize('auto','250px');
      });
      
      //保存页面配置信息
      function savePage(){
    	var name=$('#page_name').val();
    	 
      	var url="<c:url value='/drag/saveorupdate'/>"
      	$.ajax({
              type : "post",
              url : url,
              dataType : "json",
              data:{
              	  id:$('#id').val(),
              	  serialized_data:editor_one.getValue(),
              	  name:$('#page_name').val(),
              	  describe:$('#page_describe').val()
              },
              success:function(response,textStatus){
            	  var obj=response.obj;
            	  console.log(obj);
            	  $('#'+obj).focus();
            	  layer.msg(response.message);
            	  if(response.success){
                  	  parent.query(); 
            	  }
              },
              error : function() {
              	layer.msg('发生错误了');
              }
          });
      }
      //关闭父页面
      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
      function cc(){
      	parent.layer.close(index); //再执行关闭
      }
    </script>
</body>
</html>
