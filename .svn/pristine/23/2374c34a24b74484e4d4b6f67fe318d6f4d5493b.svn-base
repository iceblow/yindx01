package com.uncleserver.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uncleserver.common.CommonUtils;
import com.uncleserver.model.Config;
import com.uncleserver.model.LevelSet;
import com.uncleserver.model.PointRecord;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.service.api.BirthdayTaskService;

/**
 * 生日送积分
 *
 */
@Component
public class BirthdayTask {
	
	@Resource
	private BirthdayTaskService birthdayTaskService;
	
	@Scheduled(cron="0 0 6 * * ? ") //每天6点执行
	public void birthday(){
		Calendar now = Calendar.getInstance();
		//所有今天生日的用户
		List<User> userList = birthdayTaskService.getBirthDayUser();
		String nowDay = CommonUtils.getTimeFormat(now.getTime(), "yyyy-MM-dd");
		if(userList != null && userList.size() > 0){
			for (User user : userList) {
				if(user.getBirthday() != null){
					String userDay = CommonUtils.getTimeFormat(user.getBirthday(), "yyyy-MM-dd");
					if(nowDay.equals(userDay)){
						Config config = birthdayTaskService.getConfigByName("birthday_point_user");
						if(config != null && config.getConfigvalue() != null){
							UserExtra userExtra = birthdayTaskService.getUserExtra(user.getUserid());
							if(userExtra != null){
								Integer point = CommonUtils.parseInt(config.getConfigvalue(), 0);
								//用户信息
								userExtra.setPoint(userExtra.getPoint() + point);
								userExtra.setPoint_total(userExtra.getPoint_total() + point);
								birthdayTaskService.updateUserExtra(userExtra);
								// 积分记录
								PointRecord pointRecord = new PointRecord();
								pointRecord.setAddtime(new Date());
								pointRecord.setCount(point);
								pointRecord.setType((short) 5);
								pointRecord.setUserid(user.getUserid());
								birthdayTaskService.insertPointRecord(pointRecord);
								//查看VIP等级是否能升级
								LevelSet levelSet = birthdayTaskService.setlectSetByPoint(userExtra.getPoint_total());
								if (levelSet != null && levelSet.getLevel() != user.getLevel()) {
									user.setLevel(levelSet.getLevel());
									birthdayTaskService.updateUser(user);
								}
							}
						}
					}
				}
			}
		}
		
	}
}
