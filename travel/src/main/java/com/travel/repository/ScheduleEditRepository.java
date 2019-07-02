package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.model.Schedule;

@Repository
public interface ScheduleEditRepository extends JpaRepository<Schedule, Integer>{
}
