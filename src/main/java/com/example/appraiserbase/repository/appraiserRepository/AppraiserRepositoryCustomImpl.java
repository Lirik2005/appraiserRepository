package com.example.appraiserbase.repository.appraiserRepository;

import com.example.appraiserbase.model.Appraiser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AppraiserRepositoryCustomImpl implements AppraiserRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Appraiser> filterAppraiser(String searchWord) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Appraiser.class);
        criteria.createAlias("roles", "roles");
        Disjunction disjunction = Restrictions.or (
                Restrictions.ilike("lastName", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("firstName", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("placeOfWork", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("phoneNumber", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("businessCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("landCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("propertyCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("equipmentCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("intellectCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("roles.pid", searchWord, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();

      /*  Session session = entityManager.unwrap(Session.class);
        DetachedCriteria criteria = DetachedCriteria.forClass(Appraiser.class);
        criteria.createAlias("roles", "roles");
        Disjunction disjunction = Restrictions.or (
                Restrictions.ilike("lastName", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("firstName", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("placeOfWork", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("phoneNumber", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("businessCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("landCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("propertyCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("equipmentCertificate", searchWord, MatchMode.ANYWHERE),
                Restrictions.ilike("intellectCertificate", searchWord, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);

       return criteria.getExecutableCriteria(session).list();*/
    }
}
