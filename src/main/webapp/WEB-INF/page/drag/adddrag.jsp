<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360�����������webkit�ں˽���-->
    <title>�����Ӽ��༭ҳ��</title>
    <!-- cssͷ�ļ�  -->
    <rc:csshead/>
</head>
<body >
    <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form action="<c:url value='/drag/saveorupdate'/>" method="post" id="myform">
                        <div class="row">
                            <div class="col-sm-12 b-r">
                            <div class="form-group">
                                <input type="hidden" id="id" name="id" class="form-control" value="${design.id}">
                            </div>
                            <div class="form-group">
                                <label>����</label>
                                <input type="name" id="page_name"  name="page_name" placeholder="����" class="form-control" value="${design.name}" aria-required="true" aria-invalid="false" class="valid" validate="{required:true,messages:{required:'����������'}}">
                            </div>
                            <div class="form-group">
                                <label>����</label>
                                <input type="describe" id="page_describe"  name="page_describe" placeholder="����" class="form-control" value="${design.describe}" validate="{required:true,messages:{required:'����������'}}">
                            </div>
                             <div class="form-group">
                                <label>���ݲο�д��</label>
                                <textarea id="sample_serialized_data" readonly="readonly" >[{ "x": 0, "y": 0,"width": 12, "height": 12,"dataurl":"http://127.0.0.1:8080/myweb/resource/hplus/graph_echarts.html"} ]</textarea>
                            </div>
                            <div class="form-group">
                                <label>ҳ������</label>
                                <textarea id="serialized_data" name="serialized_data">${design.serialized_data}</textarea>
                            </div>
                         <div class="form-group" style="text-align: right;">
                              <button class="btn btn-primary " onclick="savePage()">��������</button>
                              <button class="btn btn-danger "  onclick="cc()">�ر�</button>
                         </div>
                       </div>
                       </form>
                    </div>
                </div>
             </div>
         </div>
        </div>
    </div>   
    <rc:jsfooter />
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
	      
	      
	    //��֤ ajax
	     rc.validAndAjaxSubmit($("#myform"),callback);
	    
	     function callback(response){
	   		if(response.success){
	   			  var obj=response.obj;
	          	  console.log(obj);
	          	  $('#'+obj).focus();
	          	  layer.msg(response.message);
	          	  if(response.success){
	                 parent.query(); 
	          	  }
	   		}
	   		else{
	   			alert(response.message);
	   		}
	   	}
	      
	    //����ҳ��������Ϣ
	    function savePage(){
	  	  $('#myform').submit();
	    }
	    //�رո�ҳ��
	    var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
	    function cc(){
	    	parent.layer.close(index); //��ִ�йر�
	    }
    </script>
</body>
</html>
