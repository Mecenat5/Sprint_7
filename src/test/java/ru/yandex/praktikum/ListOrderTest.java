package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class ListOrderTest{
    @DisplayName("Список заказов")
    @Description("Проверка - получение списка заказов")
    @Test
    public void getListOrder(){
        Response response = StepOrder.getOrderList();
        response.then().log().all().assertThat().statusCode(200)
                .and()
                .assertThat()
                .body("orders", notNullValue());
    }
}
