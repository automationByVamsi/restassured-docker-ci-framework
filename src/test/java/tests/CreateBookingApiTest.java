package tests;

import apis.CreateBookingApi;
import apis.DeleteBookingApi;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.response.CreateBookingResponse;
import utils.ApiRequestHelper;
import utils.TestDataHelper;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CreateBookingApiTest {


    @DataProvider(name = "bookingDetails",parallel = true)
    public Object[][] generateBookingDetails() {
        var faker = TestDataHelper.getFaker();
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;

        return IntStream.range(0, 3)
                        .mapToObj(i -> {

                            var numberOfDays = TestDataHelper.getRandomNumber(2);

                            return new Object[]{
                                    name.firstName(), name.lastName(), faker.bool().bool(),
                                    faker.number().randomNumber(3, true),
                                    faker.food().dish(), TestDataHelper.getFutureDate(numberOfDays, dateFormatter),
                                    TestDataHelper.getFutureDate(numberOfDays + 4, dateFormatter)
                            };
                        })
                        .toArray(Object[][]::new);
    }

    @DataProvider(name = "bookingDetailsWithLoops")
    public Object[][] generateBookingDetailsWithLoops() {
        var faker = TestDataHelper.getFaker();
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;

        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object[] objects = new Object[]{
                    name.firstName(), name.lastName(), faker.bool().bool(),
                    faker.number().randomNumber(3, true),
                    faker.food().dish(), TestDataHelper.getFutureDate(10, dateFormatter),
                    TestDataHelper.getFutureDate(15, dateFormatter)};
            list.add(objects);
        }
        return list.toArray(new Object[0][]);

    }


    @Test(description = "Create a new booking and validate the status code")
    public void createNewBooking() {

        var faker = TestDataHelper.getFaker();
        var name = faker.name();
        var createBookingApi = new CreateBookingApi();
        var createNewBookingPayload = ApiRequestHelper.
                getCreateBookingPayload(name.firstName(), name.lastName(),
                        Math.toIntExact(faker.number().randomNumber(3, true)),
                        faker.food().dish(), faker.bool().bool(), TestDataHelper.getFutureDate(10, DateTimeFormatter.ISO_DATE),
                        TestDataHelper.getFutureDate(15, DateTimeFormatter.ISO_DATE));

        var createBookingResponse = createBookingApi.
                createNewBooking(createNewBookingPayload)
                .then().assertThat().statusCode(200)
                .and().body("bookingid", Matchers.is(Matchers.not(Matchers.equalTo(0))))
                .and().extract().as(CreateBookingResponse.class);

        var bookingId = createBookingResponse.getBookingId();

        var deleteBookingApi = new DeleteBookingApi();
        deleteBookingApi.
                deleteBookingById(bookingId, "admin", "password123")
                .then().assertThat().statusCode(201);


    }


    @Test(description = "Create a new booking using Data Provider and validate the status code", dataProvider = "bookingDetails")
    public void createNewBooking(String firstName, String lastName, Boolean depositPaid, long totalPrice
            , String additionalNeeds, String checkin, String checkout) {
        var createBookingApi = new CreateBookingApi();

        var createBookingResponse = createBookingApi.
                createNewBooking(ApiRequestHelper.getCreateBookingPayloadPojo(firstName, lastName,
                        Math.toIntExact(totalPrice)
                        , additionalNeeds, depositPaid, checkin, checkout))
                .then().assertThat().statusCode(200)
                .and().body("bookingid", Matchers.is(Matchers.not(Matchers.equalTo(0))))
                .and().extract().as(CreateBookingResponse.class);

        var bookingId = createBookingResponse.getBookingId();

        var deleteBookingApi = new DeleteBookingApi();
        deleteBookingApi.
                deleteBookingById(bookingId, "admin", "password123")
                .then().assertThat().statusCode(201);
    }
}
