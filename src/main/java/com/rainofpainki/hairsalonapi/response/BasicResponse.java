package com.rainofpainki.hairsalonapi.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
public abstract class BasicResponse{
    Integer code;
    HttpStatus httpStatus;
    String message;
}
