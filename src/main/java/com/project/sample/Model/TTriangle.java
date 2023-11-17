package com.project.sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

public class TTriangle extends TShape {
    private double[] xp = new double[3];
    private double[] yp = new double[3];


    public TTriangle(double xpos, double ypos, double height, double width, ShapeType type) {
        super(xpos, ypos, height, width, type);
    }

    private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2))/2.0);
    }

    private double distanceBetween2Points(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    @Override
    public void drawShape(GraphicsContext gc, Color strokeColor, Color fillColor) {
        gc.setStroke(strokeColor);
        gc.setFill(fillColor);
        this.fillColor=fillColor;
        this.strokeColor=strokeColor;
        xp[0] = x;
        xp[1] = x;
        xp[2] = x+width;
        yp[0] = y;
        yp[1] = y+height;
        yp[2] = y+height;
        gc.fillPolygon(xp, yp, 3);
    }

    @Override
    public boolean containsPoint(double xpos, double ypos) {
        double A = area(xp[0], yp[0], xp[1], yp[1], xp[2], yp[2]);
        double A1 = area(xpos, ypos, xp[1], yp[1], xp[2], yp[2]);
        double A2 = area(xp[0], yp[0], xpos, ypos, xp[2], yp[2]);
        double A3 = area(xp[0], yp[0], xp[1], yp[1], xpos, ypos);
        return (A == A1 + A2 + A3);
    }

    @Override
    protected  void calculateArea() {
        this.area = 0.5*height*width;
    }

    @Override
    protected void calculatePerimeter() {
        double a = distanceBetween2Points(xp[0], xp[1], yp[0], yp[1]);
        double b = distanceBetween2Points(xp[1], xp[2], yp[1], yp[2]);
        double c = distanceBetween2Points(xp[2], xp[0], yp[2], yp[0]);
        this.perimeter = a + b + c;
    }
}
