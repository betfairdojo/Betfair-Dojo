import org.scalatest.junit.JUnitSuite;
import org.junit.Test;
import org.junit.Assert._;

class GameTest extends JUnitSuite {

	// expecting total for this round to be 90
	val gameAllFoursAndFives = List(4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5,4,5) 
	val gameAllStrikes = List(10,10,10,10,10,10,10,10,10,10,10,10)
	val gameAllStrikesInvalid = List(10,10,10,10,10,10,10,10,10,10)
	val gameAllFives = List(5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5) 
	
	
	@Test def calculateFoursAndFives {		
		assertEquals(90, Game().calculateTotal(gameAllFoursAndFives))
	}
		
	@Test def calculateFives {
		assertEquals(150, Game().calculateTotal(gameAllFives))
	}

	@Test def calculateStrikes {
		assertEquals(300, Game().calculateTotal(gameAllStrikes))
	}
		
}