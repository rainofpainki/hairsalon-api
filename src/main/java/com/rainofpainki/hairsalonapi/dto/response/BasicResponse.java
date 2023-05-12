package com.rainofpainki.hairsalonapi.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
public abstract class BasicResponse{
    protected Integer code;
    protected HttpStatus httpStatus;
    protected String message;
}
