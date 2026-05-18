package ma.ensias.ecommerce.tp4.model;

public class Customer {
    private final String id;
    private final String fullName;
    private final String email;
    private final String countryCode;
    private final String phoneNumber;

    public Customer(String id, String fullName, String email, String countryCode, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
