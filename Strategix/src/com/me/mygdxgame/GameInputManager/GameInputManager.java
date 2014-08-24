
package com.me.mygdxgame.GameInputManager;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;


public class GameInputManager extends InputAdapter {
	public boolean keyDown (int k)
	{   if(k == Keys.W)
		{
		KeyboardManager.set_key(KeyboardManager.W, true);
		}
	 if(k == Keys.S)
		{
			KeyboardManager.set_key(KeyboardManager.S, true);
		}
	 if(k == Keys.A)
		{
			KeyboardManager.set_key(KeyboardManager.A, true);
		}
	 if(k == Keys.D)
		{
			KeyboardManager.set_key(KeyboardManager.D, true);
		}
	 if(k == Keys.U)
		{
			KeyboardManager.set_key(KeyboardManager.U, true);
		}
	 if(k == Keys.I)
		{
			KeyboardManager.set_key(KeyboardManager.I, true);
		}
	return true;
	}
	public boolean keyUp(int k)
		{	 if(k == Keys.W)
				{
				KeyboardManager.set_key(KeyboardManager.W, false);
				}
			if(k == Keys.S)
				{
				KeyboardManager.set_key(KeyboardManager.S, false);
				}
			if(k == Keys.A)
			{
				KeyboardManager.set_key(KeyboardManager.A, false);
			}
			if(k == Keys.D)
			{
				KeyboardManager.set_key(KeyboardManager.D, false);
			}
			if(k == Keys.U)
			{
				KeyboardManager.set_key(KeyboardManager.U, false);
			}
			if(k == Keys.I)
			{
				KeyboardManager.set_key(KeyboardManager.I, false);
			}
		
			return true;
	}

}
