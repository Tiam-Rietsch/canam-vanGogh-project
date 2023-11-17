package com.project.sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

public class TKite extends TShape {
    private double[] xp = new double[4];
    private double[] yp = new double[4];


    public TKite(double xpos, double ypos, double height, double width, ShapeType type) {
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
        xp[0] = x;
        xp[1] = x+width/2;
        xp[2] = x+width;
        xp[3] = x+width/2;
        yp[0] = y+height/2;
        yp[1] = y;
        yp[2] = y+height/2;
        yp[3] = y+height;
        gc.fillPolygon(xp, yp, 4);
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
