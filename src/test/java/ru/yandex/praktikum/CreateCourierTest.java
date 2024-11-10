package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static ru.yandex.praktikum.Constans.URL;

public class CreateCourierTest {
    String idCourier;

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
    }
    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка - курьера можно создать")
    public void creatingCourier(){
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        Response response = StepCourier.createCourier(parametersCourier);
        idCourier = StepCourier.getId(parametersCourier);
        response.then().log().all().assertThat().statusCode(201).
                and().body("ok", equalTo(true));
    }
    @Test
    @DisplayName("Создать двух одинаковых курьеров")
    @Description("Проверка - нельзя создать двух одинаковых курьеров")
    public void createIdenticalCourier() {
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        //первый создан
        StepCourier.createCourier(parametersCourier);
        idCourier = StepCourier.getId(parametersCourier);
        //второй такой же
        Response response = StepCourier.createCourier(parametersCourier);
        response.then().log().all().assertThat().statusCode(409).
                and().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Проверка - нельзя создать курьера без логина (пустое значение)")
    public void createCourierWithoutLogin() {
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        parametersCourier.setLogin("");
        Response response = StepCourier.createCourier(parametersCourier);
        idCourier = StepCourier.getId(parametersCourier);
        response.then().log().all().assertThat().statusCode(400).
                and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Проверка - нельзя создать курьера без пароля (пустое значение)")
    public void createCourierWithoutPassword() {
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        parametersCourier.setPassword("");
        Response response = StepCourier.createCourier(parametersCourier);
        idCourier = StepCourier.getId(parametersCourier);
        response.then().log().all().assertThat().statusCode(400).
                and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера без имени")
    @Description("\"Проверка - нельзя создать курьера без имени (пустое значение)")
    public void createCourierWithoutName() {
        ParametersCourier parametersCourier = StepCourier.createNewCourier();
        parametersCourier.setFirstName("");
        Response response = StepCourier.createCourier(parametersCourier);
        idCourier = StepCourier.getId(parametersCourier);
        response.then().log().all().assertThat().statusCode(400).
                and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @After
    @Step("Удаление курьера")
    public void deleteCourier() {
        if (idCourier !=null) {
            StepCourier.deleteCourier(idCourier);
    }
}
}
