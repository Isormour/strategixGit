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
	public void ruchscreena(int sterownik)
    {
        switch (sterownik)
        {
            case 1:
            	position.y += 1;
                break;

            case 2:
            	position.y -= 1;
                break;

            case 3:
            	position.x += 1;
                break;

            case 4:
            	position.x -= 1;
                break;
        }
        camera.position.set(position);
        camera.update();
        camera.apply(Gdx.gl10);
    }	
}
