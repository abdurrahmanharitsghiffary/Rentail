package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodation.AccommodationResponse;
import com.abdhage.rentail.accommodation.dto.CreateAccommodationDTO;
import com.abdhage.rentail.accommodation.dto.UpdateAccommodationDTO;
import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accommodations/{accommodationId}")
public class AccommodationAgentController {

    private final AccommodationAgentService accommodationAgentService;

    public AccommodationAgentController(AccommodationAgentService accommodationAgentService) {
        this.accommodationAgentService = accommodationAgentService;
    }

    @GetMapping
    public List<AccommodationAgentResponse> findAll(@PathVariable("accommodationId") UUID id) {
        return accommodationAgentService.findAll();
    }

    @GetMapping("/agents/{agentId}")
    public AccommodationAgentResponse findOne(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId) {
        return accommodationAgentService.findOne(new AgentId(accommodationId, agentId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccommodationAgentResponse create(@PathVariable("accommodationId") UUID accommodationId, @Valid @RequestBody CreateAgentDTO dto) {
        dto.setAccommodationId(accommodationId);
        return accommodationAgentService.create(dto);
    }


    @PatchMapping("/agents/{agentId}")
    public AccommodationAgentResponse update(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId, @Valid @RequestBody UpdateAgentDTO dto) {
        return accommodationAgentService.update(new AgentId(accommodationId, agentId), dto);
    }

    @DeleteMapping("/agents/{agentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId) {
        accommodationAgentService.destroy(new AgentId(accommodationId, agentId));
    }
}
