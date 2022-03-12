package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.utilits.DataGenerator;
import ru.netology.utilits.Registration;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    void setUpEach() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    public static String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;


    @Test
    public void shouldReplanMeeting() {
        int planningDate = 3;
        int newDate = 8;
        val user = Registration.RegistrationUser.generateUser("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[class='notification__content']")
            .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(planningDate)));
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(newDate));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(3));
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(5));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(newDate)));

    }

    @Test
    public void shouldReplanMeetingNameWithYo() {
        int planningDate = 3;
        int newDate = 8;
        val user = Registration.RegistrationUser.generateUserWithYo("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(planningDate)));
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(newDate));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(3));
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(5));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(newDate)));
    }

    @Test
    public void shouldBeFailNameUS() {
        int planningDate = 3;
        int newDate = 8;
        val user = Registration.RegistrationUser.generateInvalidUserName("us");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."))
                .shouldHave(Condition.visible);

    }

    @Test
    public void shouldBeFailDateTwoDays() {
        int planningDate = 4;
        int newDate = 2;
        val user = Registration.RegistrationUser.generateUser("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(planningDate)));
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(newDate));
        $(byText("Запланировать")).click();
        $(byText("Заказ на выбранную дату невозможен"))
                .shouldHave(Condition.visible);
    }

    @Test
    public void shouldBeFailDateOneYear() {
        int planningDate = 3;
        int newDate = 365;
        val user = Registration.RegistrationUser.generateUser("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(15));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(planningDate)));
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(newDate));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(3));
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.visible, Duration.ofSeconds(5));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(newDate)));
    }

    @Test
    public void shouldBeFailCityWithoutDelivery() {
        int planningDate = 4;
        int newDate = 8;
        val user = Registration.RegistrationUser.generateInvalidUserCity("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Доставка в выбранный город недоступна"))
                .shouldBe(Condition.visible);

    }

    @Test
    public void shouldBeFailIncorrectPhoneCode() {
        int planningDate = 4;
        int newDate = 8;
        val user = Registration.RegistrationUser.generateInvalidUserPhone("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Телефон указан неверно"))
                .shouldHave(Condition.visible);
    }

    @Test
    public void shouldBeFailIncorrectPhone() {
        int planningDate = 4;
        int newDate = 8;
        val user = Registration.RegistrationUser.generateInvalidUserPhoneNumber("ru");
        $("[placeholder='Город']").setValue(user.getCity());
        $("[placeholder='Дата встречи']").sendKeys(deleteString);
        $("[placeholder='Дата встречи']").setValue(DataGenerator.generateDate(planningDate));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Телефон указан неверно"))
                .shouldHave(Condition.visible);
    }
}
