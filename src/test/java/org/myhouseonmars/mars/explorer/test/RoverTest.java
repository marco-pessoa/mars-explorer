/**
 * 
 */
package org.myhouseonmars.mars.explorer.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.myhouseonmars.mars.explorer.domain.Instruction;
import org.myhouseonmars.mars.explorer.domain.Direction;
import org.myhouseonmars.mars.explorer.domain.Plateau;
import org.myhouseonmars.mars.explorer.domain.Position;
import org.myhouseonmars.mars.explorer.domain.Rover;

/**
 * @author Marco Pessoa
 *
 */
public class RoverTest {

	/**
	 * Test method for {@link org.myhouseonmars.mars.explorer.domain.Rover#move(org.myhouseonmars.mars.explorer.domain.Instruction[])}.
	 */
	@Test
	public void testMove() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(5, 5));
		rover.setPosition(new Position(1, 2));
		rover.setDirection(Direction.N);
		
		rover.setInstructions(Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.M);
		rover.executeInstructions();
		
		System.out.println(rover.getPosition() + " " + rover.getDirection());
		
		assertEquals(new Position(1,3), rover.getPosition());
		assertEquals(Direction.N, rover.getDirection());
	}

}
