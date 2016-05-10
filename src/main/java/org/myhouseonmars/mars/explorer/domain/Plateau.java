/**
 * 
 */
package org.myhouseonmars.mars.explorer.domain;

/**
 * Um planalto a ser explorado, com dimensoes bem definidas.
 * 
 * @author Marco Pessoa
 *
 */
public class Plateau {

	private int length;
	private int width;

	public Plateau(int width, int length) {
		this.width = width;
		this.length = length;
	}
	
	public Plateau() {
	}

	/**
	 * 
	 * @return Comprimento do planalto
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Define o comprimento do Planalto.
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * 
	 * @return Largura do Planalto
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Define a largura do Planalto.
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Verifica se uma determinada posicao esta dentro dos limites do Planalto.
	 * @param position
	 * @return
	 */
	public boolean isWithinBoundaries(Position position) {
		return position.getX() >= 0
				&& position.getX() <= this.width
				&& position.getY() >= 0
				&& position.getY() <= this.length;
			
	}
	
	
}
