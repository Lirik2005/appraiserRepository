package com.example.appraiserbase.repository.businessConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class BusinessConclusionRepositoryCustomImpl implements BusinessConclusionRepositoryCustom{



    @Override
    public List<BusinessConclusion> conclusionFilter(String searchText) {
        return null;
    }
}
