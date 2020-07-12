function upregister()
{
		$.post('/Sub/user/Register',{username:$("#Registerusername").val(),password:$("#Registerpassword").val(),tel:$("#Registertel").val(),email:$("#Registeremail").val()},function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				window.location.href="/Sub/index.html";
			}else
			{
				alert(message);
				window.location.href="/Sub/login.html";
			}
		});
}
function telregister()
{
	$.post('/Sub/user/Register',{username:$("#Registerusername").val(),password:$("#Registerpassword").val(),tel:$("#Registertel").val(),email:$("#Registeremail").val()},function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				window.location.href="/Sub/index.html";
			}else
			{
				alert(message);
				window.location.href="/Sub/login.html";
			}
		});
}
function emailregister()
{
	$.post('/Sub/user/Register',{username:$("#Registerusername").val(),password:$("#Registerpassword").val(),tel:$("#Registertel").val(),email:$("#Registeremail").val()},function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				window.location.href="/Sub/index.html";
			}else
			{
				alert(message);
				window.location.href="/Sub/login.html";
			}
		});
}
function emaillogin()
{
			$.post('/Sub/user/EmailLogin',{email:$("#email").val()},function(data){
				let message = '';
				$.each(data, function(index,item) {
					if(index=="message")
					{
						message = item;
					}
				});
				if(message=="success")
				{
					window.location.href="/Sub/index.html";
				}else
				{
					alert(message);
				}
					});
}

function tellogin()
{
		$.post('/Sub/user/TelLogin',{tel:$("#tel").val()},function(data){
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = item;
				}
			});
			if(message=="success")
			{
				window.location.href="/Sub/index.html";
			}else
			{
				alert(message);
			}
				});
}


function telcode()
{
		$.getJSON('/Sub/util/Telcode',{tel:$("#tel").val()},function(data){
			let code = '';
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = JSON.stringify(item)
				}
				if(index=="code")
				{
					code = JSON.stringify(item)
				}
			});
			alert(message)
//			alert(code)
				});
}

function emailcode()
{
		$.getJSON('/Sub/util/Emailcode',{email:$("#email").val()},function(data){
			let code = '';
			let message = '';
			$.each(data, function(index,item) {
				if(index=="message")
				{
					message = JSON.stringify(item)
				}
				if(index=="code")
				{
					code = JSON.stringify(item)
				}
			});
			alert(message)
//			alert(code)
				});
}

function login()
{
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
}
