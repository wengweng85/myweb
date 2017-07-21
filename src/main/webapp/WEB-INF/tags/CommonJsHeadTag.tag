<%@ tag pageEncoding="utf-8" body-content="empty" small-icon=""  %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!--css及javascript引入开始-->
<script src="<c:url value='/resource/hplus/js/jQuery/all/jquery.js'/>"></script>
<script src="<c:url value='/resource/hplus/js/handlebars-v2.0.0-min.js'/>"></script>
<script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
<script src="<c:url value='/resource/hplus/js/plugins/codemirror/codemirror.js'/>"></script>
<script src="<c:url value='/resource/hplus/js/plugins/codemirror/mode/javascript/javascript.js'/>"></script>

  
<!-- data table  -->
<script src="<c:url value='/resource/hplus/js/plugins/dataTables/jquery.dataTables.js'/>"></script>
<script src="<c:url value='/resource/hplus/js/plugins/dataTables/dataTables.bootstrap.js'/>"></script>
    
<script src="<c:url value='/resource/hplus/js/rc.all-2.0.js'/>"></script>
    
    
<!-- 用于记录当前项目根目录供js调用 -->
<input type="hidden" id="contextPath" name="contextPath" value="${cp}"/>
<!-- 隐藏域用于重复校验验证 -->
<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}"/>

