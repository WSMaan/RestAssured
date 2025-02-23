package getRequest;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.get.RestAssuredUtils_get;

import static org.testng.Assert.assertEquals;

@Epic("GET Requests")
@Feature("API Tests")


public class GetRequest {
    private RestAssuredUtils_get restAssuredUtils;
    Response response;

    @BeforeClass
    @Story("Set UP Base URI")
    public void setUp() {
        restAssuredUtils = new RestAssuredUtils_get();
        restAssuredUtils.setBaseURI("https://reqres.in/");
    }

    @Test
    @Story("GET User List")
    @Description("This request gets the USER LIST")
    @Severity(SeverityLevel.NORMAL)
    public void getUser() {
        response = restAssuredUtils.getUser("https://reqres.in/api/users/2");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Story("GET User List ")
    @Description("This request gets USER List from different pages")
    @Severity(SeverityLevel.NORMAL)
    public void getListUsers() {
        response = restAssuredUtils.getEndpointWithQueryParams("api/users", "page", "2");
        int total = response.jsonPath().getInt("total");
        assertEquals(12, total);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Story("Empty User List")
    @Description("This request tests empty User list respone")
    @Severity(SeverityLevel.MINOR)
    public void emptyListGet() {
        response = restAssuredUtils.getUser("api/users/23");
        assertEquals(404, response.getStatusCode());
    }
}