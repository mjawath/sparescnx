package com.sparescnx.incidentmanagement.incident.controller;

import com.sparescnx.incidentmanagement.incident.service.IncidentType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateIncidentRequestDTO {

    private String description;
    private IncidentType incidentType;

}
