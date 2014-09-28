package com.me.mygdxgame;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.Animation.AnimationAsset;
import com.me.mygdxgame.Animation.Person.AnimationPersonAssets;
import com.me.mygdxgame.Animation.Person.AnimationPersonAttack;
import com.me.mygdxgame.Animation.Person.AnimationPersonDeath;
import com.me.mygdxgame.Animation.Person.AnimationPersonGreet;
import com.me.mygdxgame.Animation.Person.AnimationPersonWalk;


public class Person {
	BoardPosition position;
	Direction kierunek = Direction.RIGHT;

    boolean selected = false;
    boolean attacked = false;
    boolean moved = false;
    
	SpriteAnimation currentAnimation;
	Map<String, SpriteAnimation> animationMap;
	Texture tex_greet;
	
    Person(int x, int y, Texture greet)
    {
    	this.position = new BoardPosition(x, y);
    	this.tex_greet = greet;
    }

    public void draw(SpriteBatch spriteBatch, float time)
    {
    	//TODO: Currently a default sprite is chosen to be 1st frame in the greeting texture
    	// from the current direction. This should not be hardcoded. Perhaps there should be
    	// a "standing" animation.
    	
    	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	if (currentAnimation == null)
        {
    		spriteBatch.draw(tex_greet, isoX, isoY, 0, 64*kierunek.toInt(), 64, 64);
        }
    	else {
    		currentAnimation.draw(spriteBatch);
        	if (currentAnimation.isFinished()) finishAnimation();
    	}

    }

    /**
     * Sets up the walking animation.
     */
    private void walkAnimationSetup(final Direction dir) {
    	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	//Create a new instance of desired animation at the current position
    	currentAnimation = new AnimationPersonWalk( AnimationPersonAssets.getInstance().getAnimationAsset("walk"), dir, isoX, isoY);
    	
    	//the finisher for walk is a little bit customized
    	//because it needs to update position
    	currentAnimation.setOnFinish( 
    			new Finishable() {
					public void finish() {
		    			moved = true;
		    			position.move(dir);        			
					}
				}
    	);
	}
    
    /**
     * Common things to perform after finishing an animation
     */
    private void finishAnimation(){
		currentAnimation = null;
    }
    
    // PUBLIC FUNCTIONS 
    public void endTurn()
    {
    	moved = false;
    	attacked = false;
    }
    
    public void set_kierunek(Direction kierunek)
    {
    	if( isBusy() )
    	{
    		this.kierunek = kierunek;
    	}
    }
    
    public void attack(){
       	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	AnimationAsset asset = AnimationPersonAssets.getInstance().getAnimationAsset("attack");
    	currentAnimation = new AnimationPersonAttack(asset, kierunek, isoX, isoY);
    	attacked = true;
    }
    
    public void move(Direction dir) {
    	walkAnimationSetup(dir);
	}
    
    public void kill(){
       	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	AnimationAsset asset = AnimationPersonAssets.getInstance().getAnimationAsset("kill");
    	currentAnimation = new AnimationPersonDeath(asset, kierunek, isoX, isoY);
    }
    
    public void greet(){
       	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	AnimationAsset asset = AnimationPersonAssets.getInstance().getAnimationAsset("greet");
    	currentAnimation = new AnimationPersonGreet(asset, kierunek, isoX, isoY);
    }
    
    public void dispose() {}
    
    public boolean isBusy(){
    	return currentAnimation != null;
    }
    
}
    	


