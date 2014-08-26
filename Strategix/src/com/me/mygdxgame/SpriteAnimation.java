package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class abstracts and encapsulates animations in game.
 * 
 * It uses native libgdx Texture, Sprite and Animation and is intended to be used as a base class
 * for more advanced/custom animations.
 *
 * @author luk32
 */
public class SpriteAnimation{
	private float x;
	private float y;
	private float duration;

	private TextureRegion[] frames;
	private Animation animation;
	private TextureRegion currentFrame;
	private float time = 0f; // Time since start of animation. 
	private int lastFrameIndex = 0;

	final private float fps = 60.0f;
	
	/**
	 * An invocation that do not set position (x,y = 0,0) and assumes that frames do not span over multiple rows (rows=1) .
	 * For details please refer to full invocation. 
	 */
	public SpriteAnimation( TextureRegion spriteSheet, float duration, int cols ){
		this(spriteSheet, duration, cols, 1, 0, 0);
	}
	
	/**
	 * An invocation that do not set position (x,y = 0,0).
	 * For details please refer to full invocation. 
	 */
	public SpriteAnimation( TextureRegion spriteSheet, float duration, int cols, int rows ){
		this(spriteSheet, duration, cols, rows, 0, 0);
	}
		
	/**
	 * An invocation that assumes that frames do not span over multiple rows (rows=1).
	 * For details please refer to full invocation. 
	 */
	public SpriteAnimation( TextureRegion spriteSheet, float duration, int cols, float x, float y ) {
		this(spriteSheet, duration, cols, 1, x, y );
	}
	
	/**
	 * Creates an animation from a sprite-sheet {@link TextureRegion}.
	 * @param spriteSheet {@link TextureRegion} that contains animation frames.
	 * @param duration Desired duration of animation in seconds.
	 * @param cols Number of columns in the sprite-sheet.
	 * @param rows Number of rows in the sprite-sheet.
	 * @param x Position at screen for drawing (x-coordinate).
	 * @param y Position at screen for drawing (y-coordinate).
	 */
	public SpriteAnimation( TextureRegion spriteSheet, float duration, int cols, int rows, float x, float y ) {
		setPosition(x, y);
		TextureRegion[][] tmp_frames;
		tmp_frames = spriteSheet.split(spriteSheet.getRegionWidth()/cols, spriteSheet.getRegionHeight()/rows);
		frames = flattenTextRegionArray(tmp_frames);
		this.duration = duration;
		animation = new Animation(duration/fps, frames);
		if( checkSurplusFrames() ){
			Gdx.app.debug("Strategix", "Sprite Animation: Some frames of animation will be skipped by default with current settings.");
		}
	}
	
	/**
	 * Updates current frame and draws it.
	 * @param spriteBatch A sprite batch the animation frame will be drawn on.
	 */
	public void draw(SpriteBatch spriteBatch){
		time += Gdx.graphics.getDeltaTime();
		currentFrame = animation.getKeyFrame(time);
		update();
		spriteBatch.draw(currentFrame, this.x, this.y);
		lastFrameIndex = animation.getKeyFrameIndex(time);
	}
	
	/**
	 * Additionally to the simple invocation also sets the position for drawing.
	 * @param spriteBatch A sprite batch the animation frame will be drawn on.
	 * @param x New x coord for the animation.
	 * @param y New y coord for the animation.
	 */
	public void draw(SpriteBatch spriteBatch, float x, float y){
		setPosition(x, y);
		draw(spriteBatch);
	}
	
	/**
	 * Returns how many frames we moved since last draw.
	 * Useful in computation of moving parts.
	 * @return
	 */
	public int framesDelta(){
		return animation.getKeyFrameIndex(time) - lastFrameIndex;
	}
	
	public boolean isFinished(){
		return animation.isAnimationFinished(time);
	}
	
	/**
	 * Sets new position for the animation.
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Moves the position of animation by x,y pixels.
	 * @param x
	 * @param y
	 */
	public void movePosition(float x, float y) {
		this.x += x;
		this.y += y;		
	}
	
	/**
	 * Used to update animation, like position, current frame, or anything.
	 * Meant to be overridden for more advanced animations.
	 */
	public void update() {}
	
	/**
	 * Checks whether the number of frames is greater than needed.
	 * E.g. If animation would have a duration of 0.3s @ 30fps, 10 frames would be needed/rendered/drawn.
	 * So there is no need for a sprite sheet to contain more frames, because they will be skipped by default. 
	 * @return True if it is.
	 */
	private boolean checkSurplusFrames(){
		return frames.length > fps/duration;
	}
	
	/**
	 * Flattens a 2D array of {@link TextureRegion} into a 1D array.
	 * 
	 * Meant to be used as a tool to convert result retrieved from {@link TextureRegion}.split() 
	 * into a parameter fed to {@link Animation}.
	 * @param array 2D {@link TextureRegion} array
	 * @return flattened 1D concatenation of input array
	 */
	public static TextureRegion[] flattenTextRegionArray(TextureRegion[][] array){
		int rows = array.length;	//"outer" dimension size
		int cols = array[0].length; //"inner" dimension size
		int len = rows * cols;
		TextureRegion[] flat = new TextureRegion[len];
		for (int i = 0; i < rows; ++i)
			System.arraycopy(array[i], 0, flat, i*cols, cols);
		return flat;
	}
}
