<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.epsoft.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo测试编辑页面</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <form action="${contextpath}/demo/savedata" >
        <div id="input_content">
	        <!-- 人员选择基本信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>基本信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
		               <div class="form-group">
			               <label class="col-sm-1  col-xs-1  control-label">个人信息搜索</label>
			               <div class="col-sm-5 col-xs-5 ">
			                  <div class="input-group">
			                        <input type="text" class="form-control" id="aac001_name">
			                        <input type="hidden" id="aac001">
			                        <div class="input-group-btn">
			                            <a onclick="aac001_clean_select()" type="button" class="btn btn-default"><i class="fa fa-remove"></i></a>
			                            <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
			                                <span class="caret"></span>
			                            </button>
			                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
			                            </ul>
			                        </div>
			                   </div>
		                   </div>
			           </div>
				       <div class="hr-line-dashed"></div> 
		            
				       <div class="form-group">
			               <rc:textedit  property="aac003" onchange="aac003_change(this)" required="true" label="姓名" validate="{required:true,chinese:true,maxlength:10,messages:{required:'姓名不能为空'}}"  />
			               <rc:textedit  property="aac002" required="true" datamask="999999999999999999" label="身份证"  validate="{required:true,idcard:true,messages:{required:'身份证不能为空'}}"/>
			               <rc:select property="aac004" required="true"  multiple="true"  label="性别"   codetype="AAC004"  validate="{required:true,messages:{required:'性别不能为空'}}"/>
			               <rc:select property="aac005" required="true"  label="民族"   codetype="AAC005"  validate="{required:true,messages:{required:'民族不能为空'}}"/>
			           </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:select property="aac011" required="true"  label="学历"   codetype="AAC011"  validate="{required:true,messages:{required:'性别不能为空'}}"/>
				           <rc:date property="aac006" required="true"   label="出生日期"  readonly="false"  validate="{required:true,messages:{required:'出生日期不能为空'}}"/>
				           <rc:select property="aac033" required="true"  label="健康状况"   codetype="AAC033" validate="{required:true,messages:{required:'健康状况不能为空'}}"/>
				           <rc:select property="aac017" required="true"  label="婚姻状况"   codetype="AAC017" validate="{required:true,messages:{required:'婚姻状况不能为空'}}"/>
				       </div>
				       <div class="hr-line-dashed"></div>
				       <div class="form-group">
				           <rc:select property="aac024" required="true"  label="政治面貌"   codetype="AAC024" validate="{required:true,messages:{required:'政治面貌不能为空'}}"/>
				           <rc:textedit property="aae006" required="true" label="联系电话"  validate="{required:true,phone:true,messages:{required:'联系电话不能为空'}}"/>
				           <rc:textedit property="aac067" required="true" label="移动电话"  validate="{required:true,mobile:true,messages:{required:'移动电话不能为空'}}"/>
				           <rc:textedit property="aae015" required="true" label="电子邮件"  validate="{required:true,email:true,messages:{required:'电子邮件不能为空'}}"/>
				       </div>
			       </div>
		       </div>
	        </div>
	        <!-- 人员基本信息结束 -->
	        
	        <!-- 人员附加信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>附加信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal">
				        <div class="form-group">
			               <rc:select property="aab800" required="true"  label="户籍所在省"  onchange="rc.code_value_select_data_change(this.value,'AAB801',['#aab801','#aab802'])"    codetype="AAB800"  validate="{required:true,messages:{required:'户籍所在省不能为空'}}"/>
			               <rc:select property="aab801" required="true"  readonly="true" label="户籍所在市"  onchange="rc.code_value_select_data_change(this.value,'AAB802',['#aab802'])"     codetype="AAB801" validate="{required:true,messages:{required:'户籍所在市不能为空'}}"/>
			               <rc:select property="aab802" required="true"  readonly="true"  label="户籍所在县"    codetype="AAB802"  validate="{required:true,messages:{required:'户籍所在县不能为空'}}"/>
			            </div>
			            <div class="hr-line-dashed"></div>
			            <div class="form-group">
			               <rc:checkbox label="就失业状态" required="true"  property="adc300" cols="1,7" codetype="ADC300" />
			               <rc:radio label="就业状态" required="true" property="adc100" cols="1,3" codetype="ADC100" />
			            </div>
			            <div class="hr-line-dashed"></div>
					    <div class="form-group">
					       <rc:textEditIconCodeValue property="aac007" codetype="AAC200" label="地区选择"   />
					    </div>
					    <!-- 注意textarea框不要放到form-group中 -->
					    <div class="hr-line-dashed"></div>
					    <rc:textarea label="备注" property="aae013"   required="true" rows="3"   validate="{required:true,messages:{required:'备注信息不能为空'}}"/>
					    
					    <div class="hr-line-dashed"></div>
					    <rc:textarea label="备注" property="aae013_2"   required="true" rows="3"  validate="{required:true,messages:{required:'备注信息不能为空'}}"/>
			       </div>
		       </div>
	        </div>
	        <!-- 人员附加信息结束-->
	        <div class="form-group" style="text-align: right;">
	              <a class="btn btn-primary " onclick="demo_save_data()"><i class="fa fa-save"></i>&nbsp;保存</a>
	         </div>
         </div>
        </form>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    $("#aac001_name").bsSuggest({
    	url:$('#contextPath').val()+'/suggest/searchcode?keytype=AC01&keyword=',
        effectiveFields: ['showname','key','name'],
        getDataMethod: "url",//获取数据的方式，url：一直从url请求；data：从 options.data 获取；firstByUrl：第一次从Url获取全部数据，之后从options.data获取
        effectiveFieldsAlias:{showname: "全称", key: "代码",name:"名称"},
        //searchFields: [ "name","key"],
        fnPreprocessKeyword: function (keyword) {  
            return rc.encodeURITwice(keyword);  
        }, 
        idField: "id",
        keyField: "showname",
        showHeader: true,//显示 header
        showBtn: true,  
        allowNoKeyword:false,
        multiWord:false
    }).on('onSetSelectValue', function (e, keyword, data) {
        //console.log('onSetSelectValue: ', keyword);
        $('#aac001').val(keyword.id);
	  	$('#aac001_name').html(keyword.key);
	  	select_demo_by_id($('#aac001').val());
    })
    
    function aac001_clean_select(){
    	$('#aac001').val('');
    	$('#aac001_name').val('');
    	rc.clean();
    }
    
    function select_demo_by_id(aac001){
    	rc.ajaxQuery("<c:url value='/demo/getDemoById'/>/"+aac001,$('#input_content'));
    }
    $(function() {
    	//验证 ajax
    	rc.validAndAjaxSubmit($("form"),demo_callback);
    })
    
    //保存页面配置信息
	function demo_save_data(){
	   $('form').submit();
	}
    
    function demo_callback(response){
    	if(response.success){
           	alert(response.message);
    	}
    	else{
    		alert(response.message);
    	}
    }
    
    function aac003_change(obj){
    	alert(obj.value);
    }
    
    </script>
</body>
</html>