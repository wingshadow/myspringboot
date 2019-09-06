package com.myland.framework.utils.time;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类，主要提供日期对象相关的计算等操作
 *
 * @author Changshuo.Feng
 * @version 1.0.0 2014年9月1日 下午3:55:47
 */
public class DateUtil {

	/**
	 * log
	 */
	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 对传入的日期对象，在指定的日期字段上添加或减少相应的日期数。
	 *
	 * @param date         要进行计算的日期对象，在该日期基础之上进行添加或减少指定的时间段
	 * @param dateField    参考 Calendar的各时间段常量定义，或者下述 see标签的描述
	 * @param intervalTime 要添加或减少的时间段数量（正数为添加，负数为减少）
	 * @return 进行计算后的新日期对象
	 * @throws IllegalArgumentException 参数date为空时抛出该异常
	 * @see Calendar#YEAR
	 * @see Calendar#MONTH
	 * @see Calendar#DAY_OF_MONTH
	 * @see Calendar#HOUR
	 * @see Calendar#MINUTE
	 * @see Calendar#SECOND
	 */
	public static final Date addDate(Date date, int dateField, int intervalTime) {
		Assert.notNull(date, "参数date不能为空");

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(dateField, intervalTime);
		return cal.getTime();
	}

	/**
	 * 对传入的日期对象，进行年份字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的年数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addYear(Date date, int intervalTime) {
		return addDate(date, Calendar.YEAR, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行月份字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的月数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addMonth(Date date, int intervalTime) {
		return addDate(date, Calendar.MONTH, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行日期字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的天数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addDay(Date date, int intervalTime) {
		return addDate(date, Calendar.DAY_OF_MONTH, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行小时字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的小时数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addHour(Date date, int intervalTime) {
		return addDate(date, Calendar.HOUR, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行分钟字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的分钟数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addMinute(Date date, int intervalTime) {
		return addDate(date, Calendar.MINUTE, intervalTime);
	}

	/**
	 * 对传入的日期对象，进行秒字段的加减操作。
	 *
	 * @param date         在该日期基础之上进行计算
	 * @param intervalTime 要添加或减少的秒数
	 * @return 计算后的新日期对象
	 * @see #addDate(Date, int, int)
	 */
	public static final Date addSecond(Date date, int intervalTime) {
		return addDate(date, Calendar.SECOND, intervalTime);
	}

	/**
	 * 重置日期对象的年月日
	 *
	 * @param initDate 原日期对象
	 * @param year     要重置成的年份
	 * @param month    要重置成的月份
	 * @param date     要重置成的日
	 * @return 重置后的日期
	 * @throws IllegalArgumentException 参数initDate为空时抛出该异常
	 */
	public static final Date resetDate(Date initDate, int year, int month, int date) {
		Assert.notNull(initDate, "Date类型参数不能为空");

		Calendar cal = Calendar.getInstance();
		cal.setTime(initDate);
		cal.set(year, month - 1, date);
		return cal.getTime();
	}

	/**
	 * 重置日期对象的年月日
	 *
	 * @param initDate  原日期对象
	 * @param dateField 需要重置的日期字段
	 * @param value     新的字段值
	 * @return 重置后的日期
	 * @throws IllegalArgumentException 参数initDate为空时抛出该异常
	 * @see Calendar#YEAR
	 * @see Calendar#MONTH
	 * @see Calendar#DAY_OF_MONTH
	 * @see Calendar#HOUR
	 * @see Calendar#MINUTE
	 * @see Calendar#SECOND
	 */
	public static final Date resetDate(Date initDate, int dateField, int value) {
		Assert.notNull(initDate, "Date类型参数不能为空");

		Calendar cal = Calendar.getInstance();
		cal.setTime(initDate);

		if (dateField == Calendar.MONTH) {
			value--;
		}

		cal.set(dateField, value);
		return cal.getTime();
	}

	/**
	 * 给定两个日期参数，计算两个日期之间相隔的天数
	 *
	 * @param firDate 日期值
	 * @param secDate 日期值
	 * @return 两个日期之间相差的天数
	 * @throws IllegalArgumentException 参数firDate或secDate为空时抛出该异常
	 */
	public static final int daysBetween(Date firDate, Date secDate) {
		Assert.notNull(firDate, "参数firDate不能为空");
		Assert.notNull(secDate, "参数secDate不能为空");

		Date truncFirDate = DateUtils.truncate(firDate, Calendar.DAY_OF_MONTH);
		Date truncSecDate = DateUtils.truncate(secDate, Calendar.DAY_OF_MONTH);

		long firTime = truncFirDate.getTime();
		long secTime = truncSecDate.getTime();

		long diff = firTime - secTime;
		if (diff < 0) {
			diff = -diff;
		}

		long daysL = diff / DateUtils.MILLIS_PER_DAY;
		return Integer.parseInt(String.valueOf(daysL));
	}

	/**
	 * 判断当前日期是否在给定的日期区间
	 *
	 * @param date
	 * @return
	 * @author wenting
	 * @create 2014年9月28日 下午8:18:27
	 */
	public static final boolean betweenDateInterval(Date startDate, Date endDate) {
		Assert.notNull(startDate, "参数date不能为空");
		Assert.notNull(endDate, "参数date不能为空");

		Date now = new Date();
		Calendar calStart = Calendar.getInstance();
		Calendar calCurrent = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTime(startDate);
		calCurrent.setTime(now);
		calEnd.setTime(endDate);
		return calStart.before(calCurrent) && calEnd.after(calCurrent);
	}

	/**
	 * 获取最近N个月
	 *
	 * @param n
	 * @return
	 * @author wangdaiwei
	 * @create 2015年3月3日 上午10:15:34
	 */
	public static String[] getLastYearMonths(int n) {
		String[] lastMonths = new String[n];
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < n; i++) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
			lastMonths[n - 1 - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
		}
		return lastMonths;
	}

	/**
	 * 根据年月获取当月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 * @author wangdaiwei
	 * @create 2015年3月4日 上午10:19:56
	 */
	public static String getBeginTimeByYearMonth(int year, int month) {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTimeStr = datef.format(beginTime) + " 00:00:00";
		return beginTimeStr;
	}

	/**
	 * 根据年月获取当月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 * @author wangdaiwei
	 * @create 2015年3月4日 上午10:19:59
	 */
	public static String getEndTimeByYearMonth(int year, int month) {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 0);
		Date endTime = cal.getTime();
		String endTimeStr = datef.format(endTime) + " 23:59:59";
		return endTimeStr;
	}

	/**
	 * @param date
	 * @return String
	 * @throws
	 * @author zhangbin
	 * @Title: getUTCTime
	 * @Description: 获取yyyy-MM-dd'T'HH:mm:ss.SSSXXX日期
	 */
	public static String getUTCTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String time = format.format(date);
		return time;
	}

	public static String getFMT_24H_YYYYMMDDHHMMSSTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		return time;
	}

	/**
	 * @param datetime
	 * @return String
	 * @throws ParseException
	 * @author zhangbin
	 * @Title: getUTCTime
	 * @Description:
	 */
	public static String getUTCTime(String datetime) throws ParseException {
		Date date = DateParseUtil.parse24HyyyyMMddHHmmss(datetime);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String time = format.format(date);
		return time;
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @author zhangbin
	 * @Title: addOrMinusYear
	 * @Description: 日期时间加减年 i>0 加年 i《0减年
	 */
	public static Date addOrMinusYear(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(1, i);
		return cal.getTime();
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @author zhangbin
	 * @Title: addOrMinusMon
	 * @Description: 当前日期时间 i》0 加月份 i《0 减月份
	 */
	public static Date addOrMinusMon(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(2, i);
		return cal.getTime();
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @author zhangbin
	 * @Title: addOrMinusDate
	 * @Description: 当前日期时间 i》0 加天 i《0 减天
	 */
	public static Date addOrMinusDate(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(5, i);
		return cal.getTime();
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @author zhangbin
	 * @Title: addOrMinusHour
	 * @Description: 当前日期时间 i》0 加小时 i《0 减小时
	 */
	public static Date addOrMinusHour(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(10, i);
		return cal.getTime();
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @author zhangbin
	 * @Title: addOrMinusMinute
	 * @Description: 当前日期时间 i》0 加分 i《0 减分
	 */
	public static Date addOrMinusMinute(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(12, i);
		return cal.getTime();
	}

	/**
	 * @param time
	 * @param i
	 * @return Date
	 * @throws
	 * @author zhangbin
	 * @Title: addOrMinusSecond
	 * @Description: 当前日期时间 i》0 加秒 i《0 减秒
	 */
	public static Date addOrMinusSecond(Long time, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date(time);
		cal.setTime(date);
		cal.add(13, i);
		return cal.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 开始时间
	 * @param bdate  结束时间
	 * @return 相差天数
	 * @throws ParseException
	 * @author hemimi
	 * @create 2016年2月17日 下午2:38:47
	 */
	public static int daysBetween(String smdate, String bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(smdate));
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(bdate));
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 根据输入的日期获取，改天的下一天
	 *
	 * @param inDate yyyy-MM-dd 格式的日期字符串
	 * @return 下一天
	 * @author hemimi
	 * @create 2016年2月17日 下午3:13:24
	 */
	public static String getNextDay(String inDate) {
		Date d2 = null;
		try {
			d2 = DateParseUtil.parseISOyyyyMMdd(inDate);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

		// 创建 Calendar 对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d2);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return DateFormatUtil.formatIsoYyyyMMdd(calendar.getTime());
	}

	/**
	 * 根据输入的参数t,获取该当前时间的后t天的日期
	 *
	 * @param inDate
	 * @param t
	 * @return
	 * @author hemimi
	 * @create 2016年2月17日 下午4:00:24
	 */
	public static String getNextsDay(String inDate, int t) {
		Date d2 = null;
		try {
			d2 = DateParseUtil.parseISOyyyyMMdd(inDate);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

		// 创建 Calendar 对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d2);
		calendar.add(Calendar.DAY_OF_MONTH, t);
		return DateFormatUtil.formatIsoYyyyMMdd(calendar.getTime());
	}

	/**
	 * 比较输入两个字符串日期的大小
	 *
	 * @param date1
	 * @param date2
	 * @return
	 * @author hemimi
	 * @create 2016年2月17日 下午4:01:36
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				log.debug("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				log.debug("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 判断输入的日期是否是周末
	 *
	 * @param inDate
	 * @return 是双休日返回true，否则是false
	 * @author hemimi
	 * @create 2016年2月23日 上午9:55:28
	 */
	public static boolean isWeekEnd(String inDate) {
		try {
			Date date = DateParseUtil.parseISOyyyyMMdd(inDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			int weekDay = cal.get(Calendar.DAY_OF_WEEK);
			if (weekDay == Calendar.SUNDAY || weekDay == Calendar.SATURDAY) {
				log.debug("日期:[{}] 是双休日", inDate);
				return true;
			}

			log.debug("日期:[{}] 不是双休日", inDate);
			return false;
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}

		return false;
	}

	/**
	 * @param startTime       设置开始时间
	 * @param endTime         设置结束时间
	 * @param targetStartTime 实际开始时间
	 * @param targetEndTime   实际结束时间
	 * @return
	 * @throws ParseException boolean
	 * @throws
	 * @author zhangbin@anjia365.com
	 * @Title: checkContainTim
	 * @Description: 判断目标时间在设置时间范围内
	 */
	public static boolean checkContainTime(String startTime, String endTime, String targetStartTime,
										   String targetEndTime) throws ParseException {

		Date startTimeDate = DateParseUtil.parse24Hhhmm(startTime);
		Date endTimeDate = DateParseUtil.parse24Hhhmm(endTime);

		Date targetStartTimeDate = DateParseUtil.parse24Hhhmm(targetStartTime);
		Date targetEndTimeDate = DateParseUtil.parse24Hhhmm(targetEndTime);

		long s1 = targetStartTimeDate.getTime() - startTimeDate.getTime();
		long s2 = targetStartTimeDate.getTime() - endTimeDate.getTime();
		long s3 = targetEndTimeDate.getTime() - endTimeDate.getTime();

		return s1 >= 0 && s2 < 0 && s3 <= 0;

	}

	/**
	 * 获取小时差
	 *
	 * @param target
	 * @return
	 * @author hemimi
	 * @create 2016年6月28日 下午2:18:54
	 */
	public static int getTimeDistanceHours(String target) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long senconds = 0;
		try {
			Date date1 = df.parse(target);
			Date date2 = new Date();
			senconds = date1.getTime() - date2.getTime();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		long betweenHours = senconds / (1000 * 3600);
		return Integer.parseInt(String.valueOf(betweenHours));

	}

	/**
	 * 获得上一个月的第一天
	 * getMonthFristDay:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @return
	 * @author zhangbin@anjia365.com
	 */
	public static String getMonthFristDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay = format.format(cal.getTime());
		return firstDay;
	}

	/**
	 * 获取上个月的最后一天
	 *
	 * @return
	 * @author hemimi
	 * @create 2016年7月13日 下午5:29:11
	 */
	public static String getMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0); // 设置为1号,当前日期既为本月第一天
		return format.format(cale.getTime());
	}

	public static String getFristMon() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance(); // 获取当前日期
		int year = cal.get(Calendar.YEAR);
		cal.set(year, 0, 1);
		return format.format(cal.getTime());
	}

	public static String getCurrentMon() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		return format.format(cal.getTime());
	}

	/**
	 * 获取上个月的最后一天
	 *
	 * @return
	 * @author hemimi
	 * @create 2016年7月13日 下午5:29:11
	 */
	public static String getFirstMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date currTime = new Date();
		String curTime = format.format(currTime);
		curTime += "-01";
		return curTime;
	}

	/**
	 * 获取当前月份
	 *
	 * @return 2016-06
	 * @author hemimi
	 * @create 2016年7月13日 下午5:29:11
	 */
	public static String getCurrMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date currTime = new Date();
		return formatter.format(currTime);
	}

	/**
	 * 根据输入的开始时间结束时间结算对应时间日期坐标(x,y)如(2016-01-01,0)
	 *
	 * @param sdate 开始日期
	 * @param edate 结束日期
	 * @return 所有的工作日期
	 * @author 信清松
	 * @create 2016年7月15日 下午4:03:08
	 */
	public static Map<String, Integer> getWorkDaysMap(String sdate, String edate) {

		int days = DateUtil.daysBetween(sdate, edate);
		Map<String, Integer> map = new HashMap<>();
		map.put(sdate, 0);
		for (int i = 0; i < days; i++) {
			String nextDate = DateUtil.getNextDay(sdate);
			map.put(nextDate, i + 1);
			sdate = nextDate;
		}
		return map;
	}

	/**
	 * 根据输入的开始时间结束时间结算对应日期数组
	 *
	 * @param sdate 开始日期
	 * @param edate 结束日期
	 * @return 所有的工作日期
	 * @author 信清松
	 * @create 2016年7月15日 下午4:20:08
	 */
	public static List<String> getWorkDaysList(String sdate, String edate) {

		int days = DateUtil.daysBetween(sdate, edate);
		List<String> workDateList = new ArrayList<String>();
		workDateList.add(sdate);
		for (int i = 0; i < days; i++) {
			String nextDate = DateUtil.getNextDay(sdate);
			workDateList.add(nextDate);
			sdate = nextDate;
		}
		return workDateList;
	}

	/**
	 * 根据输入的最小时间和最大时间获取中间整点时间坐标
	 *
	 * @param minTime 最小时间
	 * @param maxTime 最大日期
	 * @return 所有的工作日期
	 * @throws ParseException
	 * @author 信清松
	 * @create 2016年7月15日 下午4:20:08
	 */
	public static Map<String, Integer> getWorkTimeMap(String minTime, String maxTime) throws ParseException {

		Map<String, Integer> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
		if (StringUtils.isNotBlank(maxTime) && StringUtils.isNotBlank(minTime)) {
			String min = minTime.split(":")[0];
			long bg = sdf.parse(minTime).getTime();
			long eg = sdf.parse(maxTime).getTime();
			int hourTime = Math.round((eg - bg) / 3600000F);
			Integer minx = Integer.parseInt(min);
			int temptime = minx;
			String beforeTime = "";
			String timestr;
			for (int z = 0; z < hourTime; z++) {
				temptime = temptime + 1;
				if (temptime < 10) {
					timestr = "0" + temptime + ":00";
				} else {
					timestr = temptime + ":00";
				}
				if (z == 0) {
					map.put(minTime + "-" + timestr, z);
					beforeTime = timestr;
				} else {
					map.put(beforeTime + "-" + timestr, z);
					beforeTime = timestr;
				}
			}
		}

		return map;
	}

	/**
	 * 根据输入的最小时间和最大时间获取中间整点时间数组
	 *
	 * @param minTime 最小时间点
	 * @param maxTime 最大时间点
	 * @return 所有的工作日期
	 * @throws Exception
	 * @author 信清松
	 * @create 2016年7月15日 下午4:20:08
	 */
	public static List<String> getWorkTimeList(String minTime, String maxTime) throws Exception {

		List<String> workTimeList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
		if (StringUtils.isNotBlank(maxTime) && StringUtils.isNotBlank(minTime)) {
			String min = minTime.split(":")[0];
			long bg = sdf.parse(minTime).getTime();
			long eg = sdf.parse(maxTime).getTime();
			int hourTime = Math.round((eg - bg) / 3600000F);
			workTimeList.add(minTime);
			Integer minx = Integer.parseInt(min);
			int temptime = minx;
			String timestr;
			for (int z = 0; z < hourTime; z++) {
				temptime = temptime + 1;
				if (temptime < 10) {
					timestr = "0" + temptime + ":00";
				} else {
					timestr = temptime + ":00";
				}
				workTimeList.add(timestr);
			}
		}

		return workTimeList;
	}

	/**
	 * 获取日期的年份
	 *
	 * @param date 日期
	 * @return 年份
	 */
	public static String getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return Integer.toString(cal.get(Calendar.YEAR));
	}

	/**
	 * 获取当前年
	 */
	public static String getYear() {
		Calendar cal = Calendar.getInstance();
		return Integer.toString(cal.get(Calendar.YEAR));
	}

	/**
	 * 获取日期的月份
	 *
	 * @param date 日期
	 * @return 月份
	 */
	public static String getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.format("%02d", cal.get(Calendar.MONTH));
	}

	/**
	 * 获取当前2位月份
	 */
	public static String getMonth() {
		Calendar cal = Calendar.getInstance();
		return String.format("%02d", cal.get(Calendar.MONTH));
	}

	/**
	 * 获取日期的日
	 *
	 * @param date 日期
	 * @return 月
	 */
	public static String getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.format("%02d", cal.get(Calendar.DATE));
	}


	/**
	 * 获取当前2位日期
	 */
	public static String getDay() {
		Calendar cal = Calendar.getInstance();
		return String.format("%02d", cal.get(Calendar.DATE));
	}

	/**
	 * <pre>
	 * 算出间隔时长后的下一个时间
	 * 版本修改历史记录：
	 * 1) 1.0.0 2017年6月5日 上午11:25:36 hemimi created
	 * </pre>
	 *
	 * @param startTime 开始时间 比如05:00
	 * @param interval  间隔时长
	 * @return
	 */
	public static String getIntevalTime(String startTime, int interval) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String endTime = "";
		try {
			Date d2 = format.parse(startTime);
			Time startTimeFmt = new Time(d2.getTime());
			// 创建 Calendar 对象
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTimeFmt);
			calendar.add(Calendar.HOUR_OF_DAY, interval);
			endTime = format.format(calendar.getTime());
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return endTime;
	}

	/**
	 * 给定两个日期参数，计算两个日期之间相隔的秒数
	 *
	 * @param firDate 日期值
	 * @param secDate 日期值
	 * @return 两个日期之间相差的天数
	 * @throws IllegalArgumentException 参数firDate或secDate为空时抛出该异常
	 */
	public static final int secondsBetween(Date firDate, Date secDate) {
		Assert.notNull(firDate, "参数firDate不能为空");
		Assert.notNull(secDate, "参数secDate不能为空");

		//Date truncFirDate = DateUtils.truncate(firDate, Calendar.DAY_OF_MONTH);
		//Date truncSecDate = DateUtils.truncate(secDate, Calendar.DAY_OF_MONTH);

		long firTime = firDate.getTime();
		long secTime = secDate.getTime();

		long diff = firTime - secTime;
		if (diff < 0) {
			diff = -diff;
		}

		long daysL = diff / DateUtils.MILLIS_PER_SECOND;
		return Integer.parseInt(String.valueOf(daysL));
	}
    /**
     * <pre>
     * 算出最近30天的年月日
     * 版本修改历史记录：
     * 1) 1.0.0 2017年12月07日 上午11:25:36 xinqingsong created
     * </pre>
     *
     * @param date 开始时间 比如2017
     * @param day 间隔时长
     * @return
     */
    public static List<String> getAllTheDateOftheMonth(Date date,int day) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int s=day;
        while(s!=0){
            list.add(format.format(cal.getTime()));
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
            s--;
        }
        return list;
    }
}
