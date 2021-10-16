package com.example.appraiserbase.repository.landConclusionRepository;

import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import com.example.appraiserbase.model.conclusions.LandConclusion;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LandConclusionRepositoryCustomImpl implements LandConclusionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LandConclusion> landConclusionFilter(String searchLandText) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(LandConclusion.class);
        Disjunction disjunction = Restrictions.or(
                //  Restrictions.ilike("appraiser", searchLandText, MatchMode.ANYWHERE),
                Restrictions.ilike("subjectOfAssessment", searchLandText, MatchMode.ANYWHERE)
                //Restrictions.ilike("typeOfValue", searchLandText, MatchMode.ANYWHERE),
                // Restrictions.ilike("assessmentPurpose", searchLandText, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
