function myactivities() {
	let table = document.getElementById("tableinner1");
	var childs = table.childNodes;
	for(var i = childs.length - 1; i >= 0; i--) {
		table.removeChild(childs.item(i));
	}
	if($("#releasestatus").val() != "" && $("#acceptstatus").val() != "") {
		alert("只能选择一项！");
		window.location.href = "/Sub/myactivities.html";
	}
	$.getJSON("/Sub/activities/MyActivities", {
		releasestatus: $("#releasestatus").val(),
		acceptstatus: $("#acceptstatus").val()
	}, function(data) {
		var activities = [];
		var activitiesinfo = [];
		let message = '';
		$.each(data, function(index, item) {
			if(index == "activities") {
				activities = JSON.stringify(item)
			}
			if(index == "message") {
				message = JSON.stringify(item)
			}
		});
		if(message != '') {
			alert(message)
			window.location.href = "/Sub/myactivities.html";
		} else {
			$("#tableinner1").append("<tr><th>类型</th><th>简介</th><th>价格</th><th>日期</th><th>地址</th><th></th></tr>");
			$.each($.parseJSON(activities), function(index, item) {
				let type = '';
				var ctime = '';
				var vtime = '';
				var cctime=new Date(item.createtime);
				var vvtime=new Date(item.overtime);
				ctime =formatDate(cctime);
				vtime =formatDate(vvtime);
				switch(item.type) {
					case 1:
						type = "替课";
						break;
					case 2:
						type = "替操";
						break;
					case 3:
						type = "替考";
						break;
				}
				if($("#releasestatus").val() == '' && $("#acceptstatus").val() == '') {
					if(item.status == 2) {
						$("#tableinner1").append("<tr><td>" + type + "</td><td>" + item.introduction + "</td><td>¥" + item.price + "</td><td>" + ctime + "--" + vtime + "</td><td>" + item.address + "</td><td  style=\'white-space: nowrap;\' class=\'font-medium\' ><a href=\'/Sub/activitiesinfo.html?id=" + item.id + "\'>查看详情</a>/<a href=\'activitycomplete.html?id=" + item.id + "\'>完成活动</a></td></tr>");
					} else {
						$("#tableinner1").append("<tr><td>" + type + "</td><td>" + item.introduction + "</td><td>¥" + item.price + "</td><td>" + ctime + "--" + vtime + "</td><td>" + item.address + "</td><td  style=\'white-space: nowrap;\' class=\'font-medium\' ><a href=\'/Sub/activitiesinfo.html?id=" + item.id + "\'>查看详情</a></td></tr>");
					}
				} else if($("#releasestatus").val() == '') {
					$("#tableinner1").append("<tr><td>" + type + "</td><td>" + item.introduction + "</td><td>¥" + item.price + "</td><td>" + ctime + "--" + vtime + "</td><td>" + item.address + "</td><td  style=\'white-space: nowrap;\' class=\'font-medium\' ><a href=\'/Sub/activitiesinfo.html?id=" + item.id + "\'>查看详情</a></td></tr>");
				} else {
					if(item.status == 2) {
						$("#tableinner1").append("<tr><td>" + type + "</td><td>" + item.introduction + "</td><td>¥" + item.price + "</td><td>" + ctime + "--" + vtime + "</td><td>" + item.address + "</td><td  style=\'white-space: nowrap;\' class=\'font-medium\' ><a href=\'/Sub/activitiesinfo.html?id=" + item.id + "\'>查看详情</a>/<a href=\'activitycomplete.html?id=" + item.id + "\'>完成活动</a></td></tr>");
					} else {
						$("#tableinner1").append("<tr><td>" + type + "</td><td>" + item.introduction + "</td><td>¥" + item.price + "</td><td>" + ctime + "--" + vtime + "</td><td>" + item.address + "</td><td  style=\'white-space: nowrap;\' class=\'font-medium\' ><a href=\'/Sub/activitiesinfo.html?id=" + item.id + "\'>查看详情</a></td></tr>");
					}
				}
			});
		}
	});
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