package com.me.mygdxgame;


import java.awt.Menu;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Marker {
int x,y;
int sterownik = 0;
Person Target;
Texture ground;
Texture arrow;
Texture[] strzalki = new Texture[8];
Texture menu;
Texture pointer;
Texture Tex_move;
Texture Tex_attack;
List<Tile> listaAttack = new ArrayList<Tile>();
List<Tile> listaMove = new ArrayList<Tile>();


float isoX;
float isoY;
int strzalka = 0 ;
float time=0;
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

public void set_champ(Person champ)
{
	champion = new SoftReference<Person>(champ);
	
}
private void ToIso()
{
    this.isoX = x*32 - y*32 ;
    this.isoY = ((x*32 + y*32) / 2);
}
public Marker(int x,int y,Texture ground,Texture arrow, Texture[] strzalki,Texture Menu,Texture pointer,Texture attack,Texture tex_move)
{
	this.x=x;
	this.y=y;
	this.arrow = arrow;
	this.ground = ground;
	this.strzalki = strzalki;
	this.menu = Menu;
	this.pointer  = pointer;
	this.Tex_attack = attack;
	this.Tex_move = tex_move;
	ToIso();
	
}
public void dispose()
{
	ground.dispose();
	arrow.dispose();
}
//Move
public void move(int kierunek)
{
	if(champion == null)
	{
	switch (kierunek)
    {//ruch po planszy
	case 0:
		y--;
		break;
	case 1:
		x--;
		break;
	case 2:
		x++;
		break;
	case 3:
		y++;
		break;
	
    }
	ToIso();
	}
	else
	{
		
		if(menuON){
			switch (kierunek)
			{
			case 1:
			{
				pozycjaPointera++;
				if(pozycjaPointera>3)
				{
				pozycjaPointera = 1;
				}
			break;
			}
			case 2:
			{	pozycjaPointera--;
				if(pozycjaPointera<1)
				{
				pozycjaPointera = 3;
				}
			
			break;}
			
			}
		}
		if(attacking||moving)
		{
			sterownik = kierunek;
		}
		if(kierunki)
		{// wybor strzalki
			switch (kierunek)
		    {
			case 0:
				strzalka= 0;
				break;
			case 1:
				strzalka= 1;
				break;
			case 2:
				strzalka= 2;
				break;
			case 3:
				strzalka= 3;
				break;
			
		    }
		}
	}}
public void draw(SpriteBatch spriteBatch,float timer)
{
	if(champion ==null)draw_standing_animation(spriteBatch, timer, ground,isoX,isoY);
	if(attacking)draw_Tile(spriteBatch, 0);
	if(moving)draw_Tile(spriteBatch, 1);
}
//draw
public void draw_Interface(SpriteBatch spriteBatch,float timer)
{
	
	
	
	if(menuON)
	{
		draw_arrow(spriteBatch, timer, arrow,champion.get().isoX+16 ,champion.get().isoY+64);
		draw_menu(spriteBatch);
		
		
	}
	if(kierunki)
	{
		draw_strzalki(spriteBatch);
	}
	if(moving)
	{
		Tile tTile;
		switch (sterownik)
	    {

		case 0:
			tTile = listaMove.get(1);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		case 1:
			tTile = listaMove.get(0);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		case 2:
			tTile = listaMove.get(2);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		case 3:

			tTile = listaMove.get(3);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
			
	}
		return;}
	if(attacking)
	{ Tile tTile;
		switch (sterownik)
	    {

		case 0:
			tTile = listaAttack.get(1);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		case 1:
			tTile = listaAttack.get(0);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		case 2:
			tTile = listaAttack.get(2);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		case 3:

			tTile = listaAttack.get(3);
			draw_arrow(spriteBatch, timer, arrow,tTile.isoX+16 ,tTile.isoY+32);
			break;
		
	    }
		
		
	}
	
	
}
private void draw_standing_animation(SpriteBatch spriteBatch,float timer,Texture tex2,float isoX,float IsoY)
	{
	 	
	    	time +=timer;
	
	    	if(time >delay)
	    	{
	    		frames++;
	    		time = 0;
	    	}
	    	
	    	spriteBatch.draw(tex2, isoX, isoY,64*frames,0, 64, 32);
	    	if(frames==9)
	    	{
	    		time = 0;
	    		frames= 0;
	}
}
private void draw_arrow(SpriteBatch spriteBatch,float timer,Texture tex2,float isoX,float isoY)
	{
	
	
    	aTime +=timer;

    	if(aTime >0.07f)
    	{
    		aFrames++;
    		aTime = 0;
    	}
    	
    	spriteBatch.draw(tex2, isoX, isoY,32*aFrames,0, 32, 64);
    	if(aFrames==9)
    	{
    		aTime = 0;
    		aFrames= 0;
    	}

	}
private void draw_menu(SpriteBatch spriteBatch)
{
	if(menuON){
	spriteBatch.draw(menu,240,140);
	
	spriteBatch.draw(pointer,260, 140+menu.getHeight()-30-pozycjaPointera*20);
	BitmapFont font = new BitmapFont(false);
	font.draw(spriteBatch,"atack ", 300, 140+menu.getHeight()-30);
	font.draw(spriteBatch,"move ", 300, 140+menu.getHeight()-50);
	font.draw(spriteBatch,"end ", 300, 140+menu.getHeight()-70);
	font.draw(spriteBatch," menu numer"+pozycjaPointera, 240, 20);
	}	
}
//Approve
public void approve(Person tester)
{
	if(champion==null)
	{// u¿ycie przycisku U na planszy
			if(x ==tester.x)
			{
			if(y == tester.y)
			{
				tester.set_animation(1);
				tester.busy = true;
				tester.selected = true;
				set_champ(tester);
				menuON = true;
				
			}
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
					attacking= true;
					menuON = false;
					Make_AList();
				}
			}
			break;
		case 2:
		
			if(!tester.moved){
				if(!tester.busy)
				{
					moving= true;
					menuON= false;
					Make_Mlist();
					
				}
			}
			break;
		case 3:
			// end
			kierunki  = true;
			menuON = false;
			listaAttack.clear();
			listaMove.clear();
			
			break;
			
		
	    }
	return;}
	if (moving)
	{
		switch (sterownik)
	    {

		case 0:
			tester.kierunek = 0;
			y--;
			break;
		case 1:
			x--;
			tester.kierunek = 1;
			break;
		case 2:
			x++;
			tester.kierunek = 2;
			break;
		case 3:
			y++;
			tester.kierunek = 3;
			break;
		
	    }
		ToIso();
		moving = false;
		tester.moved = true;
		menuON = true;
		tester.animation = 4;
		return;
	}
	if(attacking)
	{
		switch (sterownik)
	    {

		case 0:
			tester.kierunek = 0;
			break;
		case 1:
			tester.kierunek = 1;
			break;
		case 2:
			tester.kierunek = 2;
			break;
		case 3:
			tester.kierunek = 3;
			break;
		
	    }
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
	
	if(strzalka  == 0){spriteBatch.draw(strzalki[1], isoX+correctorX+nadglowa.x, isoY-correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[1+4], isoX+correctorX+nadglowa.x, isoY-correctorY+nadglowa.y);}
	if(strzalka  == 1){spriteBatch.draw(strzalki[0], isoX-correctorX+nadglowa.x, isoY-correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[0+4], isoX-correctorX+nadglowa.x, isoY-correctorY+nadglowa.y);}
	if(strzalka == 2){spriteBatch.draw(strzalki[2], isoX+correctorX+nadglowa.x, isoY+correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[2+4], isoX+correctorX+nadglowa.x, isoY+correctorY+nadglowa.y);}
	if(strzalka  ==3){spriteBatch.draw(strzalki[3], isoX-correctorX+nadglowa.x, isoY+correctorY-1+nadglowa.y);}
	else{spriteBatch.draw(strzalki[3+4], isoX-correctorX+nadglowa.x, isoY+correctorY+nadglowa.y);}
	
		
}
private void Make_AList()
{
	Tile tTile;
	int x,y;
	x = champion.get().x;
	y = champion.get().y;
	//W=2
    //S=1
	//A=3
	//D=0
	tTile = new Tile((x-1),(y),Tex_attack);
	listaAttack.add(tTile);
	tTile = new Tile((x),(y-1),Tex_attack);
	listaAttack.add(tTile);
	tTile = new Tile((x+1),(y),Tex_attack);
	listaAttack.add(tTile);
	tTile = new Tile((x),(y+1),Tex_attack);
	listaAttack.add(tTile);
	
}
private void Make_Mlist()
{
	
		Tile tTile;
		int x,y;
		x = champion.get().x;
		y = champion.get().y;
		//W=2
	    //S=1
		//A=3
		//D=0
		tTile = new Tile((x-1),(y),Tex_move);
		listaMove.add(tTile);
		tTile = new Tile((x),(y-1),Tex_move);
		listaMove.add(tTile);
		tTile = new Tile((x+1),(y),Tex_move);
		listaMove.add(tTile);
		tTile = new Tile((x),(y+1),Tex_move);
		listaMove.add(tTile);
		
	}
private void draw_Tile(SpriteBatch spriteBatch, int numer)
{
	switch (numer)
    {

	case 0:
		for(int i =0;i<listaAttack.size();i++)
		{
			listaAttack.get(i).draw(spriteBatch);
		}
		break;
	case 1:
		for(int i =0;i<listaMove.size();i++)
		{
			listaMove.get(i).draw(spriteBatch);
		}
		break;
	case 2:
	
		break;
	case 3:
	
		break;
	
    }   
 }
private void drawMove(SpriteBatch spriteBatch)
	{
	for(int i = 0 ;i<listaAttack.size();i++)
	{	
	listaMove.get(i).draw(spriteBatch);}
	}
}



