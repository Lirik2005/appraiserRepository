package com.example.appraiserbase.service.landConclusion;

import com.example.appraiserbase.model.conclusions.LandConclusion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LandConclusionService {

    LandConclusion getLandConclusionById(Long pid);

    List<LandConclusion> getAllConclusions();

    void addNewConclusion(LandConclusion conclusion);

    LandConclusion updateConclusion(LandConclusion conclusion);

    void deleteConclusion(Long pid);

    List<LandConclusion> landConclusionFilter(String searchLandText);

}
