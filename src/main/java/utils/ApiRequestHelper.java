package utils;

import pojo.request.BookingDates;
import pojo.request.CreateBookingRequest;

import java.util.HashMap;
import java.util.Map;

public final class ApiRequestHelper {

    private ApiRequestHelper(){}


    public static  Map<String, Object> getCreateBookingPayload(String firstName,
                                                               String lastName,
                                                               int totalPrice, String additionalNeeds,
                                                               boolean depositPaid,String checkin,
                                                               String checkout){
        Map<String,Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("firstname",firstName);
        requestBodyMap.put("lastname",lastName);
        requestBodyMap.put("totalprice",totalPrice);
        requestBodyMap.put("additionalneeds",depositPaid);
        requestBodyMap.put("depositpaid",false);
        Map<String,Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin",checkin);
        bookingDatesMap.put("checkout",checkout);

        requestBodyMap.put("bookingdates",bookingDatesMap);

        return requestBodyMap;
    }


    public static  CreateBookingRequest getCreateBookingPayloadPojo(String firstName,
                                                               String lastName,
                                                               int totalPrice, String additionalNeeds,
                                                               Boolean depositPaid,String checkin,
                                                               String checkout){

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstName(firstName);
        createBookingRequest.setLastName(lastName);
        createBookingRequest.setTotalPrice(totalPrice);
        createBookingRequest.setAdditionalNeeds(additionalNeeds);
        createBookingRequest.setDepositPaid(depositPaid);
        createBookingRequest.setBookingDates(bookingDates);

        return createBookingRequest;
    }


}
