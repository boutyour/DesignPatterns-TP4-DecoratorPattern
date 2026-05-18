package ma.ensias.ecommerce.tp4.decorator;

import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;

public class ServiceFeePaymentDecorator extends PaymentGatewayDecorator {
    private final double fixedFee;
    private final double percentageFee;

    public ServiceFeePaymentDecorator(PaymentGateway wrappedGateway, double fixedFee, double percentageFee) {
        super(wrappedGateway);
        this.fixedFee = fixedFee;
        this.percentageFee = percentageFee;
    }

    @Override
    public double estimateFee(CheckoutContext context) {
        // TODO Ajouter les frais de service au coût estimé du composant décoré.
        return wrapped().estimateFee(context);
    }

    @Override
    public PaymentResult pay(CheckoutContext context) {
        // TODO En cas de paiement réussi, enrichir le message avec les frais ajoutés.
        PaymentResult result = wrapped().pay(context);
        return result;
    }
}
