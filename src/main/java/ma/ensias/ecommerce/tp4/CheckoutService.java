package ma.ensias.ecommerce.tp4;

import java.util.List;
import ma.ensias.ecommerce.tp4.adapter.PaymentGateway;
import ma.ensias.ecommerce.tp4.model.CheckoutContext;
import ma.ensias.ecommerce.tp4.model.PaymentResult;
import ma.ensias.ecommerce.tp4.strategy.GatewaySelectionStrategy;

public class CheckoutService {
    private GatewaySelectionStrategy selectionStrategy;

    public CheckoutService(GatewaySelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void setSelectionStrategy(GatewaySelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public PaymentResult checkout(CheckoutContext context, List<PaymentGateway> gateways) {
        System.out.println("Commande : " + context.getOrder().getReference());
        System.out.println("Client : " + context.getOrder().getCustomer().getFullName());
        System.out.println("Stratégie active : " + selectionStrategy.getName());

        PaymentGateway selectedGateway = selectionStrategy.selectGateway(context, gateways);

        if (selectedGateway == null) {
            PaymentResult result = PaymentResult.failure(
                "Aucune passerelle compatible avec le contexte fourni."
            );
            System.out.println("Résultat : " + result.getMessage());
            return result;
        }

        System.out.println("Passerelle sélectionnée : " + selectedGateway.getProviderName());
        System.out.println("Frais estimés : " + selectedGateway.estimateFee(context) + " MAD");
        System.out.println(
            "Délai estimé : " + selectedGateway.estimateConfirmationDelayMinutes(context) + " min"
        );
        System.out.println("Score de sécurité : " + selectedGateway.getSecurityScore() + "/10");

        PaymentResult result = selectedGateway.pay(context);
        System.out.println(result.isSuccess() ? "Résultat : paiement validé." : "Résultat : paiement refusé.");
        System.out.println("Détail : " + result.getMessage());
        return result;
    }
}
