package ma.ensias.ecommerce.tp4;

import ma.ensias.ecommerce.tp4.factory.AfricaCheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.CheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.EuropeCheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.MoroccoCheckoutApplication;
import ma.ensias.ecommerce.tp4.factory.PremiumCheckoutApplication;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.Customer;
import ma.ensias.ecommerce.tp4.model.Order;
import ma.ensias.ecommerce.tp4.strategy.FastestConfirmationStrategy;
import ma.ensias.ecommerce.tp4.strategy.LowestCostStrategy;
import ma.ensias.ecommerce.tp4.strategy.SecureGatewayStrategy;

public class Main {
    public static void main(String[] args) {
        Customer localCustomer = new Customer(
                "C-301",
                "Nora El Idrissi",
                "nora@example.com",
                "MA",
                "+212612345678");
        Order localOrder = new Order("CMD-DEC-1001", 850.00, localCustomer);
        CheckoutContext localContext = new CheckoutContext(
                localOrder,
                "RIB-MA-123-0001",
                "nora.paypal@example.com",
                localCustomer.getPhoneNumber(),
                "M-WALLET",
                false,
                false);

        Customer premiumCustomer = new Customer(
                "C-302",
                "Yasmine Tazi",
                "yasmine@example.com",
                "MA",
                "+212699000111");
        Order premiumOrder = new Order("CMD-DEC-1002", 3200.00, premiumCustomer);
        CheckoutContext secureContext = new CheckoutContext(
                premiumOrder,
                "RIB-MA-777-2002",
                "yasmine.paypal@example.com",
                premiumCustomer.getPhoneNumber(),
                "M-WALLET",
                false,
                true);

        Customer internationalCustomer = new Customer(
                "C-303",
                "John Bernard",
                "john@example.fr",
                "FR",
                "+33699887766");
        Order internationalOrder = new Order("CMD-DEC-1003", 1450.00, internationalCustomer);
        CheckoutContext expressContext = new CheckoutContext(
                internationalOrder,
                "",
                "john.paypal@example.fr",
                internationalCustomer.getPhoneNumber(),
                "EU-WALLET",
                true,
                false);

        Customer blockedCustomer = new Customer(
                "C-304",
                "Samir Bennani",
                "samir@example.com",
                "MA",
                "+212600111222");
        Order blockedOrder = new Order("CMD-DEC-1004", 12500.00, blockedCustomer);
        CheckoutContext suspiciousContext = new CheckoutContext(
                blockedOrder,
                "RIB-MA-999-4004",
                "samir.paypal@example.com",
                blockedCustomer.getPhoneNumber(),
                "M-WALLET",
                false,
                true);

        CheckoutApplication moroccoCheckout = new MoroccoCheckoutApplication(new LowestCostStrategy());
        CheckoutApplication premiumCheckout = new PremiumCheckoutApplication(new SecureGatewayStrategy());
        CheckoutApplication europeCheckout = new EuropeCheckoutApplication(new FastestConfirmationStrategy());
        CheckoutApplication africaCheckout = new AfricaCheckoutApplication(new LowestCostStrategy());

        System.out.println("=== Scénario 1 : boutique Maroc avec logging et frais de service ===");
        moroccoCheckout.processCheckout(localContext);

        System.out.println();
        System.out.println("=== Scénario 2 : boutique Premium avec anti-fraude et cashback ===");
        premiumCheckout.processCheckout(secureContext);

        System.out.println();
        System.out.println("=== Scénario 3 : boutique Europe avec retry et logging ===");
        europeCheckout.processCheckout(expressContext);

        System.out.println();
        System.out.println("=== Scénario 4 : même boutique, stratégie changée à l'exécution ===");
        moroccoCheckout.setSelectionStrategy(new FastestConfirmationStrategy());
        moroccoCheckout.processCheckout(localContext);

        System.out.println();
        System.out.println("=== Scénario 5 : nouvelle boutique Afrique avec décorations propres ===");
        africaCheckout.processCheckout(expressContext);

        System.out.println();
        System.out.println("=== Scénario 6 : montant bloqué par le décorateur anti-fraude ===");
        premiumCheckout.processCheckout(suspiciousContext);
    }
}
