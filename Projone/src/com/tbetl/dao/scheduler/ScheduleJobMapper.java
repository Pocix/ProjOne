package com.tbetl.dao.scheduler;

import java.util.List;

import com.tbetl.scheduler.ScheduleJob;

public interface ScheduleJobMapper {
	int deleteByPrimaryKey(Long jobId);

	int insert(ScheduleJob record);

	int insertSelective(ScheduleJob record);

	ScheduleJob selectByPrimaryKey(Long jobId);

	int updateByPrimaryKeySelective(ScheduleJob record);

	int updateByPrimaryKey(ScheduleJob record);

	List<ScheduleJob> getAll();
}