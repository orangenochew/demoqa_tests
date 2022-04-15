package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static utils.RandomUtils.getRandomString;


public class PracticeFormWithPageObjectsTests {

    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            currentAddress = faker.address().fullAddress(),
            userNumber = getRandomString(10),
            gender = faker.demographic().sex(),
            day = "11",
            month = "July",
            year = "1991",
            subject = "Maths",
            hobbie = "Music",
            img = "Mikhail_Nesterov_001.jpg",
            state = "Rajasthan",
            city = "Jaipur";
    String expectedFullName = format("%s %s",firstName,lastName);
    String expectedDateOfBirth = format("%s %s,%s",day,month,year);
    String expectedStateAndCity = format("%s %s",state,city);


    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest(){
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(userNumber)
                .setBirthDate(day,month,year)
                .setSubject(subject)
                .setHobbies(hobbie)
                .setPicture(img)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit()
                .checkResult("Student Name",expectedFullName)
                .checkResult("Student Email",email)
                .checkResult("Gender",gender)
                .checkResult("Mobile",userNumber)
                .checkResult("Date of Birth",expectedDateOfBirth)
                .checkResult("Subjects",subject)
                .checkResult("Hobbies",hobbie)
                .checkResult("Address",currentAddress)
                .checkResult("State and City",expectedStateAndCity);
    }
}
