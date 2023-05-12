package com.rainofpainki.hairsalonapi.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
public class ResultResponse extends BasicResponse {
    protected Boolean result;
}
