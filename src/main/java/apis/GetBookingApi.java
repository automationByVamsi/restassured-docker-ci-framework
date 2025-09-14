package apis;

import static constants.ApiPaths.*;
import http.BaseApi;
import io.restassured.response.Response;

public class GetBookingApi extends BaseApi
{
     public GetBookingApi()
     {
         super();
         super.logAllRequestDetails().
                 logAllResponseDetails();


     }

     public Response getAllBookingIds()
     {
          super.setBasePath(GET_BOOKING_IDS.getApiPath());
          return super.sendRequest(GET_BOOKING_IDS.getHttpMethodType());
     }

    public Response getBookingById(int id)
    {
        super.setBasePath(GET_BOOKING.getApiPath());
        super.setPathParam("bookingId",id);
        return super.sendRequest(GET_BOOKING.getHttpMethodType());
    }

}
