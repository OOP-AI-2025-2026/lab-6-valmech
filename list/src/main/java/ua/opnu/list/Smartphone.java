package ua.opnu.list;

class Smartphone implements GPS, Cellular {

    private String model;
    private double latitude;
    private double longitude;

    public Smartphone(String model, double latitude, double longitude) {
        this.model = model;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Реалізація методу з інтерфейсу GPS
    @Override
    public double[] getCoordinates() {
        return new double[]{latitude, longitude};
    }

    // Реалізація методів з інтерфейсу Cellular
    @Override
    public void makeCall() {
        System.out.println(model + " здійснюємо виклик...");
    }

    @Override
    public void receiveCall() {
        System.out.println(model + " приймаємо виклик...");
    }

    // Додатковий метод для виведення інформації
    public void showInfo() {
        System.out.println("Модель телефону: " + model);
        System.out.println("Поточні координати: (" + latitude + ", " + longitude + ")");
    }
}