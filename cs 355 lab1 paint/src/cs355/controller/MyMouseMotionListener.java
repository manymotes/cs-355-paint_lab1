/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.controller;

import cs355.controller.myController.MouseButtonState;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

/**
 *
 * @author kendall motes
 */
public class MyMouseMotionListener implements MouseMotionListener{
    // Singleton Stuff
	private static MyMouseMotionListener instance = null;

	// Singleton Method
	public static MyMouseMotionListener inst()
	{
		if(instance == null)
		{
			instance = new MyMouseMotionListener();
		}
		return instance;
	}

	// Constructor
	protected MyMouseMotionListener()
	{
		// Exists only to defeat instantiation
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
}
