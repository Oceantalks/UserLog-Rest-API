package se.drwp.APIdemo.restless.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import se.drwp.APIdemo.restless.model.UserLog;

public class DateSortHelper {
	private static final String TAG = "DateSortHelper.class";

	public static ArrayList<UserLog> SortListReturnLast5(ArrayList<UserLog> dates, boolean order) throws Exception {

		DateFormat f = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");

		Comparator<UserLog> comp = new Comparator<UserLog>() {
			@Override
			public int compare(UserLog o1, UserLog o2) {
				try {
					if (order) {
						return f.parse(o1.getDate()).compareTo(f.parse(o2.getDate()));
					} else {
						return f.parse(o2.getDate()).compareTo(f.parse(o1.getDate()));
					}
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		};
		Collections.sort(dates, comp);

		ArrayList<UserLog> last5Logs = new ArrayList<UserLog>();
		for (int i = 0; i <= 4; i++) {
			last5Logs.add(dates.get(i));
		}

		if (last5Logs.isEmpty() || last5Logs.size() == 0) {
			throw new Exception(TAG + ": Could not any date logs");
		}

		return last5Logs;
	}
}
