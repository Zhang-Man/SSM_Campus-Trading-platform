$(document).ready(function (){
	(function(){
		$.get("/Sub/activities/ToRelease",function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				
			}else
			{
				alert(message);
				window.location.href="/Sub/login.html";
			}
		});
	})()
	
	document.getElementById("releasesubmit").addEventListener("click",function(){
		$.post('/Sub/activities/Release',{type:$("#type").val(),introduction:$("#introduction").val(),price:$("#price").val(),createtime:$("#createtime").val(),overtime:$("#overtime").val(),address:$("#activitiesaddress").val()},function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				alert("任务发布成功")
				window.location.href="/Sub/index.html";
			}else
			{
				alert(message);
			}
				});
	})
});