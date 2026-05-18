package ma.ensias.ecommerce.tp4.adapter;

import ma.ensias.ecommerce.tp4.external.BankTransferApi;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class BankTransferAdapter implements PaymentGateway {
    private final BankTransferApi bankTransferApi;

    public BankTransferAdapter(BankTransferApi bankTransferApi) {
        this.bankTransferApi = bankTransferApi;
    }

    @Override
    public boolean supports(CheckoutContext context) {
        return "MA".equalsIgnoreCase(context.getOrder().getCustomer().getCountryCode())
            && context.getOrder().getAmount() >= 100
            && context.getRib() != null
            && !context.getRib().isBlank();
    }

    @Override
    public double estimateFee(CheckoutContext context) {
        return round(8.0 + context.getOrder().getAmount() * 0.008);
    }

    @Override
    public int estimateConfirmationDelayMinutes(CheckoutContext context) {
        return 180;
    }

    @Override
    public int getSecurityScore() {
        return 9;
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        boolean success = bankTransferApi.executeTransfer(
            context.getRib(),
            context.getOrder().getAmount(),
            context.getOrder().getReference()
        );
        if (success) {
            return PaymentResult.success(getProviderName(), "Virement bancaire accepté.");
        }
        return PaymentResult.failure(getProviderName(), "Le virement bancaire a été refusé.");
    }

    @Override
    public String getProviderName() {
        return "BankTransferApi";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
