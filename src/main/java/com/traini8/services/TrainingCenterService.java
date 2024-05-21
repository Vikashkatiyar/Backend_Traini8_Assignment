package com.traini8.services;

import java.util.List;

import com.traini8.dtos.TrainingCenterDto;

public interface TrainingCenterService {

    TrainingCenterDto create(TrainingCenterDto trainingCenterDto);
    
    List<TrainingCenterDto> fetchAll();

    

}
