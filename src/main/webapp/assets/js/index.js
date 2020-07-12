$(document).ready(function() {
	//-----------------------------------------------------------------
	document.getElementById("loginbutton").addEventListener("click", function() {
		alert('123')
		$.post('/Sub/user/Login', {
			username: $("#username").val(),
			password: $("#pw").val()
		}, function(data) {
			let message = '';
			$.each(data, function(index, item) {
				if(index == "message") {
					message = item;
				}
			});
			if(message == "success") {
				window.location.href = "/Sub/index.html";
			} else {
				alert(message);
			}
		});
	})
	//-----------------------------------------------------------------
	let inputs = document.getElementsByName("checkboxtype");
	for(i = 0; i <= 2; i++) {
		inputs[i].addEventListener('click', function() {
			if(!inputs[i].checked) {
				document.getElementById('checkAll').checked = false;
			}
		})
	}
})

let types = document.getElementsByName('checkboxtype');

document.getElementById('selectsubmit').addEventListener("click", function() {
	let typesvalue = [];
	let div = document.getElementById("ineer2");
	var childs = div.childNodes;
	for(var i = childs.length - 1; i >= 0; i--) {
		div.removeChild(childs.item(i));
	}
	for(let i = 1; i <= 3; i++) {
		if(document.getElementById("" + i + "").checked) {
			typesvalue.push(document.getElementById("" + i + "").value);
		}
	}
	if(typesvalue.length == 0) {
		typesvalue.push(1);
		typesvalue.push(2);
		typesvalue.push(3);
	}

	$.post('/Sub/activities/Select', {
		types: typesvalue,
		topprice: $("#topprice").val(),
		botprice: $("#botprice").val(),
		createtime: $("#createtime").val(),
		overtime: $("#overtime").val()
	}, function(data) {
		let message = '';
		let activities = [];
		let activitiesinfo = [];
		$.each(data, function(index, item) {
			if(index == "message") {
				message = item;
			}
			if(index == "activitiesList") {
				activities = JSON.stringify(item)
			}
		});
		if(message == 'success') {
			$.each($.parseJSON(activities), function(index, item) {
				let type = '';
				var ctime = '';
				var vtime = '';
				var cctime=new Date(item.createtime);
				var vvtime=new Date(item.overtime);
				ctime =formatDate(cctime);
				vtime =formatDate(vvtime);
				if(item.type == "1") {
					$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/tike.jpg)\'><a class=\'img-link\'></a></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a ><span class=\'post-cat background1 color-white\'>替课</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥" + item.price + "</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>" + ctime + " - " + vtime+ "</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>" + item.introduction + "</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'" + item.id + "\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
				} else if(item.type == "2") {
					$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/ticao.jpg)\'><a class=\'img-link\'></a></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a ><span class=\'post-cat background2 color-white\'>替操</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥" + item.price + "</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>" + ctime + " - " + vtime+ "</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>" + item.introduction + "</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'" + item.id + "\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
				} else {
					$("#ineer2").append("<div class=\'row mb-50\'><div class=\'col-md-3\'><div class=\'post-thumb position-relative thumb-overlay mr-20\' style=\'margin-top:-10px ; \'><div class=\'img-hover-slide border-radius-5 position-relative\' style=\'background-image: url(assets/imgs/tikao.jpg)\'><a class=\'img-link\' ></a></div></div></div><div class=\'col-md-8 align-center-vertical\'><div class=\'post-content\'><div class=\'entry-meta meta-0 font-small mb-5\'><a ><span class=\'post-cat background3 color-white\'>替考</span></a></div><h4 class=\'post-title\'><a href=\'single.html\'>¥" + item.price + "</a></h4><div class=\'entry-meta meta-1 font-small color-grey mt-10 mb-10\'><span class=\'time-reading\'><i class=\'ti-timer\'></i>" + ctime + " - " + vtime+ "</span></div><p class=\'font-medium\' style=\'overflow: hidden;text-overflow: ellipsis;display:-webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;\'>" + item.introduction + "</p><div style=\'display: inline;\'><button onclick=\'accept("+item.id+")\' id=\'" + item.id + "\' class=\'font-medium \' style=\'float:right;\'><a href=\'#\'>踏入违法的深渊</a></button></div></div></div></div>");
				}
			});
		} else {
			alert(message)
			window.location.href = "/Sub/index.html";
		}
	});
})

function checkall() {
	if(document.getElementById('checkAll').checked) {
		document.getElementById('1').checked = true;
		document.getElementById('2').checked = true;
		document.getElementById('3').checked = true;
	} else {
		document.getElementById('1').checked = false;
		document.getElementById('2').checked = false;
		document.getElementById('3').checked = false;
	}

}
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