package ua.opnu.list;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ellipse extends DrawShape {
    public Ellipse() {}

    public Ellipse(java.awt.Point startPoint, java.awt.Point endPoint) {
        super(startPoint, endPoint);
    }
    @Override
    public Shape getShape(java.awt.Point startPoint, Point endPoint) {
        return new Ellipse2D.Double(
                Math.min(startPoint.getX(), endPoint.getX()),  //координата верхнього лівого кута
                Math.min(startPoint.getY(), endPoint.getY()),  //координата верхнього лівого кута
                Math.abs(startPoint.getX() - endPoint.getX()), //ширина
                Math.abs(startPoint.getY() - endPoint.getY())  //висота
        );
    }
}
