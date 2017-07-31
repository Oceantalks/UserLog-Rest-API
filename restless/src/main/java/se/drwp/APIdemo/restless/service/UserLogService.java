package se.drwp.APIdemo.restless.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.drwp.APIdemo.restless.model.UserLog;
import se.drwp.APIdemo.restless.repository.UserLogRepository;
import se.drwp.APIdemo.restless.repository.UserRepository;
import se.drwp.APIdemo.restless.security.CheckUserPropertyHelper;
import se.drwp.APIdemo.restless.util.DateSortHelper;
import se.drwp.APIdemo.restless.util.DateStampHelper;

@Service
public class UserLogService {
	private static final String TAG = "UserLogService.class";

	private final UserLogRepository userLogRespository;

	private final UserRepository userRespository;

	@Autowired
	public UserLogService(UserLogRepository userLogRespository, UserRepository userRepository) {
		this.userLogRespository = userLogRespository;
		this.userRespository = userRepository;
	}

	@Transactional
	public UserLog createUserLog(Long id) throws Exception {
		System.out.println(TAG + ": Creating user log with userId " + id + " STARTS");
		UserLog newLog = new UserLog(id, DateStampHelper.generateDateStamp());
		UserLog returnLog = null;
		try {
			returnLog = userLogRespository.save(newLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (returnLog == null) {
			throw new Exception(TAG + ": Could not save the log for user with id " + id);
		}

		System.out.println(TAG + ": New log created for user with id " + id);
		return returnLog;
	}

	@Transactional
	public List<UserLog> findAllByUserId(Long id) throws Exception {
		System.out.println(TAG + ": Find all logs for user with id " + id + " STARTS");
		ArrayList<UserLog> last5Logs = new ArrayList<UserLog>();
		boolean sortOrder = false;

		if (userRespository.exists(id)) {

			try {
				last5Logs = userLogRespository.findAllByUserId(id);
				System.out.println(TAG + ": Found and loglist from user with id " + id + " and is now sorting");
				last5Logs = DateSortHelper.SortListReturnLast5(last5Logs, sortOrder);
				last5Logs = CheckUserPropertyHelper.checkUserProperty(last5Logs, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new Exception(TAG + ": No valid user with id " + id);
		}

		if (last5Logs == null || last5Logs.size() == 0) {
			throw new Exception(TAG + ": Could not retrieve loglist from user with id" + id);
		}

		System.out.println(TAG + ": Returning sorted loglist from user with id " + id);

		return last5Logs;
	}

}
