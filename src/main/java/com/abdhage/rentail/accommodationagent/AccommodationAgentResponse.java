package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.common.response.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AccommodationAgentResponse extends BaseResponse<AgentId> {
}
