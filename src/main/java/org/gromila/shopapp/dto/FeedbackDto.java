package org.gromila.shopapp.dto;

public class FeedbackDto {
    private Long id;
    private String text;
    private Long itemId;
    private Integer stars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "FeedbackDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", itemId=" + itemId +
                ", stars=" + stars +
                '}';
    }
}

