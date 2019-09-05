package com.springboot.district.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

/**
 * Created by Cher on 2019-09-05
 */
@ToString
public class ExtraPageImpl<T, R> extends PageImpl<T> {
    @Getter
    private R extra;

    public ExtraPageImpl(List<T> content, R extra, Pageable pageable, long total) {
        super(content, pageable, total);
        this.extra = extra;
    }

    public ExtraPageImpl(List<T> content, R extra) {
        super(content);
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExtraPageImpl<?, ?> extraPage = (ExtraPageImpl<?, ?>) o;
        return Objects.equals(extra, extraPage.extra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extra);
    }
}