package com.me.mygdxgame.Animation.Person;
import com.me.mygdxgame.Direction;
import com.me.mygdxgame.Animation.AnimationAsset;

/**
 * Concrete implementation of animation for the Person class. Basically a wrapper around {@link AnimationWalk}.
 * @author luk32
 * TODO Consider to exchange such final classes to be replaced by json + png assets.
 * TODO Write documentation
 */
final public class AnimationPersonWalk extends AnimationPerson {

	public AnimationPersonWalk( AnimationAsset asset ) {
		super(asset);
	}

	public AnimationPersonWalk( AnimationAsset asset, Direction dir, float x, float y ) {
		super(asset, dir, x, y);
	}
	
	@Override
	protected void update() {
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
