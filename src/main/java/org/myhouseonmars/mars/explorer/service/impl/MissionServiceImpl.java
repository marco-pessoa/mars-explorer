/**
 * 
 */
package org.myhouseonmars.mars.explorer.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.myhouseonmars.mars.explorer.domain.Command;
import org.myhouseonmars.mars.explorer.domain.Mission;
import org.myhouseonmars.mars.explorer.domain.Plateau;
import org.myhouseonmars.mars.explorer.domain.Position;
import org.myhouseonmars.mars.explorer.domain.Rover;
import org.myhouseonmars.mars.explorer.service.MissionService;

/**
 * @author Marco Pessoa
 *
 */
public class MissionServiceImpl implements MissionService {

	/* (non-Javadoc)
	 * @see org.myhouseonmars.mars.explorer.service.MissionService#executeMission(org.myhouseonmars.mars.explorer.domain.Mission)
	 */
	public List<Position> executeMission(Mission mission) {
		List<Position> finalPositions = new ArrayList<Position>();
		
		Plateau plateau = mission.getPlateau();
		List<Rover> rovers = mission.getRovers();
		Map<Rover, List<Command>> mapRoverCommands = mission.getMapRoverCommands();
		
		//Validacoes
		if (plateau == null)
			throw new InvalidParameterException("Mission must have a Plateau to get started");
		
		if (rovers == null || rovers.size() == 0)
			throw new InvalidParameterException("Mission must have one ore more rovers to get started");
		
		if (mapRoverCommands == null || mapRoverCommands.isEmpty())
			throw new InvalidParameterException("Mission must define at least one command to be accomplished");
		
		/**
		 * Cada sonda sera controlada sequencialmente, i.e., a segunda
		 * sonda inicia a execucao de seus comandos somente apos a primeira terminar de executar todas as suas instrucoes 
		 * e assim por diante.
		 */
		for (Rover rover : rovers) {
			List<Command> commands = mapRoverCommands.get(rover);
			rover.move(commands.toArray(new Command[commands.size()]));
			finalPositions.add(rover.getPosition());
		}
		
		return finalPositions;
	}

}
