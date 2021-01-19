package booking.request;

import io.restassured.path.json.JsonPath;

import static booking.utils.BookingUrl.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class GetRequest {

    private GetRequest() { }

    public static JsonPath readAllBookings() {

        return given()
                .when()
                .get(BASE_URL + BOOKING)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response()
                .jsonPath();
    }
}