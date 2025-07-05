package com.tecnoshop.stock.dto;

import java.util.List;

public class DecreaseStockRequest {
    private List<StockItem> items;

    public List<StockItem> getItems() {
        return items;
    }

    public void setItems(List<StockItem> items) {
        this.items = items;
    }
}
