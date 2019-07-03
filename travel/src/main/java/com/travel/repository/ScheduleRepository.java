package com.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.model.Member;
import com.travel.model.Project;
import com.travel.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

	public List<Schedule> findByProject(Project project);
	
}
