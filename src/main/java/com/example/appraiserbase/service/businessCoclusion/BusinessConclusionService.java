package com.example.appraiserbase.service.businessCoclusion;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusinessConclusionService {

    List<BusinessConclusion> getAllConclusions();

    BusinessConclusion addNewConclusion(BusinessConclusion conclusion);

    BusinessConclusion updateConclusion(BusinessConclusion conclusion);

    void deleteConclusion(Long pid);

    List<BusinessConclusion> conclusionFilter(String searchText);

}
