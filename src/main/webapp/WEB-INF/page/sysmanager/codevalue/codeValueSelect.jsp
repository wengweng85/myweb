<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码选择框</title>
    <rc:csshead/>
    
</head>
<body >
    <div class="wrapper">
         <!-- 搜索区域 -->
         <div class="tabs-container">
              <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#tab-1">代码树</a>
                  </li>
                  <li class=""><a data-toggle="tab" href="#tab-2">代码搜索</a>
                  </li>
              </ul>
              <div class="tab-content ">
                  <div id="tab-1" class="tab-pane active">
                      <div class="panel-body">
                          <div id="tree-div" class="ztree" style="overflow: auto; width:100%; height: 450px; "></div>
                      </div>
                  </div>
	              <div id="tab-2" class="tab-pane">
                      <div class="panel-body">
                          <div class="col-sm-12 col-xs-12" style="height: 450px;">
		                  <div class="input-group">
		                        <input type="text" class="form-control" id="input_search">
		                        <div class="input-group-btn">
		                            <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
		                                <span class="caret"></span>
		                            </button>
		                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
		                            </ul>
		                        </div>
		                   </div>
                         </div>
                       </div>
                  </div>
              </div>
         </div>
         <div style="border: 1px solid #fff" >
	         <div class="col-sm-2 col-xs-2">
	            <div class="nput-group-btn">
		            <!-- 隐藏域 -->
				    <rc:hidden property="callback_fun_name" value="${callback_fun_name}"/>
				    <rc:hidden property="codetype" value="${codetype}"/>
				    <rc:hidden property="code"/>
		            <span class="btn btn-outline btn-info" id="value"></span>
	            </div>
                <script id="tpl" type="text/x-handlebars-template" >
	                {{name}}
	            </script>
	         </div>
	         <div id="result" class="col-sm-2 col-xs-2></div>
	         <div class="col-sm-8 col-xs-8" align="right">
	              <button disabled="disabled" id="btn_confirm" class="btn btn-info" onclick="select_confirmframe()"><i class="fa fa-save"></i>&nbsp;确定</button>
	              <button class="btn btn-danger"  onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;取消</button>
	         </div>
         </div>
    </div>
    <rc:jsfooter/> 
    
    <script type="text/javascript">
    
    
    $(function() {
    	   if(!$('#codetype').val()){
    	       layer.alert('页面设计缺少相关代码类型参数,请联系开发人员');
    	   }else{
    	       $.fn.zTree.init($("#tree-div"), code_type_setting);
    	   }
     })
     
     $("#input_search").bsSuggest({
         url:"<c:url value='/codetype/getCodeValueFromCache'/>?code_type=${codetype}",
         effectiveFields: ['code_describe'],
         searchFields: [ "code_describe"],
         idField: "code_value",
         keyField: "code_describe",
         allowNoKeyword:true,
         multiWord:false
     }).on('onSetSelectValue', function (e, keyword, data) {
         //console.log('onSetSelectValue: ', keyword);
         $('#code').val(keyword.id);
 	  	 $('#value').html(keyword.key);
         $('#btn_confirm').removeAttr("disabled");
     })
     
     //树配置
     var code_type_setting = {
     	  view: {
               showLine: true
     	  },	
     	  check: {
     		enable: false
     	  },
     	  data: {
 			simpleData: {
 				enable: true,
 				pIdKey: "pid"
 			}
 		  },
     	  callback: {
     	      onClick:onClick
     	  },
     	  async: {
     		 enable: true,
     		 url: "<c:url value='/codetype/treedata'/>/${codetype}",
     		 autoParam:["id"]
     	  }
     };
     
     //点击事件
 	function onClick(event, treeId, treeNode, clickFlag) {
 		$('#code').val(treeNode.id);
 	  	$('#value').html(treeNode.code_describe);
        $('#btn_confirm').removeAttr("disabled");
 	}
     
     
     function select_confirmframe(){
     	var ff=$('#callback_fun_name').val();
     	if(callback_fun_name){
     		var code=$('#code').val();
         	var value=$('#value').text();
         	if(code){
         		parent.${callback_fun_name}(code,value);
         		select_closeframe();
         	}else{
         		layer.alert('请先选择一条记录');
         	}
     	}else{
     		layer.alert('页面设计缺少相关回调函数,请联系开发人员');
     	}
     }
     //关闭
     function select_closeframe(){
     	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
     	parent.layer.close(index); //再执行关闭   
     }
    
    </script>   
</body>
</html>