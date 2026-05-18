package ma.ensias.ecommerce.tp4.factory;

import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.adapter.PaypalAdapter;
import ma.ensias.ecommerce.tp4.decorator.LoggingPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.RetryPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.ServiceFeePaymentDecorator;
import ma.ensias.ecommerce.tp4.external.PaypalSdk;
import ma.ensias.ecommerce.tp4.strategy.GatewaySelectionStrategy;

public class AfricaCheckoutApplication extends CheckoutApplication {
    public AfricaCheckoutApplication(GatewaySelectionStrategy selectionStrategy) {
        super(selectionStrategy);
    }

    @Override
    public String getStoreName() {
        return "Boutique Afrique";
    }

    @Override
    protected List<PaymentGateway> createGateways() {
        return List.of(
            new PaypalAdapter(new PaypalSdk())
        );
    }

    @Override
    protected PaymentGateway decorateGateway(PaymentGateway gateway) {
        // TODO Extension : ajouter frais de service, retry, puis logging.
        return gateway;
    }
}
