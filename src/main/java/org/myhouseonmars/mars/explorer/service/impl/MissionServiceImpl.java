/**
 * 
 */
package org.myhouseonmars.mars.explorer.service.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.myhouseonmars.mars.explorer.domain.Instruction;
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
	public List<String> executeMission(Mission mission) {
		List<String> finalPositions = new ArrayList<String>();
		
		Plateau plateau = mission.getPlateau();
		List<Rover> rovers = mission.getRovers();
		
		//Validacoes
		if (plateau == null)
			throw new InvalidParameterException("Mission must have a Plateau to get started");
		
		if (rovers == null || rovers.size() == 0)
			throw new InvalidParameterException("Mission must have one ore more rovers to get started");
		
		
		/**
		 * Cada sonda sera controlada sequencialmente, i.e., a segunda
		 * sonda inicia a execucao de seus comandos somente apos a primeira terminar de executar todas as suas instrucoes 
		 * e assim por diante.
		 */
		for (Rover rover : rovers) {
			rover.executeInstructions();
			finalPositions.add(rover.toString());
		}
		
		return finalPositions;
	}

}
