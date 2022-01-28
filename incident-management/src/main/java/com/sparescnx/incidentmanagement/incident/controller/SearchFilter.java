package com.sparescnx.incidentmanagement.incident.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFilter {
    private String[] sortBy;
    private String[] filterBy;///items?price[gte]=10,price[lte]=100

    private String createdDate;//filter

}
