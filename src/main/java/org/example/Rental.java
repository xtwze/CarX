public class Rental {
    private User user;
    private Car car;
    private int minutes;

    public Rental(User user, Car car, int minutes) {
        this.user = user;
        this.car = car;
        this.minutes = minutes;
    }
    public User getUser() { return user; }
    public Car getCar() { return car; }
    public int getMinutes() { return minutes; }


    // User story: расчет стоимости аренды
    public double getTotalCost() {
        double baseCost = car.getPricePerMinute() * minutes;
        return baseCost * getRateMultiplier(); // Учет рейтинга
    }
    // система рейтинга
    private double getRateMultiplier() {
        double rate = user.getRate();
        // User story: влияние рейтинга на стоимость
        if (rate >= 9) return 0.75;
        if (rate >= 7) return 0.85;
        if (rate >= 5) return 1.0;
        if (rate >= 3) return 1.25;
        return 2.0;
    }
}