package com.udacity.pricing;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceIntegrationTest {

	/* @Test
	public void contextLoads() {

	} */
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	// TODO: Add an additional test for the Pricing Service microservice.
	 @Test
    public void alsoDontKnowYet() {
		ResponseEntity<List> response =
                  this.restTemplate.getForEntity("http://localhost:" + port + "/cars", List.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}


}
