package com.example.appraiserbase.repository.businessConclusionRepository;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import com.example.appraiserbase.model.conclusions.enums.TypeOfValue;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BusinessConclusionRepositoryCustomImpl implements BusinessConclusionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BusinessConclusion> conclusionFilter(String searchText) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(BusinessConclusion.class);
        Disjunction disjunction = Restrictions.or(
                //  Restrictions.ilike("appraiser", searchText, MatchMode.ANYWHERE),
                Restrictions.ilike("subjectOfAssessment", searchText, MatchMode.ANYWHERE)
                //Restrictions.ilike("typeOfValue", searchText, MatchMode.ANYWHERE),
                //Restrictions.ilike("assessmentPurpose", searchText, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
