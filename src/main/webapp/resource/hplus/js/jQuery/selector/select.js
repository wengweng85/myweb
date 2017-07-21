/**
 * 全局JS
 */
var Drag = {

    obj : null,

    init : function(o, oRoot, minX, maxX, minY, maxY, bSwapHorzRef, bSwapVertRef, fXMapper, fYMapper)
    {
        o.onmousedown	= Drag.start;

        o.hmode			= bSwapHorzRef ? false : true ;
        o.vmode			= bSwapVertRef ? false : true ;

        o.root = oRoot && oRoot != null ? oRoot : o ;

        if (o.hmode  && isNaN(parseInt(o.root.style.left  ))) o.root.style.left   = "0px";
        if (o.vmode  && isNaN(parseInt(o.root.style.top   ))) o.root.style.top    = "0px";
        if (!o.hmode && isNaN(parseInt(o.root.style.right ))) o.root.style.right  = "0px";
        if (!o.vmode && isNaN(parseInt(o.root.style.bottom))) o.root.style.bottom = "0px";

        o.minX	= typeof minX != 'undefined' ? minX : null;
        o.minY	= typeof minY != 'undefined' ? minY : null;
        o.maxX	= typeof maxX != 'undefined' ? maxX : null;
        o.maxY	= typeof maxY != 'undefined' ? maxY : null;

        o.xMapper = fXMapper ? fXMapper : null;
        o.yMapper = fYMapper ? fYMapper : null;

        o.root.onDragStart	= new Function();
        o.root.onDragEnd	= new Function();
        o.root.onDrag		= new Function();
    },

    start : function(e)
    {
        var o = Drag.obj = this;
        e = Drag.fixE(e);
        var y = parseInt(o.vmode ? o.root.style.top  : o.root.style.bottom);
        var x = parseInt(o.hmode ? o.root.style.left : o.root.style.right );
        o.root.onDragStart(x, y);

        o.lastMouseX	= e.clientX;
        o.lastMouseY	= e.clientY;

        if (o.hmode) {
            if (o.minX != null)	o.minMouseX	= e.clientX - x + o.minX;
            if (o.maxX != null)	o.maxMouseX	= o.minMouseX + o.maxX - o.minX;
        } else {
            if (o.minX != null) o.maxMouseX = -o.minX + e.clientX + x;
            if (o.maxX != null) o.minMouseX = -o.maxX + e.clientX + x;
        }

        if (o.vmode) {
            if (o.minY != null)	o.minMouseY	= e.clientY - y + o.minY;
            if (o.maxY != null)	o.maxMouseY	= o.minMouseY + o.maxY - o.minY;
        } else {
            if (o.minY != null) o.maxMouseY = -o.minY + e.clientY + y;
            if (o.maxY != null) o.minMouseY = -o.maxY + e.clientY + y;
        }

        document.onmousemove	= Drag.drag;
        document.onmouseup		= Drag.end;

        return false;
    },

    drag : function(e)
    {
        e = Drag.fixE(e);
        var o = Drag.obj;

        var ey	= e.clientY;
        var ex	= e.clientX;
        var y = parseInt(o.vmode ? o.root.style.top  : o.root.style.bottom);
        var x = parseInt(o.hmode ? o.root.style.left : o.root.style.right );
        var nx, ny;

        if (o.minX != null) ex = o.hmode ? Math.max(ex, o.minMouseX) : Math.min(ex, o.maxMouseX);
        if (o.maxX != null) ex = o.hmode ? Math.min(ex, o.maxMouseX) : Math.max(ex, o.minMouseX);
        if (o.minY != null) ey = o.vmode ? Math.max(ey, o.minMouseY) : Math.min(ey, o.maxMouseY);
        if (o.maxY != null) ey = o.vmode ? Math.min(ey, o.maxMouseY) : Math.max(ey, o.minMouseY);

        nx = x + ((ex - o.lastMouseX) * (o.hmode ? 1 : -1));
        ny = y + ((ey - o.lastMouseY) * (o.vmode ? 1 : -1));

        if (o.xMapper)		nx = o.xMapper(y)
        else if (o.yMapper)	ny = o.yMapper(x)

        Drag.obj.root.style[o.hmode ? "left" : "right"] = nx + "px";
        Drag.obj.root.style[o.vmode ? "top" : "bottom"] = ny + "px";
        Drag.obj.lastMouseX	= ex;
        Drag.obj.lastMouseY	= ey;

        Drag.obj.root.onDrag(nx, ny);
        return false;
    },

    end : function()
    {
        document.onmousemove = null;
        document.onmouseup   = null;
        Drag.obj.root.onDragEnd(	parseInt(Drag.obj.root.style[Drag.obj.hmode ? "left" : "right"]),
            parseInt(Drag.obj.root.style[Drag.obj.vmode ? "top" : "bottom"]));
        Drag.obj = null;
    },

    fixE : function(e)
    {
        if (typeof e == 'undefined') e = window.event;
        if (typeof e.layerX == 'undefined') e.layerX = e.offsetX;
        if (typeof e.layerY == 'undefined') e.layerY = e.offsetY;
        return e;
    }
};

var masked=false;
// 全屏遮罩层
function boxAlpha (){
    if (masked==false){
        maskLayer();
        masked=true;
    }
    else{
        $('#maskLayer').hide();
        masked=false;
        _viewEditorText();
    }
}
//是否在数组内
function in_array(needle, haystack) {
    if(typeof needle == 'string' || typeof needle == 'number') {
        for(var i in haystack) {
            if(haystack[i] == needle) {
                return true;
            }
        }
    }
    return false;
}
function maskLayer(){
    var FW=document.body.scrollWidth;
    var FH=document.body.scrollHeight;
    var SH=window.screen.height;
    FH=FH<SH?SH:FH;
    $("#alphadiv").height(FH).width(FW);
    $('#maskLayer').show();
    $('#maskLayer_iframe').css({position:"absolute",left:"0px",top:"0px"}).height(FH).width(FW);
}
//隐藏页面的富文本编辑器
function _hiddenEditorText(){
    var s=document.getElementById("_editorText");
    if(s){
        document.getElementById("_editorText").style.display = "none";
    }
}
function _viewEditorText(){
    var s=document.getElementById("_editorText");
    if(s){
        document.getElementById("_editorText").style.display = "";
    }
}

function draglayer(){
    var och=$("#drag").height();
    var ocw=$("#drag").width();
    var bsl = document.documentElement.scrollLeft || document.documentElement.scrollLeft;
    var bst = document.documentElement.scrollTop || document.documentElement.scrollTop;
    var bcw = document.documentElement.clientWidth || document.documentElement.clientWidth;
    var bch = document.documentElement.clientHeight || document.documentElement.clientHeight;

    var osl = bsl + Math.floor( ( bcw - ocw ) / 2 );
    osl = Math.max( bsl , osl );
    var ost = bst + Math.floor( ( bch - och ) / 2 );
    ost = Math.max( bst , ost );
    $("#drag").css({"top":12,"left":osl,"width":ocw}).show();
    var theHandle = document.getElementById("drag_h");
    var theRoot   = document.getElementById("drag");
    Drag.init(theHandle, theRoot);
}
$(window).resize(function (){
    if (masked==true){
        draglayer();
        maskLayer();
    }
});





/* *******************************工作地点选择器开始*************************************** */

//var _maincity= [["主要城市",["110000","120000","310000","500000","130100","210100","220100","230100","320500","330100","330200","340100","350100","350200","370100","370200","410100","420100","430100","440100","440300","441300","510100","610100"]]]
//var _allprov= [["华东地区",["320000","330000","340000","360000"]],["华南地区",["350000","440000","450000","460000"]],["华北地区",["130000","140000","150000","370000","410000"]],["华中地区",["420000","430000"]],["西南地区",["510000","520000","530000","540000"]],["东北地区",["210000","220000","230000"]],["西北地区",["610000","620000","630000","640000","650000"]]]
//var jarray={"110000":"北京市","110100":"市辖区","110200":"县","120000":"天津市","120100":"市辖区","120200":"县","130000":"河北省","130100":"石家庄市","130200":"唐山市","130300":"秦皇岛市","130400":"邯郸市","130500":"邢台市","130600":"保定市","130700":"张家口市","130800":"承德市","130900":"沧州市","131000":"廊坊市","131100":"衡水市","140000":"山西省","140100":"太原市","140200":"大同市","140300":"阳泉市","140400":"长治市","140500":"晋城市","140600":"朔州市","140700":"晋中市","140800":"运城市","140900":"忻州市","141000":"临汾市","141100":"吕梁市","150000":"内蒙古自治区","150100":"呼和浩特市","150200":"包头市","150300":"乌海市","150400":"赤峰市","150500":"通辽市","150600":"鄂尔多斯市","150700":"呼伦贝尔市","150800":"巴彦淖尔市","150900":"乌兰察布市","152200":"兴安盟","152500":"锡林郭勒盟","152900":"阿拉善盟","210000":"辽宁省","210100":"沈阳市","210200":"大连市","210300":"鞍山市","210400":"抚顺市","210500":"本溪市","210600":"丹东市","210700":"锦州市","210800":"营口市","210900":"阜新市","211000":"辽阳市","211100":"盘锦市","211200":"铁岭市","211300":"朝阳市","211400":"葫芦岛市","220000":"吉林省","220100":"长春市","220200":"吉林市","220300":"四平市","220400":"辽源市","220500":"通化市","220600":"白山市","220700":"松原市","220800":"白城市","222400":"延边朝鲜族自治州","230000":"黑龙江省","230100":"哈尔滨市","230200":"齐齐哈尔市","230300":"鸡西市","230400":"鹤岗市","230500":"双鸭山市","230600":"大庆市","230700":"伊春市","230800":"佳木斯市","230900":"七台河市","231000":"牡丹江市","231100":"黑河市","231200":"绥化市","232700":"大兴安岭地区","310000":"上海市","310100":"市辖区","310200":"县","320000":"江苏省","320100":"南京市","320200":"无锡市","320300":"徐州市","320400":"常州市","320500":"苏州市","320600":"南通市","320700":"连云港市","320800":"淮安市","320900":"盐城市","321000":"扬州市","321100":"镇江市","321200":"泰州市","321300":"宿迁市","330000":"浙江省","330100":"杭州市","330200":"宁波市","330300":"温州市","330400":"嘉兴市","330500":"湖州市","330600":"绍兴市","330700":"金华市","330800":"衢州市","330900":"舟山市","331000":"台州市","331100":"丽水市","340000":"安徽省","340100":"合肥市","340200":"芜湖市","340300":"蚌埠市","340400":"淮南市","340500":"马鞍山市","340600":"淮北市","340700":"铜陵市","340800":"安庆市","341000":"黄山市","341100":"滁州市","341200":"阜阳市","341300":"宿州市","341500":"六安市","341600":"亳州市","341700":"池州市","341800":"宣城市","350000":"福建省","350100":"福州市","350200":"厦门市","350300":"莆田市","350400":"三明市","350500":"泉州市","350600":"漳州市","350700":"南平市","350800":"龙岩市","350900":"宁德市","360000":"江西省","360100":"南昌市","360200":"景德镇市","360300":"萍乡市","360400":"九江市","360500":"新余市","360600":"鹰潭市","360700":"赣州市","360800":"吉安市","360900":"宜春市","361000":"抚州市","361100":"上饶市","370000":"山东省","370100":"济南市","370200":"青岛市","370300":"淄博市","370400":"枣庄市","370500":"东营市","370600":"烟台市","370700":"潍坊市","370800":"济宁市","370900":"泰安市","371000":"威海市","371100":"日照市","371200":"莱芜市","371300":"临沂市","371400":"德州市","371500":"聊城市","371600":"滨州市","371700":"菏泽市","410000":"河南省","410100":"郑州市","410200":"开封市","410300":"洛阳市","410400":"平顶山市","410500":"安阳市","410600":"鹤壁市","410700":"新乡市","410800":"焦作市","410900":"濮阳市","411000":"许昌市","411100":"漯河市","411200":"三门峡市","411300":"南阳市","411400":"商丘市","411500":"信阳市","411600":"周口市","411700":"驻马店市","419000":"省直辖县级行政区划","420000":"湖北省","420100":"武汉市","420200":"黄石市","420300":"十堰市","420500":"宜昌市","420600":"襄阳市","420700":"鄂州市","420800":"荆门市","420900":"孝感市","421000":"荆州市","421100":"黄冈市","421200":"咸宁市","421300":"随州市","422800":"恩施土家族苗族自治州","429000":"省直辖县级行政区划","430000":"湖南省","430100":"长沙市","430200":"株洲市","430300":"湘潭市","430400":"衡阳市","430500":"邵阳市","430600":"岳阳市","430700":"常德市","430800":"张家界市","430900":"益阳市","431000":"郴州市","431100":"永州市","431200":"怀化市","431300":"娄底市","433100":"湘西土家族苗族自治州","440000":"广东省","440100":"广州市","440200":"韶关市","440300":"深圳市","440400":"珠海市","440500":"汕头市","440600":"佛山市","440700":"江门市","440800":"湛江市","440900":"茂名市","441200":"肇庆市","441300":"惠州市","441400":"梅州市","441500":"汕尾市","441600":"河源市","441700":"阳江市","441800":"清远市","441900":"东莞市","442000":"中山市","445100":"潮州市","445200":"揭阳市","445300":"云浮市","450000":"广西壮族自治区","450100":"南宁市","450200":"柳州市","450300":"桂林市","450400":"梧州市","450500":"北海市","450600":"防城港市","450700":"钦州市","450800":"贵港市","450900":"玉林市","451000":"百色市","451100":"贺州市","451200":"河池市","451300":"来宾市","451400":"崇左市","460000":"海南省","460100":"海口市","460200":"三亚市","460300":"三沙市","469000":"省直辖县级行政区划","500000":"重庆市","500100":"市辖区","500200":"县","510000":"四川省","510100":"成都市","510300":"自贡市","510400":"攀枝花市","510500":"泸州市","510600":"德阳市","510700":"绵阳市","510800":"广元市","510900":"遂宁市","511000":"内江市","511100":"乐山市","511300":"南充市","511400":"眉山市","511500":"宜宾市","511600":"广安市","511700":"达州市","511800":"雅安市","511900":"巴中市","512000":"资阳市","513200":"阿坝藏族羌族自治州","513300":"甘孜藏族自治州","513400":"凉山彝族自治州","520000":"贵州省","520100":"贵阳市","520200":"六盘水市","520300":"遵义市","520400":"安顺市","520500":"毕节市","520600":"铜仁市","522300":"黔西南布依族苗族自治州","522600":"黔东南苗族侗族自治州","522700":"黔南布依族苗族自治州","530000":"云南省","530100":"昆明市","530300":"曲靖市","530400":"玉溪市","530500":"保山市","530600":"昭通市","530700":"丽江市","530800":"普洱市","530900":"临沧市","532300":"楚雄彝族自治州","532500":"红河哈尼族彝族自治州","532600":"文山壮族苗族自治州","532800":"西双版纳傣族自治州","532900":"大理白族自治州","533100":"德宏傣族景颇族自治州","533300":"怒江傈僳族自治州","533400":"迪庆藏族自治州","540000":"西藏自治区","540100":"拉萨市","542100":"昌都地区","542200":"山南地区","542300":"日喀则地区","542400":"那曲地区","542500":"阿里地区","542600":"林芝地区","610000":"陕西省","610100":"西安市","610200":"铜川市","610300":"宝鸡市","610400":"咸阳市","610500":"渭南市","610600":"延安市","610700":"汉中市","610800":"榆林市","610900":"安康市","611000":"商洛市","620000":"甘肃省","620100":"兰州市","620200":"嘉峪关市","620300":"金昌市","620400":"白银市","620500":"天水市","620600":"武威市","620700":"张掖市","620800":"平凉市","620900":"酒泉市","621000":"庆阳市","621100":"定西市","621200":"陇南市","622900":"临夏回族自治州","623000":"甘南藏族自治州","630000":"青海省","630100":"西宁市","632100":"海东地区","632200":"海北藏族自治州","632300":"黄南藏族自治州","632500":"海南藏族自治州","632600":"果洛藏族自治州","632700":"玉树藏族自治州","632800":"海西蒙古族藏族自治州","640000":"宁夏回族自治区","640100":"银川市","640200":"石嘴山市","640300":"吴忠市","640400":"固原市","640500":"中卫市","650000":"新疆维吾尔自治区","650100":"乌鲁木齐市","650200":"克拉玛依市","652100":"吐鲁番地区","652200":"哈密地区","652300":"昌吉回族自治州","652700":"博尔塔拉蒙古自治州","652800":"巴音郭楞蒙古自治州","652900":"阿克苏地区","653000":"克孜勒苏柯尔克孜自治州","653100":"喀什地区","653200":"和田地区","654000":"伊犁哈萨克自治州","654200":"塔城地区","654300":"阿勒泰地区","659000":"自治区直辖县级行政区划"}

//已选择的数据数组
var jobArea_Arr = new Array();
//可以选择的数量
var selectNum;

var jobArea = {
    // 数据初始化
    init : function(){
        var_ids='';
        _ids = document.getElementById(selectId).value;
        if(""!=_ids){
            var _id = _ids.split(',');
            if(_id.length>0){
	            for ( var int = 0; int < _id.length; int++) {
	                jobArea_Arr[int] = _id[int];
	            }
            }
        }else{
        	jobArea_Arr = new Array();
        }
    },
    Show : function(){
        _hiddenEditorText();
        var k=0,output='',output2='',arr,area,select_ed;
        var Div		= new Array('mainSelectData','allSelectData');
        var Title	= new Array('<h4>&nbsp;</h4>','<h4>&nbsp;</h4>');
        var LoopArr	= new Array(_maincity,_allprov);

        //已选择的节点
        for(var i in jobArea_Arr){
            output2+='<li class="jobArea'+jobArea_Arr[i]+' chkON" onclick="jobArea.Chk(\''+jobArea_Arr[i]+'\')">'+citySelectorArray[jobArea_Arr[i]]+'</li>';
        }
        $('#commonSelected dd').html(output2);


        while(k<=1){
            output	= Title[k];
            arr		= LoopArr[k];
            for (var i in arr){
                area=arr[i][0];
                output+='<dl><dt>'+area+'</dt><dd>';
                for (var j in arr[i][1] ){
                    id=arr[i][1][j];
                    //select_ed=in_array(id,jobArea_Arr)?' chkON':'';
                    var innerarr=getAreaIDs(id);
                    if(innerarr.length==1){
                        output+='<li title=\''+citySelectorArray[id]+'\' class="jobArea'+id+select_ed+'" onclick="jobArea.Chk(\''+id+'\')">'+citySelectorArray[id]+'</li>';
                    }else{
                        output+='<li title=\''+citySelectorArray[id]+'\' class=\''+select_ed+'\' onclick="jobArea.SubLayer(\''+id+'\',event)">'+citySelectorArray[id]+'</li>';
                    }
                }
                output+='</dd></dl>';
            }
            $('#'+Div[k]).html(output);
            k++;
        }
        $('#drag').width('780px');
        // 鼠标悬停变色
        $('#commonSelectAlpha li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
        $('#mainSelectData li').click(function(e){$("#sublist").hover(function(){$(this).show()},function(){$(this).hide()})})
        // 点击弹出子菜单
        $('#allSelectData li').click(function(e){$("#sublist").hover(function(){$(this).show()},function(){$(this).hide()})})
    },
    // 所有下拉
    SubLayer : function(id,event){
        var output='<div id="sub_SelectData">',width,select_ed,key;
        select_ed=in_array(id,jobArea_Arr)?' chkON':'';
        var arr=getAreaIDs(id);
        if(arr.length>0){
            width=Math.ceil(Math.sqrt(arr.length))*100;
            output+='<ul  style="width:'+width+'px"><h4 onclick="jobArea.Chk(\''+id+'\')">';
            output+='<a href="javascript:" class="jobArea' + id + select_ed +'">'+citySelectorArray[id]+'</a></h4>';
            for (var i=1;i<arr.length;i++){
                key=arr[i];
                select_ed=in_array(key,jobArea_Arr)?' chkON':'';
                output+='<li><a title=\''+citySelectorArray[key]+'\' href="javascript:" class="jobArea' + key + select_ed +'" onclick="jobArea.Chk(\''+key+'\')">'+citySelectorArray[key]+'</a></li>';
            }
            output=output+'</ul></div>';
            if(true){//使参数变成局部变量
		        var FW=$(window).width();
				var FH=$(window).height();
				var mouseX = event.pageX;//鼠标当前X坐标
				var mouseY = event.pageY;//鼠标当前Y坐标
				if(!event.pageX){//IE浏览器下
			        mouseX=event.clientX+document.body.scrollLeft -document.body.clientLeft;
			        mouseY=event.clientY+document.documentElement.scrollTop-document.body.clientTop;     
			    }
			    var selectH = 0;
			    if(arr.length>1){
					selectH = Math.floor((arr.length/Math.floor(width/150)+1))*25;//子菜单的高度
			    }else{
			    	selectH = 50;
			    }
				if((selectH+event.clientY+10)>FH){//当弹出框位置超出屏幕高度时
					mouseY = mouseY-(selectH+event.clientY-FH)-30;
				}
				if((width+event.clientX)>FW){//当弹出框位置超出屏幕宽度时
					mouseX = mouseX-(width+event.clientX-FW)-30;
				}
				$("#sublist").css({top:mouseY-4,left:mouseX-4});
	        }	
            $("#sublist").html(output).show();
        }
    },
    //单击事件
    Chk : function(id){
        if(!in_array(id,jobArea_Arr)){
            var subArea,myid;
            if(id.substr(2)=='00'){	// 选中父类清除子类
                subArea=getAreaIDs(id);
                for(var i in subArea){
                    if(in_array(subArea[i],jobArea_Arr)) this.del(subArea[i]);
                }
            }else{	// 选中子类清除父类
                myid=id.substr(0,2)+'00';
                if(in_array(myid,jobArea_Arr)) this.del(myid);
            };
            if(jobArea_Arr.length<selectNum){
                jobArea_Arr[jobArea_Arr.length]=id;
                var html='<li class="jobArea'+id+'" onclick="jobArea.Chk(\''+id+'\')">'+citySelectorArray[id]+'</li>';
                $('#commonSelected dd').append(html);
                $('.jobArea'+id).addClass('chkON');
                $('#commonSelected li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
            }else{
                alert('您最多能选择'+selectNum+'项');
                return false;
            }
        }else{
            this.del(id);
        }
    },
    del : function(id){
        for (var i in jobArea_Arr){
            if(jobArea_Arr[i]==id) jobArea_Arr.splice(i,1);;
        }
        $('#commonSelected .jobArea'+id).remove();
        $('.jobArea'+id).removeClass('chkON');
    },
    // 确定
    confirm : function(){
        var areaStr='';
        for(var i in jobArea_Arr){
            areaStr+=','+citySelectorArray[jobArea_Arr[i]];
        }
        areaStr=areaStr.substring(1)?areaStr.substring(1):'';
        $('#'+selectText).val(areaStr);
        $('#'+selectId).val(jobArea_Arr);
        boxAlpha();
        $('#commonSelected dd').empty();
       	if($('#checkworkareajs').length>0){
        	checkworkarea();//职位发布跟职位修改时用到
		}
        if($('#highsearch').length>0){
        	
        	if($('#curtab_one').val()=='0'){
	        	$('#keyword').val('');
				closetj('gzjy');
				closetj('xlyq');
				closetj('yxfw');
				closetj('gsxz');
				closetj('gsgm');
				closetj('rmsq');
				closetj('gzxz');
				delcheck();
        	}
        	if($('#curtab_one').val()=='1'){
        		checkworkarea();
        	}
			JPlaceHolder.clear();   
        }else{
        	JPlaceHolder.clear();
        }
    },
    cancel : function(){
        boxAlpha();
        $('#commonSelected dd').empty();
    }
}
function getAreaIDs(idx){
    var newArr = new Array();
    if(idx.substr(2,4)=='0000'){
        for (var i in jarray){
            if((i.substr(0,2)==idx.substr(0,2))&&i.substr(4,2)=='00'){
                newArr[newArr.length]=i;
            }
        }
    }
    else if(idx.substr(4,2)){
        for (var i in jarray){
            if((i.substr(0,4)==idx.substr(0,4))){
                newArr[newArr.length]=i;
            }
        }
    }
    return newArr;
}
/**
 * 工作地点选择
 * @param _selectNum 可以选择数量
 * @param _selectText 选择的文本显示的位置
 * @param _selectId 选择的数据的ID存储的隐藏区域
 */
function jobAreaSelect(_selectNum,_selectText,_selectId,_language){
    if("en" == _language){
        citySelectorArray = jarray;
    }else{
        citySelectorArray = jarray;
    }
    
    this.selectNum = _selectNum;
    this.selectText = _selectText;
    this.selectId = _selectId;
    var dragHtml ='<div id="commonSelectAlpha">';		//地区
    dragHtml+='		<dl id="commonSelected"><dt >已选择地点：</dt><dd></dd></dl>';
    dragHtml+='		<div id="mainSelectData"></div>';//主要地区
    dragHtml+='		<div id="allSelectData"></div>';	//所有省市
    dragHtml+='</div>';
    $('#drag_h').html('<b>请选择地区（您最多能选择'+selectNum+'项）</b><div><span class="btn" onclick="jobArea.confirm()">确&nbsp;定</span>&nbsp;&nbsp;<span class="btn" onclick="jobArea.cancel()">关&nbsp;闭</span></div>');
    $('#drag_con').html(dragHtml);
    jobArea.init();
    jobArea.Show();
    boxAlpha();
    draglayer();
}


/* *******************************工作地点选择器结束*************************************** */



/* *******************************行业选择器开始*************************************** */

//var _industry_class=[["信息传输、计算机服务和软件业",["010001","010002","010003","010004","010005"]],["制造业",["020001","020002","020003","020004","020005","020006","020007","020008","020009","020010","020011","020012","020013","020014","020015","020016","020017","020018","020019","020020","020021","020022","020023","020024","020025","020026","020027","020028","020029","020030","020031","020032"]],["建筑业",["030001","030002","030003","030004"]],["房地产业",["040001","040002","040003"]],["金融业",["050001","050002","050003","050004","050005"]],["教育",["060001","060002","060003","060004","060005"]],["采矿业",["070001","070002","070003","070004","070005","070006"]],["贸易、批发和零售业",["080001","080002","080003"]],["住宿和餐饮业",["090001","090002"]],["文化、体育和娱乐业",["100001","100002","100003","100004","100005"]],["交通运输、仓储和邮政业",["110001","110002","110003","110004","110005","110006","110007","110008","110009"]],["租赁和商务服务业",["120001","120002","120003","120004","120005","120006","120007","120008","120009","120010"]],["电力、燃气及水的生产和供应业",["130001","130002","130003"]],["水利、环境和公共设施管理业",["140001","140002","140003"]],["科学研究、技术服务和地质勘查业",["150001","150002","150003","150004"]],["卫生、社会保障和社会福利业",["160001","160002","160003"]],["农、林、牧、渔业",["170001","170002","170003","170004","170005"]],["公共管理和社会组织",["180001","180002","180003","180004","180005"]],["居民服务和其他服务业",["190001"]],["国际组织",["200001"]],["多元化业务集团公司",["210001"]]]
//var industryarray={"010001":"电信、移动通讯及增值服务","010002":"互联网/电子商务","010003":"广播电视卫星传输服务","010004":"计算机服务业","010005":"软件业","020001":"农副食品加工业","020002":"食品制造业","020003":"饮料(酒)制造业","020004":"烟草制品业","020005":"纺织业","020006":"纺织服装、鞋、帽制造业","020007":"皮革、毛皮、羽毛（绒）及其制品业","020008":"木材加工及木、竹、藤、棕、草制品业","020009":"家具制造业","020010":"造纸及纸制品业","020011":"印刷业、装订、记录媒介的复制","020012":"文教体育用品制造业","020013":"石油加工、炼焦及核燃料加工业","020014":"化学原料及化学制品制造业","020015":"医药制造业","020016":"化学纤维制造业","020017":"橡胶制品业","020018":"塑料制品业","020019":"非金属矿物制品业","020020":"黑色金属冶炼及压延加工业","020021":"有色金属冶炼及压延加工业","020022":"金属制品业","020023":"通用设备制造业","020024":"专用设备制造业","020025":"交通运输设备制造业","020026":"电气机械及器材制造业","020027":"通信设备制造业","020028":"计算机制造业","020029":"电子元器件制造","020030":"仪器仪表及文化、办公用机械制造业","020031":"工艺品及其他制造业","020032":"废气资源和废旧材料回收加工业","030001":"房屋和土木工程建筑业","030002":"建筑安装业","030003":"建筑装饰业","030004":"其他建筑业","040001":"房地产开发经营","040002":"物业管理","040003":"房地产中介服务","050001":"银行业","050002":"证券业","050003":"保险业","050004":"金融财务服务（会计、财务和审计服务业）","050005":"其他金融服务（信托、金融租赁、典当等）","060001":"学前教育","060002":"初等教育","060003":"中等教育","060004":"高等教育","060005":"其他教育","070001":"煤炭开采和洗选业","070002":"石油和天然气开采业","070003":"黑色金属矿采选业","070004":"有色金属矿采选业","070005":"非金属矿采选业","070006":"其他矿采选业","080001":"批发业","080002":"贸易/进出口","080003":"零售业","090001":"住宿业","090002":"餐饮业","100001":"新闻出版业","100002":"广播、电视、电影和音像业","100003":"文化艺术业","100004":"体育","100005":"娱乐业","110001":"铁路运输业","110002":"道路运输业","110003":"城市公共交通业","110004":"水（海）上运输业","110005":"航空运输业","110006":"管道运输业","110007":"装卸搬运和其他运输服务业","110008":"仓储业","110009":"邮政、快递服务业","120001":"租赁业","120002":"企业管理服务","120003":"法律服务","120004":"咨询与调查","120005":"广告业","120006":"人才、职业中介服务","120007":"市场管理、推广","120008":"旅游、旅行社","120009":"知识产权、会展等","120010":"其它商务服务","130001":"电力、热力的生产和供应业","130002":"燃气生产和供应业","130003":"水的生产和供应业","140001":"水利管理业","140002":"环境管理业","140003":"公共设施管理业","150001":"研究与试验发展","150002":"专业技术服务业","150003":"科技交流和推广服务业","150004":"地质勘查业","160001":"医疗、保健、预防卫生业","160002":"社会保障业","160003":"社会福利业","170001":"农业","170002":"林业","170003":"畜牧业","170004":"渔业","170005":"农、林、牧、渔服务业","180001":"中国共产党机关","180002":"国家机构","180003":"人民政协和民主党派","180004":"群众团体、社会团体和宗教组织","180005":"基层群众自治组织","190001":"居民服务和其他服务业","200001":"国际组织","210001":"多元化业务集团公司"}


//已选择的数据数组
var industrySelector_Arr = new Array();
//可以选择的数量
var selectNum;
var selectText;
var selectId;
var industrySelectorArray ;

var industrySelector = {
    //初始化
    init :function(){
        var_ids='';
        _ids = document.getElementById(selectId).value;
        if(""!=_ids){
            var _id = _ids.split(',');
            for ( var int = 0; int < _id.length; int++) {
                industrySelector_Arr[int] = _id[int];
            }
        }else{
        	industrySelector_Arr = new Array();
        }
    },
    //页面显示
    Show : function(){
        _hiddenEditorText();
        var k=0,output='',output2='',arr,area,select_ed;
        var Div		= new Array('allSelectData');
        var Title	= new Array('<h4>&nbsp;</h4>');
        var LoopArr	= new Array(_industry_class);

        for(var i in industrySelector_Arr){
            output2+='<li class="industrySelector'+industrySelector_Arr[i]+' chkON" onclick="industrySelector.Chk(\''+industrySelector_Arr[i]+'\')">'+industrySelectorArray[industrySelector_Arr[i]]+'</li>';
        }
        $('#commonSelected dd').html(output2);

        while(k<1){
            output	= Title[k];
            arr		= LoopArr[k];
            for (var i in arr){
                area=arr[i][0];
                output+='<dl><dt title=\''+area+'\'>'+area+'</dt><dd>';
                for (var j in arr[i][1] ){
                    id=arr[i][1][j];
                    if(k==0){
                        select_ed=in_array(id,industrySelector_Arr)?' chkON':'';
                        output+='<li title=\''+industrySelectorArray[id]+'\' class="industrySelector'+id+select_ed+'" onclick="industrySelector.Chk(\''+id+'\')">'+industrySelectorArray[id]+'</li>';
                    }
                }
                output+='</dd></dl>';
            }

            $('#'+Div[k]).html(output);
            k++;
        }
        $('#drag').width('780px');
        // 鼠标悬停变色
        $('#commonSelectAlpha li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
        // 点击弹出子菜单
        $('#allSelectData li').click(function(e){$("#sublist").css({top:e.pageY-4,left:e.pageX-4}).hover(function(){$(this).show()},function(){$(this).hide()})})
    },
    //单击事件
    Chk : function(id){
        if(!in_array(id,industrySelector_Arr)){
            var subArea,myid;
            if(id.substr(2)=='00'){	// 选中父类清除子类
                subArea=getIndustryIDs(id);
                for(var i in subArea){
                    if(in_array(subArea[i],industrySelector_Arr)) this.del(subArea[i]);
                }
            }else{	// 选中子类清除父类
                myid=id.substr(0,2)+'00';
                if(in_array(myid,industrySelector_Arr)) this.del(myid);
            };
            if(industrySelector_Arr.length<selectNum){
                industrySelector_Arr[industrySelector_Arr.length]=id;
                var html='<li title=\''+industrySelectorArray[id]+'\' class="industrySelector'+id+'" onclick="industrySelector.Chk(\''+id+'\')">'+industrySelectorArray[id]+'</li>';
                $('#commonSelected dd').append(html);
                $('.industrySelector'+id).addClass('chkON');
                $('#commonSelected li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
            }else{
                alert('您最多能选择'+selectNum+'项');
                return false;
            }
        }else{
            this.del(id);
        }
    },
    del : function(id){
        for (var i in industrySelector_Arr){
            if(industrySelector_Arr[i]==id) industrySelector_Arr.splice(i,1);;
        }
        $('#commonSelected .industrySelector'+id).remove();
        $('.industrySelector'+id).removeClass('chkON');
    },
    // 确定
    confirm : function(){
        var areaStr='';
        for(var i in industrySelector_Arr){
            areaStr+=','+industrySelectorArray[industrySelector_Arr[i]];
        }
        areaStr=areaStr.substring(1)?areaStr.substring(1):'';
        $('#'+selectText).val(areaStr);
        $('#'+selectId).val(industrySelector_Arr);
        boxAlpha();
        $('#commonSelected dd').empty();
         if($('#highsearch').length>0){
        	
        	if($('#curtab_one').val()=='0'){
	        	$('#keyword').val('');
				closetj('gzjy');
				closetj('xlyq');
				closetj('yxfw');
				closetj('gsxz');
				closetj('gsgm');
				closetj('rmsq');
				closetj('gzxz');
				delcheck();
        	}
        	if($('#curtab_one').val()=='1'){
        		checkworkarea();
        	}
			JPlaceHolder.clear();   
        }else{
        	JPlaceHolder.clear();  
        }
    },
    cancel : function(){
        boxAlpha();
        $('#commonSelected dd').empty();
    }
}

function getIndustryIDs(idx){
   /* var newArr = new Array();
    for (var i in jarray){
        if(i.substr(0,2)==idx.substr(0,2)&&i.substr(5,2)!='00'){
            newArr[newArr.length]=i;
        }
    }
    return newArr;*/
}

/**
 * 行业选择器
 * @param _selectNum 可以选择数量
 * @param _selectText 选择的文本显示的位置
 * @param _selectId 选择的数据的ID存储的隐藏区域
 */
function IndustrySelect(_selectNum,_selectText,_selectId,_language){
    if("en"==_language){
        industrySelectorArray = industryarray;
    }else{
        industrySelectorArray = industryarray;
    }
    this.selectNum = _selectNum;
    this.selectText = _selectText;
    this.selectId = _selectId;
    var dragHtml ='<div id="commonSelectAlpha">';
    dragHtml+='		<dl id="commonSelected"><dt>已选择行业：</dt><dd></dd></dl>';
    dragHtml+='		<div id="allSelectData"></div>';
    dragHtml+='</div>';
    $('#drag_h').html('<b>请选择行业（您最多能选择'+selectNum+'项）</b><div><span class="btn" onclick="industrySelector.confirm()">确&nbsp;定</span>&nbsp;&nbsp;<span class="btn" onclick="industrySelector.cancel()">关&nbsp;闭</span></div>');
    $('#drag_con').html(dragHtml);
    industrySelector.init();
    industrySelector.Show();
    boxAlpha();
    draglayer();
}

/* *******************************行业选择器结束*************************************** */



/* *******************************职位分类选择器开始************************************** */
//工作地点
//var jobarray=[];
//jobarray['010100']='科学研究';



//职位分类
/*var _jabclass=
    [
        ['政府/科研/教育/培训',['010100','010200','010300','010400']],
        ['人事/行政/管理',['020100','020200','020300']],
        ['IT/计算机/互联网/通讯/电子',['030100','030200','030300','030400','030500','030600','030700','030800','030900']],
        ['销售/客服/技术支持/零售',['040100','040200','040300','040400']],
        ['市场/广告/设计创意/影视',['050100','050200','050300','050400']],
        ['财税/证券期货/银行/保险',['060100','060200','060300','060400','060500','060600']],
        ['生产/采购/贸易/交通',['070100','070200','070300','070400','070500','070600','070700','070800']],
        ['生物/制药/医疗/护理',['080100','080200','080300','080400','080500','080600']],
        ['建筑/房地产',['090100','090200']],
        ['中介/咨询/法务',['100100','100200']],
        ['出版/印刷',['110100','110200']],
        ['轻工/纺织/化工/工艺品',['120100','120200','120300','120400','120500','120600']],
        ['勘探/气象/环境保护',['130100','130200','130300']],
        ['能源/采掘/冶炼/水利',['140100','140200','140300','140400','140500','140600']],
        ['餐饮/旅游/翻译',['150100','150200','150300','150400']],
        ['机械/汽车',['160100','160200']],
        ['生活服务',['170100','170200']],
        ['市政/农林',['180100','180200']],
        ['技工/普工',['190100','190200']],
        ['其他',['200100']]
    ];*/




/*
 * 职位选择
 */
//选择的数据显示的位置
var selectText;
//选择的数据ID存储的隐藏区域
var selectId;
//可以选择的数量
var selectNum;
//数据数组（中英文）
var job_Array;

//已选择的数据数组
var job_Arr = new Array();


var jobSelect = {
    init :function(){
        var_ids='';
        _ids = document.getElementById(selectId).value;
        if(""!=_ids){
            var _id = _ids.split(',');
            for ( var int = 0; int < _id.length; int++) {
                job_Arr[int] = _id[int];
            }
        }else{
        	job_Arr = new Array();
        }
    },
    Show : function(){
        var k=0,output='',output2='',arr,area,select_ed;
        var Div		= new Array('allSelectData');
        var Title	= new Array('<h4></h4>');
        var LoopArr	= new Array(_jabclass);

        for(var i in job_Arr){
            output2+='<li class="jobArea'+job_Arr[i]+' chkON" onclick="jobSelect.Chk(\''+job_Arr[i]+'\')">'+job_Array[job_Arr[i]]+'</li>';
        }
        $('#commonSelected dd').html(output2);

        output	= Title[k];
        arr		= LoopArr[k];
        for (var i in arr){
            area=arr[i][0];
            output+='<dl><dt title=\''+area+'\'>'+area+'</dt><dd>';
            for (var j in arr[i][1] ){
                id=arr[i][1][j];
                var subclassarr = getJobIDs(id);
                if(subclassarr.length > 0){
                    output+='<li title=\''+job_Array[id]+'\' class="jobArea'+id+select_ed+'" onclick="jobSelect.SubLayer(\''+id+'\',event)">'+job_Array[id]+'</li>';
                }else{
                    output+='<li title=\''+job_Array[id]+'\' class="jobArea'+id+select_ed+'" onclick="jobSelect.Chk(\''+id+'\')">'+job_Array[id]+'</li>';
                }
            }
            output+='</dd></dl>';
        }

        $('#'+Div[k]).html(output);

        $('#drag').width('780px');
        // 鼠标悬停变色
        $('#commonSelectAlpha li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
        // 点击弹出子菜单
        $('#allSelectData li').click(function(e){$("#sublist").hover(function(){$(this).show()},function(){$(this).hide()})})
    },
    // 下拉菜单
    SubLayer : function(id,event){
        var output='<div id="sub_SelectData">',width,select_ed,key;
        select_ed=in_array(id,job_Arr)?' chkON':'';
        var arr=getJobIDs(id);
        width=Math.ceil(Math.sqrt(arr.length))*100;
        output+='<ul style="width:'+width+'px"><h4 onclick="jobSelect.Chk(\''+id+'\')">';
        output+='<a href="javascript:" class="jobArea' + id + select_ed +'">'+job_Array[id]+'</a></h4>';

        for (var i=0;i<arr.length;i++){
            key=arr[i];
            select_ed=in_array(key,job_Arr)?' chkON':'';
            output+='<li><a href="javascript:" title=\''+job_Array[key]+'\' class="jobArea' + key + select_ed +'" onclick="jobSelect.Chk(\''+key+'\')">'+job_Array[key]+'</a></li>';
        }
        output=output+'</ul></div>';
        if(true){//使参数变成局部变量
	        var FW=$(window).width();
			var FH=$(window).height();
			var mouseX = event.pageX;//鼠标当前X坐标
			var mouseY = event.pageY;//鼠标当前Y坐标
			if(!event.pageX){//IE浏览器下
		        mouseX=event.clientX+document.body.scrollLeft -document.body.clientLeft;
		        mouseY=event.clientY+document.documentElement.scrollTop-document.body.clientTop;     
		    }
		    var selectH = 0;
		    if(arr.length>1){
				selectH = Math.floor((arr.length/Math.floor(width/150)+1))*25;//子菜单的高度
		    }else{
		    	selectH = 50;
		    }
			if((selectH+event.clientY+10)>FH){//当弹出框位置超出屏幕高度时
				mouseY = mouseY-(selectH+event.clientY-FH)-30;
			}
			if((width+event.clientX)>FW){//当弹出框位置超出屏幕宽度时
				mouseX = mouseX-(width+event.clientX-FW)-30;
			}
			$("#sublist").css({top:mouseY-4,left:mouseX-4});
        }
        $("#sublist").html(output).show();
    },
    //单击事件
    Chk : function(id){
        if(!in_array(id,job_Arr)){
            var subArea,myid;
            if(id.substr(2)=='00'){	// 选中父类清除子类
                subArea=getJobIDs(id);
                for(var i in subArea){
                    if(in_array(subArea[i],job_Arr)) this.del(subArea[i]);
                }
            }else{	// 选中子类清除父类
                myid=id.substr(0,2)+'00';
                if(in_array(myid,job_Arr)) this.del(myid);
            };
            if(job_Arr.length<selectNum){
                job_Arr[job_Arr.length]=id;
                var html='<li title=\''+job_Array[id]+'\' class="jobArea'+id+'" onclick="jobSelect.Chk(\''+id+'\')">'+job_Array[id]+'</li>';
                $('#commonSelected dd').append(html);
                $('.jobArea'+id).addClass('chkON');
                $('#commonSelected li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
            }else{
                alert('您最多能选择'+selectNum+'项');
                return false;
            }
        }else{
            this.del(id);
        }
    },
    del : function(id){
        for (var i in job_Arr){
            if(job_Arr[i]==id) job_Arr.splice(i,1);;
        }
        $('#commonSelected .jobArea'+id).remove();
        $('.jobArea'+id).removeClass('chkON');
    },
    // 确定
    confirm : function(){
        var areaStr='';
        for(var i in job_Arr){
            areaStr+=','+job_Array[job_Arr[i]];
        }
        areaStr=areaStr.substring(1)?areaStr.substring(1):'';
        $('#'+selectText).val(areaStr);
        $('#'+selectId).val(job_Arr);
        boxAlpha();
        $('#commonSelected dd').empty();
         if($('#highsearch').length>0){
        	
        	if($('#curtab_one').val()=='0'){
	        	$('#keyword').val('');
				closetj('gzjy');
				closetj('xlyq');
				closetj('yxfw');
				closetj('gsxz');
				closetj('gsgm');
				closetj('rmsq');
				closetj('gzxz');
				delcheck();
        	}
        	if($('#curtab_one').val()=='1'){
        		checkworkarea();
        	}
			JPlaceHolder.clear();   
        }else{
        	JPlaceHolder.clear();
        }
    },
    cancel : function(){
        boxAlpha();
        $('#commonSelected dd').empty();
    }
}

function getJobIDs(idx){
    var newArr = new Array();
    for (var i in jobarray){
        if(i.substr(0,4)==idx.substr(0,4)){
            if(idx != i){
                newArr[newArr.length]=i;
            }
        }
    }
    return newArr;
}
/**
 * 职位选择
 * @param _selectNum 可以选择数量
 * @param _selectText 选择的文本显示的位置
 * @param _selectId 选择的数据的ID存储的隐藏区域
 */
function _jobSelect(_selectNum,_selectText,_selectId,_language){
    if("en"==_language){
        job_Array = jobarray_en;
    }else{
        job_Array = jobarray;
    }
    this.selectNum = _selectNum;
    this.selectText = _selectText;
    this.selectId = _selectId;
    var dragHtml ='<div id="commonSelectAlpha">';
    dragHtml+='		<dl id="commonSelected"><dt>已选择职位：</dt><dd></dd></dl>';
    dragHtml+='		<div id="allSelectData"></div>';
    dragHtml+='</div>';
    $('#drag_h').html('<b>请选择职位（您最多能选择'+selectNum+'项）</b><div><span class="btn" onclick="jobSelect.confirm()">确&nbsp;定</span>&nbsp;&nbsp;<span class="btn" onclick="jobSelect.cancel()">关&nbsp;闭</span></div>');
    $('#drag_con').html(dragHtml);
    jobSelect.init();
    jobSelect.Show();
    boxAlpha();
    draglayer();
}

/* *******************************职位分类选择器结束************************************** */



/* ******************************专业选择器开始*************************************** */



/*
 * 专业选择
 */
//选择的数据显示的位置
var selectText;
//选择的数据ID存储的隐藏区域
var selectId;
//可以选择的数量
var selectNum;
//数据数组（中英文）
var major_Array;

//已选择的数据数组
var major_Arr = new Array();


var majorSelect = {
    init :function(){
        var_ids='';
        _ids = document.getElementById(selectId).value;
        if(""!=_ids){
            var _id = _ids.split(',');
            for ( var int = 0; int < _id.length; int++) {
                major_Arr[int] = _id[int];
            }
        }else{
        	major_Arr = new Array();
        }
    },
    Show : function(){
        var k=0,output='',output2='',arr,area,select_ed;
        var Div		= new Array('allSelectData');
        var Title	= new Array('<h4></h4>');
        var LoopArr	= new Array(_majorclass);

        for(var i in major_Arr){
            output2+='<li class="majorArea'+major_Arr[i]+' chkON" onclick="majorSelect.Chk(\''+major_Arr[i]+'\')">'+major_Array[major_Arr[i]]+'</li>';
        }
        $('#commonSelected dd').html(output2);

        output	= Title[k];
        arr		= LoopArr[k];
        for (var i in arr){
            area=arr[i][0];
            output+='<dl><dt title=\''+area+'\'>'+area+'</dt><dd>';
            for (var j in arr[i][1] ){
                id=arr[i][1][j];
                var subclassarr = getmajorIDs(id);
                if(subclassarr.length > 0){
                    output+='<li title=\''+major_Array[id]+'\' class="majorArea'+id+select_ed+'" onclick="majorSelect.SubLayer(\''+id+'\',event)">'+major_Array[id]+'</li>';
                }else{
                    output+='<li title=\''+major_Array[id]+'\' class="majorArea'+id+select_ed+'" onclick="majorSelect.Chk(\''+id+'\')">'+major_Array[id]+'</li>';
                }
            }
            output+='</dd></dl>';
        }

        $('#'+Div[k]).html(output);

        $('#drag').width('780px');
        // 鼠标悬停变色
        $('#commonSelectAlpha li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
        // 点击弹出子菜单
        $('#allSelectData li').click(function(e){$("#sublist").hover(function(){$(this).show()},function(){$(this).hide()})})
    },
    // 下拉菜单
    SubLayer : function(id,event){
        var output='<div id="sub_SelectData">',width,select_ed,key;
        select_ed=in_array(id,major_Arr)?' chkON':'';
        var arr=getmajorIDs(id);
        width=Math.ceil(Math.sqrt(arr.length))*100;
        output+='<ul style="width:'+width+'px"><h4 onclick="majorSelect.Chk(\''+id+'\')">';
        output+='<a href="javascript:" class="majorArea' + id + select_ed +'">'+major_Array[id]+'</a></h4>';

        for (var i=0;i<arr.length;i++){
            key=arr[i];
            select_ed=in_array(key,major_Arr)?' chkON':'';
            output+='<li><a href="javascript:" title=\''+major_Array[key]+'\' class="majorArea' + key + select_ed +'" onclick="majorSelect.Chk(\''+key+'\')">'+major_Array[key]+'</a></li>';
        }
        output=output+'</ul></div>';
        if(true){//使参数变成局部变量
	        var FW=$(window).width();
			var FH=$(window).height();
			var mouseX = event.pageX;//鼠标当前X坐标
			var mouseY = event.pageY;//鼠标当前Y坐标
			if(!event.pageX){//IE浏览器下
		        mouseX=event.clientX+document.body.scrollLeft -document.body.clientLeft;
		        mouseY=event.clientY+document.documentElement.scrollTop-document.body.clientTop;     
		    }
		    var selectH = 0;
		    if(arr.length>1){
				selectH = Math.floor((arr.length/Math.floor(width/150)+1))*25;//子菜单的高度
		    }else{
		    	selectH = 50;
		    }
			if((selectH+event.clientY+10)>FH){//当弹出框位置超出屏幕高度时
				mouseY = mouseY-(selectH+event.clientY-FH)-30;
			}
			if((width+event.clientX)>FW){//当弹出框位置超出屏幕宽度时
				mouseX = mouseX-(width+event.clientX-FW)-30;
			}
			$("#sublist").css({top:mouseY-4,left:mouseX-4});
        }	
        $("#sublist").html(output).show();
    },
    //单击事件
    Chk : function(id){
        if(!in_array(id,major_Arr)){
            var subArea,myid;
            if(id.substr(2)=='00'){	// 选中父类清除子类
                subArea=getmajorIDs(id);
                for(var i in subArea){
                    if(in_array(subArea[i],major_Arr)) this.del(subArea[i]);
                }
            }else{	// 选中子类清除父类
                myid=id.substr(0,2)+'00';
                if(in_array(myid,major_Arr)) this.del(myid);
            };
            if(major_Arr.length<selectNum){
                major_Arr[major_Arr.length]=id;
                var html='<li title=\''+major_Array[id]+'\' class="majorArea'+id+'" onclick="majorSelect.Chk(\''+id+'\')">'+major_Array[id]+'</li>';
                $('#commonSelected dd').append(html);
                $('.majorArea'+id).addClass('chkON');
                $('#commonSelected li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
            }else{
                alert('您最多能选择'+selectNum+'项');
                return false;
            }
        }else{
            this.del(id);
        }
    },
    del : function(id){
        for (var i in major_Arr){
            if(major_Arr[i]==id) major_Arr.splice(i,1);;
        }
        $('#commonSelected .majorArea'+id).remove();
        $('.majorArea'+id).removeClass('chkON');
    },
    // 确定
    confirm : function(){
        var areaStr='';
        for(var i in major_Arr){
            areaStr+=','+major_Array[major_Arr[i]];
        }
        areaStr=areaStr.substring(1)?areaStr.substring(1):'';
        $('#'+selectText).val(areaStr);
        $('#'+selectId).val(major_Arr);
        boxAlpha();
        $('#commonSelected dd').empty();
        
        JPlaceHolder.clear();
    },
    cancel : function(){
        boxAlpha();
        $('#commonSelected dd').empty();
    }
}

function getmajorIDs(idx){
    var newArr = new Array();
    for (var i in majorarray){
        if(i.substr(0,2)==idx.substr(0,2)){
            if(idx != i){
                newArr[newArr.length]=i;
            }
        }
    }
    return newArr;
}
/**
 * 专业选择
 * @param _selectNum 可以选择数量
 * @param _selectText 选择的文本显示的位置
 * @param _selectId 选择的数据的ID存储的隐藏区域
 */
function _majorSelect(_selectNum,_selectText,_selectId,_language){
    if("en"==_language){
        major_Array = majorarray_en;
    }else{
        major_Array = majorarray;
    }
    this.selectNum = _selectNum;
    this.selectText = _selectText;
    this.selectId = _selectId;
    var dragHtml ='<div id="commonSelectAlpha">';
    dragHtml+='		<dl id="commonSelected"><dt>已选择专业：</dt><dd></dd></dl>';
    dragHtml+='		<div id="allSelectData"></div>';
    dragHtml+='</div>';
    $('#drag_h').html('<b>请选择专业（您最多能选择'+selectNum+'项）</b><div><span class="btn" onclick="majorSelect.confirm()">确&nbsp;定</span>&nbsp;&nbsp;<span class="btn" onclick="majorSelect.cancel()">关&nbsp;闭</span></div>');
    $('#drag_con').html(dragHtml);
    majorSelect.init();
    majorSelect.Show();
    boxAlpha();
    draglayer();
}





/* *******************************职位亮点选择器*************************************** */

//var _jobbright_class=[["亮点",["010001","010002","010003","010004","010005","010006","010007","010008","010009","010010","010011","010012","010013","010014","010015","010016","010017","010018","010019","010020","010021","010022","010023","010024","010025","010026","010027","010028","010029","010030"]]]
//var jobbrightarray={"010001":"做一休一","010002":"周末双休","010003":"带薪年假","010004":"五险一金","010005":"年终双薪","010006":"奖金丰厚","010007":"绩效奖金","010008":"年终分红","010009":"股票期权","010010":"全勤奖","010011":"人才推荐奖","010012":"加班补助","010013":"交通补助","010014":"出差补贴","010015":"包吃包住","010016":"包住宿","010017":"包三餐","010018":"房补","010019":"话补","010020":"采暖补贴","010021":"高温补贴","010022":"节日福利","010023":"专业培训","010024":"立即上岗","010025":"提供班车","010026":"弹性工作","010027":"补充医疗保险","010028":"定期体检","010029":"员工旅游","010030":"出国机会"}


//已选择的数据数组
var jobbrightSelector_Arr = new Array();
//可以选择的数量
var selectNum;
var selectText;
var selectId;
var jobbrightSelectorArray ;

var jobbrightSelector = {
    //初始化
    init :function(){
        var_ids='';
        _ids = document.getElementById(selectId).value;
        if(""!=_ids){
            var _id = _ids.split(',');
            for ( var int = 0; int < _id.length; int++) {
                jobbrightSelector_Arr[int] = _id[int];
            }
        }else{
        	jobbrightSelector_Arr = new Array();
        }
    },
    //页面显示
    Show : function(){
        _hiddenEditorText();
        var k=0,output='',output2='',arr,area,select_ed;
        var Div		= new Array('allSelectData');
        var Title	= new Array('<h4>&nbsp;</h4>');
        var LoopArr	= new Array(_jobbright_class);

        for(var i in jobbrightSelector_Arr){
            output2+='<li class="jobbrightSelector'+jobbrightSelector_Arr[i]+' chkON" onclick="jobbrightSelector.Chk(\''+jobbrightSelector_Arr[i]+'\')">'+jobbrightSelectorArray[jobbrightSelector_Arr[i]]+'</li>';
        }
        $('#commonSelected dd').html(output2);

        while(k<1){
            output	= Title[k];
            arr		= LoopArr[k];
            for (var i in arr){
                area=arr[i][0];
                output+='<dl><dt title=\''+area+'\'>'+area+'</dt><dd>';
                for (var j in arr[i][1] ){
                    id=arr[i][1][j];
                    if(k==0){
                        select_ed=in_array(id,jobbrightSelector_Arr)?' chkON':'';
                        output+='<li title=\''+jobbrightSelectorArray[id]+'\' class="jobbrightSelector'+id+select_ed+'" onclick="jobbrightSelector.Chk(\''+id+'\')">'+jobbrightSelectorArray[id]+'</li>';
                    }
                }
                output+='</dd></dl>';
            }

            $('#'+Div[k]).html(output);
            k++;
        }
        $('#drag').width('780px');
        // 鼠标悬停变色
        $('#commonSelectAlpha li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
        // 点击弹出子菜单
        $('#allSelectData li').click(function(e){$("#sublist").css({top:e.pageY-4,left:e.pageX-4}).hover(function(){$(this).show()},function(){$(this).hide()})})
    },
    //单击事件
    Chk : function(id){
        if(!in_array(id,jobbrightSelector_Arr)){
            var subArea,myid;
            if(id.substr(2)=='00'){	// 选中父类清除子类
                subArea=getPosBrightIds(id);
                for(var i in subArea){
                    if(in_array(subArea[i],jobbrightSelector_Arr)) this.del(subArea[i]);
                }
            }else{	// 选中子类清除父类
                myid=id.substr(0,2)+'00';
                if(in_array(myid,jobbrightSelector_Arr)) this.del(myid);
            };
            if(jobbrightSelector_Arr.length<selectNum){
                jobbrightSelector_Arr[jobbrightSelector_Arr.length]=id;
                var html='<li title=\''+jobbrightSelectorArray[id]+'\' class="jobbrightSelector'+id+'" onclick="jobbrightSelector.Chk(\''+id+'\')">'+jobbrightSelectorArray[id]+'</li>';
                $('#commonSelected dd').append(html);
                $('.jobbrightSelector'+id).addClass('chkON');
                $('#commonSelected li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
            }else{
                alert('您最多能选择'+selectNum+'项');
                return false;
            }
        }else{
            this.del(id);
        }
    },
    del : function(id){
        for (var i in jobbrightSelector_Arr){
            if(jobbrightSelector_Arr[i]==id) jobbrightSelector_Arr.splice(i,1);;
        }
        $('#commonSelected .jobbrightSelector'+id).remove();
        $('.jobbrightSelector'+id).removeClass('chkON');
    },
    // 确定
    confirm : function(){
        var areaStr='';
        for(var i in jobbrightSelector_Arr){
            areaStr+=','+jobbrightSelectorArray[jobbrightSelector_Arr[i]];
        }
        areaStr=areaStr.substring(1)?areaStr.substring(1):'';
        $('#'+selectText).val(areaStr);
        $('#'+selectId).val(jobbrightSelector_Arr);
        boxAlpha();
        $('#commonSelected dd').empty();
        
        JPlaceHolder.clear();
    },
    cancel : function(){
        boxAlpha();
        $('#commonSelected dd').empty();
    }
}



function getPosBrightIds(idx){
    /*var newArr = new Array();
    for (var i in jarray){
        if(i.substr(0,2)==idx.substr(0,2)){
            newArr[newArr.length]=i;
        }
    }
    return newArr;*/
}


/**
 * 职位亮点选择器
 * @param _selectNum 可以选择数量
 * @param _selectText 选择的文本显示的位置
 * @param _selectId 选择的数据的ID存储的隐藏区域
 */
function JobbrightSelect(_selectNum,_selectText,_selectId,_language){
    if("en"==_language){
        jobbrightSelectorArray = jobbrightarray;
    }else{
        jobbrightSelectorArray = jobbrightarray;
    }
    this.selectNum = _selectNum;
    this.selectText = _selectText;
    this.selectId = _selectId;
    var dragHtml ='<div id="commonSelectAlpha">';
    dragHtml+='		<dl id="commonSelected"><dt>已选亮点：</dt><dd></dd></dl>';
    dragHtml+='		<div id="allSelectData"></div>';
    dragHtml+='</div>';
    $('#drag_h').html('<b>请选择亮点（您最多能选择'+selectNum+'项）</b><div><span class="btn" onclick="jobbrightSelector.confirm()">确&nbsp;定</span>&nbsp;&nbsp;<span class="btn" onclick="jobbrightSelector.cancel()">关&nbsp;闭</span></div>');
    $('#drag_con').html(dragHtml);
    jobbrightSelector.init();
    jobbrightSelector.Show();
    boxAlpha();
    draglayer();
}

/* *******************************职位亮点选择器*************************************** */





/* *******************************商业圈选择器开始*************************************** */
//已选择的数据数组
var businessCircleSelector_Arr = new Array();
//可以选择的数量
var selectNum;
var selectText;
var selectId;
var businessCircleSelectorArray ;

var businessCircleSelector = {
    //初始化
    init :function(){
        var_ids='';
        _ids = document.getElementById(selectId).value;
        if(""!=_ids){
            var _id = _ids.split(',');
            for ( var int = 0; int < _id.length; int++) {
                businessCircleSelector_Arr[int] = _id[int];
            }
        }else{
        	businessCircleSelector_Arr = new Array();
        }
    },
    //页面显示
    Show : function(){
        _hiddenEditorText();
        var k=0,output='',output2='',arr,area,select_ed;
        var Div		= new Array('allSelectData');
        var Title	= new Array('<h4>&nbsp;</h4>');
        var LoopArr	= new Array(_businessCircle_class);

        for(var i in businessCircleSelector_Arr){
            output2+='<li class="businessCircleSelector'+businessCircleSelector_Arr[i]+' chkON" onclick="businessCircleSelector.Chk(\''+businessCircleSelector_Arr[i]+'\')">'+businessCircleSelectorArray[businessCircleSelector_Arr[i]]+'</li>';
        }
        $('#commonSelected dd').html(output2);

        while(k<1){
            output	= Title[k];
            arr		= LoopArr[k];
            for (var i in arr){
                area=arr[i][0];
                output+='<dl><dt title=\''+area+'\'>'+area+'</dt><dd>';
                for (var j in arr[i][1] ){
                    id=arr[i][1][j];
                    if(k==0){
                        select_ed=in_array(id,businessCircleSelector_Arr)?' chkON':'';
                        output+='<li title=\''+businessCircleSelectorArray[id]+'\' class="businessCircleSelector'+id+select_ed+'" onclick="businessCircleSelector.Chk(\''+id+'\')">'+businessCircleSelectorArray[id]+'</li>';
                    }
                }
                output+='</dd></dl>';
            }

            $('#'+Div[k]).html(output);
            k++;
        }
        $('#drag').width('780px');
        // 鼠标悬停变色
        $('#commonSelectAlpha li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
        // 点击弹出子菜单
        $('#allSelectData li').click(function(e){$("#sublist").css({top:e.pageY-4,left:e.pageX-4}).hover(function(){$(this).show()},function(){$(this).hide()})})
    },
    //单击事件
    Chk : function(id){
        if(!in_array(id,businessCircleSelector_Arr)){
            var subArea,myid;
            if(id.substr(2)=='00'){	// 选中父类清除子类
                subArea=getbusinessCircleIDs(id);
                for(var i in subArea){
                    if(in_array(subArea[i],businessCircleSelector_Arr)) this.del(subArea[i]);
                }
            }else{	// 选中子类清除父类
                myid=id.substr(0,2)+'00';
                if(in_array(myid,businessCircleSelector_Arr)) this.del(myid);
            };
            if(businessCircleSelector_Arr.length<selectNum){
                businessCircleSelector_Arr[businessCircleSelector_Arr.length]=id;
                var html='<li title=\''+businessCircleSelectorArray[id]+'\' class="businessCircleSelector'+id+'" onclick="businessCircleSelector.Chk(\''+id+'\')">'+businessCircleSelectorArray[id]+'</li>';
                $('#commonSelected dd').append(html);
                $('.businessCircleSelector'+id).addClass('chkON');
                $('#commonSelected li').hover(function(){$(this).addClass('over')},function(){$(this).removeClass('over')});
            }else{
                alert('您最多能选择'+selectNum+'项');
                return false;
            }
        }else{
            this.del(id);
        }
    },
    del : function(id){
        for (var i in businessCircleSelector_Arr){
            if(businessCircleSelector_Arr[i]==id) businessCircleSelector_Arr.splice(i,1);;
        }
        $('#commonSelected .businessCircleSelector'+id).remove();
        $('.businessCircleSelector'+id).removeClass('chkON');
    },
    // 确定
    confirm : function(){
        var areaStr='';
        for(var i in businessCircleSelector_Arr){
            areaStr+=','+businessCircleSelectorArray[businessCircleSelector_Arr[i]];
        }
        areaStr=areaStr.substring(1)?areaStr.substring(1):'';
        $('#'+selectText).val(areaStr);
        $('#'+selectId).val(businessCircleSelector_Arr);
        boxAlpha();
        $('#commonSelected dd').empty();
        
        JPlaceHolder.clear();
    },
    cancel : function(){
        boxAlpha();
        $('#commonSelected dd').empty();
    }
}

function getbusinessCircleIDs(idx){
    /*var newArr = new Array();
    for (var i in jarray){
        if(i.substr(0,2)==idx.substr(0,2)&&i.substr(5,2)!='00'){
            newArr[newArr.length]=i;
        }
    }
    return newArr;
*/
}

/**
 * 商业圈选择器
 * @param _selectNum 可以选择数量
 * @param _selectText 选择的文本显示的位置
 * @param _selectId 选择的数据的ID存储的隐藏区域
 */
function businessCircleSelect(_selectNum,_selectText,_selectId,_language){
    if("en"==_language){
        businessCircleSelectorArray = businessCirclearray;
    }else{
        businessCircleSelectorArray = businessCirclearray;
    }
    this.selectNum = _selectNum;
    this.selectText = _selectText;
    this.selectId = _selectId;
    var dragHtml ='<div id="commonSelectAlpha">';
    dragHtml+='		<dl id="commonSelected"><dt>已选商圈：</dt><dd></dd></dl>';
    dragHtml+='		<div id="allSelectData"></div>';
    dragHtml+='</div>';
    $('#drag_h').html('<b>请选择商业圈（您最多能选择'+selectNum+'项）</b><div><span class="btn" onclick="businessCircleSelector.confirm()">确&nbsp;定</span>&nbsp;&nbsp;<span class="btn" onclick="businessCircleSelector.cancel()">关&nbsp;闭</span></div>');
    $('#drag_con').html(dragHtml);
    businessCircleSelector.init();
    businessCircleSelector.Show();
    boxAlpha();
    draglayer();
}

/* *******************************商业圈选择器结束*************************************** */