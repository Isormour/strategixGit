package com.me.mygdxgame;


import java.awt.Menu;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Direction;

public class Marker {
BoardPosition position;
Direction sterownik = Direction.DOWN;
Person Target;
Texture ground;
Texture arrow;
Texture[] strzalki = new Texture[8];
Texture menu;
Texture pointer;
Texture Tex_move;
Texture Tex_attack;
Tile[] listaAttack;
Tile[] listaMove;
BitmapFont font = new BitmapFont(false);

Direction strzalka = Direction.DOWN;
float time = 0;
int frames = 0;
float delay = 0.06f;

boolean menuON = false;
boolean kierunki = false;
boolean moving= false;
boolean attacking = false;


float aTime=0;
int aFrames=0;
int pozycjaPointera = 1;
SoftReference<Person> champion;
Person Tester1;

public Marker(int x, int y, Texture ground, Texture arrow, Texture[] strzalki, Texture Menu, Texture pointer, Texture attack, Texture tex_move)
{
	this.position = new BoardPosition(x, y);
	this.arrow = arrow;
	this.ground = ground;
	this.strzalki = strzalki;
	this.menu = Menu;
	this.pointer  = pointer;
	this.Tex_attack = attack;
	this.Tex_move = tex_move;
}

public void set_champ(Person champ)
{
	champion = new SoftReference<Person>(champ);
	
}

public void dispose()
{
	ground.dispose();
	arrow.dispose();
}
//Move
public void move(Direction kierunek)
{
	if(champion == null)
	{
		position.move(kierunek);
	}
	else
	{
		if(menuON){
			switch (kierunek)
			{
			case DOWN:
				{
					pozycjaPointera++;
					if(pozycjaPointera>3)
					{
					pozycjaPointera = 1;
					}
				}
				break;
			case UP:
				{	
					pozycjaPointera--;
					if(pozycjaPointera<1)
					{
					pozycjaPointera = 3;
					}
				}
		    default:
		    	break;
			}
		}
		if(attacking||moving)
		{
			sterownik = kierunek;
		}
		if(kierunki)
		{// wybor strzalki
			strzalka = kierunek;
		}
	}
}

public void draw(SpriteBatch spriteBatch, float timer)
{
	if(champion == null)draw_standing_animation(spriteBatch, timer, ground, position.getIsoX(), position.getIsoY());
	if(attacking) draw_Tile(spriteBatch, 0);
	if(moving) draw_Tile(spriteBatch, 1);
}

//draw
public void draw_Interface(SpriteBatch spriteBatch,float timer)
{
	if(menuON)
	{
		draw_arrow(spriteBatch, timer, arrow, champion.get().position.getIsoX()+16, champion.get().position.getIsoY()+64);
		draw_menu(spriteBatch);
	}
	if(kierunki)
	{
		draw_strzalki(spriteBatch);
	}
	if(moving)
	{
		Tile tTile;
		tTile = listaMove[sterownik.toInt()];
		draw_arrow(spriteBatch, timer, arrow, tTile.isoX+16, tTile.isoY+32);
	return;
	}
	if(attacking)
	{ 
		Tile tTile;
		tTile = listaAttack[sterownik.toInt()];
		draw_arrow(spriteBatch, timer, arrow, tTile.isoX+16, tTile.isoY+32);
	}
}

private void draw_standing_animation(SpriteBatch spriteBatch, float timer, Texture tex2, float isoX, float isoY)
{
	time +=timer;
	
	if(time >delay)
	{
		frames++;
		time = 0;
	}
	spriteBatch.draw(tex2, isoX, isoY, 64*frames, 0, 64, 32);
	if(frames==9)
	{
		time = 0;
		frames= 0;
	}
}

private void draw_arrow(SpriteBatch spriteBatch, float timer, Texture tex2, float isoX, float isoY)
{
	aTime +=timer;

	if(aTime > 0.07f)
	{
		aFrames++;
		aTime = 0;
	}
	spriteBatch.draw(tex2, isoX, isoY, 32*aFrames, 0, 32, 64);
	
	if(aFrames==9)
	{
		aTime = 0;
		aFrames = 0;
	}
}

private void draw_menu(SpriteBatch spriteBatch)
{
	if(menuON){
	spriteBatch.draw(menu,240,140);
	spriteBatch.draw(pointer,260, 140+menu.getHeight()-30-pozycjaPointera*20);
	font.draw(spriteBatch,"atack ", 300, 140+menu.getHeight()-30);
	font.draw(spriteBatch,"move ", 300, 140+menu.getHeight()-50);
	font.draw(spriteBatch,"end ", 300, 140+menu.getHeight()-70);
	font.draw(spriteBatch,"menu numer "+pozycjaPointera, 240, 20);
	}	
}

public void approve(Person tester)
{
	if(champion==null)
	{// u¿ycie przycisku U na planszy
		if(position.sameAs(tester.position))
		{
			tester.set_animation(1);
			tester.busy = true;
			tester.selected = true;
			set_champ(tester);
			menuON = true;
		}
	}
	if(kierunki)
	{
		tester.kierunek = strzalka;
		reset(tester);
		return;
	}
	if(menuON)
	{
		switch (pozycjaPointera)
	    {
		case 1:
			// atack
			if(!tester.busy)
			{ 	
				if(!tester.attacked)
				{
					attacking = true;
					menuON = false;
					Make_AList();
				}
			}
			break;
		case 2:
			if(!tester.moved){
				if(!tester.busy)
				{
					moving = true;
					menuON = false;
					Make_Mlist();
				}
			}
			break;
		case 3:
			// end
			kierunki  = true;
			menuON = false;
			break;
	    }
		return;
	}
	if (moving)
	{
		position.move(sterownik);
		tester.kierunek = sterownik;
		moving = false;
		tester.moved = true;
		menuON = true;
		tester.animation = 4;
		return;
	}
	if(attacking)
	{
		tester.kierunek = sterownik;
		attacking = false;
		tester.attacked = true;
		menuON = true;
		tester.animation = 2;
		return;
	}

}

private void reset(Person tester)
{
	champion = null;
	tester.selected = false;
	tester.endTurn();
	pozycjaPointera = 1;
	kierunki = false;
}

private void draw_strzalki(SpriteBatch spriteBatch)
{
	int correctorX = 8;
	int correctorY = 8;
	Vector2 nadglowa = new Vector2(24,48);
	int isoX = position.getIsoX();
	int isoY = position.getIsoY();
	
	if(strzalka == Direction.DOWN){spriteBatch.draw(strzalki[1], isoX+correctorX+nadglowa.x, isoY-correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[1+4], isoX+correctorX+nadglowa.x, isoY-correctorY+nadglowa.y);}
	if(strzalka == Direction.LEFT){spriteBatch.draw(strzalki[0], isoX-correctorX+nadglowa.x, isoY-correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[0+4], isoX-correctorX+nadglowa.x, isoY-correctorY+nadglowa.y);}
	if(strzalka == Direction.RIGHT){spriteBatch.draw(strzalki[2], isoX+correctorX+nadglowa.x, isoY+correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[2+4], isoX+correctorX+nadglowa.x, isoY+correctorY+nadglowa.y);}
	if(strzalka == Direction.UP){spriteBatch.draw(strzalki[3], isoX-correctorX+nadglowa.x, isoY+correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[3+4], isoX-correctorX+nadglowa.x, isoY+correctorY+nadglowa.y);}
}

private Tile[] makeTiles(Texture tex) {
	Tile[] tiles = new Tile[4]; // list size is the number of directions
	int x,y;
	x = champion.get().position.getX();
	y = champion.get().position.getY();
	tiles[Direction.RIGHT.toInt()] = new Tile((x+1), (y), tex); //Right
	tiles[Direction.DOWN.toInt()] = new Tile((x), (y-1), tex); //Down
	tiles[Direction.UP.toInt()] = new Tile((x), (y+1), tex); //Up
	tiles[Direction.LEFT.toInt()] = new Tile((x-1), (y), tex); //Left
	return tiles;
}

private void Make_AList()
{
	listaAttack = makeTiles(Tex_attack);
}

private void Make_Mlist()
{
	listaMove = makeTiles(Tex_move);
}

private void draw_Tile(SpriteBatch spriteBatch, int numer)
{
	switch (numer)
    {
	case 0:
		for(int i = 0; i < listaAttack.length; i++)
		{
			listaAttack[i].draw(spriteBatch);
		}
		break;
	case 1:
		for(int i = 0; i<listaMove.length; i++)
		{
			listaMove[i].draw(spriteBatch);
		}
	default:
		break;
    }   
}

}



