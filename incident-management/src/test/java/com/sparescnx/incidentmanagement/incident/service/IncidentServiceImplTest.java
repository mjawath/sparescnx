package com.sparescnx.incidentmanagement.incident.service;

import com.sparescnx.incidentmanagement.incident.IncidentNotFoundException;
import com.sparescnx.incidentmanagement.incident.repo.IncidentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class IncidentServiceImplTest {

    @Mock
    private IncidentRepo incidentRepo;

    @InjectMocks
    private IncidentServiceImpl incidentService;

    @Test
    public void testShouldRetrieveIncidentById(){
        //Given
        Incident incident = new Incident();
        incident.setStatus(IncidentStatus.NEW);
        incident.setId("Test");
        when(incidentRepo.findById(incident.getId())).thenReturn(Optional.of(incident));

        //When
        Optional<Incident> byId = incidentService.findById(incident.getId());

        //Then
        assertNotNull(byId);
        assertTrue(byId.isPresent());
        verify(incidentRepo, times(1)).findById(incident.getId());
    }

    @Test
    public void testShouldFailWhenRetrieveIncidentNotFound(){
        //Given
        Incident incident = new Incident();
        incident.setStatus(IncidentStatus.NEW);
        incident.setId("Test");
        when(incidentRepo.findById(incident.getId())).thenReturn(Optional.empty());

        //When
        Optional<Incident> byId = incidentService.findById(incident.getId());

        //Then
        assertNotNull(byId);
        assertTrue(byId.isEmpty());
        verify(incidentRepo, times(1)).findById(incident.getId());
    }

    @Test
    public void testShouldSuccessfullyDeleteIncident() {
        //Given
        Incident incident = new Incident();
        incident.setStatus(IncidentStatus.NEW);
        incident.setId("Test");

        Incident incidentDeleted = Incident.builder().id("Test").status(IncidentStatus.DELETED).build();
        when(incidentRepo.findById(incident.getId())).thenReturn(Optional.of(incident));
        when(incidentRepo.save(any(Incident.class))).thenReturn(incidentDeleted);

        //When
        incidentService.delete(incident.getId());

        //Then
        assertEquals(incident.getStatus(), IncidentStatus.DELETED);
        verify(incidentRepo, times(1)).save(incident);
    }

    @Test
    public void testShouldFailWhenIncidentNotFoundWhileDeleting() {
        //Given
        Incident incident = new Incident();
        incident.setStatus(IncidentStatus.NEW);
        incident.setId("Test");

        when(incidentRepo.findById(incident.getId())).thenReturn(Optional.empty());

        //When
        assertThrows(IncidentNotFoundException.class, () -> incidentService.delete(incident.getId()));

        //Then
        verify(incidentRepo, times(0)).save(incident);
    }

}