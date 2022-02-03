package com.sparescnx.incidentmanagement.incident.service;

import com.sparescnx.incidentmanagement.incident.IncidentNotFoundException;
import com.sparescnx.incidentmanagement.incident.controller.SearchFilter;
import com.sparescnx.incidentmanagement.incident.controller.UpdateIncidentRequestDTO;
import com.sparescnx.incidentmanagement.incident.repo.IncidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepo incidentRepo;

    @Override
    public Optional<Incident> findById(String incidentId) {
        return incidentRepo.findById(incidentId);
    }

    @Override
    public String create(Incident incident) {
        incident.setStatus(IncidentStatus.NEW);
        audit(incident);
        return incidentRepo.save(incident).getId();
    }

    @Override
    public String update(UpdateIncidentRequestDTO dto) {
        return incidentRepo.findById(dto.getId()).map((incident) -> {
            switch (dto.getAction()) {
                case ASSIGN:
                    incident.setAssignedTo(dto.getAssignedTo());
                    incident.setStatus(IncidentStatus.ASSINGED);
                    break;
                case ACKNOWLEDGE:
                    if (dto.getIsAcknowledged() != null) {
                        incident.setAcknowledgedAt(LocalDateTime.now());
                        incident.setStatus(IncidentStatus.ACKNOWLEDGED);
                    }
                    break;
                case RESOLVE:
                    if (dto.getIsResolved() != null) {
                        incident.setAcknowledgedAt(LocalDateTime.now());
                        incident.setStatus(IncidentStatus.ACKNOWLEDGED);
                    }
                    break;
                default:
                    throw new RuntimeException("");
            }

            audit(incident);
            return incidentRepo.save(incident).getId();
        }).orElseThrow(RuntimeException::new);

    }

    @Override
    public void delete(String id) {

        incidentRepo.findById(id).map(incident -> {
            incident.setStatus(IncidentStatus.DELETED);
            return incidentRepo.save(incident);
        }).orElseThrow(IncidentNotFoundException::new);
    }

    public List<Incident> search(SearchFilter filter) {
        if (filter.getFilterBy() == null && filter.getCreatedDate() == null) {
            return incidentRepo.findByStatusNot(IncidentStatus.DELETED, Sort.by("updatedAt", "createdAt"));
        }
        // Todo - logic should be enhanced to accommodate sort , filter
        String[] sortBy = filter.getSortBy();
        //construct order by ,
        // loop each item ,
        //      find any - for desc, trim -,
        //      otherwise ACS

        //filter by
        String[] filterBy = filter.getFilterBy();

        return incidentRepo.filterBy(sortBy, LocalDate.now());
    }

    private void audit(Incident incident) {
        incident.setUpdatedAt(LocalDateTime.now());
        incident.setUpdatedAt(LocalDateTime.now());
        incident.setUpdatedBy("");//get the current logged in user
    }
}
