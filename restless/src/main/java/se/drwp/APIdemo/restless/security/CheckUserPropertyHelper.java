package se.drwp.APIdemo.restless.security;

import java.util.ArrayList;

import se.drwp.APIdemo.restless.model.UserLog;

public class CheckUserPropertyHelper {
	private static final String TAG = "CheckUserPropertyHelper.class";
	
	public static ArrayList<UserLog> checkUserProperty(ArrayList<UserLog> list, Long id) throws Exception {
		
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(list.get(i).getUserId())) {
				
			} else {
				throw new Exception(TAG + "Error: One or more logs does not belong to user with id " + id);
			}
		}
		
		System.out.println(TAG + ": All logs belong to the user and is being returned");
		return list;
	}

}
