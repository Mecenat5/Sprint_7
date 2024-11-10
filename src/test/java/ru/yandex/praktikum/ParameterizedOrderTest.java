package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.yandex.praktikum.Constans.URL;

@RunWith(Parameterized.class)
public class ParameterizedOrderTest {
    private final List<String> color;
    private int track;

    public ParameterizedOrderTest(List<String> color) {
        this.color = color;
    }
    @Parameterized.Parameters()
    public static Object[][] getColor() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK, GRAY")},
                {List.of()}
        };
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
    }
    @Test
    @DisplayName("Создание заказа с разными расцветками самоката")
    @Description("Проверка - создается заказ с самокатами разных расцветок")
    public void OrderWithDifferentColors() {
        ParametersOrder parametersOrder = new ParametersOrder(color);
        Response response = StepOrder.createOrder(parametersOrder);
        track = response.then().extract().path("track");
        response.then().log().all().assertThat().statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }
}
