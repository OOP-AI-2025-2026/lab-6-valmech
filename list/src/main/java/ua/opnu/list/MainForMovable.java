package ua.opnu.list;

public class MainForMovable {
    public static void main(String[] args) {
        Point point = new Point(2.0, 3.0);
        System.out.println("Начальные координаты: (" + point.getX() + ", " + point.getY() + ")");

        point.moveTo(5.5, 7.2);
        System.out.println("Обновленные координаты: (" + point.getX() + ", " + point.getY() + ")");
    }
}
