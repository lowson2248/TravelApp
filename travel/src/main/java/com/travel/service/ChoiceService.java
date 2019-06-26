package com.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Choice;
import com.travel.repository.ChoiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChoiceService {
	
	@Autowired
	ChoiceRepository choiceRepository;

	public void save(Choice choice) {
		choiceRepository.saveAndFlush(choice);
	}
	
	public Choice findById(int id) {
		return choiceRepository.findByChoiceId(id);
	}

}
