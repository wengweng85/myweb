    var options={
    	url:"<c:url value='/job/querylist'/>",
    	formid:'query_form'
    }
  //初始化
    $(function(){
    	$('#jobtable').bttable(options);
    });
  
    //操作编辑
    function jobnameFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//预编译模板  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
    }
    
    function queryParams(params) {
    	 var request_param=$.extend({},$('#query_form').serializeObject(),params);
         return request_param
    }
    
    //查询
    function query(){
    	$('#jobtable').bootstrapTable('refresh', {url: "<c:url value='/job/querylist'/>"});
    }
    
  //数据编辑
    function edit(id){
    	var url = "<c:url value='/job/gotoedit'/>";
    	openwindow('editwindow',url+'/'+id); 
    }
     
    //暂停
    function pause(id){
    	var url = "<c:url value='/job/pause'/>"+"/"+id;
    	ajax(url,"确定暂停此任务吗")
    }
  
    //恢复
    function resume(id){
    	var url = "<c:url value='/job/resume'/>"+"/"+id;
    	ajax(url,"确定恢复此任务吗")
    }
    
    //删除
    function dd(id){
    	var url = "<c:url value='/job/delete'/>"+"/"+id;
    	ajax(url,"确定删除此任务吗")
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
                    layer.msg('发生错误了');
                }
            });
    	})
    }
    //新增
    function add_page(){
    	layer.open({
	   		  type: 2,
	   		  title: '新增页面',
	   		  shadeClose: true,
	   		  shade: 0.8,
	   		  area: ['50%', '90%'],
	   		  content: "<c:url value='/job/toadd'/>" //iframe的url
   		});
    }    