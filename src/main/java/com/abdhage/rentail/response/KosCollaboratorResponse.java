package com.abdhage.rentail.response;

import com.abdhage.rentail.model.ids.AgentId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KosCollaboratorResponse {
    private AgentId id;
}
