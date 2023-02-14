package co.za.bookingatsamanthas.app.demo;

import co.za.bookingatsamanthas.app.demo.Repository.Booking;
import com.google.gson.Gson;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class SamanthasPeaceApplicationTests {
	static SpringApplicationBuilder app;

	@Autowired
	Gson gson;

	@BeforeAll
	public static void StartUp(){
		app = new SpringApplicationBuilder(SamanthasPeaceApplication.class);
		app.run();
	}



	@Test
	public void whenPropertiesConfig_thenInsertSucceeds() {
		assertInsertSucceeds(app.context());
	}

	@Test
	public void MakeBookingTest() throws ParseException {
		AssertMakeBooking(app.context());

	}

	public DeleteResult cancelBooking(Booking booking, MongoTemplate mongoTemplate){
		// cancels booking
		// params: bookingDetails

		Query query = new Query();

		query.addCriteria(Criteria.where("email").is(booking.getBookedBy())
				.andOperator(Criteria.where("dayOfVisit").is(booking.getDayOfVisit()))
				.andOperator(Criteria.where("dayOfDeparture").is(booking.getDayOfDeparture()))
		);

		DeleteResult deleteResult = mongoTemplate.remove(query, Booking.class, "bookings");

		return deleteResult;
	}


	public Date Date(String dateStr /**dd/mm/yyyy*/) throws ParseException {

		String dateString = dateStr;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf.parse(dateString);
		return date;
	}


	private void assertInsertSucceeds(ConfigurableApplicationContext context) {
		String name = "A";

		MongoTemplate mongo = context.getBean(MongoTemplate.class);
		Document doc = Document.parse("{\"name\":\"" + name + "\"}");
		Document inserted = mongo.insert(doc, "items");

		assertNotNull(inserted.get("_id"));
		assertEquals(inserted.get("name"), name);

		mongo.dropCollection("items");
	}


	public void AssertMakeBooking(ConfigurableApplicationContext context) throws ParseException {
		MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

//		DateTimeService dtService = new DateTimeService();
//		TransactionsService trService = new TransactionsService();
//		Booking booking = new Booking();

//		booking.setId(new ObjectId()); booking.madeBy("Tebogo@gmail.com");
//		booking.setBookingDate(dtService.getDateTimeNow()); booking.setRoomNumber(RoomNumber.ONE);
//		booking.setDayofVisit("3/2/2023"); booking.setDayOfDeparture("4/2/2023");
//		booking.setAmount(trService.From(Date("3/2/2023")).To(Date("4/2/2023")).Costs());
		String email = "Tebogo@gmail.com";

		Document doc = Document.parse("{\"email\":\"" + email + "\"}");
		Document savedDoc = mongoTemplate.insert(doc, "bookings");

		assertSame("Tebogo@gmail.com", savedDoc.get("email"));

//		assertTrue(cancelBooking(booking, mongoTemplate).wasAcknowledged());
	}





}
