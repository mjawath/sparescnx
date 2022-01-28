package com.sparescnx.incidentmanagement.incident.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateIncidentRequestDTO {

    private String id;
    private String assignedTo;
    private Boolean isAcknowledged;
    private Boolean isResolved;

    private IncidentAction action;

    public enum IncidentAction {
        ASSIGN, ACKNOWLEDGE, RESOLVE
    }
}
