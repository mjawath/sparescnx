package com.sparescnx.incidentmanagement.incident.repo;


import com.sparescnx.incidentmanagement.incident.service.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepo extends JpaRepository<Incident, String>, IncidentCustomRepo {
}
