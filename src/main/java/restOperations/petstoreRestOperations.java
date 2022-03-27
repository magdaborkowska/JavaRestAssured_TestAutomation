package restOperations;

import configuration.EnvironmentConfiguration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class petstoreRestOperations extends RestRequests {

    public petstoreRestOperations() {
        this.uri = EnvironmentConfiguration.getInstance().swUrl;
    }

    public Response getPetById(String id) {
        return given()
                .log().all()
                .baseUri(uri)
                .basePath("pet/" + id)
                .get();
    }

    public Response getPetByStatus(String status) {
        return given()
                .log().all()
                .baseUri(uri)
                .basePath("pet/findByStatus")
                .formParam("status", status)
                .get();
    }
    public Response postOrder(String requestBody) {
        return given()
                .log().all()
                .baseUri(uri)
                .basePath("store/order")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();
    }
    public Response deleteOrder(String id) {
        return given()
                .log().all()
                .baseUri(uri)
                .header("Content-Type", "application/json")
                .basePath("store/order/" + id)
                .delete();
    }
}
