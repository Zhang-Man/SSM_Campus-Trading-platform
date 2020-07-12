//var map = { "DataAnalyze2": [{'one':1,'two':2}], "DataAnalyze": [{ "columFirst": 5, "columSecond": "06-07" }, { "columFirst": 1, "columSecond": "06-10" }, { "columFirst": 3, "columSecond": "06-14" }, { "columFirst": 1, "columSecond": "06-15" }, { "columFirst": 5, "columSecond": "06-17" }] };
		
		let chart1x = [];
		let chart1y = [];
		
		let chart2 = [];
		
		let chart3max = [];
		let chart3data = [];
		
		var i = 0;
		var colors=['#FFCE44','#DD5044','#1AA15F','#4C8BF5','#A72F4B']; 
		
		$.get('activities/ActivityAnalyze', function (key, values) {
			$.each(key,function(key,value){
				
				if(key == 'DataAnalyze'){
					$.each(value,function(index,item){
						chart1y.push(item.columFirst);
					});
					$.each(value,function(index,item){
						chart1x.push(item.columSecond);
					});
				}
				if(key == 'DataAnalyze2'){
					
					$.each(value,function(index,item){
						let obj = new Object();
						obj.name = item.columSecond;
						obj.value = item.columFirst;
						chart2.push(obj);
					});
					
				}
				if(key == 'DataAnalyze3'){
					$.each(value,function(index,item){
						let obj = new Object();
						obj.name = item.columSecond;
						obj.max = 100;
						chart3max.push(obj);
					});
					$.each(value,function(index,item){
						chart3data.push(item.columFirst);
					});
				}
			});
			
			var myChart1 = echarts.init(document.getElementById('chart1'));
	        // 指定图表的配置项和数据
	        var option1 = { 
	        	xAxis: {
	            type: 'category',
	            data:chart1x
	        },
	        yAxis: {
	            type: 'value'
	        },
	        series: [{
	            data: chart1y,
	            type: 'line',
	            showBackground: true,
	            smooth:true,
	            backgroundStyle: {
	                color: 'rgba(220, 220, 220, 0.8)'
	            }
	        }]
	        };

	        // 使用刚指定的配置项和数据显示图表。
	        
	        myChart1.setOption(option1);
	        
	        var myChart2 = echarts.init(document.getElementById('chart2'));
	        // 指定图表的配置项和数据
	        var option2 = {
	        		tooltip: {
	        	        trigger: 'item',
	        	        formatter: '{a} <br/>{b}: {c} ({d}%)'
	        	    },
	        	    legend: {
	        	        orient: 'vertical',
	        	        left: 10,
	        	        data: chart2
	        	    },
	        	    series: [
	        	        {
	        	            name: '访问来源',
	        	            type: 'pie',
	        	            itemStyle: {
	        	            	normal : {  
	        	                    color:function(){  
	        	                        return colors[i++];   
	        	                    } 
	        	        		},  
        	                    shadowBlur: 100,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                },
	        	            radius: ['50%', '70%'],
	        	            avoidLabelOverlap: false,
	        	            label: {
	        	                show: false,
	        	                position: 'center'
	        	            },
	        	            emphasis: {
	        	            	
	        	                label: {
	        	                    show: true,
	        	                    fontSize: '30',
	        	                    fontWeight: 'bold'
	        	                }
	        	            },
	        	            labelLine: {
	        	                show: false
	        	            },
	        	            data: chart2
	        	        }
	        	    ]
			};
	        	    
	       

	        // 使用刚指定的配置项和数据显示图表。
	        
	        myChart2.setOption(option2);
	        
	        var myChart3 = echarts.init(document.getElementById('chart3'));
	        // 指定图表的配置项和数据
	        var option3 = { 
	        		title: {
	        	        text: '活动分类分析图'
	        	    },
	        	    tooltip: {},
	        	    legend: {
	        	        data: ['活动分类']
	        	    },
	        	    radar: {
	        	        // shape: 'circle',
	        	        name: {
	        	            textStyle: {
	        	                color: '#fff',
	        	                backgroundColor: '#999',
	        	                borderRadius: 3,
	        	                padding: [3, 5]
	        	            }
	        	        },
	        	        indicator:chart3max
	        	    },
	        	    series: [{
	        	        name: '活动分类',
	        	        type: 'radar',
	        	         areaStyle: {normal: {}},
	        	        data: [
	        	            {
	        	                value: chart3data,
	        	                name: '活动分类 1为替课 2为替操 3为替考'
	        	            }
	        	            
	        	        ]
	        	    }]
	        };

	        // 使用刚指定的配置项和数据显示图表。
	        
	        myChart3.setOption(option3);
	        
		});