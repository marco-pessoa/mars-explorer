/**
 * 
 */
package org.myhouseonmars.mars.explorer.domain;

/**
 * Representa uma posicao em um plano cartesiano.
 * 
 * @author Marco Pessoa
 *
 */
public class Position {

	private final int x;
	private final int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Position))
			return false;
		
		Position position = (Position) obj;
		return position.getX() == this.x && position.getY() == this.y;	
	}
	
	@Override
	public String toString() {
		return this.x + " " + this.y;
	}
	
}
