package com.me.mygdxgame.Animation.Person;

import com.me.mygdxgame.Direction;
import com.me.mygdxgame.Animation.AnimationAsset;

final public class AnimationPersonGreet extends AnimationPerson {
	public AnimationPersonGreet( AnimationAsset asset ) {
		super( asset );
	}
	
	public AnimationPersonGreet( AnimationAsset asset, Direction dir, float x, float y ) {
		super( asset, dir, x, y );
	}
}
