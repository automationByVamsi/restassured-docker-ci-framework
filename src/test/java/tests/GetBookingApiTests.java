package tests;
import apis.GetBookingApi;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetBookingApiTests
{



    @Parameters("testParam")
    @Test(description = "Basic HTTP Status check for get booking ids API")
    public void validateStatusForGetBookingApi(@Optional String testParam)
    {
        var getBookingApi = new GetBookingApi();
        getBookingApi.getAllBookingIds().
               then().assertThat().statusCode(200);

    }

//    @Test(description = "Basic HTTP Status check for get booking by id API")
//    public void validateStatusForGetBookingByIdApi()
//    {
//        var getBookingApi = new GetBookingApi();
//        getBookingApi.getBookingById(25).
//                then().assertThat().statusCode(200);
//    }

}
