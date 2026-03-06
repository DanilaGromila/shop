package org.gromila.shopapp.dto;

public class OrderDetailsCreateDto {
    private Long itemId;
    private Integer quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetailsCreateDto{" +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
