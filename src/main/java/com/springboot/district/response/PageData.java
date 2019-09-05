package com.springboot.district.response;

import lombok.Data;

import java.util.List;

/**
 * Created by Cher on 2019-09-05
 */
@Data
public class PageData<T, R> {

    private Pagination pagination;

    private List<T> content;

    private R extra;

    public PageData(Pagination pagination, List<T> content, R extra) {
        this.pagination = pagination;
        this.content = content;
        this.extra = extra;
    }
}
