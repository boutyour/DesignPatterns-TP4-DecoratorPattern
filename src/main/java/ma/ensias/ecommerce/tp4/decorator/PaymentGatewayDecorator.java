package ma.ensias.ecommerce.tp4.decorator;

import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public abstract class PaymentGatewayDecorator implements PaymentGateway {
    private final PaymentGateway wrappedGateway;

    protected PaymentGatewayDecorator(PaymentGateway wrappedGateway) {
        this.wrappedGateway = wrappedGateway;
    }

    public PaymentGateway getWrappedGateway() {
        return wrappedGateway;
    }

    @Override
    public boolean supports(CheckoutContext context) {
        return wrappedGateway.supports(context);
    }

    @Override
    public double estimateFee(CheckoutContext context) {
        return wrappedGateway.estimateFee(context);
    }

    @Override
    public int estimateConfirmationDelayMinutes(CheckoutContext context) {
        return wrappedGateway.estimateConfirmationDelayMinutes(context);
    }

    @Override
    public int getSecurityScore() {
        return wrappedGateway.getSecurityScore();
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        return wrappedGateway.pay(context);
    }

    @Override
    public String getProviderName() {
        return wrappedGateway.getProviderName();
    }

    protected PaymentGateway wrapped() {
        return wrappedGateway;
    }

    protected double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
