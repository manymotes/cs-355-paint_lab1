/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.view;

import cs355.controller.Model;
import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Line;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Observable;

/**
 *
 * @author kendall motes
 */
public class View implements ViewRefresher
{ 

    public static View instance = null;
    
    public static View inst()
    {
        if (instance == null)
        {
            instance = new View();
            
        }
        return instance;
    }
    
   @Override
	public void refreshView(Graphics2D g2d)
	{
		for (int i = 0; i < Model.inst().size(); i++)
		{
			Shape currentShape = Model.inst().getShape(i);

			if (currentShape instanceof Line)
			{
				Line temp = (Line) currentShape;
				g2d.setColor(temp.getColor());
				g2d.drawLine((int) temp.getStart().x, (int) temp.getStart().y, (int) temp.getEnd().x, (int) temp.getEnd().y);
				currentShape = null;
			}
			else
				if (currentShape instanceof Rectangle)
				{
					Rectangle rectangle = (Rectangle) currentShape;
					g2d.setColor(rectangle.getColor());
					g2d.fillRect((int) rectangle.getUpperLeft().x, (int) rectangle.getUpperLeft().y, (int) rectangle.getWidth(), (int) rectangle.getHeight());
					currentShape = null;
				}
				else
					if (currentShape instanceof Ellipse)
					{
						Ellipse ellipse = (Ellipse) currentShape;
						Point2D.Double upLeft = new Point2D.Double(ellipse.getCenter().x - (ellipse.getWidth() / 2), ellipse.getCenter().y - (ellipse.getHeight() / 2));
						g2d.setColor(ellipse.getColor());
						g2d.fillOval((int) upLeft.x, (int) upLeft.y, (int) ellipse.getWidth(), (int)ellipse.getHeight());
						currentShape = null;
					}
					else
                                                if (currentShape instanceof Square)
                                                {
                                                    Square square = (Square) currentShape;
                                                    
                                                    g2d.setColor(square.getColor());
                                                    g2d.fillRect((int) square.getUpperLeft().x, (int) square.getUpperLeft().y, (int) square.getSize(), (int) square.getSize());
                                                    currentShape = null;
                                                }
                                                if(currentShape instanceof Circle)
                                                {
                                                    Circle circle = (Circle) currentShape;
                                                    Point2D.Double topleft = new Point2D.Double(circle.getCenter().x - (circle.getRadius()), circle.getCenter().y - (circle.getRadius()));
                                                    g2d.setColor(circle.getColor());
                                                    g2d.fillOval((int) topleft.x, (int) topleft.y, (int) (circle.getRadius()*2) , (int) (circle.getRadius()*2));
                                                    currentShape = null;
                                                }
						if (currentShape instanceof Triangle)
						{
							int numPoints = 3;

							Triangle triangle = (Triangle) currentShape;
							int[] xArr = new int[numPoints];
							xArr[0] = (int) triangle.getA().x;
							xArr[1] = (int) triangle.getB().x;
							xArr[2] =(int) triangle.getC().x;

							int[] yArr = new int[numPoints];
							yArr[0] = (int) triangle.getA().y;
							yArr[1] = (int) triangle.getB().y;
							yArr[2] = (int) triangle.getC().y;

							g2d.setColor(triangle.getColor());
							g2d.fillPolygon(xArr, yArr, numPoints);
						}

		}
	}

    @Override
    public void update(Observable o, Object arg) {
      }
}
