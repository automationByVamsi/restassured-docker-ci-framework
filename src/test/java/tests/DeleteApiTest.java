package tests;

import apis.CreateBookingApi;
import apis.DeleteBookingApi;
import apis.GetBookingApi;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.response.CreateBookingResponse;
import utils.ApiRequestHelper;

public class DeleteApiTest
{

    private CreateBookingApi createBookingApi;
    private DeleteBookingApi deleteBookingApi;
    private GetBookingApi getBookingApi;

    @BeforeClass
    public void initApi()
    {
        this.createBookingApi = new CreateBookingApi();
        this.deleteBookingApi = new DeleteBookingApi();
        this.getBookingApi = new GetBookingApi();


    }


    @Test
    public void deleteExistingBooking()
    {
        var createBookingPayload = ApiRequestHelper.getCreateBookingPayloadPojo("Lion","Honey",
                1230,"Balcony",true,"2025-09-13","2025-09-15");

        var createBookingResponse = this.createBookingApi.createNewBooking(createBookingPayload)
                .then().assertThat().statusCode(200).and().extract().as(CreateBookingResponse.class);

        var bookingId = createBookingResponse.getBookingId();
        var firstName = createBookingResponse.getBooking().getFirstName();



     this.getBookingApi.getBookingById(bookingId)
                .then().assertThat().statusCode(200)
                .assertThat().body("firstname", Matchers.equalTo(firstName));

        //Deleting the Booking

       this.deleteBookingApi.
                deleteBookingById(bookingId,"admin","password123")
                .then().assertThat().statusCode(201);

      this.getBookingApi.getBookingById(bookingId).then().assertThat().statusCode(404);






    }
}
