package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car testCar;
    private String testCarId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCar = new Car();
        testCarId = UUID.randomUUID().toString();
        testCar.setCarId(testCarId);
        testCar.setCarName("Test Car");
        testCar.setCarColor("Black");
        testCar.setCarQuantity(1);
    }

    @Test
    void testCreateCar() {
        when(carRepository.create(testCar)).thenReturn(testCar);
        Car result = carService.create(testCar);
        verify(carRepository).create(testCar);
        assertNotNull(result);
        assertEquals(testCarId, result.getCarId());
    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(testCar).iterator());
        List<Car> result = carService.findAll();
        verify(carRepository).findAll();
        assertFalse(result.isEmpty());
        assertEquals(testCarId, result.get(0).getCarId());
    }

    @Test
    void testFindById() {
        when(carRepository.findById(testCarId)).thenReturn(testCar);
        Car result = carService.findById(testCarId);
        verify(carRepository).findById(testCarId);
        assertNotNull(result);
        assertEquals(testCarId, result.getCarId());
    }

    @Test
    void testUpdateCar() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Updated Test Car");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(2);

        when(carRepository.update(testCarId, updatedCar)).thenReturn(updatedCar);
        carService.update(testCarId, updatedCar);
        verify(carRepository).update(testCarId, updatedCar);
    }

    @Test
    void testDeleteCarById() {
        doNothing().when(carRepository).delete(testCarId);
        carService.deleteCarById(testCarId);
        verify(carRepository).delete(testCarId);
    }
}