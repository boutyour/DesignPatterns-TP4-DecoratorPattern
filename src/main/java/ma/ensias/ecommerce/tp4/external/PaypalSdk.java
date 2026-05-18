package ma.ensias.ecommerce.tp4.external;

public class PaypalSdk {
    public String makePayment(String email, double amount) {
        if (email == null || !email.contains("@") || amount <= 0) {
            return null;
        }
        return "PAYPAL-" + Math.abs(email.hashCode()) + "-" + Math.round(amount);
    }
}
