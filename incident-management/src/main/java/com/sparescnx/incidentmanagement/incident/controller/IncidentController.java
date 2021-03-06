package com.sparescnx.incidentmanagement.incident.controller;

import com.sparescnx.incidentmanagement.incident.service.Incident;
import com.sparescnx.incidentmanagement.incident.service.IncidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

class EndpointPaths {
    public static final String INCIDENTS = "incidents";
}

@Slf4j
@RestController
@RequestMapping(path = {EndpointPaths.INCIDENTS})
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private CreateIncidentRequestMapper createRequestMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Incident> get(@PathVariable String id) {
        Optional<Incident> incident = incidentService.findById(id);
        return incident.map(ResponseEntity::ok).orElseGet(() -> {
            log.info("item {} not found ", id);
            return ResponseEntity.notFound().build();
        });
    }

    @GetMapping
    public ResponseEntity<List<Incident>> search(SearchFilter filter) {
        List<Incident> incidents = incidentService.search(filter);
        if (incidents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(incidents);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody CreateIncidentRequestDTO incidentRequestDTO) {
        Incident incident = createRequestMapper.map(incidentRequestDTO);
        String id = incidentService.create(incident);
        URI location = UriComponentsBuilder.newInstance().replacePath(EndpointPaths.INCIDENTS + "/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).body(ResponseDTO.builder()
                .id(id).title("created").build());
    }

    @PatchMapping
    public ResponseEntity<String> update(@RequestBody UpdateIncidentRequestDTO incidentRequestDTO) {
        Incident incidents = Incident.builder().build();
        incidentService.update(incidentRequestDTO);
        return ResponseEntity.ok(incidents.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        incidentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
