package com.example.appraiserbase.repository.businessConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessConclusionRepository extends JpaRepository<BusinessConclusion, Long>, BusinessConclusionRepositoryCustom {

    @Query("select b from BusinessConclusion b join fetch b.appraiser where b.pid = :id")
    BusinessConclusion findConclusionById(@Param("id") Long pid);

}
