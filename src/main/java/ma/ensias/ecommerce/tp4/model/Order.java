package ma.ensias.ecommerce.tp4.model;

public class Order {
    private final String reference;
    private final double amount;
    private final Customer customer;

    public Order(String reference, double amount, Customer customer) {
        this.reference = reference;
        this.amount = amount;
        this.customer = customer;
    }

    public String getReference() {
        return reference;
    }

    public double getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }
}
