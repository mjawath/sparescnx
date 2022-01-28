package com.sparescnx.incidentmanagement.incident.controller;

import com.sparescnx.incidentmanagement.incident.service.Incident;
import org.springframework.stereotype.Component;

@Component
public class CreateIncidentRequestMapper {

   public Incident map(CreateIncidentRequestDTO dto){
       return Incident.builder().build();
   }
}
