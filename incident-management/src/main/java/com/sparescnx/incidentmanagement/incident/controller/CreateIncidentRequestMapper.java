package com.sparescnx.incidentmanagement.incident.controller;

import com.sparescnx.incidentmanagement.incident.service.Incident;
import com.sparescnx.incidentmanagement.incident.service.IncidentType;
import org.springframework.stereotype.Component;

@Component
public class CreateIncidentRequestMapper {

    public Incident map(CreateIncidentRequestDTO dto) {
        return Incident.builder()
                .description(dto.getDescription())
                .type(IncidentType.valueOf(dto.getIncidentType()))
                .build();
    }
}
