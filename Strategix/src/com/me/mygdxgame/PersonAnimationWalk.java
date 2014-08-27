package com.me.mygdxgame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 
 */

/**
 * Concrete implementation of animation for the Person class. Basically a wrapper around {@link AnimationWalk}.
 * @author luk32
 * TODO Consider to exchange such final classes to be replaced by json + png assets.
 * TODO Write documentation
 */
final public class PersonAnimationWalk extends AnimationWalk {
	//some basic configuration variables
	static int sheetCols = 10;
	static int sheetRows = 2;
	static int sheetWidth = 64*sheetCols;
	static int sheetHeight = 64*sheetRows;
	static float duration = 1.5f;
	static Texture texture = new Texture(Gdx.files.internal("data/walk.png")); 
	
	public PersonAnimationWalk( ) {
		super( );
	}

	public PersonAnimationWalk( Direction dir, float x, float y ) {
		super( );
		setPosition(x, y);
		setDir(dir);
		setDuration(duration);
	}

	private TextureRegion getTextureRegion( Direction dir ) {
		// because sprites of walking animations for each direction are in one big file
		// we have to crop out the region contains only the frames we are interested in
		return new TextureRegion(texture, 0, sheetHeight*dir.toInt(), sheetWidth, sheetHeight);
	}

	@Override
	public void setDir(Direction dir) {
		super.setDir(dir);
		setSpriteSheet(getTextureRegion(dir), sheetCols, sheetRows);
	}
	
	
}
