package com.uncleserver.service.api.Impl;

import java.util.List;

import com.uncleserver.model.Config;
import com.uncleserver.model.LevelSet;
import com.uncleserver.model.PointRecord;
import com.uncleserver.model.User;
import com.uncleserver.model.UserExtra;
import com.uncleserver.service.api.BirthdayTaskService;


public class BirthdayTaskServiceImpl extends BaseServiceImpl implements BirthdayTaskService {

	@Override
	public List<User> getBirthDayUser() {
		return userMapper.selectBirthdayUser();
	}

	@Override
	public Config getConfigByName(String name) {
		return configMapper.selectConfigByKey(name);
	}

	@Override
	public UserExtra getUserExtra(Integer userid) {
		return userExtraMapper.selectByUserId(userid);
	}

	@Override
	public int updateUserExtra(UserExtra u) {
		return userExtraMapper.updateByPrimaryKey(u);
	}

	@Override
	public int insertPointRecord(PointRecord pointRecord) {
		return pointRecordMapper.insert(pointRecord);
	}

	@Override
	public LevelSet getLevelSetByLevel(Short level) {
		return levelSetMapper.setlectSetByLevel(level);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	public LevelSet setlectSetByPoint(Integer p) {
		return levelSetMapper.setlectSetByPoint(p);
	}

}
