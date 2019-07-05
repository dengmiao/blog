/**
 * 时间操作工具类
 * @author dengmiao
 */
let dateUtil = {
    /**
     * 格式化日期
     * @param date {Date} 日期
     * @param pattern {string} 格式，例："yyyy-MM-dd HH:mm:ss"
     * @returns {String} 返回格式化后的日期，如："2018-01-22 18:04:30"
     */
    format : (date, pattern) => {
        const time = {
            "M+": date.getMonth() + 1,
            "d+": date.getDate(),
            "H+": date.getHours(),
            "m+": date.getMinutes(),
            "s+": date.getSeconds(),
            "q+": Math.floor((date.getMonth() + 3) / 3),
            "S+": date.getMilliseconds()
        };
        if (/(y+)/i.test(pattern)) {
            pattern = pattern.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (let k in time) {
            if (new RegExp("(" + k + ")").test(pattern)) {
                pattern = pattern.replace(RegExp.$1, RegExp.$1.length == 1 ? time[k] : ("00" + time[k]).substr(("" + time[k]).length));
            }
        }
        return pattern;
    },
    /**
     * 将指定时间偏移几小时
     * @param time {String} 指定时间，例："2018-01-24 17:00"
     * @param offset {Number} 偏移量，正数代表加几小时，负数代表减几小时，例：1
     * @param pattern {String} 返回时间的格式，例："yyyy-MM-dd HH:mm"
     * @returns {String} 返回计算后的时间，如："2018-01-24 18:00"
     */
    offsetHours : (time, offset, pattern) => {
        let date = new Date(Date.parse(time));
        let yyyy = date.getFullYear();
        let MM = date.getMonth();
        let dd = date.getDate();
        let HH = date.getHours() + offset;
        let mm = date.getMinutes();
        let ss = date.getSeconds();
        return this.format(new Date(yyyy, MM, dd, HH, mm, ss), pattern);
    },
    /**
     * 将指定月份偏移几个月
     * @param month {String} 指定月份，例："2018-01"
     * @param offset {Number} 偏移量，负数代表上几个月，正数代表下几个月，例：1
     * @returns {String} 返回计算后的月份，如："2018-02"
     */
    offsetMonths : (month, offset) => {
        let date = new Date(Date.parse(month));
        let year = date.getFullYear();
        let month1 = date.getMonth();
        let preOrNextMonth = month1 + offset;
        return this.format(new Date(year, preOrNextMonth), "yyyy-MM");
    },
    /**
     * 获取指定日期是星期几
     * @param date {String} 指定日期,例："2018-01-23"
     * @returns {Number} 返回星期几(1-7)，如：2
     */
    dayOfWeek : (date) => {
        let time = new Date(Date.parse(date));
        let weekday=new Array(7);
        weekday[0]= 7;
        weekday[1]= 1;
        weekday[2]= 2;
        weekday[3]= 3;
        weekday[4]= 4;
        weekday[5]= 5;
        weekday[6]= 6;
        return weekday[time.getDay()];
    },
    /**
     * 获取指定月份有多少天
     * @param month {String} 指定月份：例"2018-01"
     * @returns {number} 返回指定月份有多少天，如：31
     */
    daysInMonth : (month0) => {
        let date = new Date(Date.parse(month0));
        let year = date.getFullYear();
        let month = date.getMonth();
        if (month == 1) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                return 29;
            else
                return 28;
        } else if ((month <= 6 && month % 2 == 0) || (month > 6 && month % 2 == 1))
            return 31;
        else
            return 30;
    }
};