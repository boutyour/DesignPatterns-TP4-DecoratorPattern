package ma.ensias.ecommerce.tp4.model;

public class PaymentResult {
    private final boolean success;
    private final String providerName;
    private final String message;

    public PaymentResult(boolean success, String providerName, String message) {
        this.success = success;
        this.providerName = providerName;
        this.message = message;
    }

    public static PaymentResult success(String providerName, String message) {
        return new PaymentResult(true, providerName, message);
    }

    public static PaymentResult failure(String providerName, String message) {
        return new PaymentResult(false, providerName, message);
    }

    public static PaymentResult failure(String message) {
        return new PaymentResult(false, "Aucune", message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getMessage() {
        return message;
    }
}
