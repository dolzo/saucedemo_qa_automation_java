package models;

import com.github.javafaker.Faker;

public class User {
    private final String name;
    private final String lastName;
    private final String postalCode;

    public User(){
        final var faker = new Faker();
        name = faker.name().firstName();
        lastName = faker.name().lastName();
        postalCode = faker.address().zipCode();

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
