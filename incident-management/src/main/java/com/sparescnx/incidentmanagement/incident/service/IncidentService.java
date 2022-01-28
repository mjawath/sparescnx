package com.sparescnx.incidentmanagement.incident.service;

import com.sparescnx.incidentmanagement.incident.controller.SearchFilter;
import com.sparescnx.incidentmanagement.incident.controller.UpdateIncidentRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IncidentService {

    Optional<Incident> findById(String id);

    String create(Incident incident);

    String update(UpdateIncidentRequestDTO ir);

    void delete(String id);

    List<Incident> search(SearchFilter filter);
}
