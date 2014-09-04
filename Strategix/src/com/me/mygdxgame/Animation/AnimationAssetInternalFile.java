package com.me.mygdxgame.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationAssetInternalFile extends AnimationAsset {
	protected String path;
	public AnimationAssetInternalFile( String path, int sheetCols, int sheetRows, int frameWidth, int frameHeight, float baseDuration ) {
		super(
			new TextureRegion( new Texture(Gdx.files.internal(path)) ),
			sheetCols,
			sheetRows,
			frameHeight,
			frameHeight,
			baseDuration
		);
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
