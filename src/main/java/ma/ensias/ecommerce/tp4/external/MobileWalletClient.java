package ma.ensias.ecommerce.tp4.external;

public class MobileWalletClient {
    public int sendMoney(String phoneNumber, int amountInCents, String operatorCode) {
        if (phoneNumber == null || !phoneNumber.startsWith("+212")) {
            return 11;
        }
        if (amountInCents <= 0 || amountInCents > 300000) {
            return 12;
        }
        if (operatorCode == null || operatorCode.isBlank()) {
            return 13;
        }
        return 0;
    }
}
