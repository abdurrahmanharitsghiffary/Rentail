package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.DeleteAgentManyDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentManyDTO;
import com.abdhage.rentail.accommodationagent.model.AgentId;
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

    @GetMapping("accommodations/{accommodationId}/agents")
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

    @RequestMapping(path = "accommodations/{id}/agents", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public int updateMany(@PathVariable UUID id, @RequestBody List<@Valid UpdateAgentManyDTO> dto) {
        return accommodationAgentService.updateMany(id, dto);
    }

    @DeleteMapping("accommodations/{id}/agents")
    public int destroyMany(@PathVariable UUID id, @Valid @RequestBody DeleteAgentManyDTO dto) {
        return accommodationAgentService.destroyMany(id, dto.getIds());
    }

    @DeleteMapping("accommodations/{accommodationId}/agents/{agentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("accommodationId") UUID accommodationId, @PathVariable("agentId") UUID agentId) {
        accommodationAgentService.destroy(new AgentId(accommodationId, agentId));
    }
}
