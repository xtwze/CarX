import org.example.*;
import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    public void testRegisterUser() {
        // ���������� ������
        CarRentalSystem system = new CarRentalSystem();
        String userId = "U1";
        String userName = "Test User";
        String driverLicense = "DL123456";
        double initialRate = 5.0;

        // ����������
        system.registerUser(userId, userName, driverLicense, initialRate);
        User registeredUser = system.getUser(userId);

        // ��������
        assertNotNull(registeredUser, "������������ ������ ���� ���������������");
        assertEquals(userId, registeredUser.getId(), "ID ������������ ������ ���������");
        assertEquals(userName, registeredUser.getName(), "��� ������������ ������ ���������");
        assertEquals(driverLicense, registeredUser.getDriverLicense(), "����� ���� ������ ���������");
        assertEquals(initialRate, registeredUser.getRate(), 0.001, "������� ������ ���� ����������");
    }


    @Test
    public void testAddCar() {
        // ����������
        CarRentalSystem system = new CarRentalSystem();
        Car car = new Car("C1", "Toyota Camry", "�������", 0);

        // ����������
        system.addCar(car);

        // ��������
        assertTrue(system.getCar("C1") != null, "���������� ������ ���� �������� � �������");
    }


    @Test
    public void testRentCar() {
        // ����������
        CarRentalSystem system = new CarRentalSystem();
        system.registerUser("U1", "����", "DL111", 5.0);
        system.addCar(new Car("C1", "Kia Rio", "������", 0));
        int minutes = 180;

        // ����������
        Rental rental = system.rentCar("U1", "C1", minutes);

        // ��������
        assertNotNull(rental, "������ ��������� ������ ������");
        assertEquals("U1", rental.getUser().getId(), "ID ������������ ������ ���������");
        assertEquals("C1", rental.getCar().getId(), "ID ���������� ������ ���������");
        assertEquals(minutes, rental.getMinutes(), "������������ ������ ������ ���������");
        assertFalse(rental.getCar().isAvailable(), "���������� ������ ���� ������� ��� �������");
    }
    @Test
    public void testCompleteRental() {
        // ����������
        CarRentalSystem system = new CarRentalSystem();
        system.registerUser("U1", "����", "DL222", 6.0);
        system.addCar(new Car("C1", "Hyundai Solaris", "������", 0));
        Rental rental = system.rentCar("U1", "C1", 120);
        int mileage = 85;
        boolean hasViolations = false;

        // ����������
        system.completeRental(rental, mileage, hasViolations);

        // ��������
        assertTrue(rental.getCar().isAvailable(), "���������� ������ ���� ����� ��������");
        assertEquals(6.5, rental.getUser().getRate(), 0.001, "������� ������ ����������� �� 0.5");
    }
    @Test
    public void testGetTotalCost() {
        // ����������
        User user = new User("U1", "�����", 8.0, "DL333");
        Car car = new Car("C1", "BMW X5", "�������", 30.0);
        int minutes = 90;
        Rental rental = new Rental(user, car, minutes);

        // ���������� � ��������
        assertEquals(2295.0, rental.getTotalCost(), 0.001,
                "��������� ������ ���� 30*90*0.85 (15% ������ �� ������� 8.0)");
    }

}