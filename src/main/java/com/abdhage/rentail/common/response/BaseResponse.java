package com.abdhage.rentail.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseResponse<ID> {
    private ID id;
    private Date createdAt;
    private Date updatedAt;
}
