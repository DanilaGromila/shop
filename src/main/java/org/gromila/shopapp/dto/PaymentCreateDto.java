package org.gromila.shopapp.dto;

public class PaymentCreateDto {
    private String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentCreateDto{" +
                "paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
