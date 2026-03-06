package org.gromila.shopapp.dto;

import org.gromila.shopapp.entity.User;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private User user;
    private List<OrderDetailsDto> orderDetails = new ArrayList<>();
    private List<PaymentDto> payments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetailsDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsDto> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<PaymentDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDto> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", user=" + user +
                ", orderDetails=" + orderDetails +
                ", payments=" + payments +
                '}';
    }
}