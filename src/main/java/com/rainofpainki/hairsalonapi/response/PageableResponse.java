package com.rainofpainki.hairsalonapi.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
public class PageableResponse extends BasicResponse {
    protected Page data;
}
