package ua.opnu.list;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle extends DrawShape {

    public Rectangle() {
    }

    public Rectangle(java.awt.Point startPoint, java.awt.Point endPoint) {
        super(startPoint, endPoint);
    }

    // Для створення прямокутника застосовується клас Rectangle2D.Double (внутрішній клас Double класу Rectangle2D).
    // Клас Rectangle2D.Double працює з координатами типу Double
    @Override
    public Shape getShape(java.awt.Point startPoint, Point endPoint) {
        return new Rectangle2D.Double(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()),
                Math.abs(startPoint.getX() - endPoint.getX()), Math.abs(startPoint.getY() - endPoint.getY()));
    }
}