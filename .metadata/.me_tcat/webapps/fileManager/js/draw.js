//控制页面的显示
$("#sel_grid").hover(function(){
	$("#sel_grid img").attr("src","./images/grid_hover.png");
},function(){
	$("#sel_grid img").attr("src","./images/grid.png");
});


$("#sel_list").hover(function(){
	$("#sel_list img").attr("src","./images/list_hover.png");
},function(){
	$("#sel_list img").attr("src","./images/list.png");
});
//修饰下载上传等控件的样式
$("#MulDown").hover(function(){
	$("#MulDown img").attr("src","./images/MulDown_hover.png");
},function(){
	$("#MulDown img").attr("src","./images/MulDown.png");
});

$("#MulDel").hover(function(){
	$("#MulDel img").attr("src","./images/MulDel_hover.png");
},function(){
	$("#MulDel img").attr("src","./images/MulDel.png");
});

$(window).resize(function(){
	
	var _width = $(window).width();
	
	resizeWindow(_width);
	
});


/**
 * 对页面重新布局
 */
function resizeWindow(_width){
	if(_width<900){
		
		$("#funcboard").css("display","none");
		$("#file_content").css("width","100%");
		$("#icon_search").css("display","none");
		$("#menu_second").css("width","100%");
	}else{
		//大于800；
		$("#funcboard").css("display","block");
		$("#file_content").css("width","74%");
		$("#icon_search").css("display","block");
		$("#menu_second").css("width","74%");
	}
}


