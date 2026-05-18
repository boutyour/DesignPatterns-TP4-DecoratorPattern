package ma.ensias.ecommerce.tp4.strategy;

import java.util.Comparator;
import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;

public class LowestCostStrategy implements GatewaySelectionStrategy {
    @Override
    public PaymentGateway selectGateway(CheckoutContext context, List<PaymentGateway> gateways) {
        return gateways.stream()
            .filter(gateway -> gateway.supports(context))
            .filter(gateway -> !context.isStrongAuthenticationRequired() || gateway.getSecurityScore() >= 7)
            .min(
                Comparator
                    .comparingDouble((PaymentGateway gateway) -> gateway.estimateFee(context))
                    .thenComparing(Comparator.comparingInt(PaymentGateway::getSecurityScore).reversed())
            )
            .orElse(null);
    }

    @Override
    public String getName() {
        return "LowestCostStrategy";
    }
}
