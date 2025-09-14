package pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateBookingResponse {

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	@JsonProperty("bookingdates")
	private Bookingdates bookingdates;

	@JsonProperty("totalprice")
	private int totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("lastname")
	private String lastname;


}