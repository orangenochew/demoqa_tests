package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();

    //locators
    SelenideElement regFormHeader = $(".practice-form-wrapper");
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement userGenderClick = $("#genterWrapper");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement emailInput = $("#userEmail");
    SelenideElement userNumberInput = $("#userNumber");
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement hobbiesClick = $(byText("Music"));
    SelenideElement uploadPicture = $("#uploadPicture");
    SelenideElement addressInput = $("#currentAddress");
    SelenideElement stateInput = $("#state");
    SelenideElement cityInput = $("#city");
    SelenideElement submitButton = $("#submit");
    SelenideElement resultHeader = $("#example-modal-sizes-title-lg");
    SelenideElement resultTable = $(".table-responsive");


    //actions
    public RegistrationFormPage openPage(){
        open("/automation-practice-form");
        zoom(0.5);
        regFormHeader.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()"); //убираем футер шоб кнопка влезла
        executeJavaScript("$('fixedban').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value){
        emailInput.setValue(value);
        return  this;
    }

    public RegistrationFormPage setGender(String value){
        userGenderClick.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value){
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year){
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setHobbies(String value) {
        hobbiesClick.click();

        return this;
    }

    public RegistrationFormPage setPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setState(String value) {
        stateInput.click();
        $(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setCity(String value) {
        cityInput.click();
        $(byText(value)).click();
        return this;
    }

    public RegistrationFormPage submit() {
        submitButton.click();
        return this;
    }

    //result
    public RegistrationFormPage checkResult(String key, String value){
        resultHeader.shouldHave(text("Thanks for submitting the form"));
        resultTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}
