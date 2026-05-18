package ma.ensias.ecommerce.tp4.decorator;

import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class FraudCheckPaymentDecorator extends PaymentGatewayDecorator {
    private final double suspiciousAmountLimit;

    public FraudCheckPaymentDecorator(PaymentGateway wrappedGateway, double suspiciousAmountLimit) {
        super(wrappedGateway);
        this.suspiciousAmountLimit = suspiciousAmountLimit;
    }

    @Override
    public boolean supports(CheckoutContext context) {
        // TODO Refuser le support si le montant dépasse la limite anti-fraude.
        return wrapped().supports(context);
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        // TODO Bloquer le paiement si le montant dépasse suspiciousAmountLimit.
        return wrapped().pay(context);
    }

    @Override
    public int getSecurityScore() {
        // TODO Augmenter légèrement le score de sécurité grâce au contrôle anti-fraude.
        return wrapped().getSecurityScore();
    }
}
