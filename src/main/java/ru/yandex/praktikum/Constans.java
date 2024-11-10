package ru.yandex.praktikum;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Constans {
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    public static final String CREATE_COURIER = "api/v1/courier/";
    public static final String COURIER_LOGIN = "api/v1/courier/login/";
    public final static String CREATE_ORDER = "/api/v1/orders";
    public final static String GET_LIST_ORDER = "/api/v1/orders";

    public static RequestSpecification spec() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .log()
                .all();

    }
}
