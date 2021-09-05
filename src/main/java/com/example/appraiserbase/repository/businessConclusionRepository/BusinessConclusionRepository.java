package com.example.appraiserbase.repository.businessConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessConclusionRepository extends JpaRepository<BusinessConclusion, Long>, BusinessConclusionRepositoryCustom {

}
