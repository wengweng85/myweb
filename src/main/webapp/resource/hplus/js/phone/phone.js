var contextPath;// 工程路径
$(function() {
	//验证框架使用此属性
	$.metadata.setType("attr", "validate");

	$.ajaxSetup ({ cache: false });

	if (!contextPath) {
		if($('#contextPath').length>0){
			contextPath=$('#contextPath').val();
		}else{
			var pathName = document.location.pathname;
			var index = pathName.substr(1).indexOf("/");
			contextPath = pathName.substr(0,index+1);
			
			if(contextPath=='/cms'){
				contextPath="";
			}
		}

	}
});
var rc = {
	/**
	 * 使用ajax方式提交form
	 * @param _form
	 * @param successFun
	 * @param failureFun
	 */
	validAndAjaxSubmit:function(_form,callback,param){
		param= $.extend(rc.submitparam,param);
		_form.validate({
			submitHandler : function(form) {
				rc.ajaxsubmit($(form),callback,param.ismask);
			}
		})
	},
	ajaxsubmit : function(_form, callback,ismask) {
		rc.ajax(_form.attr('action'),_form.serialize(),callback,'post',null,true);
	},
	ajax:function(url,param,callback,method,maskdom_selector,ismask){
		var _ismask=(ismask==undefined)?false:ismask;
		var options={};
		options.type=method||'get';
		options.cache = false;
		options.url=url;
		options.data=param;
		options.timeout=60*1000,//超时60秒
		options.global=false;
		options.dataType = 'html';
		options.success=function(responseText,textStatus){
			try{
				response = eval('(' + responseText + ')');
			}catch (e) {
				rc.ajax_error(null, '登录超时，请重新登录！');
				return;
			}
			if(callback){
				callback(response);
			}else{
				alert(response.message);
			}

		};
		$.ajax(options);
	}
}


document.createElement('header');
document.createElement('footer');

$(function(){
	
	//Main Swiper
	var swiper = new Swiper('.swiper1', {
		pagination : '.pagination1',
		loop:true,
		grabCursor: true
	});
	//Navigation arrows
	$('.arrow-left').click(function(e) {
        e.preventDefault()
		swiper.swipePrev()
    });
	$('.arrow-right').click(function(e) {
        e.preventDefault()
		swiper.swipeNext()
    });
    //Clickable pagination
    $('.pagination1 .swiper-pagination-switch').click(function(){
    	swiper.swipeTo($(this).index())
    })

	/* Vertical mode: */
	swiperV = $('.swiper-v').swiper({
		mode : "vertical", 
		pagination : '.pagination-v',
		slidesPerSlide : 1
	});
	
})

