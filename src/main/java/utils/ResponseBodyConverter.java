package utils;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class ResponseBodyConverter {

    private Response response;
    private final String contentType;
    private String responseString;

    public ResponseBodyConverter(Response response) {
        this.response = response;
        this.contentType = response.header("Content-Type");
        toStringWithContentType();
    }

    public JSONObject toJsonObject() {
        return new JSONObject(responseString);
    }

    public JSONArray toJsonArray() {
        return new JSONArray(responseString);
    }

    private void toStringWithContentType() {
        this.responseString = response.then().contentType(contentType)
                .extract().response().asString();
    }
}
