package com.rainofpainki.hairsalonapi.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
public class DataResponse extends BasicResponse {
    Integer count;
    List<Object> data;
}
