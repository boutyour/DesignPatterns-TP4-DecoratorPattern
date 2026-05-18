package ma.ensias.ecommerce.tp4.decorator;

import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class RetryPaymentDecorator extends PaymentGatewayDecorator {
    private final int maxAttempts;

    public RetryPaymentDecorator(PaymentGateway wrappedGateway, int maxAttempts) {
        super(wrappedGateway);
        this.maxAttempts = Math.max(1, maxAttempts);
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        PaymentResult lastResult = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            lastResult = wrapped().pay(context);
            if (lastResult.isSuccess()) {
                return lastResult;
            }
            System.out.println("[RETRY] Tentative " + attempt + " échouée sur " + getProviderName());
        }
        return lastResult;
    }
}
