package swCharactersTests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import restOperations.SwRestOperations;
import utils.ResponseBodyConverter;

import java.util.stream.Stream;

public class GetPlanetsTests {

    private static SwRestOperations swRestOperations;

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("1", "Tatooine", "desert"),
                Arguments.arguments("2", "Alderaan", "grasslands, mountains"),
                Arguments.arguments("3", "Yavin IV", "jungle, rainforests"),
                Arguments.arguments("4", "Hoth", "tundra, ice caves, mountain ranges"),
                Arguments.arguments("5", "Dagobah", "swamp, jungles"));
    }

    @BeforeAll
    public static void setUp() {
        swRestOperations = new SwRestOperations();
    }

    @ParameterizedTest(name = "Planet test: {0} - {1} - {2}")
    @MethodSource("dataProvider")
    void getPlanetByIdTest(String planetId, String planetName, String terrain) {

        Response response = swRestOperations.getPlanetsById(planetId);
        Assertions.assertEquals(200, response.statusCode());

        JSONObject planetResponseInJson = new ResponseBodyConverter(response).toJsonObject();

        Assertions.assertEquals(planetName, planetResponseInJson.get("name"));
        Assertions.assertEquals(terrain, planetResponseInJson.get("terrain"));
    }
}
