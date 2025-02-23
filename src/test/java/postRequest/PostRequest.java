package postRequest;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.ValidLoginUser;
import utilities.post.RestAssuredUtils_post;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class PostRequest {
    String token;
    Response response;
    ValidLoginUser validLoginUser;

    User user;
    RestAssuredUtils_post restAssuredUtils_post;

    @BeforeClass
    public void setUp() {
        restAssuredUtils_post = new RestAssuredUtils_post();
        restAssuredUtils_post.setBaseURI("https://reqres.in/");
    }

    @Test
    public void validLogin() {
        validLoginUser = new ValidLoginUser("eve.holt@reqres.in", "cityslicka");

        response = restAssuredUtils_post.valid_login("api/login", validLoginUser);

        assertEquals(response.getStatusCode(), 200);
        token = response.jsonPath().getString("token");
        assertNotNull(token);

    }

    @Test(dataProvider = "userDataProvider")
    public void create_new_user(String name, String job) {
        user = new User(name, job);

        response = restAssuredUtils_post.create_new_user(user);
        assertEquals(response.getStatusCode(), 201);
    }


    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() throws IOException {
        return restAssuredUtils_post.getUserData("users.json");
    }
}
