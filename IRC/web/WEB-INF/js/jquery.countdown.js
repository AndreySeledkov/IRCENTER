	jQuery.fn.countdown = function (date, options) {
		options = jQuery.extend({
			lang: {
				years:   ['', '', ''],
				months:  ['', '', ''],
				days:    ['д', 'д', 'д'],
				hours:   ['ч', 'ч', 'ч'],
				minutes: ['м', 'м', 'м'],
				seconds: ['с', 'с', 'с'],
                plurar:  function(n) {
                    return (n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
                }
			}			
		}, options);
 
		var timeDifference = function(begin, end) {
		    if (end < begin) {
			    return false;
		    }
		    var diff = {
		    	seconds: [end.getSeconds() - begin.getSeconds(), 60],
		    	minutes: [end.getMinutes() - begin.getMinutes(), 60],
		    	hours: [end.getHours() - begin.getHours(), 24],
		    	days: [end.getDate()  - begin.getDate(), new Date(begin.getYear(), begin.getMonth() + 1, 0).getDate() - 1],
		    	months: [end.getMonth() - begin.getMonth(), 12],
		    	years: [end.getYear()  - begin.getYear(), 0]
		    };
		    var result = new Array();
		    var flag = false;
		    for (i in diff) {
		    	if (flag) {
		    		diff[i][0]--;
		    		flag = false;
		    	}    	
		    	if (diff[i][0] < 0) {
		    		flag = true;
		    		diff[i][0] += diff[i][1];
		    	}
		    	if (!diff[i][0]) continue;

				if (diff['days'][0]>0){
					$('.hour_day_type').html('д');
					$('.minute_hour_type').html('ч');
					$('.second_minute_type').html('м');
					if (i == 'days'){$(".hour_day_data").html(diff[i][0]);}
					if (i == 'hours'){$(".minute_hour_data").html(diff[i][0]);}
					if (i == 'minutes'){$(".second_minute_data").html(diff[i][0]);}
				} else {
					$('.hour_day_type').html('ч');
					$('.minute_hour_type').html('м');
					$('.second_minute_type').html('с');
					if (i == 'hours'){$(".hour_day_data").html(diff[i][0]);}
					if (i == 'minutes'){$(".minute_hour_data").html(diff[i][0]);}
					if (i == 'seconds'){$(".second_minute_data").html(diff[i][0]);}
				}
		    }
		    return '';
		};
		var elem = $(this);
		var timeUpdate = function () {
		    var s = timeDifference(new Date(), date);	
		};
		timeUpdate();
		var timer = setInterval(timeUpdate, 1000);		
	};