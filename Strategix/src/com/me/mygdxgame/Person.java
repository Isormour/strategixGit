package com.me.mygdxgame;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.Animation.AnimationAsset;
import com.me.mygdxgame.Animation.AnimationAssetInternalFile;
import com.me.mygdxgame.Animation.Person.AnimationPersonAssets;
import com.me.mygdxgame.Animation.Person.AnimationPersonAttack;
import com.me.mygdxgame.Animation.Person.AnimationPersonDeath;
import com.me.mygdxgame.Animation.Person.AnimationPersonGreet;
import com.me.mygdxgame.Animation.Person.AnimationPersonWalk;
import com.me.mygdxgame.Animation.Person.AnimationPerson;
import com.me.mygdxgame.Animation.AnimationDirection;


public class Person {
	BoardPosition position;
	Direction kierunek = Direction.DOWN;

    boolean selected = false;
    boolean attacked = false;
    boolean moved = false;
    boolean performingAction = false;
    
    AnimationDirection currentAnimation;
	Map<String, AnimationDirection> animationMap;
	
    Person(int x, int y)
    {
    	this.position = new BoardPosition(x, y);
    	this.animationMap = new HashMap<String, AnimationDirection>();
    	this.loadAnimations();
    	setCurrentAnimation("idle");
    }

    private void loadAnimations(){
    	AnimationAsset asset;
    	asset = AnimationPersonAssets.getInstance().getAnimationAsset("attack");
    	this.animationMap.put("attack", new AnimationPersonAttack(asset));
    	asset = AnimationPersonAssets.getInstance().getAnimationAsset("death");
    	this.animationMap.put("death", new AnimationPersonDeath(asset));
    	asset = AnimationPersonAssets.getInstance().getAnimationAsset("greet");
    	this.animationMap.put("greet", new AnimationPersonGreet(asset));
    	asset = AnimationPersonAssets.getInstance().getAnimationAsset("walk");
    	AnimationDirection walk = new AnimationPersonWalk( AnimationPersonAssets.getInstance().getAnimationAsset("walk"));
    	this.animationMap.put("walk", walk);
    	
    	//TODO: "Idle" animation is a hardcoded 1st frame of greet. 
    	asset = new AnimationAssetInternalFile("data/greet.png", 1, 1, 64, 64, 0.01f);
    	AnimationDirection idle = new AnimationPerson(asset);
    	this.animationMap.put("idle", idle);
    }
    
    public void draw(SpriteBatch spriteBatch, float time)
    {
		currentAnimation.draw(spriteBatch);
    	if (currentAnimation.isFinished()) finishAnimation();
    }
    
    /**
     * Common things to perform after finishing an animation
     */
    private void finishAnimation(){
		performingAction = false;
    }
    
    // PUBLIC FUNCTIONS 
    public void endTurn()
    {
    	moved = false;
    	attacked = false;
    }
    
    public void set_kierunek(Direction kierunek)
    {
    	if( !isBusy() )
    	{
    		this.kierunek = kierunek;
    	}
    }
    
    /**
     * Sets up animation and makes it `currentAnimation`.
     * @param name The name of 
     */
    protected void setCurrentAnimation(String name){
       	float isoX = position.getIsoX();
    	float isoY = position.getIsoY();
    	currentAnimation = animationMap.get(name);
    	currentAnimation.setPosition(isoX, isoY);
    	currentAnimation.setDir(kierunek);
    	currentAnimation.reset();
    	performingAction = true;
    }
    
    public void attack(){
    	setCurrentAnimation("attack");
    	attacked = true;
    }
    
    public void move(Direction dir) {
    	setCurrentAnimation("walk");
    	position.move(dir);
    	moved = true;
	}
    
    public void kill(){
    	setCurrentAnimation("kill");
    }
    
    public void greet(){
    	setCurrentAnimation("greet");
    }
    
    public void dispose() {}
    
    public boolean isBusy(){
    	return performingAction;
    }
    
}
    	