package petStorePetsTests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import restOperations.petstoreRestOperations;
import utils.ResponseBodyConverter;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPetByStatus {

    private static petstoreRestOperations petstoreRestOperations;


    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("case1", "response_pet_by_status_available"),
                Arguments.arguments("case2", "response_pet_by_status_sold"));

    }

    @BeforeAll
    public static void setUp() {
        petstoreRestOperations = new petstoreRestOperations();
    }

    @ParameterizedTest(name = "PetByStatus test: {0} - {1}")
    @MethodSource("dataProvider")
    void getPetByStatusDetails(String description, String filename, String status) {

    String pathToJson = "resources/response/" + filename + ".json";

            Response response = petstoreRestOperations.getPetById(status);
            Assertions.assertEquals(200, response.statusCode());

    }
}
