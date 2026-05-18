package ma.ensias.ecommerce.tp4.decorator;

import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class CashbackPaymentDecorator extends PaymentGatewayDecorator {
    private final double cashbackRate;

    public CashbackPaymentDecorator(PaymentGateway wrappedGateway, double cashbackRate) {
        super(wrappedGateway);
        this.cashbackRate = cashbackRate;
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        // TODO En cas de paiement réussi, enrichir le message avec le cashback calculé.
        PaymentResult result = wrapped().pay(context);
        return result;
    }
}
