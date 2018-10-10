import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty gorevUnvan;

    public Person(String firstName, String lastName, String gorevUnvan) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.gorevUnvan = new SimpleStringProperty(gorevUnvan);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getGorevUnvan() {
        return gorevUnvan.get();
    }

    public SimpleStringProperty gorevUnvanProperty() {
        return gorevUnvan;
    }

    public void setGorevUnvan(String gorevUnvan) {
        this.gorevUnvan.set(gorevUnvan);
    }
}
