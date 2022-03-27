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

public class DELETEorder {

    private static petstoreRestOperations petstoreRestOperations;

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("response_delete_order1", "10"),
                Arguments.arguments("response_delete_order2", "15"));
    }

    @BeforeAll
    public static void setUp() {
        petstoreRestOperations = new petstoreRestOperations();
    }

    @ParameterizedTest(name = "PostUser test: {0}")
    @MethodSource("dataProvider")
    void postUserDetails(String filename, String id) throws IOException {

        String pathToJson = "src/test/java/resources/" + filename;
        Response response = petstoreRestOperations.deleteOrder(id);
        Assertions.assertEquals(200, response.statusCode());
    }
}
