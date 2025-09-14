package tests;

import apis.CreateBookingApi;
import apis.UpdateBookingApi;
import config.PropertyConfig;
import config.PropertyUtil;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ApiRequestHelper;

public class UpdateBookingApiTest {

    private CreateBookingApi createBookingApi;
    private UpdateBookingApi updateBookingApi;

    @BeforeClass
    public void initApi() {
        this.createBookingApi = new CreateBookingApi();
        this.updateBookingApi = new UpdateBookingApi();
    }


    @Test
    public void updateBookingApi() {

        //Creating the booking first

        var createBookingRequest = ApiRequestHelper.getCreateBookingPayload("Rob", "Melons",
                1679, "Gym", false, "2025-09-13", "2025-09-16");
        var createBookingResponse = this.createBookingApi.createNewBooking(createBookingRequest)
                                                         .then().assertThat().statusCode(200);

        var bookingId = createBookingResponse.extract().jsonPath().getInt("bookingid");
        System.out.println("The booking id is " + bookingId);

        String username = PropertyUtil.getConfig().username();
        String password = PropertyUtil.getConfig().password();

        System.out.println(username);
        System.out.println(password);

        System.out.println(createBookingRequest);
        createBookingRequest.replace("firstname", "bob");
        createBookingRequest.replace("lastname", "Welons");


        var updateBookingResponse = this.updateBookingApi.
                updateBooking(username, password, createBookingRequest, bookingId).then()
                .and().assertThat().statusCode(200);


    }
}
