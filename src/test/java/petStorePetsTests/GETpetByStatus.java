package petStorePetsTests;

import io.restassured.response.Response;
import org.json.JSONArray;
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

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class GETpetByStatus {

    private static petstoreRestOperations petstoreRestOperations;


    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("response_pet_by_status_available", "available"),
                Arguments.arguments("response_pet_by_status_sold", "sold"));

    }

    @BeforeAll
    public static void setUp() {
        petstoreRestOperations = new petstoreRestOperations();
    }

    @ParameterizedTest(name = "PetByStatus test: {0} - {1}")
    @MethodSource("dataProvider")
    void getPetByStatusDetails(String filename, String status) throws IOException {

        String pathToJson = "src/test/java/resources/" + filename;

        Response response = petstoreRestOperations.getPetByStatus(status);
        Assertions.assertEquals(200, response.statusCode());

        JSONArray expectedJsonArray = new JSONArray(JsonFile.getFileAsString(pathToJson));
        JSONArray actualJsonArray = new ResponseBodyConverter(response).toJsonArray();
        assertThat(actualJsonArray, sameBeanAs(expectedJsonArray));
    }

}
