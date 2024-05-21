package com.traini8.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traini8.dtos.AddressDto;
import com.traini8.dtos.TrainingCenterDto;
import com.traini8.entities.Address;
import com.traini8.entities.TrainingCenter;
import com.traini8.repository.TrainingCenterRepository;
import com.traini8.services.TrainingCenterService;

//THis is a service layer
@Service
public class TrainingCenterServiceImpl implements TrainingCenterService {

	@Autowired
	TrainingCenterRepository trainingCenterRepository;

	// This method is to save TrainingCenter data to database
	@Override
	public TrainingCenterDto create(TrainingCenterDto trainingCenterDto) {

		String userId = UUID.randomUUID().toString();

		TrainingCenter trainingCenter = dtoToObject(trainingCenterDto);

		trainingCenter.setId(userId);
		trainingCenter.setCreatedOn(LocalDateTime.now());

		// trainingCenterRepository.save() saves data into database and also returns the
		// saved Data
		TrainingCenter savedTrainingCenter = trainingCenterRepository.save(trainingCenter);

		TrainingCenterDto savedTrainingCenterDto = objectToDto(savedTrainingCenter);

		return savedTrainingCenterDto;
	}

	// THis method is just to convert data from TrainingCenterDto to TrainingCenter
	// datatype
	private TrainingCenter dtoToObject(TrainingCenterDto trainingCenterDto) {
		TrainingCenter trainingCenter = TrainingCenter.builder().centerName(trainingCenterDto.getCenterName())
				.centerCode(trainingCenterDto.getCenterCode()).studentCapacity(trainingCenterDto.getStudentCapacity())
				.coursesOffered(trainingCenterDto.getCoursesOffered()).email(trainingCenterDto.getEmail())
				.contactPhone(trainingCenterDto.getContactPhone()).build();

		Address address = dtotoAddress(trainingCenterDto.getAddress());

		trainingCenter.setAddress(address);
		return trainingCenter;
	}

	// THis method is just to convert data from AddressDto to Address datatype
	private Address dtotoAddress(AddressDto addressDto) {
		Address address = Address.builder().detailedAddress(addressDto.getDetailedAddress()).city(addressDto.getCity())
				.state(addressDto.getState()).pincode(addressDto.getPincode()).build();

		return address;
	}

	// THis method is just to convert data from TrainingCenterto TrainingCenterDto
	// datatype
	private TrainingCenterDto objectToDto(TrainingCenter trainingCenter) {
		TrainingCenterDto trainingCenterDto = TrainingCenterDto.builder().centerName(trainingCenter.getCenterName())
				.centerCode(trainingCenter.getCenterCode()).studentCapacity(trainingCenter.getStudentCapacity())
				.coursesOffered(trainingCenter.getCoursesOffered()).email(trainingCenter.getEmail())
				.contactPhone(trainingCenter.getContactPhone()).build();

		trainingCenterDto.setAddress(addresstoDto(trainingCenter.getAddress()));

		return trainingCenterDto;
	}

	// THis method is just to convert data from Address to AddressDto datatype
	private AddressDto addresstoDto(Address address) {
		AddressDto addressDto = AddressDto.builder().detailedAddress(address.getDetailedAddress())
				.city(address.getCity()).state(address.getState()).pincode(address.getPincode()).build();

		return addressDto;
	}

	// This Method to fetch all the data regarding TrainingCenter from database
	@Override
	public List<TrainingCenterDto> fetchAll() {

		// trainingCenterRepository.findAll() will return a List of TrainingCenter
		// Object from database
		List<TrainingCenter> trainingCenters = trainingCenterRepository.findAll();
		if (trainingCenters == null) {
			return new ArrayList<>();
		}

		// Here i am using java streams to convert data of type TrainingCenter to
		// TrainingCenterDto
		List<TrainingCenterDto> trainingCenterDtos = trainingCenters.stream().map(t -> objectToDto(t))
				.collect(Collectors.toList());
		return trainingCenterDtos;

	}

}
