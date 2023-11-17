package com.project.sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TRectangle extends TShape {

    public TRectangle(double xpos, double ypos, double height, double width, ShapeType type) {
        super(xpos, ypos, height, width, type);
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
        gc.fillRect(x, y, width, height);
    }

    @Override
    public boolean containsPoint(double xpos, double ypos) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = this.getX() + this.getWidth();
        double y2 = this.getY() + this.getHeight();
        return (x1 <= xpos && xpos <= x2 && y1 <= ypos && ypos <= y2);
    }
}
