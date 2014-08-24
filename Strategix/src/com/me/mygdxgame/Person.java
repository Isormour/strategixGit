package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.xpath.internal.axes.WalkerFactory;


public class Person {
	int x,y;
	
	int kierunek =0;
    int frames = 0;
    int rows = 0;
    int animation =0;
   
    
 
    boolean selected = false;
    boolean busy= false;
    
    boolean attacked = false;
    boolean moved = false;
    
    int attack_corrector=0;
    int dodatek = 1;
    
    float timer=0;
    float delay=0.04f;
    float atackcorr = 1;
    float atack_dodatek;
  
    
	float isoX,isoY;
	Texture tex_greet;
	Texture tex_atack;
	Texture tex_death;
	Texture tex_walk;
	
	
    Person(int x,int y,Texture greet,Texture atack,Texture death,Texture walk)
    {
    	this.x=x;
    	this.y=y;
    	this.tex_greet = greet;
    	this.tex_atack = atack;
    	this.tex_death = death;
    	this.tex_walk = walk;
    	
    	
    	ToIso();
    	
    	
    	
    }
    private void ToIso()
    {
        this.isoX = x*32 - y*32;
        this.isoY = ((x*32 + y*32) / 2);
    }
    private void draw_death(SpriteBatch spriteBatch,float time,Texture death)
{
	timer +=time;
	busy = true;
	if(timer >delay)
	{
		frames++;
		timer = 0;
	}
	
	spriteBatch.draw(death, isoX, isoY,64*frames,64*kierunek, 64, 64);
	if(frames==9)
	{
		busy = false;
		timer = 0;
		frames= 0;
		animation =5;
		
	}
	
}
    private void draw_standing_Animation(SpriteBatch spriteBatch,float time ,Texture tex2)
    { 	
    	timer +=time;
    	busy = true;
    	if(timer >delay)
    	{
    		frames++;
    		timer = 0;
    	}
    	
    	spriteBatch.draw(tex2, isoX, isoY,64*frames,64*kierunek, 64, 64);
    	if(frames==9)
    	{
    		busy = false;
    		timer = 0;
    		frames= 0;
    		animation =0;
    		
    	}
    }
    private void draw_walk(SpriteBatch spriteBatch,float time,Texture tex2)
    {
    	timer +=time;
    	busy = true;	
    	 switch (kierunek)
         {
    	 //W=2
         //S=1
    	 //A=3
    	 //D=0
    	 case 0:
    		
    		 spriteBatch.draw(tex2, isoX + frames * 1.6f +  16*(rows),  isoY - frames * 0.8f - 8*(rows), 64*frames, 64 * (kierunek * 2)+64*rows, 64, 64);
    		 break;
    	 case 1:
    		
    		 spriteBatch.draw(tex2, isoX - frames * 1.6f -  16*(rows),  isoY - frames * 0.8f - 8*(rows), 64*frames, 64 * (kierunek * 2)+64*rows, 64, 64);
    		 break;
    	 case 2:
    		 spriteBatch.draw(tex2, isoX + frames * 1.6f +  16*(rows),  isoY +frames * 0.8f + 8*(rows), 64*frames, 64 * (kierunek * 2)+64*rows, 64, 64);
    		 break;
    	 case 3:
    		 spriteBatch.draw(tex2, isoX - frames * 1.6f -  16*(rows),  isoY + frames * 0.8f + 8*(rows), 64*frames, 64 * (kierunek * 2)+64*rows, 64, 64);
    		 break;
    		 
         }
    		if(timer >delay)
        	{
        		frames++;
        		timer = 0;
        	}
        	if(rows==1&&frames==9)
    		{
    			
        		timer = 0;
        		frames= 0;
        		animation =0;
        		rows --;
        		busy = false;
        		moved = true;
        		switch (kierunek)
                {
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
        	if(frames==9)
        	{
        		frames=0;
        		rows ++;
        	}
    }
    private void draw_atack(SpriteBatch spriteBatch,float time,Texture tex2)
    {
    	timer +=time;
    	busy = true;
    	if(timer >delay)
    	{
    		frames++;
    		timer = 0;
    		attack_corrector = attack_corrector + dodatek;
    	}
    	if(frames==5)
    	{
    		dodatek = dodatek * (-1);
    	}
    	switch(kierunek){
      case 0:
		 spriteBatch.draw(tex2, isoX + 1.6f * attack_corrector,  isoY - 1.6f * attack_corrector, 64*frames, 64 * kierunek , 64, 64);
		 break;
	  case 1:
		 spriteBatch.draw(tex2, isoX - 1.6f * attack_corrector,  isoY - 1.6f * attack_corrector, 64*frames, 64 * kierunek , 64, 64);
		 break;
	  case 2:
		 spriteBatch.draw(tex2, isoX + 1.6f * attack_corrector,  isoY +1.6f * attack_corrector, 64*frames, 64 * kierunek, 64, 64);
		 break;
	  case 3:
		 spriteBatch.draw(tex2, isoX - 1.6f * attack_corrector,  isoY + 1.6f * attack_corrector, 64*frames, 64 * kierunek, 64, 64);
		 break;
		 }
    	if(frames==9)
    	{
    		busy = false;
    		timer = 0;
    		frames= 0;
    		animation =0;
    		
    		attack_corrector=0;
    		dodatek = 1;
    		attacked = true;
    	}
    }
    // PUBLIC FUNCTIONS 
    public void endTurn()
    {
    	moved = false;
    	attacked = false;
    }
    public void set_kierunek(int kierunek)
    {
    	if(!busy)
    	{
    		this.kierunek = kierunek;
    		
    	}
    }
    public void set_animation(int animation_number)
    {
    	if(!busy)
    	{
    		animation = animation_number;
    		
    	}
    }
    public void draw(SpriteBatch spriteBatch,float time)
    {
    	
    	if (animation == 0)
        {
    		spriteBatch.draw(tex_greet, isoX, isoY,64*frames,64*kierunek, 64, 64);
        }
        else if (animation == 1)
        {
        	draw_standing_Animation(spriteBatch, time,tex_greet);
        }
        else if(animation == 2)
        {
        	draw_atack(spriteBatch, time,tex_atack);
        }
        else if(animation == 3)
        {
        	draw_death(spriteBatch, time, tex_death);
        }
        else if(animation == 4)
        {
        	draw_walk(spriteBatch, time,tex_walk);
        }
        else if(animation == 5)
        {
        	spriteBatch.draw(tex_death, isoX, isoY,64*9,64*kierunek, 64, 64);
        }
    }
    public void dispose()
    {
    	tex_atack.dispose();
    	tex_death.dispose();
    	tex_greet.dispose();
    	tex_walk.dispose();
    }
    
    
}
    	


