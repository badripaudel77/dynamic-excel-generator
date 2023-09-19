package amigo.file.generator.utils;

import com.github.javafaker.Faker;

/**
 * Uses java faker library to generate dummy data, also uses the prefix, postfix and value attribute used by the user
 */
public class DataFaker {
    Faker faker;
    public DataFaker() {
        faker = new Faker();
    }

    public String getRandomPhoneNumber() {
        return this.faker.phoneNumber().phoneNumber();
    }

    public String getName() {
        return this.faker.name().fullName();
    }
}
