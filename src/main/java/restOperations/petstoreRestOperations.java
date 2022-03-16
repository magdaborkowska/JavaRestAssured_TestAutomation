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
                .log().uri()
                .baseUri(uri)
                .basePath("pet/" + id)
                .get();
    }

    public Response getPetByStatus(String status) {
        return given()
                .log().uri()
                .baseUri(uri)
                .basePath("pet/findByStatus?status=" + status)
                .get();
    }

}
