package dev.akozel.cleaningtime.core.common.model;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedItems<T> {

    private List<T> items;
    private Long total;

    public static <T> PaginatedItems<T> of(List<T> items, Long count) {
        PaginatedItems<T> paginatedItems = new PaginatedItems<>();
        paginatedItems.setItems(items);
        paginatedItems.setTotal(count);
        return paginatedItems;
    }

}
