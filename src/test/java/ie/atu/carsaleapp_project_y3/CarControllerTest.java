package ie.atu.carsaleapp_project_y3;


import ie.atu.carsaleapp_project_y3.controller.CarController;
import ie.atu.carsaleapp_project_y3.entity.Car;
import ie.atu.carsaleapp_project_y3.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class) //focuses on testing the CarController
public class CarControllerTest {
@Autowired
    private MockMvc mockMvc;

@MockBean
    private CarService carService;    //mock version of carService

private Car createCarTest() {
    Car car = new Car();
    car.setCar_id(1);
    car.setMake("Toyota");
    car.setModel("Camry");
    car.setYear(2021);
    car.setCost(5000.50);
    return car;
}
@Test
    public void testGetAllCars() throws Exception{
    Car car =createCarTest();
    when(carService.getAllCars()).thenReturn(Collections.singletonList(car));

    mockMvc.perform(MockMvcRequestBuilders.get("/cars/allcars"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].car_id").value(car.getCar_id()));
}

@Test
    public void testGetCarById() throws Exception{
    Car car = createCarTest();
    when(carService.getCarById(1L)).thenReturn(Optional.of(car));

    mockMvc.perform(MockMvcRequestBuilders.get("/cars/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.car_id").value((car.getCar_id())));
}

@Test
    public void testGetCarById_NotFound() throws Exception{
    when(carService.getCarById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(MockMvcRequestBuilders.get("/cars/1"))
            .andExpect(status().isNotFound());
}
@Test
    public void testAddCar() throws Exception{
    Car car = createCarTest();
    when(carService.addCar(any(Car.class))).thenReturn(car);

    mockMvc.perform(MockMvcRequestBuilders.post("/cars/addCar")
                    .contentType("application/json")
                    .content("{\"car_id\":1,\"make\":\"Toyota\",\"model\":\"Camry\",\"year\":2021,\"cost\":5000.50}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.car_id").value(car.getCar_id()));

}


}
