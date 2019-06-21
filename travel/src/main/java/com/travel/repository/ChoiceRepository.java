package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

}
