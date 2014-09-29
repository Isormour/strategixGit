package com.me.mygdxgame;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera{
	protected float moveSpeed = 2;
	
	Camera(float width, float height)
	{
		super(width, height);
	}
	
	/**
	 * Moves the camera by specified amount of pixels in a given direction.
	 * @param dir Direction of movement.
	 * @param pixels Number of pixels for displacement.
	 */
	public void translate(Direction dir, float pixels) {
        switch (dir)
        {
            case UP:
            	this.translate(0, pixels);
                break;
            case DOWN:
            	this.translate(0, -pixels);
                break;
            case RIGHT:
            	this.translate(pixels, 0);
                break;
            case LEFT:
            	this.translate(-pixels, 0);
                break;
        }
        this.update();
	}
	
	/**
	 * Moves the camera by a pixel in a given direction.
	 * @param pixels Number of pixels for displacement.
	 */	
	public void translate(Direction dir)
    {
		this.translate(dir, moveSpeed);
    }
	
}
