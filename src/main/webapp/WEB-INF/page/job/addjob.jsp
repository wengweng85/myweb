<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360�����������webkit�ں˽���-->
     <title>�����Ӽ��༭ҳ��</title>
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
                                <label>��������</label>
                                <textarea id="description" name="description" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label>����</label>
                                <input type="describe" id="job_class_name"  name="job_class_name" placeholder="��ȫ��" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>���ʽ</label>
                                <input type="describe" id="cron_expression"  name="cron_expression" placeholder="���ʽ" class="form-control" >
                            </div>
	                        <div class="form-group" style="text-align: right;">
	                           <button class="btn btn-primary " onclick="savePage()" type="submit">��������</button>
	                           <button class="btn btn-danger "  onclick="cc()">�ر�</button>
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
    <script src="<c:url value='/resource/hplus/js/plugins/validate/jquery.validate.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/dragcommon.js'/>"></script>
    <script>

  //����ҳ��������Ϣ
    function savePage(){
    	var url="<c:url value='/job/saveorupdate'/>";
    	//if(cronValidate($('#cron_expression').val())){
    		
    		 $.ajax({
              type : "post",
              url : url,
              dataType : "json",
              data:$('.ibox-content .form-control').serializeObject(),
              success:function(response,textStatus){
            	  var obj=response.obj;
            	  console.log(obj);
            	  $('#'+obj).focus();
            	  layer.msg(response.message);
            	  if(response.success){
                  	  parent.query(); 
            	  }
              },
              error : function(response,textStatus) {
            	 layer.msg(response.message);
              }
          });
    	/* }else{
    		 layer.msg('���ʽ��ʽ����');
    	} */
    }
    //�رո�ҳ��
    var index = parent.layer.getFrameIndex(window.name); //�ȵõ���ǰiframe�������
    function cc(){
    	parent.layer.close(index); //��ִ�йر�
    }
    </script>
</body>
</html>
