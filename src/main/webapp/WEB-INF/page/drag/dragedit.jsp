<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
     <title>页面可视化编辑-${design.name}</title>
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/drag/dist/gridstack.css'/>" rel="stylesheet">
    <style type="text/css">
        .grid-stack {
            background-color: lightgoldenrodyellow;
            border: 1px dashed #ccc;
            width:1080px;
            height:200px;
        }
        .grid-stack-item-content {
            background-color: #18bc9c;
            border: 1px dashed lightgoldenrodyellow;
        }
        
    
        /* float */
        .float-div,.float-open{background:#fbfbfb;border:2px solid #e1e1e1;border-left:0 none;border-top-right-radius:4px;border-bottom-right-radius:4px;box-shadow:1px 1px 2px rgba(0, 0, 0, 0.5);display:inline-block;font-size:12px;}
        .float-div{height:300px;left:0px;padding:10px 15px;width:150px;z-index:100;top:117px;_margin-top:117px;}
        .float-open{height:32px;width:32px;left:-70px;padding:0px;z-index:99;top:206px;_margin-top:206px;}
        .float-div,.float-open{position:fixed;*zoom:1;_position:absolute;_top:expression(eval(document.documentElement.scrollTop));}
        .float-close{background:url('/myweb/resource/drag/img/close_32px.png') no-repeat left top;overflow:hidden;opacity:.6;filter:alpha(opacity=60);position:absolute;right:-20px;top:-20px;text-indent:100%;white-space:nowrap;width:32px;height:32px;}
        .open-btn{background:url('/myweb/resource/drag/img/right_32px.png') no-repeat left top;display:block;overflow:hidden;opacity:.6;filter:alpha(opacity=60);text-indent:100%;white-space:nowrap;width:32px;height:32px;}
        .float-close:hover,.open-btn:hover{opacity:1;filter:alpha(opacity=100);}
    </style>
    
</head>

<body>
    <input type="hidden" id="id" value="${design.id}">
    <div style="display: none;">
	    <textarea id="saved-data">
	    ${design.serialized_data}
	    </textarea>
    </div>
      
    </script>
    <div class="float-open" id="float-open" style="left:-2px;"><a class="open-btn" href="javascript:void(0);">&gt;</a></div> 
	<div class="float-div" id="float-div" style="left:-450px;"> 
	    <a class="float-close" href="javascript:void(0);">X</a> 
          <h4>操作</h4> 
          <p>
             <button type="button" class="btn btn-w-m btn-info" onclick="add_new_widget()">新增</button>
          </p>
          <p>
		     <button type="button" class="btn btn-w-m btn-info" onclick="savedata()">保存</button>
		  </p>
		  <p>
		     <button type="button" class="btn btn-w-m btn-info" onclick="view('${design.id}')">预览 </button>
		  </p>
		  <p>
		     <button type="button" class="btn btn-w-m btn-info" onclick="goback()">返回 </button>
		  </p>
		  <p>
		     <button type="button" class="btn btn-w-m btn-danger" onclick="window.opener=null;window.close();">关闭 </button>
		  </p>
	</div>
    
    <div class="grid-stack">
    </div>
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/js/lodash.min.js'/>"></script>
    
    <script src="<c:url value='/resource/drag/js/jquery.ui.touch-punch.min.js'/>"></script>
    <script src="<c:url value='/resource/drag/dist/gridstack.js'/>"></script>
    <script src="<c:url value='/resource/drag/dragedit.js'/>"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        //close
    	function ml_close() {
            $('.float-div').animate({
                left: '-450px'
            }, 300, function() {
                $('.float-open').delay(50).animate({
                    left: '-2px'
                }, 300);
            });
        }
        //open
        function ml_open() {
            $('.float-open').animate({
                left: '-70px'
            }, 100, function() {
                $('.float-div').delay(50).animate({
                    left: '0px'
                }, 300);
            });
        }
        //close click
        $('.float-close').click(function() {
            ml_close();
            return false;
        });
        //open mouseover
        $('.open-btn').mouseover(function() {
            ml_open();
            return false;
        });

        /* setTimeout(function() {
            ml_close()
        }, 1000); */
    });
    
    //保存页面布局数据到数据库
    function savedata(){
    	save_grid();
    	var url="<c:url value='/drag/savedata'/>"
    	$.ajax({
            type : "post",
            url : url,
            dataType : "json",
            data:{
            	id:$('#id').val(),
            	serialized_data:$('#saved-data').val()
            },
            success:function(response,textStatus){
            	layer.msg(response.message);
            },
            error : function() {
            	layer.msg('发生错误了');
            }
        });
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
    //返回
    function goback(){
    	window.location.href="<c:url value='/drag/list'/>";
    }
    </script>
</body>
</html>
