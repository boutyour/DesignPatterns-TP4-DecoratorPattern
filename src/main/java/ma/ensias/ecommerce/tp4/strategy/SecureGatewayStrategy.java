package ma.ensias.ecommerce.tp4.strategy;

import java.util.Comparator;
import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;

public class SecureGatewayStrategy implements GatewaySelectionStrategy {
    @Override
    public PaymentGateway selectGateway(CheckoutContext context, List<PaymentGateway> gateways) {
        return gateways.stream()
            .filter(gateway -> gateway.supports(context))
            .max(
                Comparator
                    .comparingInt(PaymentGateway::getSecurityScore)
                    .thenComparingDouble(gateway -> -gateway.estimateFee(context))
            )
            .orElse(null);
    }

    @Override
    public String getName() {
        return "SecureGatewayStrategy";
    }
}
