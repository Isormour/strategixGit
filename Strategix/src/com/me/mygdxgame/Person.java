package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.org.apache.xpath.internal.axes.WalkerFactory;


public class Person {
	BoardPosition position;
	Direction kierunek = Direction.RIGHT;
    int frames = 0;
    int rows = 0;
    int animation = 0;
 
    boolean selected = false;
    boolean busy= false;
    
    boolean attacked = false;
    boolean moved = false;
    
    int attack_corrector = 0;
    int dodatek = 1;
    
    float timer = 0;
    float delay = 0.04f;
    float atackcorr = 1;
    float atack_dodatek;
    
	Texture tex_greet;
	Texture tex_atack;
	Texture tex_death;
	Texture tex_walk;
	
	SpriteAnimation currentAnimation;
	
    Person(int x, int y, Texture greet, Texture atack, Texture death, Texture walk)
    {
    	this.position = new BoardPosition(x, y);
    	this.tex_greet = greet;
    	this.tex_atack = atack;
    	this.tex_death = death;
    	this.tex_walk = walk;
    }

    public void draw(SpriteBatch spriteBatch, float time)
    {
    	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	if (animation == 0)
        {
    		spriteBatch.draw(tex_greet, isoX, isoY, 64*frames, 64*kierunek.toInt(), 64, 64);
        }
        else if (animation == 1)
        {
        	draw_standing_Animation(spriteBatch, time, tex_greet);
        }
        else if(animation == 2)
        {
        	draw_atack(spriteBatch, time, tex_atack);
        }
        else if(animation == 3)
        {
        	draw_death(spriteBatch, time, tex_death);
        }
        else if(animation == 4)
        {
        	draw_walk(spriteBatch);
        }
        else if(animation == 5)
        {
        	spriteBatch.draw(tex_death, isoX, isoY, 64*9, 64*kierunek.toInt(), 64, 64);
        }
    }
    
    private void draw_death(SpriteBatch spriteBatch, float time, Texture death)
    {
		timer += time;
		busy = true;
		if(timer >delay)
		{
			frames++;
			timer = 0;
		}
		spriteBatch.draw(death, position.getIsoX(), position.getIsoY(), 64*frames, 64*kierunek.toInt(), 64, 64);
		if(frames==9)
		{
			busy = false;
			timer = 0;
			frames = 0;
			animation = 5;
		}
	}
    
    private void draw_standing_Animation(SpriteBatch spriteBatch, float time, Texture tex2)
    { 	
    	timer += time;
    	busy = true;
    	if(timer > delay)
    	{
    		frames++;
    		timer = 0;
    	}
		spriteBatch.draw(tex2, position.getIsoX(), position.getIsoY(), 64*frames, 64*kierunek.toInt(), 64, 64);
    	{
    		busy = false;
    		timer = 0;
    		frames= 0;
    		animation = 0;
    	}
    }
    
    private void draw_walk(SpriteBatch spriteBatch)
    {
    	if(!busy){
        	float isoX = position.getIsoX(); // get starting position in screen coordinates
        	float isoY = position.getIsoY();
        	int sheetCols = 10;	// set the walking animation coordinates
        	int sheetRows = 2;
        	int sheetWidth = 64*sheetCols;
        	int sheetHeight = 64*sheetRows;
        	float duration = 0.5f;
    		busy = true;
    		// because sprites of walking animations for each direction are in one big file
    		// we have to crop out the region contains only the frames we are interested in
    		TextureRegion texture = new TextureRegion(tex_walk, 0, sheetHeight*kierunek.toInt(), sheetWidth, sheetHeight);
    		//create animation and draw 1st frame
    		currentAnimation = new AnimationWalk(texture, duration, sheetCols, sheetRows, isoX, isoY, kierunek);
    		currentAnimation.draw(spriteBatch);
    	}
    	else {
    		currentAnimation.draw(spriteBatch);
    		if(currentAnimation.isFinished()){
    			animation = 0;
    			busy = false;
    			moved = true;
    			position.move(kierunek);        			
    		}
    	}
    }
    
    private void draw_atack(SpriteBatch spriteBatch, float time, Texture tex2)
    {
    	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	timer += time;
    	busy = true;
    	if(timer > delay)
    	{
    		frames++;
    		timer = 0;
    		attack_corrector = attack_corrector + dodatek;
    	}
    	if(frames==5)
    	{
    		dodatek = dodatek * (-1);
    	}
    	switch(kierunek){
			case LEFT:
				spriteBatch.draw(tex2, isoX + 1.6f * attack_corrector,  isoY - 1.6f * attack_corrector, 64*frames, 64 * kierunek.toInt(), 64, 64);
				break;
			case DOWN:
				spriteBatch.draw(tex2, isoX - 1.6f * attack_corrector,  isoY - 1.6f * attack_corrector, 64*frames, 64 * kierunek.toInt(), 64, 64);
				break;
			case UP:
				spriteBatch.draw(tex2, isoX + 1.6f * attack_corrector,  isoY + 1.6f * attack_corrector, 64*frames, 64 * kierunek.toInt(), 64, 64);
				break;
			case RIGHT:
				spriteBatch.draw(tex2, isoX - 1.6f * attack_corrector,  isoY + 1.6f * attack_corrector, 64*frames, 64 * kierunek.toInt(), 64, 64);
				break;
		}
    	if(frames==9)
    	{
    		busy = false;
    		timer = 0;
    		frames = 0;
    		animation = 0;
    		attack_corrector = 0;
    		dodatek = 1;
    		attacked = true;
    	}
    }
    
    // PUBLIC FUNCTIONS 
    public void endTurn()
    {
    	moved = false;
    	attacked = false;
    }
    
    public void set_kierunek(Direction kierunek)
    {
    	if(!busy)
    	{
    		this.kierunek = kierunek;
    		
    	}
    }
    
    public void set_animation(int animation_number)
    {
    	if(!busy)
    	{
    		animation = animation_number;
    	}
    }
    
    public void dispose()
    {
    	tex_atack.dispose();
    	tex_death.dispose();
    	tex_greet.dispose();
    	tex_walk.dispose();
    }
    
    
}
    	


