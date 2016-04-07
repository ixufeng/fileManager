function giveMsg(msg,width,height){
	if(width==undefined){
		width = 200;
	}
	if(height == undefined){
		height = 100;
	}

	var top = windowHeight/2 - height/2;
	var left = windowWidth/2 - width/2;
	$('#msg_con').append('<p>'+msg+'</p>');
	$('#msg_con').css('left',left).css('display','block').css('width',width).css('height',height);
	$('#msg_con').animate({'width':'200px','height':'100px','top':top,'left':left},200,function(){
		$('#msg_con').animate({'width':width,'height':'100px','top':windowHeight/2-height/2,'left':windowWidth/2-width/2},500,function(){
			$('#msg_con').animate({'width':width,'height':height,'top':-height,'left':left},500,function(){
				$('#msg_con').css('display','none');
			});

		});
	});
}

//弹出上传的border
function showuploadBorder(){

	$("#demo").css('left',windowWidth/2-325);
	$('#demo').animate({'top':windowHeight/2 - 180},500);
}
function hideuploadBorder(){
	$("#demo").css('left',windowWidth/2-325);
	$('#demo').animate({'top':-500},500);
	
}


//实现对显示方式的改变
if($.cookie('show_type')===undefined){
	//设置默认显示
	changeShowFile(0);
	
}else if($.cookie('show_type')=="grid"){
	changeShowFile(0);
}else{
	
	changeShowFile(1);
}
/**
 * 
 * @param flag
 * 将搜索的json格式的内容展示
 */
function changeShowFile(flag){
	//grid显示
	
	if(flag==0){
		$("#sel_grid").css("background","rgb(202,202,202)");
		$("#sel_list").css("background","rgb(255,255,255)");
		$("#file_ul_list").css("display","none");
		$("#file_ul_grid").css("display","block");
		$.cookie('show_type','grid');
	}else{
	//list显示
		$("#sel_list").css("background","rgb(202,202,202)");
		$("#sel_grid").css("background","rgb(255,255,255)");
		$("#file_ul_grid").css("display","none");
		$("#file_ul_list").css("display","block");
		$.cookie('show_type','list');
	}
		
}
/**
 * 搜索框样式的改变
 */
function searchTypeChange(){
	
	var value = $("#selOption").val();
	if(value=="fileDate"){
		$("#search_key").attr("type","date");
	}else{
		$("#search_key").attr("type","text");
	}
	
}
/**
 * 让MulDo显示或者隐藏
 */
function showMulDo(){
	if(fileList.length==0){
		$(".MulDo").hide();
	}else{
		$(".MulDo").show();
	}
}

showMulDo();
/**
 * 将当前页面的所有文件都选择
 */
function selectAll(){
	
	var bool = $("#checkbox_c1").attr("isSel");
	
	if(bool==0){
		$("#checkbox_c1").attr("isSel","1");
		//遍历当前页面的所有元素
		$("#file_content>ul>li").each(function(){
			$(this).css("background-color","rgb(204,232,255)");
			var fileLogicName = $(this).find('p').html();
			var fileUniqueName = $(this).attr("filename");
			var fileType=$(this).attr('class');
			var path = $(this).attr('path');
			var fullPath = path +'/'+ name; 
			var fileImg = $(this).find('img').attr("src");
			//将选中的文件记录到fileList中
			var  file = new MyFile(fileLogicName,fileUniqueName,fullPath,fileImg,fileType);
			fileList[fileUniqueName]=file;
			fileList.push(1);
			fileNameList.push(fileUniqueName);
			
		});	
	}else{
		$("#checkbox_c1").attr("isSel","0");
		$("#file_content>ul>li").each(function(){
			
			$(this).css("background-color","rgb(255,255,255)");
			var fileUniqueName = $(this).attr("filename");
			fileList[fileUniqueName]=null;
			fileList.pop(1);
			fileNameList.pop(fileUniqueName);
		});	
	}
	
	showMulDo();
} 

/**
 * 用户功能列表的弹出
 */
$("#user").hover(function(){
	
	$("#user_box").css("height","200px");
},function(){
	$("#user_box").css("height","0px");
});


function showFileSendMenu(){
	
	alert(fileNameList.length);
}

//初始化页面时进行判断
(function(_width){
	
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
	
})($(window).width());

/**
 * 取消文件新建文件
 */
function cancelNewFile(){
	$("#new_file").css("display","none");
	$("#con_cover").css("display","none");
}








