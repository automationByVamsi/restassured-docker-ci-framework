package tests;

import apis.CreateBookingApi;
import apis.DeleteBookingApi;
import apis.GetBookingApi;
import apis.UpdateBookingApi;
import config.PropertyUtil;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import pojo.response.CreateBookingResponse;
import pojo.response.UpdateBookingResponse;
import utils.ApiRequestHelper;
import utils.TestDataHelper;

import java.time.format.DateTimeFormatter;

public class CRUDTest  extends BaseTest
{

    @Test(description = "End to End Test for Restful booker API",dataProvider = "bookingDetails")
    public void crudTest(String firstName, String lastName, Boolean depositPaid, long totalPrice
            , String additionalNeeds, String checkin, String checkout)
    {



        String username = System.getenv("RESTFULBOOKER_USERNAME");
        String password = System.getenv("RESTFULBOOKER_PASSWORD");

        var createBookingApi = new CreateBookingApi();
        var getBookingApi = new GetBookingApi();
        var updateBookingApi = new UpdateBookingApi();
        var deleteBookingApi = new DeleteBookingApi();


        // Step - 1: Creating a new booking details
        var createBookingPayload = ApiRequestHelper.getCreateBookingPayload(firstName,lastName,
                Math.toIntExact(totalPrice),
                additionalNeeds,depositPaid,checkin,checkout);
        var createBookingResponse = createBookingApi.
                createNewBooking(createBookingPayload)
                .then().assertThat().statusCode(200)
                .and().extract().as(CreateBookingResponse.class);

        //Fetching the booking id from the response
        var bookingId = createBookingResponse.getBookingId();

        //Step-2 : Fetching the created booking details and validating the body
            getBookingApi.getBookingById(bookingId).then()
                .assertThat().statusCode(200)
                .assertThat().body("firstname", Matchers.equalTo(firstName))
                .assertThat().body("lastname", Matchers.equalTo(lastName))
                .assertThat().body("totalprice",Matchers.equalTo( Math.toIntExact(totalPrice)));

        //Step-3: Updating the Booking Details

        String updatedFirstname = faker.name().firstName();
        String updatedLastname = faker.name().lastName();
        String updatedAdditionalNeeds = faker.food().dish();
        int updatedTotalprice = TestDataHelper.getRandomNumber(4);
        boolean updatedDepositInfo = faker.bool().bool();
        var numberOfDays = TestDataHelper.getRandomNumber(2);
        String updatedCheckin = TestDataHelper.getFutureDate(numberOfDays, DateTimeFormatter.ISO_DATE);
        String updatedCheckout =TestDataHelper.getFutureDate(numberOfDays+4, DateTimeFormatter.ISO_DATE);


        var updatedBookingPayload = ApiRequestHelper.getCreateBookingPayload(updatedFirstname,updatedLastname,
                Math.toIntExact(updatedTotalprice),
                updatedAdditionalNeeds,updatedDepositInfo,updatedCheckin,updatedCheckout);

       var updateBookingResponse =  updateBookingApi.
                updateBooking(username,password,updatedBookingPayload,bookingId).then()
                .assertThat().statusCode(200).extract().as(UpdateBookingResponse.class);


       var fetchedCheckin = updateBookingResponse.getBookingdates().getCheckin();
       var fetchedFirstname = updateBookingResponse.getFirstname();


        //Step-4 : Fetching the updated booking details and validating the body
        getBookingApi.getBookingById(bookingId).then()
                     .assertThat().statusCode(200)
                     .assertThat().body("firstname", Matchers.equalTo(fetchedFirstname))
                     .assertThat().body("bookingdates.checkin",Matchers.equalTo(fetchedCheckin));


        deleteBookingApi.
                deleteBookingById(bookingId,username,password)
                .then().assertThat().statusCode(201);

        getBookingApi.getBookingById(bookingId).then().assertThat().statusCode(404);








    }
}
