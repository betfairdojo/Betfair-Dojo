/**
 * 
 */
/**
 * @author GreenD
 *
 */
class Bowling {

	def score( scores:List[Int] ) : Int = {
		scoreFrames(scores,0)
	}
	
	private def scoreFrames( scores:List[Int], numFrames:Int ) : Int =
		// Only count 10 frames, anything else is a bonus throw
		if( numFrames >= 10 ) 0 else scores match {
			// Empty list case
			case Nil => 0
			
			// We have a strike
			case 10::(rest @ b::c::_) => 10+b+c+scoreFrames(rest,numFrames+1)
			
			// We have a spare
			case a::b::(rest @ c::_) if a+b==10 => 10+c+scoreFrames(rest,numFrames+1)
			
			// Anything else
			case a::b::rest => a+b+scoreFrames(rest,numFrames+1)
		}
}