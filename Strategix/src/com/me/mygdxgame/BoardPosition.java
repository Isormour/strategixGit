/**
 * 
 */
package com.me.mygdxgame;

import javax.swing.text.Position;

/**
 * Basic class implementing a position on board concept.
 * @author luk32
 * 
 */
public class BoardPosition {
private int x = 0;
private int y = 0;

public BoardPosition(int x, int y){
	this.x = x;
	this.y = y;
}

public int getX(){
	return x;
}

public int getY() {
	return y;
}

/**
 *  @return Value of x coordinate in isometric view. 
 */
public float getIsoX(){
	return x*32 - y*32;
}

/**
 *  @return Value of y coordinate in isometric view. 
 */
public float getIsoY(){
	return ((x*32 + y*32) / 2);
}

/**
 * Checks whether this and other positions point to the same field.
 * @param other Other position.
 * @return True if this and other point to the same position, false otherwise.
 */
public boolean sameAs(BoardPosition other) {
	return (x == other.getX() && y == other.getY());
}

@Override
	public String toString() {
		return Integer.toString(x) + ", " + Integer.toString(y);
	}

/**
 * Changes the position on board according to {@link Direction} (by one field).
 * @param dir {@link Direction} in which the position should be moved.
 */
public void move(Direction dir) {
	switch (dir)
	{
	case DOWN:
		y--;
		break;
	case LEFT:
		x--;
		break;
	case RIGHT:
		x++;
		break;
	case UP:
		y++;
		break;
	}	
}

}
