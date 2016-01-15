
var MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
function IsPinYear(year)//判断是否闰平年
{
    return(0 == year%4 && (year%100 !=0 || year%400 == 0));
}

function getDayArr(){
	var data = new Array();
	for(var i = 1;i< MonHead[new Date().getMonth()] ; i ++){
		data[i-1] =i+'日';
	}
	return data;
}

function getMonthArr(){
	var data = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	return data;
}
