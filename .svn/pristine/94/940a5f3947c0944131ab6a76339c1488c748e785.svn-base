myDate = new Date();
var currentDay = myDate.getDate();

function signFun(currentDate, dayList) {
    currentYear = currentDate.split("-")[0];
    currentMonth = currentDate.split("-")[1];
    var $dateBox = $("#data_calendar"),
        $currentDate = $(".data_toady"),
        $qiandaoBnt = $(".sing_btn_box"),
        _html = '';

    $currentDate.text(currentYear + ' 年 ' + PrefixInteger(currentMonth, 2) + ' 月');
    var monthFirst = new Date(currentYear, currentMonth-1, 1).getDay();

    var d = new Date(currentYear, currentMonth, 0);
    var totalDay = d.getDate(); //获取当前月的天数

    for (var i = 0; i < 42; i++) {
        _html += '<li id="li_'+i+'"><div></div></li>'
    }
    $dateBox.html(_html); //生成日历网格

    var $dateLi = $dateBox.find("li");
    for (var i = 0; i < totalDay; i++) {
        $dateLi.eq(i + monthFirst + 1).find('div').text(i + 1);
        if(dayList != null) {
            for (var j = 0; j < dayList.length; j++) {
                if (i == dayList[j].day.split("-")[2]-1) {
                    if (currentDay == dayList[j].day.split("-")[2]) {
                        $dateLi.eq(i + monthFirst + 1).find('div').addClass("circle_solid");
                    } else {
                        $dateLi.eq(i + monthFirst + 1).find('div').addClass("circle_hollow");
                    }
                }
            }
        }
    }
    // 删除空的一行
    if (monthFirst == 6) {
        $("#data_calendar li:lt(7)").remove();
    }

    $qiandaoBnt.on("click", function() {
        $("#li_"+(currentDay + monthFirst)+" div").addClass("circle_solid")
    }); //签到

}

function getPreMonth(currentDate) {
    var year = currentDate.split("-")[0];
    var month = currentDate.split("-")[1];
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    if (month2 < 10) { month2 = '0' + month2; }

    return year2 + '-' + month2;
}

function getNextMonth(currentDate) {
    var year = currentDate.split("-")[0];
    var month = currentDate.split("-")[1];
    var year2 = year;
    var month2 = parseInt(month) + 1;
    if (month2 > 12) {
        year2 = parseInt(year2) + 1;
        month2 = 1;
    }
    if (month2 < 10) { month2 = '0' + month2; }

    return year2 + '-' + month2;
}

function PrefixInteger(num, length) {
    return (Array(length).join('0') + num).slice(-length);
}

function check(dayList) {
    var myDate = new Date();
    var today =  myDate.getFullYear() + '-' + PrefixInteger(myDate.getMonth()+1, 2) + '-' + PrefixInteger(myDate.getDate(), 2);
    if(dayList != null) {
        for(var x in dayList) {
            if (today == dayList[x].day) {
                return true
            }
        }
    }

    return false;
}
