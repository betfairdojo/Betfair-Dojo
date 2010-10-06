import scala.collection.immutable.List

case class Game{

  def calculateTotal(currentScores: List[Int]): Int = {
    recurseIt(currentScores, 0, 0)
  }

  private def recurseIt(currentScores: List[Int], runningTotal: Int, numFramesSoFar: Int): Int = {
    if (numFramesSoFar >= 10) {
      return runningTotal
    }
    val currentVal: Int = currentScores(0)
    val nextVal: Int = currentScores(1)
    if (currentVal == 10) {
      recurseIt(currentScores.tail, (runningTotal + currentVal + nextVal + currentScores(2)),numFramesSoFar+1)
    } 
    else if((currentVal + nextVal) == 10) {
    	recurseIt(currentScores.tail.tail, (runningTotal + currentVal + nextVal + currentScores(2)),numFramesSoFar+1)
    }
    else {
      recurseIt(currentScores.tail.tail, runningTotal + currentVal + nextVal, numFramesSoFar+1)
    }
  }

}