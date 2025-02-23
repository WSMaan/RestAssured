package getRequest;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.get.RestAssuredUtils_get;

import static org.testng.Assert.assertEquals;

public class GetRequest {
    private RestAssuredUtils_get restAssuredUtils;

    @BeforeClass
    public void setUp() {
        restAssuredUtils = new RestAssuredUtils_get();
        restAssuredUtils.setBaseURI("https://reqres.in/");
    }

    @Test
    public void getUser() {
        Response response = restAssuredUtils.getUser("https://reqres.in/api/users/2");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void getListUsers(){
        Response response = restAssuredUtils.getEndpointWithQueryParams("api/users","page","2");
        int total = response.jsonPath().getInt("total");
        assertEquals(12,total);
        assertEquals(200,response.getStatusCode());
    }

    @Test
    public void emptyListGet() {
        Response response = restAssuredUtils.getUser("api/users/23");
        assertEquals(404, response.getStatusCode());
    }
}