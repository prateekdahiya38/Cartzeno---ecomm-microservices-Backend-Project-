package com.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
public class ExceptionResponseDto {
    private String message;
    private ExceptionStatus status;
}
