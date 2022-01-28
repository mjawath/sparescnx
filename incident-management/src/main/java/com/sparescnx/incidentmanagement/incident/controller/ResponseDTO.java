package com.sparescnx.incidentmanagement.incident.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseDTO {
    private String id;
    private String title;
}
