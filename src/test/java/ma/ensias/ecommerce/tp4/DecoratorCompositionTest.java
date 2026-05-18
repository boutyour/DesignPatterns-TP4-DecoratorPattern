package ma.ensias.ecommerce.tp4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.decorator.CashbackPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.FraudCheckPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.LoggingPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.PaymentGatewayDecorator;
import ma.ensias.ecommerce.tp4.decorator.RetryPaymentDecorator;
import ma.ensias.ecommerce.tp4.decorator.ServiceFeePaymentDecorator;
import ma.ensias.ecommerce.tp4.factory.AfricaCheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.CheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.EuropeCheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.MoroccoCheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.PremiumCheckoutApplication;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.Customer;
import ma.ensias.ecommerce.tp4.model.Order;
import ma.ensias.ecommerce.tp4.strategy.LowestCostStrategy;

public class DecoratorCompositionTest {
    public static void main(String[] args) {
        CheckoutContext context = createMoroccoContext(850.00);

        CheckoutApplication morocco = new MoroccoCheckoutApplication(new LowestCostStrategy());
        CheckoutApplication europe = new EuropeCheckoutApplication(new LowestCostStrategy());
        CheckoutApplication premium = new PremiumCheckoutApplication(new LowestCostStrategy());
        CheckoutApplication africa = new AfricaCheckoutApplication(new LowestCostStrategy());

        System.out.println("=== Mini-test Decorator : enrichissement des passerelles ===");

        List<PaymentGateway> moroccoGateways = morocco.createAvailableGateways();
        List<PaymentGateway> europeGateways = europe.createAvailableGateways();
        List<PaymentGateway> premiumGateways = premium.createAvailableGateways();
        List<PaymentGateway> africaGateways = africa.createAvailableGateways();

        printDecorators(morocco.getStoreName(), moroccoGateways);
        printDecorators(europe.getStoreName(), europeGateways);
        printDecorators(premium.getStoreName(), premiumGateways);
        printDecorators(africa.getStoreName(), africaGateways);

        checkAllContain(moroccoGateways, LoggingPaymentDecorator.class);
        checkAllContain(moroccoGateways, ServiceFeePaymentDecorator.class);

        checkAllContain(europeGateways, LoggingPaymentDecorator.class);
        checkAllContain(europeGateways, RetryPaymentDecorator.class);

        checkAllContain(premiumGateways, LoggingPaymentDecorator.class);
        checkAllContain(premiumGateways, CashbackPaymentDecorator.class);
        checkAllContain(premiumGateways, FraudCheckPaymentDecorator.class);
        checkAllContain(premiumGateways, ServiceFeePaymentDecorator.class);

        checkAllContain(africaGateways, LoggingPaymentDecorator.class);
        checkAllContain(africaGateways, RetryPaymentDecorator.class);
        checkAllContain(africaGateways, ServiceFeePaymentDecorator.class);

        PaymentGateway decoratedWallet = moroccoGateways.get(2);
        double feeWithDecorator = decoratedWallet.estimateFee(context);
        if (feeWithDecorator <= 12.20) {
            throw new AssertionError("Les frais de service doivent augmenter le coût estimé du wallet.");
        }

        checkNoDecoratorLogicInCheckoutService();

        System.out.println("Validation : les passerelles restent des PaymentGateway enrichies.");
        System.out.println("Tous les tests Decorator sont passés.");
    }

    private static CheckoutContext createMoroccoContext(double amount) {
        Customer customer = new Customer("C-T", "Test User", "test@example.com", "MA", "+212600000000");
        Order order = new Order("CMD-TEST", amount, customer);
        return new CheckoutContext(
            order,
            "RIB-MA-TEST",
            "test.paypal@example.com",
            customer.getPhoneNumber(),
            "M-WALLET",
            false,
            false
        );
    }

    private static void printDecorators(String storeName, List<PaymentGateway> gateways) {
        System.out.println("- " + storeName + " -> " + gateways.size() + " passerelle(s)");
        for (PaymentGateway gateway : gateways) {
            System.out.println("  * " + gateway.getProviderName() + " : " + describeChain(gateway));
        }
    }

    private static String describeChain(PaymentGateway gateway) {
        StringBuilder builder = new StringBuilder(gateway.getClass().getSimpleName());
        PaymentGateway current = gateway;
        while (current instanceof PaymentGatewayDecorator decorator) {
            current = decorator.getWrappedGateway();
            builder.append(" -> ").append(current.getClass().getSimpleName());
        }
        return builder.toString();
    }

    private static void checkAllContain(
        List<PaymentGateway> gateways,
        Class<? extends PaymentGatewayDecorator> decoratorClass
    ) {
        for (PaymentGateway gateway : gateways) {
            if (!containsDecorator(gateway, decoratorClass)) {
                throw new AssertionError(
                    "Décorateur attendu absent sur " + gateway.getProviderName()
                        + " : " + decoratorClass.getSimpleName()
                );
            }
        }
    }

    private static boolean containsDecorator(
        PaymentGateway gateway,
        Class<? extends PaymentGatewayDecorator> decoratorClass
    ) {
        PaymentGateway current = gateway;
        while (current instanceof PaymentGatewayDecorator decorator) {
            if (decoratorClass.isInstance(decorator)) {
                return true;
            }
            current = decorator.getWrappedGateway();
        }
        return false;
    }

    private static void checkNoDecoratorLogicInCheckoutService() {
        Path path = Path.of("src/main/java/ma/ensias/ecommerce/tp4/CheckoutService.java");
        if (!Files.exists(path)) {
            return;
        }

        try {
            String source = Files.readString(path);
            List<String> forbiddenTokens = List.of(
                "new LoggingPaymentDecorator",
                "new ServiceFeePaymentDecorator",
                "new FraudCheckPaymentDecorator",
                "new CashbackPaymentDecorator",
                "new RetryPaymentDecorator"
            );
            for (String token : forbiddenTokens) {
                if (source.contains(token)) {
                    throw new AssertionError("CheckoutService ne doit pas composer les décorateurs : " + token);
                }
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Impossible de lire CheckoutService.java", exception);
        }
    }
}
