/*
*获取设备窗口的大小
*/
var windowHeight = $(window).height();
var windowWidth = $(window).width();


$('#menu').on('click', 'li', function(){
	event.preventDefault();
	var order = $(this).html();

 	switch (order){
 		case "新建文件夹":
 			creatFileBorder("creatDir");

 		break;
 		case "新建文件" :
 			creatFileBorder("creatFile");
 		break;
 		case "打开文件":

 		break;
 		case "下载文件":
 			/*
 			 * 改进为批量下载
 			 */
 			giveMsg("开始下载");
 			var fileName = $(this).attr('fileName');
 			var path = $('#icon_home').attr('path');
 			window.location.href = 'download.do?file='+fileName+'&path='+path;

 		break;
 		case "查看文件":

 		break;
 		case "删除文件":
 			/*
 			 * 改进为批量删除
 			 */
 		
 			if(window.confirm("确认删除？")){
 				var file = $(this).attr('filename');
 				var path = $("#icon_home").attr('path');
 	 			window.location.href = 'delFile.do?fileName='+file+'&path='+path;
 			}
 			
 		break;
 		case "预览文件":
 			giveMsg("功能尚未提供！");

 		break;
 		case "重命名":
 			var originName = $(this).attr('file');
 			creatFileBorder("dirRename",originName);
 		break;
 		case '上传文件':
 			showuploadBorder();
 		break;
 	}

 	$("#menu").hide();
	return false;
});

/*
*弹出新建文件夹的border
*/
function creatFileBorder(flag,fileName){

	var height = windowHeight/2 -80;
	var left = windowWidth/2 -150;
	$("#new_file").attr("flag",flag).attr("file",fileName);
	$("#con_cover").show();
	$("#new_file").show();
	$("#new_file").css("left",left);
	$("#new_file").animate({"top":height},150,function(){
	});
}

/*
*获取menu菜单的命令，将其传给后---pageChange.php---台处理
*/
$("#file_confirm").click(function(){

	//获取命令
	var flag = $("#new_file").attr('flag');

	//获取新文件夹的名称
	var DirName = $("#name").val();
	if(DirName==""||DirName==null){
		alert("文件名不能为空");
		return;
	}
	//获取当前文件的名称

	var currentFileName = "";
	
	currentFileName = $("#new_file").attr('file');
	//获取当前路径
	var path = $("#icon_home").attr("path");
	
	//检测文件名；
	if(testFileName(DirName)){
		//文件名合法，把数据给服务器
		window.location.href = "NewDir.do?flag="+flag+"&dir="+DirName+"&path="+path+"&currentFileName="+currentFileName;
	}
	$("#con_cover").hide();
});

/**
*	检测文件名的合法性；
*/
function testFileName(){
	return true;
}

/*
*弹出功能菜单；
*/
function showMenu(e,arr,fileName){

	if (event.which == "3") {
			$("#menu>ul>li").remove();
			for(var i=0;i<arr.length;i++){
				$("#menu>ul").append("<li fileName="+fileName+">"+arr[i]+"</li>");
				var x = event.pageX;
				var y = event.pageY;
				$("#menu").css("top", y).css("left", x).css("display", "block");
		}

	}
		return false;
}