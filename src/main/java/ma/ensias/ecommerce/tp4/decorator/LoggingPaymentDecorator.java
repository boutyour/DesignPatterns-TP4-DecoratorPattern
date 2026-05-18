package ma.ensias.ecommerce.tp4.decorator;

import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class LoggingPaymentDecorator extends PaymentGatewayDecorator {
    public LoggingPaymentDecorator(PaymentGateway wrappedGateway) {
        super(wrappedGateway);
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        // TODO Ajouter une trace avant et après l'appel au composant décoré.
        // Le décorateur doit ensuite retourner le résultat du paiement.
        return wrapped().pay(context);
    }
}
