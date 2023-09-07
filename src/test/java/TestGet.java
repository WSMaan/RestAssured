import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.*;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.equalTo;

public class TestGet {
    @BeforeClass
    void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void getListUsers() {
        Response response = RestAssured.get("/api/users?page=2");
        response.then()
                .body("data.id[0]", equalTo(7))
                .statusCode(200).log().all();
    }

    @Test
    void createUser() {
        NewUser newUser = new NewUser("55", "abc@gmail.com", "ABC", "XYZ", "NoAvatar");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(newUser)
                .post("/api/users");
        response.then().statusCode(201)
                .log().all();

        NewUser newUser1 = new NewUser("99", "xyz@gmailcom", "HHh", "yyy", "NA");
        Response response1 = RestAssured.given().contentType(ContentType.JSON)
                .body(newUser1)
                .post("/api/users");
        response1.then().statusCode(201).log().all();
    }

    @Test
    void changeUser() {
        String putName = "{\"name\":\"hhhh\"}";
        Response response = RestAssured.given()
                .body(putName)
                .put("/api/users/2");
        response.then().statusCode(200).log().all();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    static class NewUser {
        String id;
        String email;
        String first_name;
        String last_name;
        String avatar;


    }
}
