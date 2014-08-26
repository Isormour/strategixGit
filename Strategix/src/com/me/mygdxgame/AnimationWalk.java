/**
 * 
 */
package com.me.mygdxgame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class that is used for walk animations (duh).
 * @author luk32
 *
 */
public class AnimationWalk extends SpriteAnimation {
	private Direction dir;
	/**
	 * Please refer to {@link SpriteAnimation} documentation for full information 
	 * @param spriteSheet
	 * @param cols
	 * @param dir Movement direction
	 */
	public AnimationWalk(TextureRegion spriteSheet, float duration, int cols, Direction dir) {
		super(spriteSheet, duration, cols);
		this.dir = dir;
	}

	/**
	 * Please refer to {@link SpriteAnimation} documentation for full information 
	 * @param spriteSheet
	 * @param cols
	 * @param rows
	 * @param dir Movement direction
	 */
	public AnimationWalk(TextureRegion spriteSheet, float duration, int cols, int rows, Direction dir) {
		super(spriteSheet, duration, cols, rows);
		this.dir = dir;
	}

	/**
	 * Please refer to {@link SpriteAnimation} documentation for full information 
	 * @param spriteSheet
	 * @param cols
	 * @param x
	 * @param y
	 * @param dir Movement direction
	 */
	public AnimationWalk(TextureRegion spriteSheet, float duration, int cols, int x, int y, Direction dir) {
		super(spriteSheet, duration, cols, x, y);
		this.dir = dir;
	}

	/**
	 * Please refer to {@link SpriteAnimation} documentation for full information 
	 * @param spriteSheet
	 * @param cols
	 * @param rows
	 * @param x
	 * @param y
	 * @param dir Movement direction
	 */
	public AnimationWalk(TextureRegion spriteSheet, float duration, int cols, int rows, float x, float y, Direction dir) {
		super(spriteSheet, cols, rows, x, y);
		this.dir = dir;
	}
	
	/**
	 * Updates position of frame on screen according to the movement direction.
	 * WARNING. I think it's suited for 32x16 pixel board cells. Not sure, I didn't come up with the values.
	 */
	public void update(){
		switch (dir)
		{
			case DOWN:
				movePosition(  1.6f * framesDelta(), -0.8f * framesDelta());
				break;
			case LEFT:
				movePosition( -1.6f * framesDelta(), -0.8f * framesDelta());
				break;
			case RIGHT:
				movePosition(  1.6f * framesDelta(),  0.8f * framesDelta());
				break;
			case UP:
				movePosition( -1.6f * framesDelta(),  0.8f * framesDelta());
				break;
		}
	}
}
