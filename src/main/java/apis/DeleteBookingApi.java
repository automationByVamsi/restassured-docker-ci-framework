package apis;

import http.BaseApi;
import io.restassured.response.Response;

import static constants.ApiPaths.DELETE_BOOKING;
import static constants.ApiPaths.GET_BOOKING;

public class DeleteBookingApi extends BaseApi {


    public DeleteBookingApi()
    {
        super();
        logAllRequestDetails();
        logAllResponseDetails();

    }


    public Response deleteBookingById(int id,String username,String password)
    {
        super.setBasicAuth(username,password);
        super.setBasePath(DELETE_BOOKING.getApiPath());
        super.setPathParam("bookingId",id);
        return super.sendRequest(DELETE_BOOKING.getHttpMethodType());
    }
}
