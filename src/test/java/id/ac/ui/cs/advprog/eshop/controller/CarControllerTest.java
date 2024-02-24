package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    private Car testCar;

    @BeforeEach
    void setUp() {
        testCar = new Car();
        testCar.setCarId("1");
        testCar.setCarName("Test Car");
        testCar.setCarColor("Blue");
        testCar.setCarQuantity(1);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        verify(model).addAttribute(eq("car"), any(Car.class));
        assertEquals("createCar", viewName);
    }

    @Test
    void testCreateCarPost() {
        String viewName = carController.createCarPost(testCar, model);
        verify(carService).create(testCar);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void testCarListPage() {
        when(carService.findAll()).thenReturn(List.of(testCar));
        String viewName = carController.carListPage(model);
        verify(model).addAttribute("cars", List.of(testCar));
        assertEquals("carList", viewName);
    }

    @Test
    void testEditCarPage() {
        when(carService.findById(anyString())).thenReturn(testCar);
        String viewName = carController.editCarPage("1", model);
        verify(model).addAttribute("car", testCar);
        assertEquals("editCar", viewName);
    }

    @Test
    void testEditCarPost() {
        String viewName = carController.editCarPost(testCar, model);
        verify(carService).update(testCar.getCarId(), testCar);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void testDeleteCar() {
        String viewName = carController.deleteCar(testCar.getCarId());
        verify(carService).deleteCarById(testCar.getCarId());
        assertEquals("redirect:listCar", viewName);
    }
}