package com.kongallis.forum.dto;

import java.util.List;

public class PaginationResponse {

    private List<UserDto> items;
    private Long total;

    public List<UserDto> getItems() {
        return items;
    }

    public void setItems(List<UserDto> items) {
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
