package ua.opnu.list;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;

public class RoundedRectangle extends DrawShape {

    public RoundedRectangle() {}

    public RoundedRectangle(java.awt.Point startPoint, java.awt.Point endPoint) {
        super(startPoint, endPoint);
    }

    // Для створення прямокутника зі скругленими кутами застосовується клас RoundRectangle2D.Double
    // (Внутрішній клас Double класу RoundRectangle2D).
    // Клас RoundRectangle2D.Double працює з координатами типу Double.
    @Override
    public Shape getShape(java.awt.Point startPoint, Point endPoint) {
        return new RoundRectangle2D.Double(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()),
                Math.abs(startPoint.getX() - endPoint.getX()), Math.abs(startPoint.getY() - endPoint.getY()), 55.0, 55.0);
    }
}