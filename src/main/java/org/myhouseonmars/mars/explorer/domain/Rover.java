/**
 * 
 */
package org.myhouseonmars.mars.explorer.domain;

/**
 * Sonda veicular designada para a exploracao de um planalto ({@link Plateau}). A sonda
 * e capaz de guardar sua posicao corrente e se movimentar seguindo uma sequencia
 * de instrucoes do tipo {@link Command}. 
 * 
 * @author Marco Pessoa
 *
 */
public class Rover {

	private Position position;
	private Plateau plateau;
	private Direction direction;
	
	
	/**
	 * Movimenta a sonda de acordo com um ou mais comandos fornecidos. 
	 * 
	 * @param commands Um ou mais comandos para a movimentacao da sonda
	 */
	public void move(Command... commands) {
		if (this.plateau == null || this.plateau.getWidth() < 1 || this.plateau.getLength() < 1)
			throw new UnsupportedOperationException("No Plateau or Plateau dimensions defined");
		
		for (Command command : commands) {
			if (command.equals(Command.M)) {
				moveForward();
			} else {
				turnTo(command);
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

	private void turnTo(Command command) {
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

	private boolean isTurnToR(Command command) {
		return Command.R.equals(command);
	}

	private boolean isTurnToL(Command command) {
		return Command.L.equals(command);
	}

	public Rover() {
		this.plateau = new Plateau(0,0);
		this.position = new Position(0,0);
		this.direction = Direction.N;
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
	public void setPlateu(Plateau plateu) {
		this.plateau = plateu;
	}
	
	/**
	 * 
	 * @return Planalto onde se encontra esta sonda.
	 */
	public Plateau getPlateu() {
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
	
}
