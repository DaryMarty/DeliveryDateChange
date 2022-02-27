package ru.netology.utilits;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@RequiredArgsConstructor

public class DataGenerator {

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String city = faker.options().option("Москва", "Санкт-Петербург", "Казань", "Ижевск", "Самара", "Нижний Новгород", "Уфа", "Екатеринбург", "Краснодар");
        return city;
    }

    public static String generateInvalidCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String city = faker.options().option("Сарапул", "Тольятти", "Альметьевск", "Зеленоград", "Глазов", "Дзержинск", "Норильск");
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generateNameWithYo(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName().concat("ё");
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+79#########");
        return phone;
    }

    public static String generateInvalidPhoneCode(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("###########");
        return phone;
    }

    public static String generateInvalidPhoneNumberOfCharacters(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+79######");
        return phone;
    }

    public static String generateDate(int days){
        Faker faker = new Faker(new Locale("ru"));
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static class Registration {

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            return (UserInfo) user;
        }

    }

    public static class RegistrationNameWithYo {

        private RegistrationNameWithYo() {
        }

        public static UserInfo generateUserWithYo(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateNameWithYo(locale), generatePhone(locale));
            return (UserInfo) user;
        }

    }

    public static class InvalidRegistrationCity {

        private InvalidRegistrationCity() {
        }

        public static UserInfo generateInvalidUser(String locale) {
            UserInfo user = new UserInfo(generateInvalidCity(locale), generateName(locale), generatePhone(locale));
            return (UserInfo) user;
        }
    }

    public static class InvalidRegistrationName {

        private InvalidRegistrationName() {
        }

        public static UserInfo generateInvalidUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateName("us"), generatePhone(locale));
            return (UserInfo) user;
        }
    }

    public static class InvalidRegistrationPhoneCode {

        private InvalidRegistrationPhoneCode() {
        }

        public static UserInfo generateInvalidUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generateInvalidPhoneCode(locale));
            return (UserInfo) user;
        }
    }

    public static class InvalidRegistrationPhoneNumberOfCharacters {

        private InvalidRegistrationPhoneNumberOfCharacters() {
        }

        public static UserInfo generateInvalidUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generateInvalidPhoneNumberOfCharacters(locale));
            return (UserInfo) user;
        }
    }

}

