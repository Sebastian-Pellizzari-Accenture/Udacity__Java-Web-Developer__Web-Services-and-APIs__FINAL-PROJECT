package com.udacity.pricing;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.udacity.pricing.domain.price.Price;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * ASSUMES that the Eureka Server is already running along witht the  VehiclesApiApplication
 * 		and BoogleMapsApplication. Also assumes that at least 1 car was already created using 
 * 		POST http://localhost:8080/cars.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceTests {
    @Autowired
    private TestRestTemplate restTemplate;
	
    @LocalServerPort
    private int port;

	private String base_path;

	@BeforeEach 
	public void init(){
		base_path = "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

    @Test
    public void getPriceValid() throws Exception {
		ResponseEntity<Price> response = restTemplate.getForEntity(base_path + "/prices/1", Price.class);
		// check if the response was okay
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		// check that we got something else than null
		Price price = response.getBody();
		assertNotNull(price);
		String view = price_to_string(price); // to see the relevant content of the response in the debugger
		assertNotNull(price.getCurrency());
		assertNotNull(price.getPrice());
    }

	/**
	 * ASSUMES that no car exists with the id 404 which is not planned for this application anyways
	 * 		as the given code and the altered microservice were implemented to only hold 20 price 
	 * 		values.
	 *  */  
	@Test
    public void getPriceIn__valid() throws Exception {
		ResponseEntity<Price> response = restTemplate.getForEntity(base_path + "/prices/404", Price.class);
		// check if the response was okay
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

	private String price_to_string(Price price) {
		return "{" +
			"currency='" + price.getCurrency() + '\'' +
			", price=" + price.getPrice() +
		'}';
	}
}
