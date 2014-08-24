package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
	int x,y;
	float isoX,isoY;
	Texture tex;
	private void ToIso()
	{
	    this.isoX = x*32 - y*32 ;
	    this.isoY = ((x*32 + y*32) / 2);
	}
	public Tile(int x,int y,Texture tex)
	{
		this.x = x;
		this.y = y;
		this.tex = tex;
		ToIso();
	}
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(tex,isoX,isoY);
	}

}
