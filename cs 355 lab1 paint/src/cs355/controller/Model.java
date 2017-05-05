/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.controller;

import cs355.model.drawing.Shape;
import java.util.ArrayList;

/**
 *
 * @author kendall motes
 */
public class Model {
    // Variables
	private final ArrayList<Shape> shapes = new ArrayList<Shape>();

	// Singleton Stuff
	private static Model instance = null;

	// Singleton Method
	public static Model inst()
	{
		if(instance == null)
		{
			instance = new Model();
		}
		return instance;
	}

	// Constructor
	protected Model()
	{
		// Exists only to defeat instantiation
	}
        
        // Methods
	public void addShape(Shape shape)
	{
		this.shapes.add(shape);
	}

	public Shape getShape(int index)
	{
		return this.shapes.get(index);
	}

	public int size()
	{
		return this.shapes.size();
	}
}
