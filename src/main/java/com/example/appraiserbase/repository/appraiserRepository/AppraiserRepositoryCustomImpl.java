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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppraiserRepositoryCustomImpl implements AppraiserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Appraiser> filterAppraiser(String searchText) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Appraiser.class);
        Disjunction disjunction = Restrictions.or(
                Restrictions.ilike("lastName", searchText, MatchMode.ANYWHERE),
                Restrictions.ilike("firstName", searchText, MatchMode.ANYWHERE),
                Restrictions.ilike("placeOfWork", searchText, MatchMode.ANYWHERE),
                Restrictions.ilike("phoneNumber", searchText, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

   /* public List<Appraiser> filterAppraiser(String searchText) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Appraiser> cq = cb.createQuery(Appraiser.class);
        Root<Appraiser> appraiser = cq.from(Appraiser.class);
        List<Predicate> predicates = new ArrayList<>();
        if (searchText != null && !searchText.isEmpty()) {
            predicates.add(cb.like(appraiser.get("lastName"), "%" + searchText + "%"));
        }

            cq.select(appraiser).where(cb.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(cq).getResultList();

    }*/


}



