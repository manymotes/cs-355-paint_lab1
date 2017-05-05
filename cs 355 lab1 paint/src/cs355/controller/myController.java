/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.controller;

import cs355.GUIFunctions;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Line;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;
import cs355.view.View;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Iterator;

/**
 *
 * @author kendall motes
 */
public class myController implements CS355Controller, MouseListener, MouseMotionListener{

   

   private enum controller_state
	{
		TRIANGLE, SQUARE, RECTANGLE, CIRCLE, ELLIPSE, LINE, SELECT, ZOOM_IN, ZOOM_OUT, H_SCROLL_BAR, V_SCROLL_BAR, RESET
	};
   public enum MouseButtonState
	{
		NONE, LEFT, MIDDLE, RIGHT
	};
    private MouseButtonState mouseButtonState = MouseButtonState.NONE;
    private controller_state currentState = controller_state.RESET;
    private Point2D.Double startPoint = null;
    private int triCornCount = 0;
    private Point2D.Double[] triCornArray = new Point2D.Double[3];
    private Color currentColor = Color.white;
    private Shape currentShape = null;
 
    // Singleton Stuff
    private static myController instance = null;
    // Singleton Methods

    public static myController inst() {
        if (instance == null) {
            instance = new myController();
        }
        return instance;
    }

    // Constructor
    protected myController() {
        // Exists only to defeat instantiation
    }

    public void setMouseButtonState(MouseButtonState mbs)
	{
		this.mouseButtonState = mbs;
	}

	public MouseButtonState getMouseButtonState()
	{
		return this.mouseButtonState;
	}
   
  @Override
	public void colorButtonHit(Color color)
	{
		// this.currentState = controllerState.Color;
		if(color != null)
		{
			this.currentColor = color;
			GUIFunctions.changeSelectedColor(this.currentColor);
		}
	}

    @Override
    public void lineButtonHit() {
        this.resetState();
        this.currentState = controller_state.LINE;
    }

    @Override
    public void squareButtonHit() {
        this.resetState();
      this.currentState = controller_state.SQUARE;
    }

    @Override
    public void rectangleButtonHit() {
        this.resetState();
       this.currentState = controller_state.RECTANGLE;
    }

    @Override
    public void circleButtonHit() {
        this.resetState();
       this.currentState = controller_state.CIRCLE;
    }

    @Override
    public void ellipseButtonHit() {
        this.resetState();
       this.currentState = controller_state.ELLIPSE;
    }

    @Override
    public void triangleButtonHit() {
        this.resetState();
        this.currentState = controller_state.TRIANGLE;
    }

    @Override
    public void selectButtonHit() {
        this.resetState();
       this.currentState = controller_state.SELECT;
    }

    @Override
    public void zoomInButtonHit() {
       this.currentState = controller_state.ZOOM_IN;
    }

    @Override
    public void zoomOutButtonHit() {
       this.currentState = controller_state.ZOOM_OUT;
    }

    @Override
    public void hScrollbarChanged(int value) {
      this.currentState = controller_state.H_SCROLL_BAR;
    }

    @Override
    public void vScrollbarChanged(int value) {
       this.currentState = controller_state.V_SCROLL_BAR;
    }
    
    public void processClicked(Point2D.Double p)
    {
       this.startPoint = p;

            switch(this.currentState)
		{
		case TRIANGLE:
			this.triCornArray[this.triCornCount] = p;
			this.triCornCount++;
			if(this.triCornCount == 3)
			{
				this.triCornCount = 0;
				this.currentShape = new Triangle(this.currentColor, this.triCornArray[0], this.triCornArray[1], this.triCornArray[2]);
				Model.inst().addShape(this.currentShape);
				this.currentShape = null;
			}
			break;
                case SQUARE:
			break;

		case RECTANGLE:
			break;

		case CIRCLE:
			break;

		case ELLIPSE:
			break;

		case LINE:
			break;

		case SELECT:
			break;

		case RESET:
			break;

		default:

		}

		GUIFunctions.refresh();

    }
    public void processDragged(Point2D.Double p)
	{
		switch(this.currentState)
		{
		case TRIANGLE:
			break;

		case SQUARE:
			this.updateSquare(p);
			break;

		case RECTANGLE:
			updateRectangle(p);
			break;

		case CIRCLE:
			this.updateCircle(p);
			break;

		case ELLIPSE:
			this.updateEllipse(p);
			break;

		case LINE:
			((Line) this.currentShape).setEnd(p);

			break;

		case SELECT:
			break;

		case RESET:
			break;

		default:

		}

		GUIFunctions.refresh();
	}

    	public void processPressed(Point2D.Double p)
	{
		this.startPoint = p;

		switch(this.currentState)
		{
		case TRIANGLE:
			break;

		case SQUARE:
			this.currentShape = new Square(this.currentColor, p, 0);
			break;

		case RECTANGLE:
			this.currentShape = new Rectangle(this.currentColor, p, 0, 0);
			break;

		case CIRCLE:
			this.currentShape = new Circle(this.currentColor, p, 0);
			break;

		case ELLIPSE:
			this.currentShape = new Ellipse(this.currentColor, p, 0, 0);
			break;

		case LINE:
			this.currentShape = new Line(this.currentColor, p, p);
			break;

		case SELECT:
			break;

		case RESET:
			break;

		default:

		}

		if(this.currentState != currentState.TRIANGLE)
		{
			Model.inst().addShape(this.currentShape);
		}
		GUIFunctions.refresh();
	}
        
        public void processReleased(Point2D.Double p)
	{
		GUIFunctions.refresh();
	}
        
        
    public void updateCircle(Point2D.Double p)
    {
        double newY = Math.abs(this.startPoint.y - p.y);
        double newX = Math.abs(this.startPoint.x - p.x);
        
        Circle circle;
        circle = (Circle) currentShape;
       
        double diameter = (newX > newY) ? newY : newX;
        double radius = diameter / 2;
        
        
        if (this.startPoint.x > p.x)
        {
            if (this.startPoint.y > p.y)
            {
                circle.setCenter(new Point2D.Double(this.startPoint.x - radius,  this.startPoint.y - radius));
            }
            else{
                 circle.setCenter(new Point2D.Double(this.startPoint.x - radius,  this.startPoint.y + radius));
            }
        }
        else
        {
            if (this.startPoint.y > p.y)
            {
                circle.setCenter(new Point2D.Double( this.startPoint.x + radius,  this.startPoint.y - radius));
            }
            else
            {
                circle.setCenter(new Point2D.Double( this.startPoint.x + radius,  this.startPoint.y + radius));
            }
        }
        circle.setRadius(radius);
    }
    
    public void updateSquare(Point2D.Double p)
	{
		int deltaY = (int) Math.abs(this.startPoint.y - p.y);
		int deltaX = (int) Math.abs(this.startPoint.x - p.x);
		int side = (deltaX > deltaY) ? deltaY : deltaX;

		Square square = (Square) currentShape;

		if(this.startPoint.x > p.x)
			if(this.startPoint.y > p.y) // new point is to the upper left of start point
				square.setUpperLeft(new Point2D.Double(this.startPoint.x - side, this.startPoint.y - side));
			else
				
				square.setUpperLeft(new Point2D.Double(this.startPoint.x - side, this.startPoint.y));
		else if(this.startPoint.y > p.y) // new point is to upper right of start point
			square.setUpperLeft(new Point2D.Double(this.startPoint.x, this.startPoint.y - side));

		square.setSize(side);
	}
    
    void updateRectangle(Point2D.Double p)
    {
        Rectangle rectangle;
        rectangle = (Rectangle) currentShape;
        
        rectangle.setHeight(Math.abs(this.startPoint.y - p.y));
		rectangle.setWidth(Math.abs(this.startPoint.x - p.x));

		if(this.startPoint.x > p.x)
			if(this.startPoint.y > p.y) // new point is to the upper left of start point
				rectangle.setUpperLeft(p);
			else
				// new point is to the lower left of start point
				rectangle.setUpperLeft(new Point2D.Double(p.x, this.startPoint.y));

		else if(this.startPoint.y > p.y) // new point is to upper right of start point
			rectangle.setUpperLeft(new Point2D.Double(this.startPoint.x, p.y));  
    }
    
    void updateEllipse(Point2D.Double p)
    {
        double height = Math.abs(this.startPoint.y - p.y);
		double width = Math.abs(this.startPoint.x - p.x);
		Ellipse ellipse = (Ellipse) currentShape;
		ellipse.setHeight(height);
		ellipse.setWidth(width);
                
                if(this.startPoint.x > p.x)
			if(this.startPoint.y > p.y) // new point is to the upper left of start point
				ellipse.setCenter(new Point2D.Double(p.x + width / 2, p.y + height / 2));
			else
				// new point is to the lower left of start point
				ellipse.setCenter(new Point2D.Double(p.x + width / 2, this.startPoint.y + height / 2));

		else
		{
			if(this.startPoint.y > p.y) // new point is to upper right of start point
				ellipse.setCenter(new Point2D.Double(this.startPoint.x + width / 2, p.y + height / 2));
			else
				ellipse.setCenter(new Point2D.Double(this.startPoint.x + width / 2, this.startPoint.y + height / 2));
		}
    }

    @Override
    public void openScene(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggle3DModelDisplay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openImage(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveImage(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggleBackgroundDisplay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveDrawing(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openDrawing(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doDeleteShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doEdgeDetection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doSharpen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doMedianBlur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doUniformBlur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doGrayscale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doMoveForward() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doMoveBackward() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doSendToFront() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doSendtoBack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    switch(e.getButton())
		{
		case 0:
			myController.inst().setMouseButtonState(MouseButtonState.NONE);
			break;

		case 1:
			myController.inst().setMouseButtonState(MouseButtonState.LEFT);
			myController.inst().processClicked(new Point2D.Double((double) e.getPoint().x, (double) e.getPoint().y));
			break;

		case 2:
			break;

		case 3:
			break;
		}   
    }
    @Override
    public void mousePressed(MouseEvent e) {
     switch(e.getButton())
		{
		case 0:
			myController.inst().setMouseButtonState(MouseButtonState.NONE);
			break;

		case 1:
			myController.inst().setMouseButtonState(MouseButtonState.LEFT);
			myController.inst().processPressed((new Point2D.Double((double) e.getPoint().x, (double) e.getPoint().y)));
			break;

		case 2:
			;
			break;

		case 3:
			break;
		}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	switch(e.getButton())
		{
		case 0:
			break;

		case 1:
			myController.inst().setMouseButtonState(MouseButtonState.NONE);
			myController.inst().processReleased((new Point2D.Double((double) e.getPoint().x, (double) e.getPoint().y)));
			break;

		case 2:
			break;

		case 3:
			break;
		}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

     @Override
    public void mouseDragged(MouseEvent e) {
        if(myController.inst().getMouseButtonState() == MouseButtonState.LEFT)
		{
			switch(e.getButton())
			{
			case 0:
				myController.inst().processDragged(new Point2D.Double((double) e.getPoint().x, (double) e.getPoint().y));
				break;

			case 1:
				break;
			case 2:
				break;

			case 3:
				break;

			}
		}
        
 }

    @Override
    public void mouseMoved(MouseEvent e) {
       
    }
    private void resetState()
	{
		this.currentShape = null;
		this.currentState = currentState.RESET;
	}

}
