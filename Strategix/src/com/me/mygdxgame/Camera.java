package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class Camera {
	OrthographicCamera camera;
	Vector3 position;
	boolean updated = false;
	
	Camera(float width,float height)
	{
		camera = new OrthographicCamera(width, height);
		position = new Vector3(width, height, 0);
        camera.update();
        camera.apply(Gdx.gl10);
	}
	
	/**
	 * Moves the camera by specified amount of pixels in a given direction.
	 * @param dir Direction of movement.
	 * @param pixels Number of pixels for displacement.
	 */
	public void ruchscreena(Direction dir, int pixels) {
        switch (dir)
        {
            case UP:
            	position.y += pixels;
                break;
            case DOWN:
            	position.y -= pixels;
                break;
            case RIGHT:
            	position.x += pixels;
                break;
            case LEFT:
            	position.x -= pixels;
                break;
        }
        camera.position.set(position);
        camera.update();
        camera.apply(Gdx.gl10);
	}
	
	/**
	 * Moves the camera by a pixel in a given direction.
	 * @param pixels Number of pixels for displacement.
	 */	
	public void ruchscreena(Direction dir)
    {
		this.ruchscreena(dir, 1);
    }	
}
