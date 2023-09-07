import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class test {

    @BeforeClass
    void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }


    @Test
    void getMethod() {
        Response response = RestAssured.get("/api/users/2");
        response.then()
                .body("data.last_name",equalTo("Weaver"))
                .statusCode(200).log().all();
    }

}

