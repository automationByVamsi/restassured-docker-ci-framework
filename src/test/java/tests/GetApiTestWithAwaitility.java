package tests;

import apis.GetBookingApi;
import org.awaitility.Awaitility;
import org.testng.annotations.Test;

import java.time.Duration;

public class GetApiTestWithAwaitility {


    @Test
    public void waitUntilAndIgnoreAllExceptions() {

        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("Waiting and Ignoring all exceptions")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .and().ignoreExceptions()
                  .then().until(() ->
                  {
                      getBookingApi.getBookingById(187).then().assertThat().statusCode(400);
                      return true;
                  });
    }


    @Test
    public void waitUntilAsserted() {

        var getBookingApi = new GetBookingApi();

        Awaitility.await()
                  .and().with().alias("Waiting and Ignoring all exceptions")
                  .and().with().timeout(Duration.ofSeconds(5))
                  .and().ignoreExceptions()
                  .then().untilAsserted(() ->
                  {
                      getBookingApi.getBookingById(187).then().assertThat().statusCode(400);

                  });
    }
}

