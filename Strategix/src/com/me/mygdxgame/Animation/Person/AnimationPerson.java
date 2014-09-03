package com.me.mygdxgame.Animation.Person;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.Direction;
import com.me.mygdxgame.Animation.AnimationAsset;
import com.me.mygdxgame.Animation.AnimationDirection;

public class AnimationPerson extends AnimationDirection {
	public AnimationPerson(AnimationAsset asset) {
		this(asset, Direction.DOWN, 0f, 0f);
	}

	public AnimationPerson(AnimationAsset asset, Direction dir, float x, float y) {
		super(asset);
		setPosition(x, y);
		setDir(dir);
	}

	private TextureRegion getTextureRegion(Direction dir) {
		// because sprites of walking animations for each direction are in one big file
		// we have to crop out the region contains only the frames we are interested in
		return new TextureRegion(asset.getTexture(), 0, asset.getSheetHeight()*dir.toInt(), asset.getSheetWidth(), asset.getSheetHeight());
	}

	@Override
	public void setDir(Direction dir) {
		super.setDir(dir);
		setSpriteSheet(getTextureRegion(dir));
	}
}