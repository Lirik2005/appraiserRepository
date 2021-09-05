package com.example.appraiserbase.service.businessCoclusion;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import com.example.appraiserbase.repository.businessConclusionRepository.BusinessConclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessConclusionServiceImpl implements BusinessConclusionService{

    private final BusinessConclusionRepository repository;

    @Autowired
    public BusinessConclusionServiceImpl(BusinessConclusionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<BusinessConclusion> getAllConclusions() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public BusinessConclusion addNewConclusion(BusinessConclusion conclusion) {
        return repository.save(conclusion);
    }

    @Override
    public BusinessConclusion updateConclusion(BusinessConclusion conclusion) {
        return repository.save(conclusion);
    }

    @Override
    public void deleteConclusion(Long pid) {
        repository.deleteById(pid);
    }

    @Override
    public List<BusinessConclusion> conclusionFilter(String searchText) {
        return repository.conclusionFilter(searchText);
    }
}
