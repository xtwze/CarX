package org.example;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem system = new CarRentalSystem();

        // Регистрация пользователя (user story: регистрация)
        system.registerUser("user1", "Иван Иванов", "DL123456", 5.0);

        // Добавление авто (user story: каталог с разными тарифами)
        system.addCar(new Car("х777хх 777", "Kia Rio", "эконом", 0));
        system.addCar(new Car("х111хх 111", "BMW X5", "премиум", 0));

        // Аренда (user story: бронирование и расчет стоимости)
        Rental rental = system.rentCar("user1", "х111хх 111", 180); // 3 часа

        if (rental != null) {
            System.out.printf("Стоимость аренды: %.2f руб.%n", rental.getTotalCost());

            // Завершение поездки (user story: отчетность и рейтинг)
            system.completeRental(rental, 120, false);
            System.out.println("Новый рейтинг: " +
                    system.findUserById("user1").getRate());
        }
    }
}