package org.gromila.shopapp.dto;

public class PaymentDto {
    private Long id;
    private String paymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", payment status ='" + paymentStatus + '\'' +
                '}';
    }
}
