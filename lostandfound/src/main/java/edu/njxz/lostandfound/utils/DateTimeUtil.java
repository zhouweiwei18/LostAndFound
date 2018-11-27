package edu.njxz.lostandfound.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static final String FORMAT = "yyyy-MM-dd";

	public static Date strToDate(String dateTimeStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		Date date = sdf.parse(dateTimeStr);
		return date;
	}

	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		String str = sdf.format(date);
		return str;
	}

	public static void main(String[] args) {

		Date date = new Date();

		String str = DateTimeUtil.dateToStr(date);

		System.out.println(str);

	}
}
