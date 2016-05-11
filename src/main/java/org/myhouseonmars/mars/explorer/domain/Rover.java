/**
 * 
 */
package org.myhouseonmars.mars.explorer.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Sonda veicular designada para a exploracao de um planalto ({@link Plateau}). A sonda
 * e capaz de guardar sua posicao corrente e se movimentar seguindo uma sequencia
 * de instrucoes do tipo {@link Instruction}. 
 * 
 * @author Marco Pessoa
 *
 */
public class Rover {

	private Position position;
	private Plateau plateau;
	private Direction direction;
	private List<Instruction> instructions;
	
	
	/**
	 * Movimenta a sonda de acordo com a lista de instrucoes fornecida. 
	 * 
	 */
	public void executeInstructions() {
		if (this.plateau == null || this.plateau.getWidth() < 1 || this.plateau.getLength() < 1)
			throw new UnsupportedOperationException("No Plateau or Plateau dimensions defined");
		
		if (this.instructions == null)
			throw new NullPointerException("Instructions list cannot be null");
		
		for (Instruction instruction : this.instructions) {
			if (instruction.equals(Instruction.M)) {
				moveForward();
			} else {
				turnTo(instruction);
			}
		}
	}
	
	private void moveForward() {
		Position nextStep;
		
		if (Direction.S.equals(this.direction)) {
			nextStep = new Position(this.position.getX(), this.getPosition().getY() - 1 );
		} else if (Direction.E.equals(this.direction)) {
			nextStep = new Position(this.position.getX() + 1, this.getPosition().getY());
		} else if (Direction.W.equals(this.direction)) {
			nextStep = new Position(this.position.getX() - 1, this.getPosition().getY());
		} else { // N
			nextStep = new Position(this.position.getX(), this.getPosition().getY() + 1 );
		}
		
		if (this.plateau.isWithinBoundaries(nextStep)) {
			this.position = nextStep;
		}
	}

	private void turnTo(Instruction command) {
		if (isTurnToL(command)) { //Gira a esquerda
			if (this.direction.equals(Direction.E)) {
				this.direction = Direction.N;
			} else if (this.direction.equals(Direction.W)) {
				this.direction = Direction.S;
			} else if (this.direction.equals(Direction.S)) {
				this.direction = Direction.E;
			} else {  //N
				this.direction = Direction.W;
			}
		} else if (isTurnToR(command)) { //Gira a direita
			if (this.direction.equals(Direction.E)) {
				this.direction = Direction.S;
			} else if (this.direction.equals(Direction.W)) {
				this.direction = Direction.N;
			} else if (this.direction.equals(Direction.S)) {
				this.direction = Direction.W;
			} else {  //N
				this.direction = Direction.E;
			}
		}
	}

	private boolean isTurnToR(Instruction command) {
		return Instruction.R.equals(command);
	}

	private boolean isTurnToL(Instruction command) {
		return Instruction.L.equals(command);
	}

	public Rover() {
		this.plateau = new Plateau(0,0);
		this.position = new Position(0,0);
		this.direction = Direction.N;
		this.instructions = new ArrayList<Instruction>();
	}
	
	public Rover(Plateau plateau, Position position, Direction direction, Instruction...instructions) {
		this.plateau = plateau;
		this.position = position;
		this.direction = direction;
		this.instructions = Arrays.asList(instructions);
	}
	
	/**
	 * Define a posicao da sonda no planalto
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * 
	 * @return Posicao da sonda no planalto
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Estabelece o Planalto a ser explorado por esta sonda.
	 * @param plateu
	 */
	public void setPlateau(Plateau plateu) {
		this.plateau = plateu;
	}
	
	/**
	 * 
	 * @return Planalto onde se encontra esta sonda.
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * Direcao para onde a sonda esta apontada.
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * 
	 * @return Direcao para onde a sonda esta apontada.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return Lista de insrucoes que a sonda deve executar.
	 */
	public List<Instruction> getInstructions() {
		return instructions;
	}

	/**
	 * @param Uma lista de instrucoes para a sonda executar.
	 */
	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	/**
	 * 
	 * @param Uma ou mais instrucoes para a sonda executar.
	 */
	public void setInstructions(Instruction... instructions) {
		this.instructions = Arrays.asList(instructions);
	}
	
	@Override
	public String toString() {
		return this.position.getX() + " " + this.position.getY() + " " + this.direction;
	}
	
}
