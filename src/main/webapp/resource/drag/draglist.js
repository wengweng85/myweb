//����ʼ��
function tableinit(options){
    var t=$(options.datatable_selector).dataTable({
	autowidth:true,
        processing: false,  //���ؼ�����ʾ,���д���
         serverSide: true,  //���÷������˷�ҳ
         searching: false,  //����ԭ������
         orderMulti: false,  //���ö�������
         //scrollY: 350,
         //scrollX:false,
    //scrollCollapse: false,
    paging:true,
    ordering:true,
    info:true,
    keys:true,
    fixedHeader:true,//��ͷ�̶�
    responsive: true,//����Ӧ
    order: [],  //ȡ��Ĭ�������ѯ,����ѡ��һ�л����С��ͷ
    pagingType: "simple_numbers",  //��ҳ��ʽ��simple,simple_numbers,full,full_numbers

    //�б��ͷ�ֶ�
    columns: options.columns,
    
    columnDefs: options.columnDefs,

    ajax: function (data, callback, settings) {
        //��װ�������
        var param = $(options.query_form_selector).serializeObject();
        param.limit = data.length;//ҳ����ʾ��¼��������ҳ����ʾÿҳ��ʾ�������ʱ��
        param.curpage = (data.start / data.length)+1;//��ǰҳ��
        //ajax��������
        $.ajax({
            type: "post",
            url: $(options.query_form_selector).attr('action'),
            cache: false,  //���û���
            data: param,  //������װ�Ĳ���
            dataType: "json",
            beforeSend:function(){
            	var index = layer.load(1);
            },
            complete:function(){
            	layer.closeAll();
            },
            success: function (response) {
                //��װ��������
                var returnData = {};
                returnData.recordsTotal = response.total;//��������ȫ����¼
                returnData.recordsFiltered = response.total;//��̨��ʵ�ֹ��˹��ܣ�ÿ�β�ѯ������ȫ�����
                returnData.data = response.obj;//���ص������б�
                //console.log(returnData);
                //����DataTables�ṩ��callback���������������ѷ�װ��ɲ�����DataTables������Ⱦ
                //��ʱ��������ȷ����ȷ�����쳣�ж�Ӧ��ִ�д˻ص�ǰ���д������
                callback(returnData);
            },
            error : function(response) {
                layer.alert('����������'+response.message);
            }
        });
    }
  }).api();
  return t;
};