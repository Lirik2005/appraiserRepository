package com.example.appraiserbase.service.landConclusion;

import com.example.appraiserbase.model.conclusions.LandConclusion;
import com.example.appraiserbase.repository.landConclusionRepository.LandConclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LandConclusionServiceImpl implements LandConclusionService {

    private final LandConclusionRepository landRepository;

    @Autowired
    public LandConclusionServiceImpl(LandConclusionRepository repository) {
        this.landRepository = repository;
    }

    @Override
    @Transactional
    public LandConclusion getLandConclusionById(Long pid) {
        return landRepository.findConclusionById(pid);
    }

    @Override
    @Transactional
    public List<LandConclusion> getAllConclusions() {
        return landRepository.findAll();
    }

    @Override
    @Transactional
    public void addNewConclusion(LandConclusion conclusion) {
        landRepository.save(conclusion);
    }

    @Override
    public LandConclusion updateConclusion(LandConclusion conclusion) {
        return landRepository.save(conclusion);
    }

    @Override
    public void deleteConclusion(Long pid) {
        landRepository.deleteById(pid);
    }

    @Override
    public List<LandConclusion> landConclusionFilter(String searchLandText) {
        return landRepository.landConclusionFilter(searchLandText);
    }
}
