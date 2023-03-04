package com.yomabank.profileservice.datatransferobject.mapper;

import com.yomabank.profileservice.datatransferobject.global.ResponsePaginationBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ModelMapper modelMapper;
    @Autowired
    Mapper(ModelMapper mapper) {
        this.modelMapper = mapper;
    }
    public <T> ResponsePaginationBody  convertPageableToResponsePaginationBody(Page<T> page) {
        ResponsePaginationBody dto = modelMapper.map(page, ResponsePaginationBody.class);
        dto.setLast(page.isLast());
        dto.setFirst(page.isFirst());
        dto.setHasNext(page.hasNext());
        dto.setHasPrevious(page.hasPrevious());
        return dto;
    }
}
