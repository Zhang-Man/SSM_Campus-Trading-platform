package com.substitute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.substitute.dao.ActivitiesDao;
import com.substitute.entity.Activities;
import com.substitute.service.ActivitiesService;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {
	@Autowired
	private ActivitiesDao activitiesDao;

	@Override
	public int insertSelective(Activities record) {
		return activitiesDao.insertSelective(record);
	}

	@Override
	public List<Activities> SelectMyActivitiesWithStatus(Activities record) {
		return activitiesDao.SelectMyActivitiesWithStatus(record);
	}

	@Override
	public List<Activities> SelectAllMyActivities(Activities record) {
		return activitiesDao.SelectAllMyActivities(record);
	}

	@Override
	public Activities selectByPrimaryKey(Integer id) {
		return activitiesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Activities record) {
		return activitiesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Activities> SelectAllActivitiesWithSAndV() {
		return activitiesDao.SelectAllActivitiesWithSAndV();
	}

	@Override
	public List<Activities> SelectAllActivitiesWithSAndNV() {
		return activitiesDao.SelectAllActivitiesWithSAndNV();
	}

	@Override
	public List<Activities> SelectAllActivitiesWithStatus() {
		return activitiesDao.SelectAllActivitiesWithStatus();
	}

	@Override
	public List<Activities> SelectAllActivities(Map paramMap,List paramMap2) {
		return activitiesDao.SelectAllActivities(paramMap,paramMap2);
	}

	@Override
	public List<Activities> SelectMyAcceptActivitiesWithStatus(Activities record) {
		return activitiesDao.SelectMyAcceptActivitiesWithStatus(record);
	}
}
