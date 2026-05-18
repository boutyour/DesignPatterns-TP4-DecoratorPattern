package ma.ensias.ecommerce.tp4.strategy;

import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;

public interface GatewaySelectionStrategy {
    PaymentGateway selectGateway(CheckoutContext context, List<PaymentGateway> gateways);

    String getName();
}
