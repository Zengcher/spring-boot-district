package com.springboot.district.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

/**
 * Created by Cher on 2019-09-05
 */
@Getter
@ToString
@EqualsAndHashCode
@ApiModel
public class Pagination {
    @ApiModelProperty(value = "当前页码", position = 1, example = "1")
    private final int page;

    @ApiModelProperty(value = "每页显示数量", position = 2, example = "10")
    private final int size;

    @ApiModelProperty(value = "总数据量大小", position = 3, example = "20")
    private final long total;

    @ApiModelProperty(value = "最后一页页码", position = 4, example = "2")
    private final int last;

    public Pagination(int page, int size, long total, int last) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.last = last;
    }

    public static Pagination of(Page page) {
        return new Pagination(page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}