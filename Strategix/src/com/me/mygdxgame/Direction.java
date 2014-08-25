/**
 * 
 */
package com.me.mygdxgame;

/**
 * Simple enum that implements the Direction concept.
 * @author luk32
 */
public enum Direction { 
	RIGHT(2), DOWN(0), UP(3), LEFT(1);
	private int value;
	private Direction(int value) { this.value = value; }
	/** get the integer value of the direction **/
	public int toInt(){ return this.value; }
};
