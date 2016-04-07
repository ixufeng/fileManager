
$(document).ready(function () {

		$(document).bind("contextmenu", function (e) {
			return false;
		});

});

//进入文件夹或选中文件
$("#file_content>ul>li").click(function(e){
	$(this).css("background-color","rgb(204,232,255)");
	var fileType=$(this).attr('class');
	var path = $(this).attr('path');
	var name = $(this).find('p').html();
	var fullPath = path +'/'+ name; 
	if(fileType == "dir"){
		
		//如果点击的是文件夹，则进入文件夹
		//页面跳转来读取dir中的内容
		window.location.href = './homePage.jsp?currentPath='+fullPath;
	}
	if(fileType == "file"){

	}
});

//返回主目录
$("#icon_home").click(function(){

	$(this).css("background","rgb(0,0,0)");
	window.location.href = './homePage.jsp';

});


$("#file_content").click(function(){
	$("#menu").css("display","none");
});


//鼠标右击空白出现的功能菜单
$("body").mousedown(function (event) {

		var arr=['新建文件夹','新建文件','上传文件'];
		showMenu(event,arr);

});


//右击文件出现的功能菜单
$("#file_content>ul>li").mousedown(function(event){

	var fileName = $(this).attr('fileName');

	if(event.which == 3){
		var arr=['打开','预览文件','下载文件','查看文件','删除文件','重命名'];
		showMenu(event,arr,fileName);
		event.stopPropagation();
	}
});

//返回上级目录
	$("#back_icon").click(function() {

		var currentPath = $("#icon_home").attr("path");
		if(currentPath=='./home'){
			giveMsg("已经是主目录了！");
			return;
		}
		var parentPath = getBack(currentPath);

		window.location.href = './homePage.jsp?currentPath='+parentPath;

	});
function getBack(path){

	var items = path.split('/');
	var parentPath ="";
	for(var  i = 0; i<items.length - 1; i++){
		if(i==items.length - 2){
			parentPath+=items[i];
		}else{
			parentPath+=items[i]+"/";
		}

	}
	return parentPath;

}

