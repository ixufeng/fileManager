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