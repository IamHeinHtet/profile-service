package com.yomabank.profileservice.datatransferobject.global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePaginationBody<T> extends ResponseBody<T> {
    private boolean isLast;
    private boolean isFirst;
    private boolean hasNext;
    private boolean hasPrevious;
    private int totalPages;
    private long totalElements;
}
