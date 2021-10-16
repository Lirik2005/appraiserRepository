package com.example.appraiserbase.repository.landConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import com.example.appraiserbase.model.conclusions.LandConclusion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandConclusionRepositoryCustom {

    List<LandConclusion> landConclusionFilter(String searchText);
}
