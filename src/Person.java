import java.time.LocalDate;

public class Person {
    private String name;
    private String personalIdentityNumber;
    private LocalDate subscriptionPaymentDate;

    public Person(String personalIdentityNumber, String name, LocalDate subscriptionPaymentDate) {
        setName(name);
        setPersonalIdentityNumber(personalIdentityNumber);
        setSubscriptionPaymentDate(subscriptionPaymentDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public void setPersonalIdentityNumber(String personalIdentityNumber) {
        this.personalIdentityNumber = personalIdentityNumber;
    }

    public LocalDate getSubscriptionPaymentDate() {
        return subscriptionPaymentDate;
    }

    public void setSubscriptionPaymentDate(LocalDate subscriptionPaymentDate) {
        this.subscriptionPaymentDate = subscriptionPaymentDate;
    }

    @Override
    public String toString() {
        return String.format("Namn: %s \nPersonnummer: %s \nBetalade årsavgift: %s",
                name,personalIdentityNumber,subscriptionPaymentDate);

    }
}
