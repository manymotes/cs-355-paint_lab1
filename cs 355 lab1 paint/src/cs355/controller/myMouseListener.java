/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.controller;

import cs355.controller.myController.MouseButtonState;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

/**
 *
 * @author kendall motes
 */
public class myMouseListener implements MouseListener
{
    private static myMouseListener instance = null;

	// Singleton Method
	public static myMouseListener inst()
	{
		if(instance == null)
		{
			instance = new myMouseListener();
		}
		return instance;
	}

        protected myMouseListener()
	{
		// Exists only to defeat instantiation
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
