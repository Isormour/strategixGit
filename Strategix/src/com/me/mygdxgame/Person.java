package com.me.mygdxgame;

import java.util.Map;

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
	
	SpriteAnimation currentAnimation;
	Map<String, SpriteAnimation> animationMap;
	
    Person(int x, int y, Texture greet, Texture atack, Texture death)
    {
    	this.position = new BoardPosition(x, y);
    	this.tex_greet = greet;
    	this.tex_atack = atack;
    	this.tex_death = death;
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
        	walkAnimationDraw(spriteBatch);
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
    
    /**
     * Sets up the walking animation.
     */
    private void walkAnimationSetup() {
    	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	//Create a new instance of desired animation at the current position
    	currentAnimation = new PersonAnimationWalk(kierunek, isoX, isoY);
    	
    	//the finisher for walk is a little bit customized
    	//because it needs to update position
    	currentAnimation.setOnFinish( 
    			new Finishable() {
					public void finish() {
						finishAnimation();
		    			moved = true;
		    			position.move(kierunek);        			
					}
				}
    	);
	}
    
    private void walkAnimationDraw(SpriteBatch spriteBatch)
    {
    	if( currentAnimation == null )	walkAnimationSetup();
   		currentAnimation.draw(spriteBatch);
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

    /**
     * Common things to perform after finishing an animation
     */
    private void finishAnimation(){
		currentAnimation = null;
		animation = 0;
		busy = false;
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
    }
    
    
}
    	


