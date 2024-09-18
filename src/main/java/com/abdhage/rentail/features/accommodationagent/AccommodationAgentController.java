package com.abdhage.rentail.features.accommodationagent;

import com.abdhage.rentail.features.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.features.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.features.accommodationagent.model.AgentId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AccommodationAgentController {

    private final AccommodationAgentService accommodationAgentService;

    public AccommodationAgentController(AccommodationAgentService accommodationAgentService) {
        this.accommodationAgentService = accommodationAgentService;
    }

    @GetMapping("accommodations/{accommodationId}")
    public List<AccommodationAgentResponse> findAll(@PathVariable UUID accommodationId) {
        return accommodationAgentService.findAllByAccommodationId(accommodationId);
    }

    @GetMapping("accommodations/{accommodationId}/agents/{agentId}")
    public AccommodationAgentResponse findOne(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId) {
        return accommodationAgentService.findOne(new AgentId(accommodationId, agentId));
    }

    @PostMapping("agents")
    @ResponseStatus(HttpStatus.CREATED)
    public AccommodationAgentResponse create(@Valid @RequestBody CreateAgentDTO dto) {
        return accommodationAgentService.create(dto);
    }

    @PatchMapping("accommodations/{accommodationId}/agents/{agentId}")
    public AccommodationAgentResponse update(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId, @Valid @RequestBody UpdateAgentDTO dto) {
        return accommodationAgentService.update(new AgentId(accommodationId, agentId), dto);
    }

    @DeleteMapping("accommodations/{accommodationId}/agents/{agentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId) {
        accommodationAgentService.destroy(new AgentId(accommodationId, agentId));
    }
}
