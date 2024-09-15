package com.goshier.kopos.response;

import com.goshier.kopos.model.ids.AgentId;
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
