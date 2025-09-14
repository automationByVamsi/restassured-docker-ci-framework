package pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class BookingDates{

	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;


}