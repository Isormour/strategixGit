package com.me.mygdxgame.GameInputManager;

public class KeyboardManager {
	private static boolean []key;
	private static boolean []pkey;
	private static final int keys_number = 6;
	
	
	public static final int W = 0;
	public static final int S = 1;
	public static final int A = 2;
	public static final int D = 3;
	public static final int U = 4;
	public static final int I = 5;
	
	static {
		key = new boolean [keys_number];
		pkey = new boolean [keys_number];
	}
	public static void update()
	{
		for(int i=0;i<keys_number;i++)
		{
			pkey[i]=key[i];
		}
	}
	public static void set_key(int k,boolean b)
	{
		key[k] = b;
	}
	
	public static boolean is_Down (int k)
	{
		return  key[k];
	}
	public static boolean is_Pressed (int k)
	{
		return  key[k] && !pkey[k];
	}
	

}
