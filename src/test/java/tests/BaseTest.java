package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import utils.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BaseTest {


    protected final Faker faker = TestDataHelper.getFaker();

    @DataProvider(name = "bookingDetails",parallel = true)
    public Object[][] generateBookingDetails() {
        var faker = TestDataHelper.getFaker();
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;

        return IntStream.range(0, 1)
                        .mapToObj(i -> {

                            var numberOfDays = TestDataHelper.getRandomNumber(2);

                            return new Object[]{
                                    name.firstName(), name.lastName(), faker.bool().bool(),
                                    faker.number().randomNumber(3, true),
                                    faker.food().dish(), TestDataHelper.getFutureDate(numberOfDays, dateFormatter),
                                    TestDataHelper.getFutureDate(numberOfDays + 4, dateFormatter)
                            };
                        })
                        .toArray(Object[][]::new);
    }

    @DataProvider(name = "bookingDetailsWithLoops")
    public Object[][] generateBookingDetailsWithLoops() {
        var faker = TestDataHelper.getFaker();
        var name = faker.name();
        var dateFormatter = DateTimeFormatter.ISO_DATE;

        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object[] objects = new Object[]{
                    name.firstName(), name.lastName(), faker.bool().bool(),
                    faker.number().randomNumber(3, true),
                    faker.food().dish(), TestDataHelper.getFutureDate(10, dateFormatter),
                    TestDataHelper.getFutureDate(15, dateFormatter)};
            list.add(objects);
        }
        return list.toArray(new Object[0][]);

    }

}
