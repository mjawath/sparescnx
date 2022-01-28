package com.sparescnx.incidentmanagement.incident.repo;

import com.sparescnx.incidentmanagement.incident.service.Incident;

import java.time.LocalDate;
import java.util.List;

public interface IncidentCustomRepo {

    List<Incident> filterBy(String[] sortBy, LocalDate createdDate);
}
