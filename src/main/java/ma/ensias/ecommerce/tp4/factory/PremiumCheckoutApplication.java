package ma.ensias.ecommerce.tp4.factory;

import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.BankTransferAdapter;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.adapter.PaypalAdapter;
import ma.ensias.ecommerce.tp4.decorator.CashbackPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.FraudCheckPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.LoggingPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.ServiceFeePaymentDecorator;
import ma.ensias.ecommerce.tp4.external.BankTransferApi;
import ma.ensias.ecommerce.tp4.external.PaypalSdk;
import ma.ensias.ecommerce.tp4.strategy.GatewaySelectionStrategy;

public class PremiumCheckoutApplication extends CheckoutApplication {
    public PremiumCheckoutApplication(GatewaySelectionStrategy selectionStrategy) {
        super(selectionStrategy);
    }

    @Override
    public String getStoreName() {
        return "Boutique Premium";
    }

    @Override
    protected List<PaymentGateway> createGateways() {
        return List.of(
            new BankTransferAdapter(new BankTransferApi()),
            new PaypalAdapter(new PaypalSdk())
        );
    }

    @Override
    protected PaymentGateway decorateGateway(PaymentGateway gateway) {
        // TODO Enrichir chaque passerelle Premium avec :
        // 1. ServiceFeePaymentDecorator
        // 2. FraudCheckPaymentDecorator
        // 3. CashbackPaymentDecorator
        // 4. LoggingPaymentDecorator
        return gateway;
    }
}
