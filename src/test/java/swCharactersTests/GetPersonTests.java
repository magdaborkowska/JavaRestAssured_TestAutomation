package swCharactersTests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import restOperations.SwRestOperations;
import utils.ResponseBodyConverter;

import java.util.stream.Stream;

public class GetPersonTests {

    private static SwRestOperations swRestOperations;

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("1", "Luke Skywalker", "172", "male"),
                Arguments.arguments("5", "Leia Organa", "150", "female"));
    }

    @BeforeAll
    public static void setUp() {
        swRestOperations = new SwRestOperations();
    }

    @ParameterizedTest(name = "People test: {0} - {1} - {2} - {3}")
    @MethodSource("dataProvider")
    void getPersonByIdDetails(String personId, String personName, String height, String gender) {

        Response response = swRestOperations.getPersonById(personId);
        Assertions.assertEquals(200, response.statusCode());

        JSONObject personResponseInJson = new ResponseBodyConverter(response).toJsonObject();

        Assertions.assertEquals(personName, personResponseInJson.get("name"));
        Assertions.assertEquals(height, personResponseInJson.get("height"));
        Assertions.assertEquals(gender, personResponseInJson.get("gender"));
    }

    @Test
    void getPersonByIdTest() {
        Response response = swRestOperations.getPersonById("1");
        Assertions.assertEquals(200, response.statusCode());
    }

}
