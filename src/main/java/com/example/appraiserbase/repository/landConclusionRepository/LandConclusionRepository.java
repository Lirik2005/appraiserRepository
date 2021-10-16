package com.example.appraiserbase.repository.landConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import com.example.appraiserbase.model.conclusions.LandConclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LandConclusionRepository extends JpaRepository<LandConclusion, Long>, LandConclusionRepositoryCustom {

    @Query("select b from LandConclusion b join fetch b.appraiser where b.pid = :id")
    LandConclusion findConclusionById(@Param("id") Long pid);

}
