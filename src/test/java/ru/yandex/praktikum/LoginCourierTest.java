package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    String idCourier;

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Проверка - авторизация курьера при вводе валидных значений")
    public void loginNewCourier() {
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //сначала создаем курьера
        StepCourier.createCourier(parametersCourier);
        //убираем из параметров курьера Имя
        parametersCourier.setFirstName(null);
        //регистрируем id, чтобы потом удалить
        idCourier = StepCourier.getId(parametersCourier);
        //авторизуем курьера с данными только логин и пароль
        Response response = StepCourier.loginCourier(parametersCourier);
        //проверяем ответ и что параметр id в ответе есть и имеет значение
        response.then().log().all().assertThat().statusCode(200).
                and().body("id", notNullValue());
    }
    @Test
    @DisplayName("Авторизация курьера c невалидным логином")
    @Description("Проверка - не происходит авторизация курьера с невалидным логином")
    public void loginCourierWithNotExistingLogin() { // переименовать
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //сначала создаем курьера
        StepCourier.createCourier(parametersCourier);
        //убираем из параметров курьера Имя
        parametersCourier.setFirstName(null);
        //регистрируем id, чтобы потом удалить
        idCourier = StepCourier.getId(parametersCourier);
        //значение параметра Логин меняем на несуществующее (определено в CourierTestData - могут быть ограничения на длину)
        parametersCourier.setLogin("dfrf");
        //авторизуем курьера с этими данными
        Response response = StepCourier.loginCourier(parametersCourier);
        //проверяем ответ и текст сообщения об ошибке
        response.then().log().all().assertThat().statusCode(404).
                and().body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Авторизация курьера c невалидным паролем")
    @Description("Проверка - не происходит авторизация курьера с невалидным паролем")
    public void loginCourierWithNotExistingPassword() { // переименовать
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //сначала создаем курьера
        StepCourier.createCourier(parametersCourier);
        //убираем из параметров курьера Имя
        parametersCourier.setFirstName(null);
        //регистрируем id, чтобы потом удалить
        idCourier = StepCourier.getId(parametersCourier);
        //значение параметра Пароль меняем на несуществующее (определено в CourierTestData - могут быть ограничения на длину)
        parametersCourier.setPassword("44554");
        //авторизуем курьера с этими данными
        Response response = StepCourier.loginCourier(parametersCourier);
        //проверяем ответ и текст сообщения об ошибке
        response.then().log().all().assertThat().statusCode(404).
                and().body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Авторизация курьера без ввода логина")
    @Description("Проверка - не происходит авторизация курьера без ввода логина")
    public void loginCourierWithoutLogin() { // переименовать
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //сначала создаем курьера
        StepCourier.createCourier(parametersCourier);
        //убираем из параметров курьера Имя
        parametersCourier.setFirstName(null);
        //регистрируем id, чтобы потом удалить
        idCourier = StepCourier.getId(parametersCourier);
        //значение параметра Логин - пустое значение
        parametersCourier.setLogin("");
        //авторизуем курьера с этими данными
        Response response = StepCourier.loginCourier(parametersCourier);
        //проверяем ответ и текст сообщения об ошибке
        response.then().log().all().assertThat().statusCode(400).
                and().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Авторизация курьера без ввода пароля")
    @Description("Проверка - не происходит авторизация курьера без ввода пароля")
    public void loginCourierWithoutPassword() { // переименовать
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //сначала создаем курьера
        StepCourier.createCourier(parametersCourier);
        //убираем из параметров курьера Имя
        parametersCourier.setFirstName(null);
        //регистрируем id, чтобы потом удалить
        idCourier = StepCourier.getId(parametersCourier);
        //значение параметра Пароль - пустое значение
        parametersCourier.setPassword("");
        //авторизуем курьера с этими данными
        Response response = StepCourier.loginCourier(parametersCourier);
        //проверяем ответ и текст сообщения об ошибке
        response.then().log().all().assertThat().statusCode(400).
                and().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Авторизация курьера c несуществующими логином и паролем")
    @Description("Проверка - не происходит авторизация курьера c несуществующими логином и паролем")
    public void loginCourierWithNotExistingData() { // переименовать
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //сначала создаем курьера
        StepCourier.createCourier(parametersCourier);
        //убираем из параметров курьера Имя
        parametersCourier.setFirstName(null);
        //регистрируем id, чтобы потом удалить
        idCourier = StepCourier.getId(parametersCourier);
        //значение параметры Логин и Пароль меняем на несуществующие (определено в CourierTestData - могут быть ограничения на длину)
        parametersCourier.setLogin("dfrtg");
        parametersCourier.setPassword("33456");
        //авторизуем курьера с этими данными
        Response response = StepCourier.loginCourier(parametersCourier);
        //проверяем ответ и текст сообщения об ошибке
        response.then().assertThat().statusCode(404).
                and().body("message", equalTo("Учетная запись не найдена"));
    }
    @After
    @Step("Удаление курьера")
    public void deleteCourier() {
        if (idCourier !=null) {
            StepCourier.deleteCourier(idCourier);
        }
}
}
