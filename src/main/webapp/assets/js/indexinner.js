$(document).ready(function() {
		(function(){
		$.getJSON("/Sub/activities/AllActivities",function(data){
		let vipactivities = [];
		let nvipactivities = [];
		let message = '';
		$.each(data, function(index,item) {
			if(index=="vipactivities")
			{
				vipactivities = JSON.stringify(item)
			}
			if(index=="nvipactivities")
			{
				nvipactivities = JSON.stringify(item)
			}
			if(index=="message")
			{
				message = JSON.stringify(item)
			}
		});
		if(message!='')
		{
			alert(message)
		}else
		{
			$.each($.parseJSON(vipactivities), function(index,item) {
				let ctime = '';
				let vtime = '';
				let cctime=new Date(item.createtime);
				let vvtime=new Date(item.overtime);
				ctime =formatDate(cctime);
				vtime =formatDate(vvtime);
		  if(item.type=="1"){
			$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/tike.jpg)\'><a class=\'img-link\' href=\'single.html\'></a><span class=\'top-right-icon background8\'><i class=\'mdi mdi-star\'></i></span></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a href=\'category.html\'><span class=\'post-cat background1 color-white\'>替课</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'> ¥"+item.price+"</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>"+ctime+"-"+vtime+"</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>"+item.introduction+"</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'"+item.id+"\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
		  }else if(item.type=="2") {
			$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/ticao.jpg)\'><a class=\'img-link\' href=\'single.html\'></a><span class=\'top-right-icon background8\'><i class=\'mdi mdi-star\'></i></span></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a href=\'category.html\'><span class=\'post-cat background2 color-white\'>替操</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥"+item.price+"</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>"+ctime+"-"+vtime+"</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>"+item.introduction+"</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'"+item.id+"\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
		  }
		  else{
			$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/tikao.jpg)\'><a class=\'img-link\' href=\'single.html\'></a><span class=\'top-right-icon background8\'><i class=\'mdi mdi-star\'></i></span></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a href=\'category.html\'><span class=\'post-cat background3 color-white\'>替考</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥"+item.price+"</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>"+ctime+"-"+vtime+"</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>"+item.introduction+"</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'"+item.id+"\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
		  }
		});
		
		$.each($.parseJSON(nvipactivities), function(index,item) {
			let ctime = '';
			let vtime = '';
			let cctime=new Date(item.createtime);
			let vvtime=new Date(item.overtime);
			ctime =formatDate(cctime);
			vtime =formatDate(vvtime);
		if(item.type=="1") {
			$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/tike.jpg)\'><a class=\'img-link\'></a></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a ><span class=\'post-cat background1 color-white\'>替课</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥"+item.price+"</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>"+ctime+"-"+vtime+"</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>"+item.introduction+"</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'"+item.id+"\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
		}else if(item.type=="2") {
			$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/ticao.jpg)\'><a class=\'img-link\'></a></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a ><span class=\'post-cat background2 color-white\'>替操</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥"+item.price+"</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>"+ctime+"-"+vtime+"</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>"+item.introduction+"</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'"+item.id+"\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
		}
		else {
			$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/tikao.jpg)\'><a class=\'img-link\' ></a></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a ><span class=\'post-cat background3 color-white\'>替考</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥"+item.price+"</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>"+ctime+"-"+vtime+"</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>"+item.introduction+"</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'"+item.id+"\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
		}
		});
		
		}
		
		})
		})()
})
function formatDate(now) {
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	if(month<=9){
		month="0"+month+"";
	}
	var date = now.getDate();
	if(date<=9){
		date="0"+date+"";
	}
	var hour = now.getHours();
	if(hour<=9){
		hour="0"+hour+"";
	}
	var minute = now.getMinutes();
	if(minute<=9){
		minute="0"+minute+"";
	}
	return year + "-" + month + "-" + date + " " + hour + ":" + minute;
}

function accept(id)
{
	$.post('/Sub/activities/AcceptActivities',{id:id},function(data){
		let message = '';
		$.each(data, function(index,item) {
			if(index=="message")
			{
				message = item;
			}
		});
		if(message=="success")
		{
			alert('接受成功');
			window.location.href="/Sub/index.html";
		}else if(message=="未登录")
		{
			alert(message);
			window.location.href="/Sub/login.html";
		}
		else
		{
			alert(message);
		}
			});
}
