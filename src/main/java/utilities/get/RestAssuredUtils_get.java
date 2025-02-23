
package utilities.get;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredUtils_get {


    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    @Step("Get user from {endpoint}")
    public Response getUser(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    @Step("Get User from {endpoint} {paramName} {paramValue} ")
    public Response getEndpointWithQueryParams(String endpoint, String paramName, String paramValue) {
        return RestAssured.given()
                .queryParam(paramName, paramValue)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }


}
