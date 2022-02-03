package com.sparescnx.incidentmanagement.incident.repo;


import com.sparescnx.incidentmanagement.incident.service.Incident;
import com.sparescnx.incidentmanagement.incident.service.IncidentStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepo extends JpaRepository<Incident, String>, IncidentCustomRepo {

    List<Incident> findByStatusNot(IncidentStatus status, Sort... sort);
}
