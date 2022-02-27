package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Value;
import com.github.javafaker.Faker;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.netology.utilits.DataGenerator;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.zip.DataFormatException;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
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
        val user = DataGenerator.Registration.generateUser("ru");
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
        val user = DataGenerator.RegistrationNameWithYo.generateUserWithYo("ru");
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
        val user = DataGenerator.InvalidRegistrationName.generateInvalidUser("us");
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
        val user = DataGenerator.Registration.generateUser("ru");
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
        val user = DataGenerator.Registration.generateUser("ru");
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
        val user = DataGenerator.InvalidRegistrationCity.generateInvalidUser("ru");
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
        val user = DataGenerator.InvalidRegistrationPhoneCode.generateInvalidUser("ru");
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
        val user = DataGenerator.InvalidRegistrationPhoneNumberOfCharacters.generateInvalidUser("ru");
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
