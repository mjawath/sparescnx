package com.sparescnx.incidentmanagement.incident.repo;

import com.sparescnx.incidentmanagement.incident.service.Incident;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class IncidentRepoImpl implements IncidentCustomRepo{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Incident> filterBy(String[] sortBy, LocalDate createdDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Incident> criteriaQuery = cb.createQuery(Incident.class);
        Root<Incident> incidentRoot = criteriaQuery.from(Incident.class);
        Path<LocalDateTime> createdAt = incidentRoot.get("createdAt");
        Predicate credate = cb.between(createdAt, LocalDateTime.from(createdDate.atStartOfDay()),LocalDateTime.from(createdDate.plusDays(1).atStartOfDay()));
        return entityManager.createQuery(criteriaQuery.select(incidentRoot).where(credate).orderBy(cb.asc(createdAt))).getResultList();
    }
}
