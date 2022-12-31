package com.example.concurrencyplayground;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;

public class SimpleRestAssured {

    public static ExtractableResponse<Response> get(String path) {
        return thenExtract(given()
                .when().get(path));
    }

    public static ExtractableResponse<Response> get(String path, Map<String, String> header) {
        return thenExtract(given()
                .headers(header)
                .when().get(path));
    }

    public static ExtractableResponse<Response> post(String path, Object request) {
        return thenExtract(given()
                .body(request)
                .when().post(path));
    }

    public static ExtractableResponse<Response> post(String path, Object request, Map<String, String> header) {
        return thenExtract(given()
                .headers(header)
                .body(request)
                .when().post(path));
    }

    public static ExtractableResponse<Response> put(String path, Object request) {
        return thenExtract(given()
                .body(request)
                .when().put(path));
    }

    public static ExtractableResponse<Response> put(String path, Object request, Map<String, String> header) {
        return thenExtract(given()
                .headers(header)
                .body(request)
                .when().put(path));
    }

    public static ExtractableResponse<Response> delete(String path) {
        return thenExtract(given()
                .when().delete(path));
    }

    public static ExtractableResponse<Response> delete(String path, Map<String, String> header) {
        return thenExtract(given()
                .headers(header)
                .when().delete(path));
    }

    public static ExtractableResponse<Response> patch(String path, Map<String, String> header) {
        return thenExtract(given()
                .headers(header)
                .when().patch(path));
    }

    public static ExtractableResponse<Response> patch(String path, Object request) {
        return thenExtract(given()
                .body(request)
                .when().patch(path));
    }

    private static RequestSpecification given() {
        return RestAssured.given().log().all().contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private static ExtractableResponse<Response> thenExtract(Response response) {
        return response
                .then().log().all()
                .extract();
    }

    public static <T> T toObject(ExtractableResponse<Response> response, Class<T> clazz) {
        return response.as(clazz);
    }

    public static <T> List<T> toObjectList(ExtractableResponse<Response> response, Class<T> clazz) {
        return response.body().jsonPath().getList(".", clazz);
    }
}
