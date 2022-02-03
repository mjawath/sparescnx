package com.sparescnx.incidentmanagement.incident.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateIncidentRequestDTO {

    private String description;
    private String incidentType;

}
