package com.example.gocoffee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDtoResponse<T> {
    private int page;
    private int size;
    private long totalPages;
    private long totalElements;
    private List<T> data;
    public static <T> PageDtoResponse<T> from(int page, int size, long totalElements, List<T> data) {
        long totalPages = totalElements / size;
        if (totalElements % size != 0) {
            totalPages++;
        }
        return PageDtoResponse.<T>builder()
                .page(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .data(data)
                .build();
    }
}
