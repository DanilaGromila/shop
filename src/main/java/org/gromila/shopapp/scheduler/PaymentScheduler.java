package org.gromila.shopapp.scheduler;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.service.PaymentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentScheduler {
    private final PaymentService paymentService;

    @Scheduled(fixedRate = 60000)
    public void checkAndTimeoutPayments() {
        paymentService.timeOutInProcessPayments();
    }
}