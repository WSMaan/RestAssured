package postRequest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
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
    @Story("SetUp Base URI")
    public void setUp() {
        restAssuredUtils_post = new RestAssuredUtils_post();
        restAssuredUtils_post.setBaseURI("https://reqres.in/");
    }

    @Test
    @Story("Valid Login feature")
    @Description("This request verifies Valid Login and captures the Response TOKEN")
    @Severity(SeverityLevel.CRITICAL)
    public void validLogin() {
        validLoginUser = new ValidLoginUser("eve.holt@reqres.in", "cityslicka");

        response = restAssuredUtils_post.valid_login("api/login", validLoginUser);

        assertEquals(response.getStatusCode(), 200);
        token = response.jsonPath().getString("token");
        assertNotNull(token);

    }

    @Test(dataProvider = "userDataProvider")
    @Story("Create New Users")
    @Description("This story creates new users with NAME and JOB fields")
    @Severity(SeverityLevel.CRITICAL)
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
