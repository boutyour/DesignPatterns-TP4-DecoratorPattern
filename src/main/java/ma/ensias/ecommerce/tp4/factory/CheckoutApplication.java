package ma.ensias.ecommerce.tp4.factory;

import java.util.List;
import ma.ensias.ecommerce.tp4.CheckoutService;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;
import ma.ensias.ecommerce.tp4.strategy.GatewaySelectionStrategy;

public abstract class CheckoutApplication {
    private final CheckoutService checkoutService;

    protected CheckoutApplication(GatewaySelectionStrategy selectionStrategy) {
        this.checkoutService = new CheckoutService(selectionStrategy);
    }

    public final PaymentResult processCheckout(CheckoutContext context) {
        System.out.println("Boutique : " + getStoreName());
        List<PaymentGateway> gateways = createAvailableGateways();
        System.out.println("Passerelles disponibles : " + gateways.size());
        return checkoutService.checkout(context, gateways);
    }

    public final List<PaymentGateway> createAvailableGateways() {
        return createGateways().stream()
            .map(this::decorateGateway)
            .toList();
    }

    public void setSelectionStrategy(GatewaySelectionStrategy selectionStrategy) {
        checkoutService.setSelectionStrategy(selectionStrategy);
    }

    public abstract String getStoreName();

    protected abstract List<PaymentGateway> createGateways();

    protected PaymentGateway decorateGateway(PaymentGateway gateway) {
        return gateway;
    }
}
