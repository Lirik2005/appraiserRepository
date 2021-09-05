package com.example.appraiserbase.repository.businessConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessConclusionRepositoryCustom {

    List<BusinessConclusion> conclusionFilter(String searchText);
}
