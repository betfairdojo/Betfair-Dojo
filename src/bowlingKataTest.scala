/**
 * 
 */

import org.scalatest.junit.JUnitSuite;
import org.junit.Test;
import org.junit.Assert._;

/**
 * @author GreenD
 *
 */
class bowlingKataTest extends JUnitSuite {

	@Test def testFoursFives() {
		var bowling = new BowlingUsingPatternMatching()
		assertEquals(90,bowling.score(List(4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5)))
	}
	
	@Test def testAllFives() {
		var bowling = new BowlingUsingPatternMatching()
		assertEquals(150,bowling.score(List(5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5)))
	}

	@Test def testAllStrikes() {
		var bowling = new BowlingUsingPatternMatching()
		assertEquals(300,bowling.score(List(10,10,10,10,10,10,10,10,10,10,10,10)))
	}

}