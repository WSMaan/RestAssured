
package utilities.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredUtils_get {


    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public Response getUser(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    public Response getEndpointWithQueryParams(String endpoint, String paramName, String paramValue){
        return RestAssured.given()
                .queryParam(paramName,paramValue)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }


}
