package com.sparescnx.incidentmanagement.incident.service;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Incident {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String id;
    private String createdBy;
    private String description;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String assignedTo;
    private LocalDateTime acknowledgedAt;
    private IncidentStatus status;
    private IncidentType type;

}

