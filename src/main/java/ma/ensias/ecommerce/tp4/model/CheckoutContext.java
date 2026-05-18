package ma.ensias.ecommerce.tp4.model;

public class CheckoutContext {
    private final Order order;
    private final String rib;
    private final String paypalEmail;
    private final String walletPhoneNumber;
    private final String walletOperatorCode;
    private final boolean expressCheckout;
    private final boolean strongAuthenticationRequired;

    public CheckoutContext(
        Order order,
        String rib,
        String paypalEmail,
        String walletPhoneNumber,
        String walletOperatorCode,
        boolean expressCheckout,
        boolean strongAuthenticationRequired
    ) {
        this.order = order;
        this.rib = rib;
        this.paypalEmail = paypalEmail;
        this.walletPhoneNumber = walletPhoneNumber;
        this.walletOperatorCode = walletOperatorCode;
        this.expressCheckout = expressCheckout;
        this.strongAuthenticationRequired = strongAuthenticationRequired;
    }

    public Order getOrder() {
        return order;
    }

    public String getRib() {
        return rib;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public String getWalletPhoneNumber() {
        return walletPhoneNumber;
    }

    public String getWalletOperatorCode() {
        return walletOperatorCode;
    }

    public boolean isExpressCheckout() {
        return expressCheckout;
    }

    public boolean isStrongAuthenticationRequired() {
        return strongAuthenticationRequired;
    }
}
