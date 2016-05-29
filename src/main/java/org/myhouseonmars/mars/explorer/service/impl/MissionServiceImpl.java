/**
 * 
 */
package org.myhouseonmars.mars.explorer.service.impl;

import java.lang.reflect.MalformedParametersException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.myhouseonmars.mars.explorer.domain.Direction;
import org.myhouseonmars.mars.explorer.domain.Instruction;
import org.myhouseonmars.mars.explorer.domain.Mission;
import org.myhouseonmars.mars.explorer.domain.Plateau;
import org.myhouseonmars.mars.explorer.domain.Position;
import org.myhouseonmars.mars.explorer.domain.Rover;
import org.myhouseonmars.mars.explorer.service.MissionService;
import org.springframework.stereotype.Service;

/**
 * @author Marco Pessoa
 *
 */
@Service
public class MissionServiceImpl implements MissionService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.myhouseonmars.mars.explorer.service.MissionService#executeMission(org
	 * .myhouseonmars.mars.explorer.domain.Mission)
	 */
	public List<String> executeMission(Mission mission) {
		List<String> finalPositions = new ArrayList<String>();

		Plateau plateau = mission.getPlateau();
		List<Rover> rovers = mission.getRovers();

		// Validacoes
		if (plateau == null)
			throw new InvalidParameterException("Mission must have a Plateau to get started");

		if (rovers == null || rovers.size() == 0)
			throw new InvalidParameterException("Mission must have one ore more rovers to get started");

		/**
		 * Cada sonda sera controlada sequencialmente, i.e., a segunda sonda
		 * inicia a execucao de seus comandos somente apos a primeira terminar
		 * de executar todas as suas instrucoes e assim por diante.
		 */
		for (Rover rover : rovers) {
			rover.executeInstructions();
			finalPositions.add(rover.toString());
		}

		return finalPositions;
	}

	@Override
	public Mission createMission(List<String> strInstructions) {
		if (strInstructions.size() < 3 || isEven(strInstructions.size()))
			throw new InvalidParameterException("There is not enough info to setup a mission");
		
		Plateau plateau = createPlateau(strInstructions.get(0));
		
		ArrayList<Rover> rovers = new ArrayList<>();
		for (int i = 1; i < strInstructions.size(); i++) {
			if (isEven(i)) {
				//Infere as instrucoes e as adiciona a ultima sonda da lista
				rovers.get(rovers.size()-1).setInstructions(createInstructionsList(strInstructions.get(i)));
			} else {
				rovers.add(createRover(strInstructions.get(i), plateau));
			}
		}
		
		Mission mission = new Mission(plateau, rovers);
		
		return mission;
	}

	private Rover createRover(String string, Plateau plateau) {
		String[] arrString = string.trim().split(" ");
		if (arrString.length != 3)
			throw new MalformedParametersException("Invalid Rover creation String: " + string);
			
		int x = getValidCoordinate(arrString[0]);
		int y = getValidCoordinate(arrString[1]);
		Position position = new Position(x, y);
		Direction direction = createDirection(arrString[2]);

		return new Rover(plateau, position, direction);
	}

	private int getValidCoordinate(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Coordinate must be a number");
		}
	}

	private Direction createDirection(String string) {
		if (string.length() != 1)
			throw new InvalidParameterException("Invalid cardinal point: " + string);
		
		char cardinal = string.charAt(0);

		Direction direction;
		switch (cardinal) {
		case 'N':
			direction = Direction.N;
			break;
		case 'S':
			direction = Direction.S;
			break;
		case 'E':
			direction = Direction.E;
			break;
		case 'W':
			direction = Direction.W;
			break;
		default:
			throw new InvalidParameterException("Invalid cardinal point: " + cardinal);
		}
		return direction;
	}

	private List<Instruction> createInstructionsList(String string) {
		char[] charArray = string.toCharArray();
		ArrayList<Instruction> instructions = new ArrayList<>();
		
		for (char c : charArray) {
			switch (c) {
			case 'R':
				instructions.add(Instruction.R);
				break;
			case 'L':
				instructions.add(Instruction.L);
				break;
			case 'M':
				instructions.add(Instruction.M);
				break;
			default:
				throw new InvalidParameterException("Invalid rover instruction: " + c);
			}
		}
		return instructions;
	}

	private Plateau createPlateau(String string) {
		String[] arrString = string.trim().split(" ");
		if (arrString.length != 2)
			throw new MalformedParametersException("Invalid Plateau creation String: " + string);
			
		int x = getValidCoordinate(arrString[0]);
		int y = getValidCoordinate(arrString[1]);
		
		return new Plateau(x, y);
	}

	private boolean isEven(int number) {
		return (number % 2 == 0);
	}

}
