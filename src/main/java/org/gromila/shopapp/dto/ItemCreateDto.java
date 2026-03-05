package org.gromila.shopapp.dto;

public class ItemCreateDto {
    private String name;
    private Double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ItemCreateDto{" +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
