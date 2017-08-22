<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
     <title>文件上传</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/plugins/webuploader/webuploader.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/demo/webuploader-demo.min.css'/>" rel="stylesheet" >
</head>
<body >
    <body class="gray-bg" style="overflow-x: hidden;">
    <div >
    <div class="row">
       <div class="col-sm-12">
           <div class="ibox float-e-margins">
               <div class="ibox-content">
                   <div class="page-container">
                       <div id="uploader" class="wu-example">
                           <div class="queueList">
                               <div id="dndArea" class="placeholder">
                                   <div id="filePicker"></div>
                                   <p>或将照片拖到这里，单次最多可选300张</p>
                               </div>
                           </div>
                           <input type="hidden" id="file_bus_id" value="${filerecord.file_bus_id}">
                           <input type="hidden" id="file_bus_type" value="${filerecord.file_bus_type}">
                           <input type="hidden" id="upload_callback" value="${filerecord.upload_callback}">
                           <div class="statusBar" style="display:none;">
                               <div class="progress">
                                   <span class="text">0%</span>
                                   <span class="percentage"></span>
                               </div>
                               <div class="info"></div>
                               <div class="btns">
                                   <div id="filePicker2"></div>
                                   <div class="uploadBtn">开始上传</div>
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
    <script type="text/javascript">
        var BASE_URL = 'js/plugins/webuploader';
        var contextPath='<c:url value="/"/>';
        var upload_callback=function(file_uuid){
        	parent.${filerecord.upload_callback}('${filerecord.file_bus_id}',file_uuid)
        }
    </script>
    <script src="<c:url value='/resource/hplus/js/plugins/webuploader/webuploader.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/rc.webuploader.img.js'/>"></script>
</body>
</html>
