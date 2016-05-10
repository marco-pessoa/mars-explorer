/**
 * 
 */
package org.myhouseonmars.mars.explorer.domain;

import java.util.List;
import java.util.Map;

/**
 * Uma missao exploratoria de superficie composta por um planalto e um conjunto de sondas que recebem os comandos
 * que determinam sua movimentacao sobre o planalto. 
 * @author Marco Pessoa
 *
 */
public class Mission {
	
	private Plateau plateau;
	private List<Rover> rovers;
	private Map<Rover, List<Command>> mapRoverCommands;
	
	public Mission() {
	}
	
	public Mission(Plateau plateau, List<Rover> rovers) {
		this.plateau = plateau;
		this.rovers = rovers;
	}
	
	/**
	 * @return O planalto a ser explorado.
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * @param O planalto a ser explorado
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	/**
	 * @return Lista de sondas designadas para a missao.
	 */
	public List<Rover> getRovers() {
		return rovers;
	}
	
	/**
	 * @param Lista de sondas designadas para a missao.
	 */
	public void setRovers(List<Rover> rovers) {
		this.rovers = rovers;
	}

	public Map<Rover, List<Command>> getMapRoverCommands() {
		return mapRoverCommands;
	}

	public void setMapRoverCommands(Map<Rover, List<Command>> mapRoverCommands) {
		this.mapRoverCommands = mapRoverCommands;
	}
	
	

}
