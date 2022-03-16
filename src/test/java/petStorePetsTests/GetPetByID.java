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

import java.util.stream.Stream;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPetByID {

    private static petstoreRestOperations petstoreRestOperations;

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.arguments("4", "Husky", "doggie", "available"),
                Arguments.arguments("5", "Dogs", "Dog 2", "sold"));
    }

    @BeforeAll
    public static void setUp() {
        petstoreRestOperations = new petstoreRestOperations();
    }

    @ParameterizedTest(name = "PetByID test: {0} - {1}")
    @MethodSource("dataProvider")
    void getPetByIdDetails(String petId, String categoryName, String name, String status) {

        Response response = petstoreRestOperations.getPetById(petId);
        Assertions.assertEquals(200, response.statusCode());


        JSONObject petIdResponseInJson = new ResponseBodyConverter(response).toJsonObject();

        JSONObject expectedJson = getJsonObject(status, name, categoryName);
        JSONObject limitedActualResponse = getJsonObject(petIdResponseInJson.get("status"), petIdResponseInJson.get("name"), petIdResponseInJson.getJSONObject("category").get("name"));

        assertThat(limitedActualResponse, sameBeanAs(expectedJson));
    }

    private JSONObject getJsonObject(Object statusValue, Object nameValue, Object categoryName) {
        JSONObject expectedJson = new JSONObject();

        expectedJson.put("status", statusValue);
        expectedJson.put("name", nameValue);

        JSONObject categoryNameObj = new JSONObject();
        categoryNameObj.put("name", categoryName);

        expectedJson.put("category", categoryNameObj);
        return expectedJson;
    }
}
