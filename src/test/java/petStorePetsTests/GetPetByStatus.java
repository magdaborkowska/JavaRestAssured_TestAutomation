//package petStorePetsTests;
//
//import io.restassured.response.Response;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import restOperations.petstoreRestOperations;
//import utils.ResponseBodyConverter;
//
//import java.io.FileNotFoundException;
//
//import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class GetPetByStatus {
//
//    private static petstoreRestOperations petstoreRestOperations;
//
//    static JSONObject dataProvider() throws FileNotFoundException {
////        TODO:finish data provider with JSONs
//        JSONObject expectedJson = new JSONObject("resources/response/response_pet_by_status_available.json");
//
//    }
//
//    @BeforeAll
//    public static void setUp() {
//        petstoreRestOperations = new petstoreRestOperations();
//    }
//
//    @ParameterizedTest(name = "PetByStatus test: {0} - {1}")
//    @MethodSource("dataProvider")
//    void getPetByStatusDetails(String status) {
//
//        Response response = petstoreRestOperations.getPetById(status);
//        Assertions.assertEquals(200, response.statusCode());
////
////        JSONObject petStatusResponseInJson = new ResponseBodyConverter(response).toJsonObject();
//////        TODO: finish test
////        assertThat(petStatusResponseInJson, sameBeanAs(expectedJson));
//    }
//}
