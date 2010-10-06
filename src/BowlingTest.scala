import org.scalatest.junit.JUnitSuite
import org.junit.Test
import org.junit.Assert._

class BowlingTest {

	@Test def testFoursAndFives {
		var bowling = new Bowling()
		assertEquals(90,bowling.score(List(4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5)))
	}
	
	@Test def testAllStrikes {
		var bowling = new Bowling()
		assertEquals(300,bowling.score(List(10,10,10,10,10,10,10,10,10,10,10,10)))
	}

	@Test def testAllFives {
		var bowling = new Bowling()
		assertEquals(150,bowling.score(List(5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5)))
	}
	
	@Test def testEmpty {
		var bowling = new Bowling()
		assertEquals(0,bowling.score(List()))
	}

}