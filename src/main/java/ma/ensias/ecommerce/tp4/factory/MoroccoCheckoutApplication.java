package ma.ensias.ecommerce.tp4.factory;

import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.BankTransferAdapter;
import ma.ensias.ecommerce.tp4.adapter.MobileWalletAdapter;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.adapter.PaypalAdapter;
import ma.ensias.ecommerce.tp4.decorator.LoggingPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.ServiceFeePaymentDecorator;
import ma.ensias.ecommerce.tp4.external.BankTransferApi;
import ma.ensias.ecommerce.tp4.external.MobileWalletClient;
import ma.ensias.ecommerce.tp4.external.PaypalSdk;
import ma.ensias.ecommerce.tp4.strategy.GatewaySelectionStrategy;

public class MoroccoCheckoutApplication extends CheckoutApplication {
    public MoroccoCheckoutApplication(GatewaySelectionStrategy selectionStrategy) {
        super(selectionStrategy);
    }

    @Override
    public String getStoreName() {
        return "Boutique Maroc";
    }

    @Override
    protected List<PaymentGateway> createGateways() {
        return List.of(
            new BankTransferAdapter(new BankTransferApi()),
            new PaypalAdapter(new PaypalSdk()),
            new MobileWalletAdapter(new MobileWalletClient())
        );
    }

    @Override
    protected PaymentGateway decorateGateway(PaymentGateway gateway) {
        // TODO Enrichir chaque passerelle avec :
        // 1. ServiceFeePaymentDecorator
        // 2. LoggingPaymentDecorator
        return gateway;
    }
}
