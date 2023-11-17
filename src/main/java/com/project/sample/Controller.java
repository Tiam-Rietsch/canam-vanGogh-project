package com.project.sample;

import com.project.sample.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import java.util.Stack;

import static com.project.sample.Model.ShapeType.OVAL;
import static com.project.sample.Model.ShapeType.TRIANGLE;

public class Controller {
    private TShape selectedShape;
    private Stack<TShape> allShapes = new Stack<>();
    private String state;
    private ShapeType shapeType;

    private double prevMouseX;
    private double prevMouseY;

    private Color fillColor;
    private Color strokeColor;

    @FXML
    private Canvas myCanvas;

    @FXML
    private ToggleButton rectangleButton;

    @FXML
    private ToggleButton triangleButton;

    @FXML
    private ToggleButton ovalButton;

    @FXML
    private ToggleButton lineButton;

    @FXML
    private ToggleButton kiteButton;

    @FXML
    private ColorPicker bgColorPicker;

    @FXML
    private ColorPicker strokeColorPicker;

    @FXML
    private TextField xPosInput;

    @FXML
    private TextField yPosInput;

    @FXML
    private TextField heightInput;

    @FXML
    private TextField widthInput;

    @FXML
    private Label mouseX;

    @FXML
    private Label mouseY;


    public void setSelectedShape(double xpos, double ypos) {
        this.selectedShape = null;
        for (TShape shape : allShapes) {
            if (shape.containsPoint(xpos, ypos) || shape.getResizeRect().containsPoint(xpos, ypos)) {
                this.selectedShape = shape;
                break;
            }
        }
    }

    public void redrawCanvas(GraphicsContext gc, TShape selectedShape) {
        allShapes.forEach(shape -> {
            if (shape == selectedShape) {
                shape.draw(gc, shape.getStrokeColor(), shape.getFillColor(),true);
            } else {
                shape.draw(gc, shape.getStrokeColor(), shape.getFillColor(), false);
            }
        });

    }

    public ShapeType getSelectedShapeType() {
        if (rectangleButton.isSelected()) {
            return ShapeType.RECTANGLE;
        } else if (ovalButton.isSelected()) {
            return OVAL;
        } else if (triangleButton.isSelected()) {
            return TRIANGLE;
        } else if (lineButton.isSelected()) {
            return ShapeType.LINE;
        } else if (kiteButton.isSelected()) {
            return shapeType.KITE;
        }
        return null;
    }

    public void initializeShape(GraphicsContext gc, double xpos, double ypos) {
        switch (this.shapeType) {
            case RECTANGLE -> {
                TRectangle newRect = new TRectangle(xpos, ypos, 0, 0, ShapeType.RECTANGLE);
                newRect.drawShape(gc, this.strokeColor, this.fillColor);
                this.allShapes.push(newRect);
                this.selectedShape = newRect;
            }
            case TRIANGLE -> {
                TTriangle newTriangle = new TTriangle(xpos, ypos, 0, 0, ShapeType.TRIANGLE);
                newTriangle.drawShape(gc, this.strokeColor, this.fillColor);
                this.allShapes.push(newTriangle);
                this.selectedShape = newTriangle;
            }
            case OVAL -> {
                TOval newOval = new TOval(xpos, ypos, 0, 0, ShapeType.OVAL);
                newOval.drawShape(gc, this.strokeColor, this.fillColor);
                this.allShapes.push(newOval);
                this.selectedShape = newOval;

            }
            case ShapeType.LINE -> {
                TLine newLine = new TLine(xpos, ypos, 0, 0, ShapeType.LINE);
                newLine.drawShape(gc, this.strokeColor, this.fillColor);
                this.allShapes.push(newLine);
                this.selectedShape = newLine;
            }
            case ShapeType.KITE -> {
                TKite newKite = new TKite(xpos, ypos, 0, 0, ShapeType.KITE);
                newKite.drawShape(gc, this.strokeColor, this.fillColor);
                this.allShapes.push(newKite);
                this.selectedShape = newKite;
            }
        }
    }

    public void drawShape(MouseEvent event) {
        shapeType = getSelectedShapeType();
        this.fillColor = bgColorPicker.getValue();
        this.strokeColor = strokeColorPicker.getValue();

        GraphicsContext gc = this.myCanvas.getGraphicsContext2D();

        setSelectedShape(event.getX(), event.getY());
        if (selectedShape != null) {
            if (selectedShape.containsPoint(event.getX(), event.getY())) {
                this.state = "move";
                prevMouseX = event.getX();
                prevMouseY = event.getY();
            } else if (selectedShape.getResizeRect().containsPoint(event.getX(), event.getY())) {
                this.state = "resize";
                System.out.println(this.state);
            }
        } else if (selectedShape == null) {
            initializeShape(gc, event.getX(), event.getY());
            this.state = "resize";
        }

        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getWidth());
        redrawCanvas(gc, selectedShape);
    }

    public void removeEmptyShape(MouseEvent event) {
        if (this.selectedShape.getWidth() == 0 || this.selectedShape.getHeight() == 0) {
            allShapes.remove(this.selectedShape);
            this.selectedShape = null;
        }

        GraphicsContext gc = myCanvas.getGraphicsContext2D();

        redrawCanvas(gc, selectedShape);
    }

    public void resizeShape(MouseEvent event) {
        GraphicsContext gc = this.myCanvas.getGraphicsContext2D();

        if (this.state == "resize") {
            this.selectedShape.updateEndCoordinates(event.getX(), event.getY());
        } else if (this.state == "move") {
            this.selectedShape.updateStartCoordinates(event.getX()-prevMouseX, event.getY()-prevMouseY);
        }

        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        redrawCanvas(gc, this.selectedShape);

        this.xPosInput.setText(String.valueOf(selectedShape.getX()));
        this.yPosInput.setText(String.valueOf(selectedShape.getY()));
        this.heightInput.setText(String.valueOf(selectedShape.getHeight()));
        this.widthInput.setText(String.valueOf(selectedShape.getWidth()));
        this.prevMouseY = event.getY();
        this.prevMouseX = event.getX();
    }

    public void showMousePositions(MouseEvent event) {
        mouseX.setText(String.valueOf(event.getX()));
        mouseY.setText(String.valueOf(event.getY()));
    }

}