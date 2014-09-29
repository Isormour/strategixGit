package com.me.mygdxgame;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.Animation.AnimationAsset;
import com.me.mygdxgame.GameInputManager.GameInputManager;
import com.me.mygdxgame.GameInputManager.KeyboardManager;

public class Strategix1 implements ApplicationListener {
    SpriteBatch spriteBatch;
	BitmapFont font;
	Camera cam;
	Vector2 touch;
	float czasownik;
	Marker player1;
	Texture tex_Floor;
	Floor[][] map = new Floor[4][4];
	boolean starter = true;
	
	Person person;
	Texture attackSelector;
	Texture moveSelector;
	Texture tex_arrow;
	Texture tex_ground;
	Texture tex_menu;
	Texture tex_greet;
	Texture tex_atack;
	Texture tex_death;
	Texture tex_walk;
	Texture tex_pointer;
	
	Texture[] strzalki = new Texture[8];
	
	
	Map<String, AnimationAsset> animationAssets = new HashMap<String, AnimationAsset>();
	Map<Integer, Boolean> Keys_input;
	
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont(false);
		cam = new Camera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		tex_Floor = new Texture(Gdx.files.internal("data/rect64.png"));
		touch = new Vector2();
		czasownik = Gdx.app.getGraphics().getDeltaTime();
		Gdx.input.setInputProcessor(new GameInputManager());
		
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map.length;j++)
			{
				map[i][j]= new Floor(i, j, tex_Floor);
			}
		}
		
		GLTexture.setEnforcePotImages(false);
		person = new Person(1, 1);
		
		// menu textures
		tex_menu = new Texture(Gdx.files.internal("data/menu/menu.png"));
		tex_pointer = new Texture(Gdx.files.internal("data/menu/pointer.png"));
		attackSelector = new Texture(Gdx.files.internal("data/menu/attackSelector.png"));
		moveSelector = new Texture(Gdx.files.internal("data/menu/moveSelector2.png"));
		
		
		
		for(int i = 0; i<4; i++)
		{
			strzalki[i] = new Texture(Gdx.files.internal("data/strzalki/strzalka"+i+".png"));
		}
		
		for(int i = 4; i<8; i++)
		{	int numer = i-4;
			strzalki[i] = new Texture(Gdx.files.internal("data/strzalki/strzalka"+numer+"n.png"));
		}
		
		// world textures
		tex_arrow =new Texture(Gdx.files.internal("data/arrow.png"));
		tex_ground =new Texture(Gdx.files.internal("data/marker_anim.png"));
		
		//player marker
		player1 = new Marker(2, 2, tex_ground, tex_arrow, strzalki, tex_menu, tex_pointer, attackSelector, moveSelector);
		
		//input manager
	} //create
		
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
		tex_Floor.dispose();
		for(int i=map.length-1; i>=0; i--)
		{
			for(int j=map.length-1; j>=0; j--)
			{
				map[i][j].dispose();
			}
		}
		person.dispose();
		player1.dispose();
		//kuniec dispose
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0.35f, 0.35f, 1f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		czasownik += Gdx.app.getGraphics().getDeltaTime();
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
	
		if(starter == true)
		{
			cam.translate(Direction.UP, 150);
			cam.translate(Direction.RIGHT, 200);
			cam.update();
			starter = false;
		}
	
		//game input
		if(Gdx.input.justTouched())
		{
			//TODO: Below code won't work, because input x and y seem to be arbitrary to the map.
			//Probably, the screen coordinates should be adjusted to the map coordinates.
			touch.x = Gdx.input.getX();
			touch.y = Gdx.input.getY();
			Vector3 touchpos = new Vector3(touch, 0);
			
			cam.unproject(touchpos);
			touch.x = touchpos.x;
			touch.y = touchpos.y;
			Rectangle tester_rect = new Rectangle(person.position.getIsoX(),person.position.getIsoY(), 64,64);
			if(tester_rect.contains(touchpos.x, touchpos.y))
			{
				if(!person.isBusy()){
					person.greet();
				}
			}
		}
		//game input
	if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT))
	{
		if(Gdx.input.isKeyPressed(Keys.W))cam.translate(Direction.UP);
		if(Gdx.input.isKeyPressed(Keys.S))cam.translate(Direction.DOWN);
		if(Gdx.input.isKeyPressed(Keys.A))cam.translate(Direction.LEFT);
		if(Gdx.input.isKeyPressed(Keys.D))cam.translate(Direction.RIGHT);
	}
	if(KeyboardManager.is_Pressed(KeyboardManager.W))player1.move(Direction.UP);
	if(KeyboardManager.is_Pressed(KeyboardManager.S))player1.move(Direction.DOWN);
	if(KeyboardManager.is_Pressed(KeyboardManager.A))player1.move(Direction.LEFT);
	if(KeyboardManager.is_Pressed(KeyboardManager.D))player1.move(Direction.RIGHT);
	if(KeyboardManager.is_Pressed(KeyboardManager.U))
	{
		player1.approve(person);
	}
	font.draw(spriteBatch, "touch: " + touch.x + ", " + touch.y, 240, 20);
	font.draw(spriteBatch,Boolean.toString(person.selected) , 240, 240);
	font.draw(spriteBatch,"time"+Float.toString(Gdx.app.getGraphics().getDeltaTime()) , 240, 220);
	font.draw(spriteBatch,"kierunek "+person.kierunek.toString() , 240, 180);
	
	font.draw(spriteBatch, "tester: " + person.position, 240, 140);
	font.draw(spriteBatch, "marker: "+ player1.position, 240, 160);

	font.draw(spriteBatch,"marker time"+Float.toString(player1.time) , 300, 100);
	font.draw(spriteBatch,"busy "+Boolean.toString(person.isBusy()) , 240, 60);

	font.draw(spriteBatch,"zmiana kierunku WSAD", -150, 240);
	font.draw(spriteBatch,"U- potwierdzenie", -150, 220);
	font.draw(spriteBatch,"J-powitanie ", -150, 200);
	font.draw(spriteBatch,"K-smierc ", -150, 180);
	font.draw(spriteBatch,"L-przemieszczanie ", -150, 160);
	
	for(int i=map.length-1;i>=0;i--)
	{
		for(int j=map.length-1;j>=0;j--)
		{
			map[i][j].draw(spriteBatch);
		}
	}
	
	player1.draw(spriteBatch, Gdx.app.getGraphics().getDeltaTime());
	person.draw(spriteBatch, Gdx.app.getGraphics().getDeltaTime());
	player1.draw_Interface(spriteBatch, Gdx.app.getGraphics().getDeltaTime());
	spriteBatch.end();
	//input update
	KeyboardManager.update();
	}//render
	
	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = Gdx.graphics.getWidth();
		cam.viewportHeight = Gdx.graphics.getHeight();
		cam.update();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
