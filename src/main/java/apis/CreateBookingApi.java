package apis;

import static constants.ApiPaths.*;
import http.BaseApi;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.request.CreateBookingRequest;

import java.util.Map;

public class CreateBookingApi extends BaseApi
{

    public CreateBookingApi()
    {
        super();
        super.logAllRequestDetails().
                logAllResponseDetails();
         super.setContentType(ContentType.JSON);

    }


    public Response createNewBooking(Map<String,Object> createBookingRequestPayload)
    {
        return getCreateBookingApiResponse(createBookingRequestPayload);
    }

    public Response createNewBooking(CreateBookingRequest createBookingRequestPayload)
    {
        return getCreateBookingApiResponse(createBookingRequestPayload);
    }



    private Response getCreateBookingApiResponse(Object createBookingRequestPayload) {
        super.setBasePath(CREATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingRequestPayload);
        return super.sendRequest(CREATE_BOOKING.getHttpMethodType());
    }



}
