import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNew {
    @BeforeClass
    void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    void createNewUser() {
        User user1 = new User("ABC", "TESTER");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user1)
                .post("/api/users");
        response
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    void changeName() {
        String newName = "{\"name\":\"XYZ\"}";
        Response response = RestAssured.given()
                .body(newName)
                .put("/api/users/2");
        response.then()
                .statusCode(200)
                .log().all();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        String name;
        String job;
    }
}