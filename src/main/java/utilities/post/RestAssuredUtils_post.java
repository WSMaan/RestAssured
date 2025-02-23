package utilities.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import user.User;
import user.ValidLoginUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RestAssuredUtils_post {

    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public Response create_new_user(User user) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("api/users")
                .then()
                .extract().response();

    }

    public Response valid_login(String endpoint, ValidLoginUser user) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when().post(endpoint)
                .then()
                .extract().response();

    }

    @DataProvider(name = "getUserData")
    public Object[][] getUserData(String resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("users.json");
        List<User> users = mapper.readValue(is, new TypeReference<List<User>>() {
        });
        Object[][] data = new Object[users.size()][2];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getName();
            data[i][1] = users.get(i).getJob();
        }
        return data;
    }
}
