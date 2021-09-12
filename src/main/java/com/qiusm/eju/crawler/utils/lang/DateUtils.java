package com.qiusm.eju.crawler.utils.lang;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final String[] PARSE_PATTERNS = {
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyyMMddhhmmss"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate(PARSE_PATTERNS[0]);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    public static String formatDate(long miller, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(miller, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(miller, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        return formatDate(date.getTime());
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获得当前时间，格式 yyyy/MM/dd HH:mm:ss
     *
     * @return 【yyyyMMddhhmmss】格式的文本日期
     */
    public static String getCurrDateTime() {
        return getCurrDateTime(PARSE_PATTERNS[12]);
    }

    /**
     * 根据时间格式获得当前时间
     */
    public static String getCurrDateTime(String patterns) {
        return formatDate(Calendar.getInstance().getTime(), patterns);
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取现在到date，相隔的天数
     *
     * @param date 时间点
     * @return 天数
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取现在到date，相隔的小时数
     *
     * @param date 时间点
     * @return 小时数
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取现在到date，相隔的分钟数
     *
     * @param date 时间点
     * @return 分钟数
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 获取两个日期之间的天数
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return TimeUnit.MILLISECONDS.toDays(afterTime - beforeTime);
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 查看某个日期是否在当前日期之后
     */
    public static boolean after(Date date) {
        return date.before(new Date());
    }

    /**
     * @return <p>获取当天00:00:00</p>
     * @author qiushengming
     * @date 2017年3月19日 下午8:57:43
     */
    public static Date getTodayStart() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * @return <p>获取当天23:59:59的时间</p>
     * @author qiushengming
     * @date 2017年3月19日 下午8:59:27
     */
    public static Date getTodayEnd() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 根据string类型的日期，添加天数
     *
     * @param dateStr 日期字符串
     * @param pattern 对应dateStr的格式
     * @param amount  偏移量天数
     * @return dateStr + amount后的日期
     */
    public static String addDays(String dateStr, String pattern, int amount)
            throws ParseException {
        return formatDate(addDays(Objects.requireNonNull(parse(dateStr, pattern)), amount), pattern);
    }

    /**
     * 根据日期，添加天数
     *
     * @param date    日期
     * @param pattern 格式化
     * @param amount  便宜量
     * @return date + amount后的日期
     * @throws ParseException 解析异常
     */
    public static String addDays(Date date, String pattern, int amount) {
        return formatDate(addDays(date, amount), pattern);
    }

    /**
     * 根据日期，添加小时数
     *
     * @param date   java.util.Date
     * @param hource
     * @return Date
     * @throws ParseException
     */
    public static Date addHour(Date date, int hource) throws ParseException {
        return addHours(date, hource);
    }

    /**
     * 获得两个时间的间隔,可以按秒、分钟、小时、天来获取
     *
     * @param date1 大的日期在前
     * @param date2
     * @return
     */
    public static int elapsed(Date date1, Date date2, int field) {
        if (date1 == null || date2 == null)
            throw new IllegalArgumentException("The date must not be null");

        long elapsed = date1.getTime() - date2.getTime();
        switch (field) {
            case Calendar.SECOND:
                return ((Number) (elapsed / 1000f)).intValue();
            case Calendar.MINUTE:
                return ((Number) (elapsed / (1000f * 60f))).intValue();
            case Calendar.HOUR:
                return ((Number) (elapsed / (1000f * 60f * 60f))).intValue();
            case Calendar.DATE:
                return ((Number) (elapsed / (1000f * 60f * 60f * 24f)))
                        .intValue();
            case Calendar.MONTH:
                return ((Number) (elapsed / (1000f * 60f * 60f * 24f * 30f)))
                        .intValue();
            case Calendar.YEAR:
                // return ((Number) (elapsed / (1000f * 60f * 60f * 24f * 30f *
                // 12f))).intValue();
                // 上面的方法 2015-07-24 减去 1948-08-10 得到67,导致错误，现修改为如下
                return getAgeByTwoDate(date1, date2);
        }

        return ((Number) (elapsed / (1000 * 60 * 60 * 24))).intValue();
    }

    /**
     * 获得两个时间的间隔,可以按秒、分钟、小时、天来获取
     *
     * @param strDate1 大的日期在前
     * @param strDate2
     * @return
     * @throws ParseException
     */
    public static int elapsed(String strDate1, String strDate2, String pattern,
                              int field) throws ParseException {
        return elapsed(parse(strDate1, pattern), parse(strDate2, pattern),
                field);
    }

    /**
     * 使用参数Format将字符串转为Date
     *
     * @param strDate
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException {
        return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }

    /**
     * 按照默认格式（YYYY/MM/DD）将字符串转为Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, PARSE_PATTERNS[2]);
    }

    /**
     * 按照格式（yyyyMMddhhmmss）将字符串转为Date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse2(String strDate) throws ParseException {
        return parse(strDate, PARSE_PATTERNS[12]);
    }

    /**
     * 获取传入月份的最大天数
     *
     * @param date
     * @return
     */
    public static Date getMaxDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取两个日期的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime()
                .getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取月的第一天
     *
     * @return
     */
    public static String getMonthFirstDay(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    /**
     * 获取月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay(String pattern) {
        return new SimpleDateFormat(pattern)
                .format(getMaxDateOfMonth(new Date()));
    }

    public static boolean isSameDay(String dateStr1, String dateStr2,
                                    String pattern) throws ParseException {
        return isSameDay(parse(dateStr1, pattern), parse(dateStr2, pattern));
    }

    public static boolean isBetweenTwoHours(Date currDate, int beginHour,
                                            int endHour) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(currDate);
        int currHour = curr.get(Calendar.HOUR_OF_DAY);
        int period1 =
                currHour - beginHour < 0 ? (currHour - beginHour + 24)
                        : (currHour - beginHour);
        int period2 =
                endHour - beginHour < 0 ? (endHour - beginHour + 24)
                        : (endHour - beginHour);

        if (period1 <= period2) {
            return true;
        }
        return false;
    }

    /**
     * 根据日期计算年龄
     *
     * @param date1 大日期(入院日期)
     * @param date2 小日期(出生日期)
     * @return 正常返回正整数，日期为空时=-1
     * @Description:
     * @author yuxiangtong
     * @date 2015-7-24 下午3:57:38
     */
    public static int getAgeByTwoDate(Date date1, Date date2) {
        int age = -1;
        if (null != date1 && null != date2) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(date2);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    age--;
                }
            }
        }
        return age;
    }

    /**
     * 根据日期计算时间间隔
     *
     * @param date1 小日期(出生日期)
     * @param date2 大日期(入院日期)
     * @return <br>
     * 正常：返回正整数，日期为空时=-1
     * @author wfm
     */
    public static double getAgeByTwoDateToRetainOneDecimal(Date date1,
                                                           Date date2, String unit) {
        double age = -1;
        if (null != date1 && null != date2) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            int dayOfYearNow = cal.get(Calendar.DAY_OF_YEAR);
            Long millisNow = cal.getTimeInMillis();
            cal.setTime(date1);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            int dayOfYearBirth =
                    (yearNow % 4 == 0 ? 366 : 365)
                            - cal.get(Calendar.DAY_OF_YEAR);
            Long millisBirth = cal.getTimeInMillis();
            // 以年为单位计算年龄
            if (StringUtils.equals("Y", unit)) {
                age = yearNow - yearBirth;

                if (monthNow <= monthBirth) {
                    if (monthNow == monthBirth) {
                        if (dayOfMonthNow < dayOfMonthBirth) {
                            age--;
                        }
                    } else {
                        age--;
                    }
                }
                // 计算年龄的小数位
                // 临床建议修复：1）系统年龄算法不变，但精确到小数点后一位(采用进位法，保留一位小数，如，1.11，保留一位后为：1.2；
                // 当小数点后一位已经为9时，不允许再进位，如：1.99，不可再进位，保留为：1.9；而1.81，可以进位为：1.9。)。
                // 1.9999999999
                double decimal =
                        MathUtils.div(dayOfYearNow + dayOfYearBirth, 365);
                if ((decimal + "").indexOf(".9") > -1)
                    decimal = 0.9;
                else
                    decimal = MathUtils.round(decimal - (int) decimal, 1);
                age = age + decimal;
                // 以月为单位计算年龄
            } else if (StringUtils.equals("M", unit)) {
                int year = yearNow - yearBirth;
                int mouth = monthNow - monthBirth;
                int day = dayOfMonthNow - dayOfMonthBirth;
                if (year == 0 && mouth == 0) {
                    return 0;
                }
                if (year > 0)
                    age = year * 12;
                if (mouth > 0) {
                    age = age + mouth;
                    if (day < 0)
                        age = age - 1;
                } else {
                    age = age - 1;
                }
                // 以日为单位计算年龄
            } else if (StringUtils.equals("D", unit)) {
                // age = dayOfYearNow + dayOfYearBirth;
                age = (millisNow - millisBirth) / (1000 * 60 * 60 * 24);
            }
        }
        return age;
    }

    public static double getAgeByTwoDateToRetainOneDecimal(Date date1,
                                                           Date date2) {
        return getAgeByTwoDateToRetainOneDecimal(date1, date2, "Y");
    }

    /**
     * 判断日期1是否在日期2、日期3之间
     *
     * @param date1
     * @param date2 区间小日期
     * @param date3 区间大日期
     * @param
     * @return <br>
     * boolean
     * @author wfm
     */
    public static boolean isBetween(Date date1, Date date2, Date date3) {
        if (date1 == null || date2 == null || date3 == null)
            return false;
        Long long1 = date1.getTime();
        Long long2 = date2.getTime();
        Long long3 = date3.getTime();
        if (long2 <= long1 && long1 <= long3)
            return true;
        return false;
    }


    /**
     * <p>功能:计算年龄。</p>
     * <p>说明:应临床要求,类型1:计算年龄后年龄为XX/xxx,代表XX岁xxx天。类型2:具体的天数 </p>
     * <p>备注:无。 </p>
     *
     * @param date1(大日期,入/出院日期) date2(小日期,出生日期)
     * @return string
     * @author caikang
     */
    public static String getAgeInYearAndDay(Date date1, Date date2, String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        int year = -1;
        int day = -1;
        String str = null;
        if (null != date1 && null != date2) {
            Calendar cal1 = Calendar.getInstance();

            cal1.setTime(date1);
            int yearNow = cal1.get(Calendar.YEAR);
            int monthNow = cal1.get(Calendar.MONTH);
            int dayNow = cal1.get(Calendar.DAY_OF_MONTH);

            Calendar cal2 = Calendar.getInstance();

            cal2.setTime(date2);
            int yearBirth = cal2.get(Calendar.YEAR);
            int monthBirth = cal2.get(Calendar.MONTH);
            int dayBirth = cal2.get(Calendar.DAY_OF_MONTH);

            //判断年份,年相同的情况
            if (yearNow <= yearBirth) {
                //判断月份
                if (monthNow == monthBirth) {
                    if (dayNow > dayBirth) {
                        year = 0;
                        day = dayNow - dayBirth;
                    }
                } else if (monthNow > monthBirth) {
                    year = 0;
                    day = (int) ((date1.getTime() - date2.getTime()) / 86400000);
                }
                if (type.equals("1")) {
                    str = year + "/" + day + "/";
                } else if (type.equals("2") && day > 0) {
                    str = day + "";
                }
            } else {
                //年不同的情况,先判断月,再判断天
                if (type.equals("2")) {
                    day = (int) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / 86400000);
                    str = day + "";
                } else if (type.equals("1")) {
                    year = yearNow - yearBirth;
                    if (monthNow > monthBirth) {
                        cal1.set(yearBirth, monthNow, dayNow);
                        day = (int) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / 86400000);
                    } else if (monthNow < monthBirth) {
                        year = year - 1;
                        cal1.set(yearBirth + 1, monthNow, dayNow);
                        day = (int) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / 86400000);
                    } else if (monthNow == monthBirth) {
                        if (dayNow >= dayBirth) {
                            day = dayNow - dayBirth;
                        } else if (dayNow < dayBirth) {
                            year = year - 1;
                            cal1.set(yearBirth + 1, monthNow, dayNow);
                            day = (int) ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / 86400000);
                        }
                    }
                    str = year + "/" + day + "/";
                }
            }
        }
        //年岁为负数,则返回空
        if (type.equals("1") && year < 0 || (year == 0 && day <= 0)) {
            return null;
        } else if (type.equals("2") && day <= 0) {
            return null;
        }
        return str;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getDistanceOfTwoDate(new Date(), new Date()));
    }
}
