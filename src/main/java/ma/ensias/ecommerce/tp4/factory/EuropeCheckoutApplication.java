package ma.ensias.ecommerce.tp4.factory;

import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.adapter.PaypalAdapter;
import ma.ensias.ecommerce.tp4.decorator.LoggingPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.RetryPaymentDecorator;
import ma.ensias.ecommerce.tp4.external.PaypalSdk;
import ma.ensias.ecommerce.tp4.strategy.GatewaySelectionStrategy;

public class EuropeCheckoutApplication extends CheckoutApplication {
    public EuropeCheckoutApplication(GatewaySelectionStrategy selectionStrategy) {
        super(selectionStrategy);
    }

    @Override
    public String getStoreName() {
        return "Boutique Europe";
    }

    @Override
    protected List<PaymentGateway> createGateways() {
        return List.of(
            new PaypalAdapter(new PaypalSdk())
        );
    }

    @Override
    protected PaymentGateway decorateGateway(PaymentGateway gateway) {
        // TODO Enrichir chaque passerelle avec RetryPaymentDecorator puis LoggingPaymentDecorator.
        return gateway;
    }
}
