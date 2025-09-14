package apis;

import static constants.ApiPaths.*;
import http.BaseApi;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.request.CreateBookingRequest;

import java.util.Map;

public class UpdateBookingApi extends BaseApi {

    public UpdateBookingApi()
    {
        super();
        logSpecificRequestDetails(LogDetail.BODY);
        logAllResponseDetails();
        setContentType(ContentType.JSON);
    }


    public Response updateBooking(String username, String password, Map<String,Object> createBookingRequestPayload,int bookingId)
    {
        return getUpdateBookingApiResponse(username,password,createBookingRequestPayload,bookingId);
    }

    public Response updateBooking(String username,String password,CreateBookingRequest createBookingRequestPayload,int bookingId)
    {
        return getUpdateBookingApiResponse(username,password,createBookingRequestPayload,bookingId);
    }



    private Response getUpdateBookingApiResponse(String username, String password,Object createBookingRequestPayload,int bookingId)
    {

        setBasePath(UPDATE_BOOKING.getApiPath());
        setBasicAuth(username, password);
        setPathParam("bookingId",bookingId);
        setRequestBody(createBookingRequestPayload);
        return sendRequest(UPDATE_BOOKING.getHttpMethodType());
    }

}
