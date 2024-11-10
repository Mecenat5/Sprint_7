package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ru.yandex.praktikum.Constans.*;

public class StepCourier {
    public static ParametersCourier createNewCourier() {
        return new ParametersCourier("ninja", "1234", "saske");
    }
    @Step("Создание курьера")
    public static Response createCourier(ParametersCourier parametersCourier) {
        return spec()
                .body(parametersCourier)
                .when()
                .post(CREATE_COURIER);
    }
    @Step("Логин курьера в системе")
    public static Response loginCourier(ParametersCourier parametersCourier) {
        return spec()
                .body(parametersCourier)
                .when()
                .post(COURIER_LOGIN);
    }
    @Step("Получить id при регистрации курьера")
    public static String getId(ParametersCourier parametersCourier) {
        String id;
        try {
            id = loginCourier(parametersCourier)
                    .then()
                    .extract()
                    .path("id").toString();
        } catch (Exception e) {
            id = null;
        }
        return id;
    }
    @Step("Удаление курьера")
    public static Response deleteCourier(String courierId) {
        return spec()
                .delete(CREATE_COURIER + courierId);

    }
}
