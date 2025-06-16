import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    private List<User> users = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public void registerUser(String id, String name, String driverLicense, double initialRate){
        users.add(new User(id, name, initialRate, driverLicense));
    }
    public User getUser(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public Car getCar(String carId) {
        for (Car car : cars) {
            if (car.getId().equals(carId)) {
                return car;
            }
        }
        return null;
    }


    public void addCar(Car car) {
        switch(car.getCarClass().toLowerCase()) {
            case "эконом":
                car.setPricePerMinute(7.0);
                break;
            case "комфорт":
                car.setPricePerMinute(15.0);
                break;
            case "премиум":
                car.setPricePerMinute(30.0);
                break;
            case "грузовые":
                car.setPricePerMinute(20.0);
                break;
        }
        cars.add(car);
    }
    //Оформляет аренду автомобиля.
    public Rental rentCar(String userId, String carId, int minutes) {
        User user = findUserById(userId);
        Car car = findCarById(carId);

        if (user == null || car == null || !car.isAvailable()) {
            return null;
        }

        car.setAvailable(false);
        Rental rental = new Rental(user, car, minutes);
        rentals.add(rental);
        return rental;
    }

    //Завершает аренду и корректирует рейтинг:
    // Нарушения: -1.0 к рейтингу
    //Аккуратная езда: +0.5 к рейтингу
    public void completeRental(Rental rental, int mileage, boolean hasViolations) {
        rental.getCar().setAvailable(true);
        double newRate = rental.getUser().getRate() + (hasViolations ? -1.0 : 0.5);
        rental.getUser().setRate(newRate);
    }

    // Вспомогательные методы
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) return user;
        }
        return null;
    }

    private Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getId().equals(carId)) return car;
        }
        return null;
    }
}

