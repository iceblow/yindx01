/**
 * Created by Aion on 2017/4/13.
 */
var today = new Date();
var getDays = function(max) {
    var days = [];
    for(var i=1; i<= (max||31);i++) {
        days.push(i < 10 ? "0"+i : i);
    }
    return days;
};
var getDaysByMonthAndYear = function(month, year) {
    var int_d = new Date(year, parseInt(month)+1-1, 1);
    var d = new Date(int_d - 1);
    return getDays(d.getDate());
};
var formatNumber = function (n) {
    return n < 10 ? "0" + n : n;
};
var initMonthes = ('01 02 03 04 05 06 07 08 09 10 11 12').split(' ');
var getTodayHours = function () {
    if (today.getHours() == 23) {
        return 0;
    } else {
        return today.getHours() + 1;
    }
};
//时间选择
$("#datetime-picker").datetimePicker({
    value: [today.getFullYear(), formatNumber(today.getMonth()+1), formatNumber(today.getDate()), formatNumber(getTodayHours()), formatNumber(today.getMinutes())],
    onChange: function(p, values, displayValues){
        var currentMonth = this.cols[1].value;
        var currentDay = this.cols[2].value;
        var currentHour = this.cols[4].value;
        // 限制天数上限
        var days = getDaysByMonthAndYear(this.cols[1].value, this.cols[0].value);
        var currentValue = this.cols[2].value;
        if (today.getMonth()+1 == currentMonth && today.getDate() > currentValue) {
            this.cols[2].setValue(today.getDate())
        }
        // 限制天数下限
        if (days.length < currentValue) {
            this.cols[2].setValue(days.length)
        }
        // 限制小时
        if (today.getMonth()+1 == currentMonth && today.getDate() == currentDay && currentHour <= today.getHours()) {
            this.cols[4].setValue(today.getHours() + 1);
        }

        service.server_time = displayValues;
        conInfo.list.server_time = displayValues;
    },
    cols: [
        // Years
        { values: [2017] },
        // Months
        {
            values: (function () {
                var arr = initMonthes;
                for (var i = 0; i < today.getMonth(); i++) { initMonthes.splice(0, 1); }
                return arr;
            })()
        },
        // Days
        {
            values: getDays()
        },
        // Space divider
        {
            divider: true,
            content: '  '
        },
        // Hours
        {
            values: (function () {
                var arr = [];
                for (var i = 0; i <= 23; i++) { arr.push(i); }
                return arr;
            })()
        },
        // Divider
        { divider: true, content: ':' },
        // Minutes
        {
            values: (function () {
                var arr = [];
                for (var i = 0; i <= 59; i++) { arr.push(i < 10 ? '0' + i : i); }
                return arr;
            })()
        }
    ]
});