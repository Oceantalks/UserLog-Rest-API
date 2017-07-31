package se.drwp.APIdemo.restless.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateStampHelper {
	
	public static String generateDateStamp() {
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
		Date date = Calendar.getInstance().getTime();
		String formatedTime = format.format(date);
		return formatedTime;
	}

}
