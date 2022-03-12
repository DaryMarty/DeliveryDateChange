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

        public static DataGenerator.UserInfo generateUserWithYo(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateNameWithYo(locale), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }

        public static DataGenerator.UserInfo generateInvalidUserCity(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateInvalidCity(locale), generateName(locale), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }

        public static DataGenerator.UserInfo generateInvalidUserName(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName("us"), generatePhone(locale));
            return (DataGenerator.UserInfo) user;
        }

        public static DataGenerator.UserInfo generateInvalidUserPhone(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName(locale), generateInvalidPhoneCode(locale));
            return (DataGenerator.UserInfo) user;
        }

        public static DataGenerator.UserInfo generateInvalidUserPhoneNumber(String locale) {
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(generateCity(locale), generateName(locale), generateInvalidPhoneNumberOfCharacters(locale));
            return (DataGenerator.UserInfo) user;
        }
    }
}
