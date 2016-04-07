
function searchHelper(){
	//获取搜索的关键字
	var search_key = $("#search_key").val();
	var search_type = $("#selOption").val();
	if(search_key==""){
		return;
	}
	
	$.ajax({
	    type: 'POST',
	    url: 'search.do',
	    dataType: 'json',
	    data: {
	    	'searchType':search_type,
	    	'searchWord':search_key
	    	},
	    success:function(data){
	    	
	    	showSearchFile(data);
	    	
	    }
	    
	});
	
	
}


//参照格式
/*
<li>
<img alt="文件" src="./images/test.png"/>
<span>文件名</span>
<a href='#'>查看</a>
<a href='#'>下载</a>
<a hreg='#'>更多</a>
</li>*/

function showSearchFile(json){
	
	$("#searchRes_con>li").remove();
	
	if(json[0]=="没有找到文件"){
		var html = '';
		html = '<li>没有找到文件</li>';
		$("#searchRes_con").append(html);
	}else{
		

		$(json).each(function(){
			var html = '';
			html = '<li><img alt="文件" src="'+this.filePic+'">&nbsp;<span>'+this.fileName+'</span>&nbsp<a href="#">查看</a>&nbsp<a href="#">下载</a>&nbsp<a href="#">更多</a></li>';
			$("#searchRes_con").append(html);
		});
	}
	
	
	
	
	
}













