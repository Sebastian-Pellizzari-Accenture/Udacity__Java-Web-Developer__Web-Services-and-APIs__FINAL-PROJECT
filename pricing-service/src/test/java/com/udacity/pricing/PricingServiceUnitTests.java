package com.udacity.pricing;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// @SpringBootTest
// TODO: Add an additional test for the Pricing Service microservice.
@WebMvcTest(PricingController.class)
public class PricingServiceUnitTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PricingService pricingService;


	// running eureka server, vehicleapi and BoogleMaps in the background
	// 			also manually added some cars using POST ../cars
	@Test
	public void testInvalidID() throws Exception {
		mockMvc.perform(get("/services/price").param("vehicleId", "-1"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testvalidID() throws Exception {
		mockMvc.perform(get("/services/price").param("vehicleId", "1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));
	}
	
	

}
