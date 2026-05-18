package ma.ensias.ecommerce.tp4.adapter;

import ma.ensias.ecommerce.tp4.external.MobileWalletClient;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class MobileWalletAdapter implements PaymentGateway {
    private final MobileWalletClient mobileWalletClient;

    public MobileWalletAdapter(MobileWalletClient mobileWalletClient) {
        this.mobileWalletClient = mobileWalletClient;
    }

    @Override
    public boolean supports(CheckoutContext context) {
        return "MA".equalsIgnoreCase(context.getOrder().getCustomer().getCountryCode())
            && context.getOrder().getAmount() > 0
            && context.getOrder().getAmount() <= 3000
            && context.getWalletPhoneNumber() != null
            && context.getWalletPhoneNumber().startsWith("+212")
            && context.getWalletOperatorCode() != null
            && !context.getWalletOperatorCode().isBlank();
    }

    @Override
    public double estimateFee(CheckoutContext context) {
        return round(2.0 + context.getOrder().getAmount() * 0.012);
    }

    @Override
    public int estimateConfirmationDelayMinutes(CheckoutContext context) {
        return 1;
    }

    @Override
    public int getSecurityScore() {
        return 6;
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        int amountInCents = (int) Math.round(context.getOrder().getAmount() * 100);
        int statusCode = mobileWalletClient.sendMoney(
            context.getWalletPhoneNumber(),
            amountInCents,
            context.getWalletOperatorCode()
        );
        if (statusCode == 0) {
            return PaymentResult.success(getProviderName(), "Paiement wallet confirmé.");
        }
        return PaymentResult.failure(
            getProviderName(),
            "Paiement wallet refusé avec code " + statusCode + "."
        );
    }

    @Override
    public String getProviderName() {
        return "MobileWalletClient";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
