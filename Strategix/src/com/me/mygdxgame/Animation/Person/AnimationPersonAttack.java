package com.me.mygdxgame.Animation.Person;

import com.me.mygdxgame.Direction;
import com.me.mygdxgame.Animation.AnimationAsset;

public class AnimationPersonAttack extends AnimationPerson {
	public AnimationPersonAttack(AnimationAsset asset) {
		super(asset);
	}

	public AnimationPersonAttack(AnimationAsset asset, Direction dir, float x, float y) {
		super(asset, dir, x, y);
	}
	
	@Override
	protected void update() {
		int direction = getCurrentFrameIndex() > 4 ? -1 : 1;
		float dx = 1.6f * direction * framesDelta();
		float dy = 0.8f * direction * framesDelta();
    	switch(dir){
		case DOWN:
			movePosition( dx, -dy);
			break;
		case LEFT:
			movePosition(-dx, -dy);
			break;
		case RIGHT:
			movePosition( dx,  dy);
			break;
		case UP:
			movePosition(-dx,  dy);
			break;    	
		}
	}
}
