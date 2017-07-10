//表格初始化
function tableinit(options){
    var t=$(options.datatable_selector).dataTable({
	autowidth:true,
        processing: false,  //隐藏加载提示,自行处理
         serverSide: true,  //启用服务器端分页
         searching: false,  //禁用原生搜索
         orderMulti: false,  //启用多列排序
         //scrollY: 350,
         //scrollX:false,
    //scrollCollapse: false,
    paging:true,
    ordering:true,
    info:true,
    keys:true,
    fixedHeader:true,//表头固定
    responsive: true,//自适应
    order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
    pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers

    //列表表头字段
    columns: options.columns,
    
    columnDefs: options.columnDefs,

    ajax: function (data, callback, settings) {
        //封装请求参数
        var param = $(options.query_form_selector).serializeObject();
        param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
        param.curpage = (data.start / data.length)+1;//当前页码
        //ajax请求数据
        $.ajax({
            type: "post",
            url: $(options.query_form_selector).attr('action'),
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            beforeSend:function(){
            	var index = layer.load(1);
            },
            complete:function(){
            	layer.closeAll();
            },
            success: function (response) {
                //封装返回数据
                var returnData = {};
                returnData.recordsTotal = response.total;//返回数据全部记录
                returnData.recordsFiltered = response.total;//后台不实现过滤功能，每次查询均视作全部结果
                returnData.data = response.obj;//返回的数据列表
                //console.log(returnData);
                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                callback(returnData);
            },
            error : function(response) {
                layer.alert('发生错误了'+response.message);
            }
        });
    }
  }).api();
  return t;
};