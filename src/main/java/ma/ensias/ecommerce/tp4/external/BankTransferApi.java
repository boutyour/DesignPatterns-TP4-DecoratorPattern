package ma.ensias.ecommerce.tp4.external;

public class BankTransferApi {
    public boolean executeTransfer(String rib, double amount, String orderReference) {
        return rib != null
            && !rib.isBlank()
            && amount >= 100
            && orderReference != null
            && !orderReference.isBlank();
    }
}
