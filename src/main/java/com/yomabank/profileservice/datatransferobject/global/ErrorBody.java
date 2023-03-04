package com.yomabank.profileservice.datatransferobject.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorBody {
    private String message;
    @JsonIgnore
    private HttpStatus httpStatus ;
}
