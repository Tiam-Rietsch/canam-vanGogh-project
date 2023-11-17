package com.project.sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class TShape {
    protected double x;
    protected double y;
    protected double height;
    protected double width;
    protected double area;
    protected double perimeter;
    protected TRectangle resizeRectangle;
    protected ShapeType shapeType;
    protected Color fillColor;
    protected Color strokeColor;

    public TShape(double xpos, double ypos, double height, double width, ShapeType type) {
        this.x = xpos;
        this.y = ypos;
        this.height = height;
        this.width = width;
        this.shapeType = type;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public void draw(GraphicsContext gc, Color strokeColor, Color fillColor, boolean isSelected) {

        drawShape(gc, strokeColor, fillColor);

        System.out.println(isSelected);
        if (isSelected) {
            drawSelectionRect(gc);
            drawResizeRect(gc);
        }

        calculatePerimeter();
        calculateArea();
    }

    public void updateEndCoordinates(double xEnd, double yEnd) {
        if (yEnd - this.y < 0) {
            updateStartY(yEnd);
            System.out.print("Start: " + yEnd + " Height: " + this.height);
        }

        if (xEnd - this.x < 0) {
            updateStartX(xEnd);
        }

        this.height = Math.abs(yEnd - this.y);
        this.width = Math.abs(xEnd - this.x);

    }

    public void updateStartX(double xStart) {
        this.x = xStart;
    }

    public void updateStartY(double yStart) {
        this.y = yStart;
    }

    public void updateStartCoordinates(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public Color getFillColor() {
        return this.fillColor;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    public TShape getResizeRect() {
        return this.resizeRectangle;
    }

    public double getArea() {
        return this.area;
    }

    public double getPerimeter() {
        return this.perimeter;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setX(double xpos) {
        this.x = xpos;
    }

    public void setY(double ypos) {
        this.y = ypos;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    private void drawResizeRect(GraphicsContext gc) {
        resizeRectangle = new TRectangle(x +width, y+height, 10, 10, shapeType);
        resizeRectangle.drawShape(gc, Color.GOLD, Color.GOLD);
    }

    private void drawSelectionRect(GraphicsContext gc) {
        gc.setStroke(Color.CRIMSON);
        gc.setLineWidth(1);
        gc.setLineDashes(5);
        gc.strokeRect(x, y, width, height);
        gc.setStroke(Color.BLACK);
    }

    public abstract void drawShape(GraphicsContext gc, Color strokeColor, Color fillColor);
    public abstract boolean containsPoint(double xpos, double ypos);
    protected abstract void calculateArea();
    protected abstract void calculatePerimeter();
}
