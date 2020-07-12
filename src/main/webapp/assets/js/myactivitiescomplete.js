$(document).ready(function (){
		(function(){
		$.getJSON("/Sub/activities/ActivitiesInfo",{id:querystring('id')},function(data){
		let activities = [];
		let activitiesinfo = [];
		let message = '';
		$.each(data, function(index,item) {
			if(index=="activities")
			{
				activities = JSON.stringify(item)
			}
			if(index=="message")
			{
				message = JSON.stringify(item)
			}
		});
		if(message!='')
		{
			alert(message);
			window.location.href="/Sub/index.html";
		}else
		{   

			activities = $.parseJSON(activities);
			var ctime = '';
			var vtime = '';
			var cctime=new Date(activities.createtime);
			var vvtime=new Date(activities.overtime);
			ctime =formatDate(cctime);
			vtime =formatDate(vvtime);
			console.log(vtime)
			document.getElementById("activitiesid").innerHTML = activities.id;
			document.getElementById("activitiestype").innerHTML = activities.type;
			document.getElementById("activitiesintroduction").value = activities.introduction;
			document.getElementById("activitiescreatetime").value =ctime;
			document.getElementById("activitiesovertime").value = vtime;
			document.getElementById("activitiesprice").value = activities.price;
			document.getElementById("activitiesaddress").value = activities.address;
		}
			});
	})()
	function querystring(qs)
	{
		let s = location.href;
			s = s.replace("?","?&").split("&");
		let result='';
		for(i=1;i<s.length;i++)
		{
			if(s[i].indexOf(qs+"="==0))
			{
				result=s[i].replace(qs+'=','');
			}
		}
		return result;
	}
});

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
	var seconds = now.getSeconds();
	if(seconds<=9){
		seconds="0"+seconds+"";
	}	
	return year + "-" + month + "-" + date + "T" + hour + ":" + minute + ":"+seconds;
}