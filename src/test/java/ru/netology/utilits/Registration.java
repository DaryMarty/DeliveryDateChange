package ru.netology.utilits;

import static ru.netology.utilits.DataGenerator.*;

public class Registration {

    public static class RegistrationUser {

        private RegistrationUser() {
        }

        public static DataGenerator.UserInfo generateUser(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }

    }

    public static class RegistrationNameWithYo {

        private RegistrationNameWithYo() {
        }

        public static DataGenerator.UserInfo generateUserWithYo(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateNameWithYo(locale), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }

    }

    public static class InvalidRegistrationCity {

        private InvalidRegistrationCity() {
        }

        public static DataGenerator.UserInfo generateInvalidUser(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateInvalidCity(locale), generateName(locale), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }
    }

    public static class InvalidRegistrationName {

        private InvalidRegistrationName() {
        }

        public static DataGenerator.UserInfo generateInvalidUser(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName("us"), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }
    }

    public static class InvalidRegistrationPhoneCode {

        private InvalidRegistrationPhoneCode() {
        }

        public static DataGenerator.UserInfo generateInvalidUser(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName(locale), generateInvalidPhoneCode(locale));
            return (DataGenerator.UserInfo) user;
        }
    }

    public static class InvalidRegistrationPhoneNumberOfCharacters {

        private InvalidRegistrationPhoneNumberOfCharacters() {
        }

        public static DataGenerator.UserInfo generateInvalidUser(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName(locale), generateInvalidPhoneNumberOfCharacters(locale));
            return (DataGenerator.UserInfo) user;
        }
    }
}
