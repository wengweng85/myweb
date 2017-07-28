<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.rc.com/rctag" prefix="rc"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo����ҳ��</title>
    <rc:csshead/>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- ��ѯ���� -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����</h5>
            </div>
            <div class="ibox-content">
	            <form class="form-horizontal" id="query_form" >
			        <div class="form-group">
			           <label class="col-sm-1 control-label">���֤</label>
			           <div class="col-sm-2">
			               <rc:textedit property="aac002" onchange="aac002_change_test()" />
			           </div>
			           <label class="col-sm-1 control-label">����</label>
			           <div class="col-sm-2">
			               <rc:textedit property="aac003"/>
			           </div>
			            <label class="col-sm-1 control-label">�Ա�</label>
			           <div class="col-sm-2">
			               <rc:select property="aac004" codetype="AAC004" onchange="layer.msg('�Ա�onchange,ֵΪ'+this.value)"/>
			           </div>
			           <label class="col-sm-1 control-label">ѧ��</label>
			           <div class="col-sm-2">
			               <rc:select property="aac011" codetype="AAC011"/>
			           </div>
			       </div>
			       <div class="hr-line-dashed"></div>
			       <div class="form-group">
			           <label class="col-sm-1 control-label">��������(��ʼ)</label>
			           <div class="col-sm-2">
			               <rc:date property="aac006_begin"/>
			           </div>
			           <label class="col-sm-1 control-label">��������(����)</label>
			           <div class="col-sm-2">
			               <rc:date property="aac006_end"/>
			           </div>
			           <label class="col-sm-1 control-label">�������</label>
			           <div class="col-sm-2">
			               <rc:textedit property="aae009"/>
			           </div>
			           <div class="col-sm-3" align="right">
		                  <a class="btn btn-info" onclick="query()"  >��ѯ</a>
		                  <a class="btn btn-info" onclick="rc.clean($('query_form'))">����</a>
		                  <a class="btn btn-info" onclick="add_page()">����</a>
		               </div>
			       </div>
		       </form>
	       </div>
        </div>
            
        <!-- ��ѯ��� -->    
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>��ѯ����б�</h5>
                <div class="ibox-tools">
                    <a onclick="batchdelete()" class="btn btn-danger btn-xs">����ɾ��</a>
                </div>
            </div>
            <!-- ģ�� -->
            <script id="tpl" type="text/x-handlebars-template" >
                <a  class="btn btn-info" onclick="gotoedit('{{aac001}}')">�༭</a> 
                <a  class="btn btn-danger" onclick="dd('{{aac001}}')" >ɾ��</a> 
            </script>
            <div class="ibox-content">
			    <table id="ac01table" data-url="<c:url value='/demo/getAc01List'/>" 
			          data-click-to-select="true"
                      data-single-select="true"
                      data-page-size="10" >
			    <thead>
				    <tr>
				        <th data-checkbox="true" ></th>
				        <th data-formatter="indexFormatter">���</th>
	                    <th data-field="aac002" >���֤����</th>
	                    <th data-field="aac003" >����</th>
	                    <th data-field="aac004" >�Ա�</th>
	                    <th data-field="aac005" >����</th>
	                    <th data-field="aac006" >��������</th>
	                    <th data-field="aac033" >����״��</th>
	                    <th data-field="aac017" >����״��</th>
	                    <th data-field="aac024" >������ò</th>
	                    <th data-field="aac011" >ѧ��</th>
	                    <th data-field="aae006" >��ϵ�绰</th>
	                    <th data-field="aae009" >���������</th>
	                    <th data-formatter="jobnameFormatter">����</th>
				    </tr>
			    </thead>
			    </table>
            </div>
        </div>
        <!-- End Panel Basic -->
    </div>
    <rc:jsfooter/>
    <script type="text/javascript">
    
    function aac002_change_test(){
    	$aac002=$('#aac002').val();
    	layer.msg('���֤onchange,ֵΪ'+$aac002)
    }
    var options={
    	formid:'query_form'
    }
    //��ʼ��
    $(function(){
    	$('#ac01table').inittable(options);
    });
  
    //�����༭
    function jobnameFormatter(value, row, index) {
        var tpl = $("#tpl").html();  
	  	//Ԥ����ģ��  
	  	var template = Handlebars.compile(tpl);  
	  	return template(row);
    }
    
    function indexFormatter(value, row, index) {
        return index+1;
    }
    
    //��ѯ
    function query(){
    	$('#ac01table').refreshtable();
    }

    </script>
</body>
</html>