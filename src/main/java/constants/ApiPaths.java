package constants;

import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiPaths {


    GET_BOOKING("/booking/{bookingId}", Method.GET),
    GET_BOOKING_IDS("/booking", Method.GET),
    CREATE_BOOKING("/booking",Method.POST),
    UPDATE_BOOKING("/booking/{bookingId}", Method.PUT),
    DELETE_BOOKING("/booking/{bookingId}", Method.DELETE);


    private String apiPath;
    private Method httpMethodType;



}
