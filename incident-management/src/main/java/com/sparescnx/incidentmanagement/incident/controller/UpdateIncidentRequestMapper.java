package com.sparescnx.incidentmanagement.incident.controller;

import com.sparescnx.incidentmanagement.incident.service.Incident;
import org.springframework.stereotype.Component;

@Component
public class UpdateIncidentRequestMapper {

   public Incident map(UpdateIncidentRequestDTO dto){
       return Incident.builder().build();
   }
}
