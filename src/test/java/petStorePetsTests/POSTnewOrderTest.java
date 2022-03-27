package petStorePetsTests;

import com.shazam.shazamcrest.MatcherAssert;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import restOperations.petstoreRestOperations;
import utils.JsonFile;
import utils.ResponseBodyConverter;

import java.io.IOException;
import java.util.stream.Stream;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class POSTnewOrderTest {

    private static petstoreRestOperations petstoreRestOperations;

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("request_body_new_order1"),
                Arguments.arguments("request_body_new_order1"));
    }

    @BeforeAll
    public static void setUp() {
        petstoreRestOperations = new petstoreRestOperations();
    }

    @ParameterizedTest(name = "PostUser test: {0}")
    @MethodSource("dataProvider")
    void postUserDetails(String filename) throws IOException {

        String pathToJson = "src/test/java/resources/" + filename;
        String requestBodyAsString = JsonFile.getFileAsString(pathToJson);
        Response response = petstoreRestOperations.postOrder(requestBodyAsString);
        Assertions.assertEquals(200, response.statusCode());

        JSONObject expectedResponse = new JSONObject(JsonFile.getFileAsString(pathToJson));
        JSONObject actualResponse = new ResponseBodyConverter(response).toJsonObject();
        MatcherAssert.assertThat(actualResponse, sameBeanAs(expectedResponse).ignoring("map.shipDate"));
    }
}
