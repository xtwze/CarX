import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    void testRegisterUser() {
        // Подготовка данных
        CarRentalSystem system = new CarRentalSystem();
        String userId = "U1";
        String userName = "Test User";
        String driverLicense = "DL123456";
        double initialRate = 5.0;

        // Выполнение
        system.registerUser(userId, userName, driverLicense, initialRate);
        User registeredUser = system.getUser(userId);

        // Проверки
        assertNotNull(registeredUser, "Пользователь должен быть зарегистрирован");
        assertEquals(userId, registeredUser.getId(), "ID пользователя должно совпадать");
        assertEquals(userName, registeredUser.getName(), "Имя пользователя должно совпадать");
        assertEquals(driverLicense, registeredUser.getDriverLicense(), "Номер прав должен совпадать");
        assertEquals(initialRate, registeredUser.getRate(), 0.001, "Рейтинг должен быть установлен");
    }


    @Test
    void testAddCar() {
        // Подготовка
        CarRentalSystem system = new CarRentalSystem();
        Car car = new Car("C1", "Toyota Camry", "комфорт", 0);

        // Выполнение
        system.addCar(car);

        // Проверки
        assertEquals(15.0, car.getPricePerMinute(), 0.001, "Цена для 'комфорт' должна быть 15 руб/мин");
        assertTrue(system.getCar("C1") != null, "Автомобиль должен быть добавлен в систему");
    }


    @Test
    void testRentCar() {
        // Подготовка
        CarRentalSystem system = new CarRentalSystem();
        system.registerUser("U1", "Иван", "DL111", 5.0);
        system.addCar(new Car("C1", "Kia Rio", "эконом", 0));
        int minutes = 180;

        // Выполнение
        Rental rental = system.rentCar("U1", "C1", minutes);

        // Проверки
        assertNotNull(rental, "Должен вернуться объект аренды");
        assertEquals("U1", rental.getUser().getId(), "ID пользователя должно совпадать");
        assertEquals("C1", rental.getCar().getId(), "ID автомобиля должно совпадать");
        assertEquals(minutes, rental.getMinutes(), "Длительность аренды должна совпадать");
        assertFalse(rental.getCar().isAvailable(), "Автомобиль должен быть помечен как занятый");
    }
    @Test
    void testCompleteRental() {
        // Подготовка
        CarRentalSystem system = new CarRentalSystem();
        system.registerUser("U1", "Петр", "DL222", 6.0);
        system.addCar(new Car("C1", "Hyundai Solaris", "эконом", 0));
        Rental rental = system.rentCar("U1", "C1", 120);
        int mileage = 85;
        boolean hasViolations = false;

        // Выполнение
        system.completeRental(rental, mileage, hasViolations);

        // Проверки
        assertTrue(rental.getCar().isAvailable(), "Автомобиль должен быть снова доступен");
        assertEquals(6.5, rental.getUser().getRate(), 0.001, "Рейтинг должен увеличиться на 0.5");
    }
    @Test
    void testGetTotalCost() {
        // Подготовка
        User user = new User("U1", "Ольга", 8.0, "DL333");
        Car car = new Car("C1", "BMW X5", "премиум", 30.0);
        int minutes = 90;
        Rental rental = new Rental(user, car, minutes);

        // Выполнение и проверка
        assertEquals(2295.0, rental.getTotalCost(), 0.001,
                "Стоимость должна быть 30*90*0.85 (15% скидка за рейтинг 8.0)");
    }

}