package com.me.mygdxgame.Animation.Person;
import com.me.mygdxgame.Direction;
import com.me.mygdxgame.Animation.AnimationAsset;

/**
 * Concrete implementation of animation for the Person class. Basically a wrapper around {@link AnimationWalk}.
 * @author luk32
 * TODO Consider to exchange such final classes to be replaced by json + png assets.
 * TODO Write documentation
 */
final public class AnimationPersonDeath extends AnimationPerson {
	public AnimationPersonDeath( AnimationAsset asset) {
		super(asset);
	}

	public AnimationPersonDeath( AnimationAsset asset, Direction dir, float x, float y ) {
		super(asset, dir, x, y);
	}
}
