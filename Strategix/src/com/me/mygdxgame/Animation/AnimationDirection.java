/**
 * 
 */
package com.me.mygdxgame.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.Direction;
import com.me.mygdxgame.SpriteAnimation;

/**
 * @author luk32
 *
 */
public class AnimationDirection extends SpriteAnimation {
	protected Direction dir = Direction.DOWN;

	/**
	 * 
	 */
	public AnimationDirection(AnimationAsset asset) {
		super(asset);
	}

	/**
	 * @param spriteSheet
	 * @param duration
	 * @param cols
	 */
	public AnimationDirection(TextureRegion spriteSheet, float duration, int cols) {
		super(spriteSheet, duration, cols);
	}

	/**
	 * @param spriteSheet
	 * @param duration
	 * @param cols
	 * @param rows
	 */
	public AnimationDirection(TextureRegion spriteSheet, float duration, int cols, int rows) {
		super(spriteSheet, duration, cols, rows);
	}

	/**
	 * @param spriteSheet
	 * @param duration
	 * @param cols
	 * @param x
	 * @param y
	 */
	public AnimationDirection(TextureRegion spriteSheet, float duration, int cols, float x, float y) {
		super(spriteSheet, duration, cols, x, y);
	}

	/**
	 * @param spriteSheet
	 * @param duration
	 * @param cols
	 * @param rows
	 * @param x
	 * @param y
	 */
	public AnimationDirection(TextureRegion spriteSheet, float duration, int cols, int rows, float x, float y) {
		super(spriteSheet, duration, cols, rows, x, y);
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		tryChangeAnimation();
		this.dir = dir;
	}
	
}
