package booking.test;

import booking.model.Booking;
import booking.request.GetRequest;
import booking.request.PostRequest;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BookingsTest {

    private static final String BOOKING_ID = "bookingid";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String BOOKING = "booking.";

    @Test
    public void readAllBookings() {
        JsonPath json = GetRequest.readAllBookings();

        List<Integer> bookingIds = json.getList(BOOKING_ID);
        assertThat(bookingIds.size()).isPositive();
    }

    @Test
    public void createBooking() {

        final JSONObject bookingDatesJson = Booking.buildBookingDatesJson("2018-01-01", "2019-01-01");
        final JSONObject bookingJson = Booking.buildBookingJson("Bartek", "Demo",
                new BigDecimal("1000"), true, bookingDatesJson, "sauna");

        JsonPath json = PostRequest.createBooking(bookingJson);
        assertThat(json.getString(BOOKING + FIRST_NAME)).isEqualTo(bookingJson.getString(FIRST_NAME));
        assertThat(json.getString(BOOKING + LAST_NAME)).isEqualTo(bookingJson.getString(LAST_NAME));
    }
}