package restOperations;

import configuration.EnvironmentConfiguration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SwRestOperations extends RestRequests {

    public SwRestOperations() {
        this.uri = EnvironmentConfiguration.getInstance().swUrl;
    }

    public Response getPersonById(String id) {
        return given()
                .log().uri()
                .baseUri(uri)
                .basePath("people/" + id)
                .get();
    }

    public Response getPersonByGender(String gender) {
        return given()
                .log().uri()
                .baseUri(uri)
                .basePath("people/" + gender)
                .get();
    }

    public Response getPlanetsById(String id) {
        return given()
                .log().uri()
                .baseUri(uri)
                .basePath("planets/" + id)
                .get();
    }
}
