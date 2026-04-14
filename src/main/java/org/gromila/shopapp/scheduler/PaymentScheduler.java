package org.gromila.shopapp.scheduler;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentScheduler {
    private final PaymentService paymentService;

    @Value("${payment.expiration}")
    private long minutes;

    @Scheduled(cron = "0 * * * * *")
    public void checkAndTimeoutPayments() {
        paymentService.timeOutInProcessPayments(minutes);
    }
}