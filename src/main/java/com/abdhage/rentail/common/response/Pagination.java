package com.abdhage.rentail.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    private final String nextCursor;
    private final String currentCursor;
    private final String prevCursor;
    private final Integer pageSize;
    private final Integer totalResults;
    private final Integer totalRecords;
}
