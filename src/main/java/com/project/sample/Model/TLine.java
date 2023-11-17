package com.project.sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TLine extends TShape {

    public TLine(double xpos, double ypos, double height, double width, ShapeType type) {
        super(xpos, ypos, height, width, type);
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
        gc.setLineWidth(2);
        this.strokeColor=strokeColor;        gc.setLineDashes(0);
        gc.strokeLine(x, y, x+width, y+height);
    }

    @Override
    public boolean containsPoint(double xpos, double ypos) {
        double slope;
        double intercept;
        slope = (y - (y+height))/(x - (x+width));
        intercept = y - (slope*x);

        System.out.println("y = " + ypos + "   mx+b = " + (slope*xpos + intercept));
        return Math.floor(ypos) == Math.floor(slope*xpos + intercept);
    }

    @Override
    protected  void calculateArea() {
        this.area = 0.0;
    }

    @Override
    protected void calculatePerimeter() {
        this.perimeter = distanceBetween2Points(x, x+width, y, y+height);
    }
}
