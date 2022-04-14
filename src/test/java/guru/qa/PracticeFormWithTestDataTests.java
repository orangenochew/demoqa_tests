package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;


public class PracticeFormWithTestDataTests {
    String firstName = "Taras",
            lastName = "Bulba",
            email = "taras@bulba.ua";
    String expectedFullName = format("%s %s",firstName,lastName);

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest(){
        open("/automation-practice-form");
        zoom(0.5);
        executeJavaScript("$('footer').remove()"); //убираем футер шоб кнопка влезла
        executeJavaScript("$('fixedban').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#gender-radio-1").doubleClick();
        $("#userNumber").setValue("8965462736");
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").sendKeys((Keys.CONTROL + "a")); //выделяем
        $("#dateOfBirthInput").sendKeys(Keys.SPACE);           // и затираем поле
        $("#dateOfBirthInput").setValue("10 May 1938").pressEnter();
        $("#subjectsInput").setValue("M").pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("Mikhail_Nesterov_001.jpg");
        $("#currentAddress").setValue("Velliangiri Foothills, Ishana Vihar Post," +
                "Coimbatore - 641 114");
        $("#state").click();
        $(byText("Rajasthan")).click(); //поиск по тексту так себе решение
        $("#city").click();
        $(byText("Jaipur")).click();
        $("#submit").click();

        //проверка результата
        $(".table-responsive").shouldHave(text(expectedFullName),
                text(email),text("Gender Male"),
                text("Mobile 8965462736"),text("Date of Birth 10 May,1938"),
                text("Subjects Maths"),text("Hobbies Music"),
                text("Picture Mikhail_Nesterov_001.jpg"),
                text("Address Velliangiri Foothills, Ishana Vihar Post,Coimbatore - 641 114"),
                text("State and City Rajasthan Jaipur"));
    }
}
