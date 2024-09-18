package com.abdhage.rentail.features.accommodationagent;

import com.abdhage.rentail.features.accommodationagent.model.AgentId;
import com.abdhage.rentail.common.response.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AccommodationAgentResponse extends BaseResponse<AgentId> {
}
