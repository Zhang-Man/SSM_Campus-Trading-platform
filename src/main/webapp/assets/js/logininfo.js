$(document).ready(function() {
(function() {
	$.getJSON("/Sub/user/Info", function(data) {
		let user = '';
		let userinfo = [];
		let message = '';
		var time = '';
	$.each(data, function(index, item) {
		if(index == "message") {
			message = item;
		}
		if(index == "user") {
			user = JSON.stringify(item)
		}
	});
	if(message == "false") {
		document.getElementById("login1").innerHTML = "点击登录";
		document.getElementById("loginskip").href = "login.html";
		document.getElementById("userMenu").style.display = "none";
		document.getElementById("viptime").style.display = 'none';
	} else {
		$.each($.parseJSON(user), function(index, item) {
			userinfo.push(item)
		});
		document.getElementById("login1").innerHTML = userinfo[2];
		document.getElementById("loginskip").href = "myactivities.html";
		document.getElementById("userMenu").style.display = "inline";
		$.getJSON("/Sub/vip/GetVipTime", function(data) {
			$.each(data, function(index, item) {
				if(index == "time") {
					time = JSON.stringify(item)
				if(time !== "") {
			document.getElementById("viptime").style.display = 'inline';
			document.getElementById("viptime").innerText ="会员到期时间："+time;
		}
				}
			});
		});

	}
});
})()
//-----------------------------------------------------------------
})

function logout() {
	$.post('/Sub/user/Logout', function(data) {
		let message = '';
		$.each(data, function(index, item) {
			if(index == "message") {
				message = item;
			}
		});
		if(message == "success") {
			document.getElementById("login1").innerHTML = "点击登录";
			document.getElementById("loginskip").href = "login.html";
			document.getElementById("userMenu").style.display = "none";
			document.getElementById("viptime").style.display = 'none';
		} else {
			alert(message);
			window.location.href = "/Sub/login.html";
		}
	});
}
function logout1() {
	$.post('/Sub/user/Logout', function(data) {
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
			window.location.href = "/Sub/login.html";
		}
	});
}
function Tovip(){
		$.get('/Sub/vip/ToVip',function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				$.getJSON("/Sub/vip/GetVipTime", function(data) {
					$.each(data, function(index, item) {
						if(index == "time") {
							time = JSON.stringify(item)
						if(time !== "") {
					document.getElementById("viptime").innerText ="会员到期时间："+time;
				}
						}
					});
				});
			}else
			{
				alert(message);
			}
				});

}