package ma.ensias.ecommerce.tp4.adapter;

import ma.ensias.ecommerce.tp4.external.PaypalSdk;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class PaypalAdapter implements PaymentGateway {
    private final PaypalSdk paypalSdk;

    public PaypalAdapter(PaypalSdk paypalSdk) {
        this.paypalSdk = paypalSdk;
    }

    @Override
    public boolean supports(CheckoutContext context) {
        return context.getPaypalEmail() != null
            && context.getPaypalEmail().contains("@")
            && context.getOrder().getAmount() > 0;
    }

    @Override
    public double estimateFee(CheckoutContext context) {
        return round(3.0 + context.getOrder().getAmount() * 0.029);
    }

    @Override
    public int estimateConfirmationDelayMinutes(CheckoutContext context) {
        return 5;
    }

    @Override
    public int getSecurityScore() {
        return 7;
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        String transactionId = paypalSdk.makePayment(
            context.getPaypalEmail(),
            context.getOrder().getAmount()
        );
        if (transactionId != null) {
            return PaymentResult.success(getProviderName(), "Paiement PayPal validé : " + transactionId);
        }
        return PaymentResult.failure(getProviderName(), "Le paiement PayPal a été refusé.");
    }

    @Override
    public String getProviderName() {
        return "PaypalSdk";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
