/**
 * 
 */
package org.myhouseonmars.mars.explorer.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.myhouseonmars.mars.explorer.domain.Direction;
import org.myhouseonmars.mars.explorer.domain.Instruction;
import org.myhouseonmars.mars.explorer.domain.Mission;
import org.myhouseonmars.mars.explorer.domain.Plateau;
import org.myhouseonmars.mars.explorer.domain.Position;
import org.myhouseonmars.mars.explorer.domain.Rover;
import org.myhouseonmars.mars.explorer.service.impl.MissionServiceImpl;

/**
 * @author Marco Pessoa
 *
 */
public class MissionServiceImplTest {

	public static MissionServiceImpl service;
	
	@BeforeClass
	public static void setup() {
		service = new MissionServiceImpl();
	}
	
	/**
	 * Test method for {@link org.myhouseonmars.mars.explorer.service.impl.MissionServiceImpl#executeMission(org.myhouseonmars.mars.explorer.domain.Mission)}.
	 */
	@Test
	public void testExecuteMission() {
		
		Plateau plateau = new Plateau(5, 5);
		
		List<Rover> rovers = new ArrayList<Rover>();
		
		Rover rover1 = new Rover(plateau, new Position(1,2), Direction.N, 
						Instruction.L,
						Instruction.M, 
						Instruction.L, 
						Instruction.M, 
						Instruction.L, 
						Instruction.M, 
						Instruction.L, 
						Instruction.M, 
						Instruction.M);
		rovers.add(rover1);
		
		Rover rover2 = new Rover(plateau, new Position(3,3), Direction.E,
						Instruction.M,
						Instruction.M,
						Instruction.R,
						Instruction.M,
						Instruction.M,
						Instruction.R,
						Instruction.M,
						Instruction.R,
						Instruction.R,
						Instruction.M);
		rovers.add(rover2);
		
		Mission mission = new Mission(plateau, rovers);
		List<String> lstOutput = service.executeMission(mission );
		
		List<String> lstMission = Arrays.asList("1 3 N", "5 1 E");
		
		assertArrayEquals(lstMission.toArray(), lstOutput.toArray());
	}

}
