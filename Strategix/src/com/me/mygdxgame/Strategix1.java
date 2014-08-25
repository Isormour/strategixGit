package com.me.mygdxgame;

import java.util.Map;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.me.mygdxgame.GameInputManager.*;

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
	
	Person tester;
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
	
	
	
	Map<Integer,Boolean> Keys_input;
	
	
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
		
		tex_greet = new Texture(Gdx.files.internal("data/greet.png"));
		tex_atack = new Texture(Gdx.files.internal("data/atack.png"));
		tex_death = new Texture(Gdx.files.internal("data/death.png"));
		tex_walk = new Texture(Gdx.files.internal("data/walk.png"));
		tester = new Person(1, 1, tex_greet,tex_atack,tex_death,tex_walk);
		
		
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
		tester.dispose();
		player1.dispose();
		//kuniec dispose
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0.35f, 0.35f, 1f, 1.0f);
		Gdx.gl.glClear(Gdx.gl10.GL_COLOR_BUFFER_BIT);
		czasownik += Gdx.app.getGraphics().getDeltaTime();
	
		if(starter == true)
		{
			cam.ruchscreena(Direction.DOWN, 500);
			cam.ruchscreena(Direction.LEFT, 600);
			cam.camera.update();
			starter = false;
		}
	
		//game input
		if(Gdx.input.justTouched())
		{
			touch.x = Gdx.input.getX();
			touch.y = Gdx.input.getY();
			Rectangle tester_rect = new Rectangle(tester.position.getIsoX(),tester.position.getIsoY()+48, 64,64);
			if(tester_rect.contains(touch))
			{
				if(!tester.busy){
					tester.animation=1;
				}
			}
		}
		if(!tester.busy)
		{
			//if(Gdx.input.isKeyPressed(Keys.J))tester.set_animation(1);
			//if(Gdx.input.isKeyPressed(Keys.H))tester.set_animation(2);
			//if(Gdx.input.isKeyPressed(Keys.K))tester.set_animation(3);
			//if(Gdx.input.isKeyPressed(Keys.L))tester.set_animation(4);
		}
		
		
		//game input
	if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT))
	{
		
		if(Gdx.input.isKeyPressed(Keys.W))cam.ruchscreena(Direction.UP);
		if(Gdx.input.isKeyPressed(Keys.S))cam.ruchscreena(Direction.DOWN);
		if(Gdx.input.isKeyPressed(Keys.A))cam.ruchscreena(Direction.LEFT);
		if(Gdx.input.isKeyPressed(Keys.D))cam.ruchscreena(Direction.RIGHT);
	}
	if(KeyboardManager.is_Pressed(KeyboardManager.W))player1.move(Direction.UP);
	if(KeyboardManager.is_Pressed(KeyboardManager.S))player1.move(Direction.DOWN);
	if(KeyboardManager.is_Pressed(KeyboardManager.A))player1.move(Direction.LEFT);
	if(KeyboardManager.is_Pressed(KeyboardManager.D))player1.move(Direction.RIGHT);
	if(KeyboardManager.is_Pressed(KeyboardManager.U))
	{
		player1.approve(tester);
	}
	spriteBatch.setProjectionMatrix(cam.camera.combined);
	spriteBatch.begin();
	font.draw(spriteBatch,Boolean.toString(tester.selected) , 240, 240);
	font.draw(spriteBatch,"time"+Float.toString(Gdx.app.getGraphics().getDeltaTime()) , 240, 220);
	font.draw(spriteBatch,"frames "+Integer.toString(tester.frames) , 240, 200);
	font.draw(spriteBatch,"kierunek "+tester.kierunek.toString() , 240, 180);
	
	font.draw(spriteBatch,
			"tester x: "+ Long.toString(tester.position.getX()) + 
			", y: " + Long.toString(tester.position.getY()), 
			240, 140);
	font.draw(spriteBatch,
			"marker x: "+ Long.toString(player1.position.getX()) +
			", y: "+ Long.toString(player1.position.getY()),
			240, 160);

	font.draw(spriteBatch,"delay"+Float.toString(tester.delay) , 240, 100);
	font.draw(spriteBatch,"marker time"+Float.toString(player1.time) , 300, 100);
	font.draw(spriteBatch,"timer "+Float.toString(tester.timer) , 240, 80);
	font.draw(spriteBatch,"busy "+Boolean.toString(tester.busy) , 240, 60);
	font.draw(spriteBatch,"rows"+ Integer.toString(tester.rows) , 240, 40);

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
	tester.draw(spriteBatch,  Gdx.app.getGraphics().getDeltaTime());
	player1.draw_Interface(spriteBatch, Gdx.app.getGraphics().getDeltaTime());
	spriteBatch.end();
	//input update
	KeyboardManager.update();
	}//render
	
	@Override
	public void resize(int width, int height) {
		cam.camera.setToOrtho(false, width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
