package com.abdhage.rentail.common.response;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ApiPaginatedResponse<T> extends ApiResponse<List<T>> {
    private Pagination meta;

    public ApiPaginatedResponse(List<T> data, Integer status) {
        super(data, status, null);
    }

    public void setMetadata(Pagination paginated) {
        this.meta = paginated;
    }
}
