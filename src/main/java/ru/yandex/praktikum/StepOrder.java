package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StepOrder extends Constans{

    @Step("Создание заказа")
    public static Response createOrder(ParametersOrder parametersOrder) {
        return spec()
                .body(parametersOrder)
                .when()
                .post(CREATE_ORDER);
    }
    @Step("Получение списка заказов")
    public static Response getOrderList() {
        return spec()
                .when()
                .get(GET_LIST_ORDER);
    }
}
