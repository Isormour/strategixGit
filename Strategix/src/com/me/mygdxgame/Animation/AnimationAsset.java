package com.me.mygdxgame.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationAsset {

	protected int sheetCols;
	protected int sheetRows;
	int sheetWidth;
	int sheetHeight;
	protected int frameWidth;
	protected int frameHeight;
	protected float baseDuration;
	protected TextureRegion texture;

	public AnimationAsset(TextureRegion texture, int sheetCols, int sheetRows, int frameWidth, int frameHeight, float baseDuration) {
		this.sheetCols = sheetCols;
		this.sheetRows = sheetRows;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.baseDuration = baseDuration;
		this.sheetWidth = frameWidth * sheetCols;
		this.sheetHeight = frameHeight * sheetRows;
		this.texture = texture;
	}

	public int getSheetCols() {
		return sheetCols;
	}

	public int getSheetRows() {
		return sheetRows;
	}

	public int getSheetWidth() {
		return sheetWidth;
	}

	public int getSheetHeight() {
		return sheetHeight;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public float getBaseDuration() {
		return baseDuration;
	}

	public TextureRegion getTexture() {
		return texture;
	}

}