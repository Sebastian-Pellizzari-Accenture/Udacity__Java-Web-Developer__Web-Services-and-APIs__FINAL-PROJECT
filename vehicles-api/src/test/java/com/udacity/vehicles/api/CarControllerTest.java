package com.udacity.vehicles.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

// rm
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Implements testing of the CarController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    @MockitoBean
    private CarService carService;

    @MockitoBean
    private PriceClient priceClient;

    @MockitoBean
    private MapsClient mapsClient;

    /**
     * Creates pre-requisites for testing, such as an example car.
     */
    @BeforeEach
    public void setup() {
        Car car = getCar();
        car.setId(1L);
        given(carService.save(any())).willReturn(car);
        given(carService.findById(any())).willReturn(car);
        given(carService.list()).willReturn(Collections.singletonList(car));
    }

    /**
     * Tests for successful creation of new car in the system
     * @throws Exception when car creation fails in the system
     */
    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        mvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    /**
     * Tests if the read operation appropriately returns a list of vehicles.
     * @throws Exception if the read operation of the vehicle list fails
     */
    @Test
    public void listCars() throws Exception {
        /**
         * TODO: Add a test to check that the `get` method works by calling
         *   the whole list of vehicles. This should utilize the car from `getCar()`
         *   below (the vehicle will be the first in the list).
         */
        // NOTE: the get car is already inserted and used due to the before each condition.
        Car car = getCar();
        ResultActions result = mvc.perform(get(new URI("/cars")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/hal+json"))
            // as the CollectionModel<EntityModel<Car>> is internally implemented as an object 
            // containing a list and NOT a list
            .andExpect(content().json("{}"))
            // check if there is exactly one element in the list
            .andExpect(jsonPath("$._embedded.carList.length()").value(1))
            // and check if the content of the element exactly matches the content from the example car below         
            // to know what we actually get
            .andDo(print());  
        verifySingleCarList(result, car, "$._embedded.carList[0]");
        verify(carService).list();  // verify that the list method was called exactly once !
    }

    /**
     * Tests the read operation for a single car by ID.
     * @throws Exception if the read operation for a single car fails
     */
    @Test
    public void findCar() throws Exception {
        /**
         * TODO: Add a test to check that the `get` method works by calling
         *   a vehicle by ID. This should utilize the car from `getCar()` below.
         */
         // NOTE: the get car is already inserted and used due to the before each condition.
        Car car = getCar();

        ResultActions result = mvc.perform(get(new URI("/cars/1")))    // as we added exacly one car
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/hal+json"))
            // as the CollectionModel<EntityModel<Car>> is internally implemented as an object 
            // containing a list and NOT a list
            .andExpect(content().json("{}"))
            .andDo(print());
        verifySingleCarList(result, car, "$");
        verify(carService).findById(1L);  // verify that the findById method was called exactly once !
    }

    /**
     * Tests the deletion of a single car by ID.
     * @throws Exception if the delete operation of a vehicle fails
     */
    @Test
    public void deleteCar() throws Exception {
        /**
         * TODO: Add a test to check whether a vehicle is appropriately deleted
         *   when the `delete` method is called from the Car Controller. This
         *   should utilize the car from `getCar()` below.
         */

        // remove the car
        mvc.perform(delete(new URI("/cars/1")))
            .andExpect(status().isNoContent())
            .andDo(print());
        verify(carService).delete(1L);   
    }

    private void verifySingleCarList(ResultActions result, Car car, String prefix) throws Exception {
         result.andExpect(jsonPath(prefix + ".condition").value(car.getCondition().toString()))
            .andExpect(jsonPath(prefix + ".details.model").value(car.getDetails().getModel()))
            .andExpect(jsonPath(prefix + ".details.manufacturer.name").value(car.getDetails().getManufacturer().getName()))
            .andExpect(jsonPath(prefix + ".details.manufacturer.code").value(car.getDetails().getManufacturer().getCode()))
            .andExpect(jsonPath(prefix + ".details.numberOfDoors").value(car.getDetails().getNumberOfDoors()))
            .andExpect(jsonPath(prefix + ".details.fuelType").value(car.getDetails().getFuelType()))
            .andExpect(jsonPath(prefix + ".details.engine").value(car.getDetails().getEngine()))
            .andExpect(jsonPath(prefix + ".details.mileage").value(car.getDetails().getMileage()))
            .andExpect(jsonPath(prefix + ".details.modelYear").value(car.getDetails().getModelYear()))
            .andExpect(jsonPath(prefix + ".details.productionYear").value(car.getDetails().getProductionYear()))
            .andExpect(jsonPath(prefix + ".details.externalColor").value(car.getDetails().getExternalColor()))
            .andExpect(jsonPath(prefix + ".details.body").value(car.getDetails().getBody()))
            .andExpect(jsonPath(prefix + ".location.lat").value(car.getLocation().getLat()))
            .andExpect(jsonPath(prefix + ".location.lon").value(car.getLocation().getLon()));
    }

    /**
     * Creates an example Car object for use in testing.
     * @return an example Car object
     */
    private Car getCar() {
        Car car = new Car();
        car.setLocation(new Location(40.730610, -73.935242));
        Details details = new Details();
        Manufacturer manufacturer = new Manufacturer(101, "Chevrolet");
        details.setManufacturer(manufacturer);
        details.setModel("Impala");
        details.setMileage(32280);
        details.setExternalColor("white");
        details.setBody("sedan");
        details.setEngine("3.6L V6");
        details.setFuelType("Gasoline");
        details.setModelYear(2018);
        details.setProductionYear(2018);
        details.setNumberOfDoors(4);
        car.setDetails(details);
        car.setCondition(Condition.USED);
        return car;
    }
}
