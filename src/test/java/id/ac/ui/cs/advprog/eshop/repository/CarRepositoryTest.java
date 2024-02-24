package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.UUID;

class CarRepositoryTest {

    private CarRepository carRepository;
    private Car testCar;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
        testCar = new Car();
        testCar.setCarName("Test Car");
        testCar.setCarColor("Black");
        testCar.setCarQuantity(1);
    }

    @Test
    void testCreateCar() {
        Car createdCar = carRepository.create(testCar);
        assertNotNull(createdCar.getCarId());
        assertEquals("Test Car", createdCar.getCarName());
    }

    @Test
    void testCreateCarWithPredefinedId() {
        String predefinedId = UUID.randomUUID().toString();
        testCar.setCarId(predefinedId);
        Car createdCar = carRepository.create(testCar);
        assertEquals(predefinedId, createdCar.getCarId());
        assertEquals("Test Car", createdCar.getCarName());
    }

    @Test
    void testFindAllCars() {
        carRepository.create(testCar);
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car foundCar = carIterator.next();
        assertEquals("Test Car", foundCar.getCarName());
    }

    @Test
    void testFindById() {
        Car createdCar = carRepository.create(testCar);
        Car foundCar = carRepository.findById(createdCar.getCarId());
        assertNotNull(foundCar);
        assertEquals("Test Car", foundCar.getCarName());
    }

    @Test
    void testFindByIdNonExisting() {
        carRepository.create(testCar);
        Car foundCar = carRepository.findById(UUID.randomUUID().toString());
        assertNull(foundCar);
    }

    @Test
    void testUpdateCar() {
        Car createdCar = carRepository.create(testCar);
        Car updatedCar = new Car();
        updatedCar.setCarName("Updated Car");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(2);
        Car resultCar = carRepository.update(createdCar.getCarId(), updatedCar);
        assertNotNull(resultCar);
        assertEquals("Updated Car", resultCar.getCarName());
        assertEquals("Red", resultCar.getCarColor());
        assertEquals(2, resultCar.getCarQuantity());
    }

    @Test
    void testUpdateNonExistingCar() {
        carRepository.create(testCar);
        Car nonExistingCar = new Car();
        nonExistingCar.setCarId(UUID.randomUUID().toString());
        Car resultCar = carRepository.update(nonExistingCar.getCarId(), nonExistingCar);
        assertNull(resultCar);
    }

    @Test
    void testDeleteCar() {
        Car createdCar = carRepository.create(testCar);
        carRepository.delete(createdCar.getCarId());
        assertNull(carRepository.findById(createdCar.getCarId()));
    }
}