<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人基本信息选择框</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- 查询条件 -->
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
			           <!-- 隐藏域 -->
			           <rc:hidden property="callback_fun_name" value="${callback_fun_name}"/>
			           <rc:hidden property="code"/>
			           <rc:hidden property="value"/>
			           <!-- 隐藏域 结束 -->
                       <rc:textedit property="aac002" cols="1,3" label="身份证"/>
			           <rc:textedit property="aac003" cols="1,3" label="姓名"/>
			           <rc:select property="aac004" codetype="AAC004" cols="1,3" label="性别"/>
			       </div>
			       <div class="hr-line-dashed"></div>
			       <div class="form-group">
			           <div class="col-sm-12 col-xs-12" align="right">
		                  <a class="btn btn-info" onclick="select_query()"><i class="fa fa-search"></i>&nbsp;查询</a>
		                  <a class="btn btn-info" onclick="select_reset()"><i class="fa fa-refresh"></i>&nbsp;重置</a>
		               </div>
			       </div>
		       </form>
	       </div>
        </div>
            
        <!-- 查询结果 -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>查询结果列表</h5>
                <div class="ibox-tools">
                                                     双击选中
                </div>
            </div>
            <div class="ibox-content">
			    <table id="ac01querytable" data-url="<c:url value='/demo/getAc01List'/>" data-page-size="10">
			    <thead>
				    <tr>
				        <th data-formatter="select_indexFormatter">序号</th>
	                    <th data-field="aac002" >身份证号码</th>
	                    <th data-field="aac003" >姓名</th>
	                    <th data-field="aac004" >性别</th>
	                    <th data-field="aac005" >民族</th>
	                    <th data-field="aac011" >学历</th>
	                    <th data-field="aae006" >联系电话</th>
	                    <th data-field="aae009" >经办机构名</th>
				    </tr>
			    </thead>
			    </table>
            </div>
            <div class="col-sm-12 col-xs-12" align="right">
                <button disabled="disabled" id="btn_confirm" class="btn btn-info" onclick="select_confirmframe()"><i class="fa fa-save"></i>&nbsp;确定</button>
                <button class="btn btn-danger"  onclick="select_closeframe()"><i class="fa fa-remove"></i>&nbsp;取消</button>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    var demo_options={
    	formid:'query_form'
    }
    //初始化
    $(function(){
    	$('#ac01querytable').inittable(demo_options);
    	$('#ac01querytable')
        .on('click-row.bs.table', function (e, row, $element) {
        	select_click(row);
        })
        .on('dbl-click-row.bs.table', function (e, row, $element) {
        	select_click(row);
        	select_confirmframe();
        })
        /*
        .on('all.bs.table', function (e, name, args) {
            console.log('Event:', name, ', data:', args);
        })
         .on('sort.bs.table', function (e, name, order) {
            $result.text('Event: sort.bs.table');
        })
        .on('check.bs.table', function (e, row) {
            $result.text('Event: check.bs.table');
        })
        .on('uncheck.bs.table', function (e, row) {
            $result.text('Event: uncheck.bs.table');
        })
        .on('check-all.bs.table', function (e) {
            $result.text('Event: check-all.bs.table');
        })
        .on('uncheck-all.bs.table', function (e) {
            $result.text('Event: uncheck-all.bs.table');
        })
        .on('load-success.bs.table', function (e, data) {
            $result.text('Event: load-success.bs.table');
        })
        .on('load-error.bs.table', function (e, status) {
            $result.text('Event: load-error.bs.table');
        })
        .on('column-switch.bs.table', function (e, field, checked) {
            $result.text('Event: column-switch.bs.table');
        })
        .on('page-change.bs.table', function (e, number, size) {
            $result.text('Event: page-change.bs.table');
        })
        .on('search.bs.table', function (e, text) {
            $result.text('Event: search.bs.table');
        }); */
    });
   
    //列选中后将个人编号赋到页面隐藏域中
    function select_click(row){
        $('#code').val(row.aac001);
    	$('#value').val(row.aac003+"("+row.aac002+")");
        $('#btn_confirm').removeAttr("disabled");
    }
    //
    function select_indexFormatter(value, row, index) {
        return index+1;
    }
    //
    function select_confirmframe(){
    	var ff=$('#callback_fun_name').val();
    	if(callback_fun_name){
    		var code=$('#code').val();
        	var value=$('#value').val();
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
    //查询
    function select_query(){
    	$('#ac01querytable').refreshtable();
    }
    
    function select_reset(){
    	rc.clean($('query_form'));
    	$('#btn_confirm').attr('disabled',"true")
    }
    
    
    </script>
</body>
</html>