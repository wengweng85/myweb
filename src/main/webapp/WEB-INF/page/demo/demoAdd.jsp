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
<body class="gray-bg"  style="margin-top:25px" >
     <!-- 固定浮动框  navbar-fixed-top 顶部、navbar-fixed-bottom 底部-->
     <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <!--fluid 是偏移一部分-->
                    <div class="container-fluid " style="padding: 0px 20px;">
	                    <div class="form-group" style="text-align: right;">
				              <a class="btn btn-primary " onclick="demo_save_data()"><i class="fa fa-save"></i>&nbsp;保存</a>
				        </div>
                </div>
            </div>
     </div>
     <!-- 浮动框结束 -->
    <div class="wrapper wrapper-content ">
        <form action="${contextpath}/demo/savedata" >
        <div >
	        <!-- 人员选择基本信息开始 -->
	        <div class="ibox ">
	            <div class="ibox-title">
	                <h5>基本信息</h5>
	            </div>
	            <div class="ibox-content">
		            <div class="form-horizontal"  >
		               <div class="form-group">
		               <!-- 
		                    <rc:textEditIcon property="aac001" label="人员选择" cols="1,3"  url="${contextpath}/demo/toselect"  callback="select_demo_by_id" />
		              -->     
			               <rc:textEditSuggest label="个人信息搜索" cols="1,3" property="aac001" keytype="AC01" callback="select_demo_by_id"></rc:textEditSuggest>
			          
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
			               <rc:select property="aab800" required="true"  label="户籍所在省"   filter="aaa102='610000'"  onchange="rc.code_value_select_data_change(this.value,'AAB801',['#aab801','#aab802'])"    codetype="AAB800"  validate="{required:true,messages:{required:'户籍所在省不能为空'}}"/>
			               <rc:select property="aab801" required="true"  readonly="true"   filter=" 1=2 " label="户籍所在市"  onchange="rc.code_value_select_data_change(this.value,'AAB802',['#aab802'])"     codetype="AAB801" validate="{required:true,messages:{required:'户籍所在市不能为空'}}"/>
			               <rc:select property="aab802" required="true"  readonly="true"  filter=" 1=2 "  label="户籍所在县"    codetype="AAB802"  validate="{required:true,messages:{required:'户籍所在县不能为空'}}"/>
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
					   <!-- toolbar -->
			            <div id="toolbar" class="btn-group">
							 <button id="btn_add_file" type="button" class="btn btn-info"  onclick="demo_add_file()">
							 <span class="glyphicon glyphicon-plus" aria-hidden="false"></span>新增文件
							 </button>
							  <button id="btn_delete" type="button" class="btn btn-danger" onclick="demo_delete_file()">
							 <span class="glyphicon glyphicon-remove" aria-hidden="false"></span>删除文件
							 </button>
						</div>
						<div>
				          <div class="ibox-title">
				                <h5>个人附件信息</h5>
				          </div>
				          <div class="ibox-content">
						    <table id="ac01filetable" 
							          data-click-to-select="true"
				                      data-toolbar="#toolbar"
				                      data-show-export="false"
				                      data-show-toggle="false"
				                      data-pagination="false"
				                      data-page-size="10" >
							    <thead>
								<tr>
							        <th data-checkbox="true" ></th>
							        <th data-formatter="file_indexFormatter">序号</th>
							        <th data-field="file_uuid" >文件编号</th>
				                    <th data-field="file_name" >文件名</th>
				                    <th data-field="file_length" >文件大小(byte)</th>
				                    <th data-field="file_type" >文件类型</th>
							    </tr>
							    </thead>
							</table>
							<!-- 隐藏域 用于保存表格json数据 -->
							<rc:hidden property="selectnodes" />
							<!-- 隐藏域结束 -->
						</div>
			       </div>
		       </div>
	        </div>
            </div>
            </div>
        </form>
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
   
    var $table=$('#ac01filetable');
    
    function select_demo_by_id(aac001){
    	rc.ajaxQuery("<c:url value='/demo/getDemoById'/>/"+aac001);
    	var url="<c:url value='/common/fileload/getFileList'/>?file_bus_id="+aac001;
    	$('#ac01filetable').refreshtable(url);
    }
    
    $(function() {
    	//验证 ajax
    	rc.validAndAjaxSubmit($("form"),demo_callback);
    	$table.inittable();
    })
    
    //保存页面配置信息
	function demo_save_data(){
    	var ids = $.map($table.bootstrapTable('getData'), function (row) {
            return row.bus_uuid;
        });
    	$('#selectnodes').val(ids);
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
    
    //操作编辑
    function file_jobnameFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//预编译模板  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
    }
    
    function file_indexFormatter(value, row, index) {
        return index+1;
    }
    
    
    //增加一行
    function demo_add_file(){
       /*  var index = $table.bootstrapTable('getData').length;
        $table.bootstrapTable('insertRow', {
            index: index,
            row: {
            	"bus_uuid":index,"file_name":"","file_length":"","file_type":""
            }
        }); */
        rc.open_file_upload_page('000000000000000000000000000000','001','file_upload_callback');
    }
    
    //文件上传回调函数
    function file_upload_callback(file_bus_id,message){
    	console.log(message);
    	var res=eval("("+message+")");//转换为json对象 
    	var index = $table.bootstrapTable('getData').length;
        $table.bootstrapTable('insertRow', {
            index: index,
            row:res
        }); 
    }
   
    //删除一行
    function demo_delete_file(){
    	    var layerindex=layer.confirm('确定批量删除这些数据吗,删除后将不可逆？', function(index){
	    	var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
	            return row.bus_uuid
	        });
	    	console.log(ids.toString());
	    	if(ids.length>0){
    			  rc.ajax("<c:url value='/common/fileload/batdelete'/>", {selectnodes:ids.toString()},function (response) {
     	   		    	alert(response.message);
	     	   		    $table.bootstrapTable('remove', {
		                    field: 'bus_uuid',
		                    values: ids
		                });
     	   		   }); 
    			  layer.close(layerindex);
	        }else{
	        	layer.alert('请先选择你要删除的数据');
	        }
    	});
    }
    </script>
</body>
</html>