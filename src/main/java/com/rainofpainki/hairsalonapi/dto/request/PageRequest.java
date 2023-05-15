package com.rainofpainki.hairsalonapi.dto.request;

import org.springframework.data.domain.Sort;

public class PageRequest {
    private int page = 1;
    private int size = 10;
    private Sort sort = Sort.unsorted();

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, sort);
    }
}
