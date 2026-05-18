package ma.ensias.ecommerce.tp4.adapter;

import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public interface PaymentGateway {
    boolean supports(CheckoutContext context);

    double estimateFee(CheckoutContext context);

    int estimateConfirmationDelayMinutes(CheckoutContext context);

    int getSecurityScore();

    PaymentResult pay(CheckoutContext context);

    String getProviderName();
}
