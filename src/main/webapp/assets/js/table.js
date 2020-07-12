$(document).ready(function() {
	add();
})
function add(){
	var type=2;//1发布的还是0接的
	var testdata=new Array();
	testdata[0]=new Array();
	testdata[0][0]="";
	if(type===1){
		$("#tableinner1").append("<tr><td>2312</td><td>2312</td><td>23133333333333</td><td>2312</td><td  style=\'white-space: nowrap;\' class=\'font-medium\' ><a href=\'myactivities.html\'>查看详情</a>/<a href=\'activitycomplete.html\'>完成活动</a></td></tr>");
	}
	else{
		$("#tableinner1").append("<tr><td>2312</td><td>2312</td><td>23133333333333</td><td>2312</td><td  style=\'white-space: nowrap;\'  ><a href=\'myactivities.html\'>查看详情</a></td></tr>");
	}
}
