package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Floor {
	int x, y;
    TextureRegion tekstura;
    float isoX;
    float isoY;
    Floor(int x,int y,Texture tex)
    {
    	this.x=x;
    	this.y=y;
    	tekstura = new TextureRegion(tex);
    	ToIso();
    	
    }
    private void ToIso()
    {
        this.isoX = x*32 - y*32;
        this.isoY = (x*32 + y*32) / 2;
    }
    public void draw(SpriteBatch spriteBatch)
    {
    	spriteBatch.draw(tekstura, isoX, isoY);
    }
    public void dispose()
    {
    	tekstura.getTexture().dispose();
    }
}
