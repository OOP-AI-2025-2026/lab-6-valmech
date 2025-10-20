package ua.opnu.list;

public class MainForCar {
    public static void main(String[] args) {
        Car car1 = new Car(15000, 2020, 130);
        Car car2 = new Car(15000, 2020, 150);
        Car car3 = new Car(12000, 2019, 110);

        compareCars(car1, car2);
        compareCars(car1, car3);
        compareCars(car2, car3);
    }

    // Метод для зручного відображення результатів порівняння
    private static void compareCars(Car c1, Car c2) {
        int result = c1.compareTo(c2);
        System.out.println("Порівняння автомобілів:");
        System.out.println(c1);
        System.out.println(c2);

        if (result > 0) {
            System.out.println("Перший автомобіль вважається БІЛЬШИМ за другий\n");
        } else if (result < 0) {
            System.out.println("Другий автомобіль вважається БІЛЬШИМ за перший\n");
        } else {
            System.out.println("Автомобілі рівні за параметрами\n");
        }
    }
}