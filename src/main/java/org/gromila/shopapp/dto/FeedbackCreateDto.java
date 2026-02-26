package org.gromila.shopapp.dto;

public class FeedbackCreateDto {
    private String text;
    private Integer stars;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "FeedbackCreateDto{" +
                ", text='" + text + '\'' +
                ", stars=" + stars +
                '}';
    }
}
