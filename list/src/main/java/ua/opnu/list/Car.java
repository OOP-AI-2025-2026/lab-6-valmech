package ua.opnu.list;
public class Car implements Comparable {

    private int price;
    private int year; // рік виготовлення
    private int horsePower;

    // Конструктор з трьома аргументами
    public Car(int price, int year, int horsePower) {
        this.price = price;
        this.year = year;
        this.horsePower = horsePower;
    }

    // Гетери та сетери
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    // Реалізація методу compareTo() з логікою з умови
    @Override
    public int compareTo(Object obj) {
        if (!(obj instanceof Car)) {
            throw new IllegalArgumentException("Object is not a Car");
        }

        Car other = (Car) obj;

        // 1️⃣ Спочатку порівнюємо ціну (менша ціна — «краще»)
        if (this.price != other.price) {
            return (this.price < other.price) ? 1 : -1;
        }

        // 2️⃣ Якщо ціни рівні — порівнюємо рік (новіший — «краще»)
        if (this.year != other.year) {
            return (this.year > other.year) ? 1 : -1;
        }

        // 3️⃣ Якщо рік однаковий — порівнюємо кінські сили (більше — «краще»)
        if (this.horsePower != other.horsePower) {
            return (this.horsePower > other.horsePower) ? 1 : -1;
        }

        // 4️⃣ Якщо всі параметри однакові
        return 0;
    }

    // Для зручного виведення інформації
    @Override
    public String toString() {
        return "Car{price=" + price + ", year=" + year + ", horsePower=" + horsePower + "}";
    }
}