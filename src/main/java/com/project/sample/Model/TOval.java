package com.project.sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TOval extends TShape {

    private double centerX;
    private double centerY;

    public TOval(double xpos, double ypos, double height, double width, ShapeType type) {
        super(xpos, ypos, height, width, type);
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    private double distanceBetween2Points(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
    }

    @Override
    public void drawShape(GraphicsContext gc, Color strokeColor, Color fillColor) {
        gc.setStroke(strokeColor);
        gc.setFill(fillColor);
        gc.fillOval(x, y, width, height);
        this.fillColor=fillColor;
        this.strokeColor=strokeColor;
        this.centerX = x + width / 2;
        this.centerY = y + height /2;
    }

    @Override
    public boolean containsPoint(double xpos, double ypos) {
        double h = this.centerX;
        double k = this.centerY;
        double a = this.height / 2;
        double b = this.width /2;
        double val = (Math.pow(xpos-h, 2)/Math.pow(a, 2)) + (Math.pow(ypos-k, 2)/Math.pow(b, 2));

        return val <= 1;
    }

    @Override
    protected void calculateArea() {
        double a = this.height / 2;
        double b = this.width / 2;
        this.area = a*b*Math.PI;
    }

    @Override
    protected void calculatePerimeter() {
        double a = this.height / 2;
        double b = this.width / 2;
        double m = Math.pow((a - b), 2)/Math.pow((a + b), 2);
        this.perimeter = Math.PI*(a + b)*(1 +(3*m /(10 + Math.sqrt(4 - 3*m))));
    }
}
