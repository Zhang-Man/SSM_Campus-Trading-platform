var temp = 0;
$(document).ready(function (){
	var star = document.getElementsByName("star");
	var oDiv = document.getElementsByTagName('div')[0];

	for(var i = 0, len = star.length; i < len; i++){
		star[i].index = i;
		
		star[i].onmouseover = function(){
			clear();
			for(var j = 0; j < this.index + 1; j++){
				star[j].style.backgroundPosition = '0 0';
			}
		}
		
		star[i].onmouseout = function(){
			for(var j = 0; j < this.index + 1; j++){
				star[j].style.backgroundPosition = '0 -20px';
			}
			current(temp);
		}
		
		star[i].onclick = function(){
			temp = this.index +1;
			document.getElementsByTagName('p')[0].innerHTML = temp + ' 颗星';
			current(temp);
		}
	}
	//清除所有
	function clear(){
		for(var i = 0, len = star.length; i < len; i++){
			star[i].style.backgroundPosition = '0 -20px';
		}
	}
	//显示当前第几颗星
	function current(temp){
		for(var i = 0; i < temp; i++){
			star[i].style.backgroundPosition = '0 0';
		}
	}
});

function overactivities()
{
	$.post('/Sub/activities/OverActivities',{id:$("#activitiesid").text(),type:1,star:temp},function(data){
				let message = '';
				let id = '';
				$.each(data, function(index,item) {
					if(index=="message")
					{
						message = item;
					}
				});
				if(message=="success")
				{
					alert('任务完成')
					window.location.href="/Sub/myactivities.html?";
				}else
				{
					alert(message);
					window.location.href="/Sub/myactivities.html?";
				}
					});

}
function loseactivities()
{
	$.post('/Sub/activities/OverActivities',{id:$("#activitiesid").text(),type:3,star:0},function(data){
				let message = '';
				let id = '';
				$.each(data, function(index,item) {
					if(index=="message")
					{
						message = item;
					}
				});
				if(message=="success")
				{
					alert('任务失败')
					window.location.href="/Sub/myactivities.html?";
				}else
				{
					alert(message);
					window.location.href="/Sub/myactivities.html?";
				}
					});

}
			