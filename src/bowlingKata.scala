// As some background to those new to Scala, typically...
// - def in Scala is equivalent to a method call in Java
// - Scala is a statically typed JVM lang (as enforced by the compiler), but relies heavily on type inference. As such, the type of a variable
//   or return type for a method is often dropped totally, or (at least) placed after the 'variable' name/label
// - Scala rarely needs semi colon's to delimit an end of line, though they are sometimes used to seperate multiple statements on the same line
// - following the functional paradigm, Scala has lots of List based operations and eschews pattern matching and recursion in place of 'looping'
// - note the compiler only optimises 'tail recursive' calls (by compiling and exploding this into for() loops, and hence reusing the same stack frame)
// - pattern matching is one of the most powerful features of Scala (if not *the* most powerful feature), and refactoring into a pattern match should 
//   generally be sought (along with trying to remove imperative idioms; maintaining state and using mutable data structures)
// - final takeaway, (and one of my preferred saying about functional programming), the functional paradigm moves us from coding in time to coding in space ;-) (i.e. we can build applications from basic, atomic, stateless blocks of functionality as opposed to seeing problems as time based flows of execution)

// Idiomatic solution A - bowling counter using a pattern match and binding
// Would be nice to show a closure in use with these solutions, but the recursive nature of the function call requires the List to be passed in explicitly :-(
class BowlingUsingPatternMatching {

    def score(bowls:List[Int]) = { // Infer the return type based on the last 'value' returned in this method
	// Debugging... side effect out a println to display what is going on to the console..
	var frameScores = scoreFrames(bowls); println("DEBUG: Frames scores: " + frameScores); println("slice: " + frameScores.slice(0,10))
	// End fo debug

	scoreFrames(bowls).slice(0,10).sum // take the first 10 elements from the List and sum them..any additional elements are the result of additional strikes
    }

    // scoreFrames tries to do a pattern match on the list elements, and returns a sum of the score of each frame as a List of Ints
    // Note additional 'spare' values for an all strike game are removed by the slice call in the 'caller'..
    def scoreFrames(bowls:List[Int]) : List[Int] = bowls match {
        case Nil => List(0)
        case 10::(rest @ a::b::_) =>               // if the first element == 10, bind the names a and b to the next 2 elements, and rest to the rest..
            10+a+b :: scoreFrames(rest)            // add 10 to it's 2 subsequent values, drop the first element and recurse over the rest of the List
        case a::b::(rest @ c::_) if a+b == 10 =>   // bind first 2 elements in list to labels a and b, and see if they == 10
            10+c :: scoreFrames(rest)              // add 10 to the subsequent value to the spare, and recurse over the remainder of the List
        case a::b::rest =>                         // otherwise, add the first 2 elements of the List, and recurse over the rest of the List
            a+b :: scoreFrames(rest)
    }
}

// Idiomatic solution B - bowling counter using extractors
// extractors allow us to bind an parameters to an object and are usable in pattern matches to see if a pattern can apply to params submitted to a 
// constructor. Note this is simialr to case classes, but works against object (which are like 'static' classes in Java), and is slower than 
// using case classes
class BowlingUsingExtractors() {

    def score(bowls:List[Int]) = scoreFrames(bowls).slice(0,10).sum    // take the first 10 elements from the List and sum them..any additional elements are the result of additional strikes

    def scoreFrames(bowls:List[Int]) : List[Int] = bowls match {
        case Nil => List(0)
        case isStrike() =>
            bowls.slice(0,3).sum :: scoreFrames(bowls drop 1) // :: is the cons operator and is right associative. Basically it prepends
        case isSpare() =>
            bowls.slice(0,3).sum :: scoreFrames(bowls drop 2) // elements to the left to the start of a List. the drop 'infix' operator 
        case _ =>
            bowls.slice(0,2).sum :: scoreFrames(bowls drop 2) // removes the named number of elements from the start of the List
    }

    object isStrike {  // typical extractors would implement an apply method for creating an object instance based on params passed in..
        def unapply(bowls:List[Int]) = bowls match {
            case 10::_ => true
            case _ => false
        }
    }

    object isSpare {
        def unapply(bowls:List[Int]) = bowls match {
            case a::b::_ if a+b == 10 => true
            case _ => false
        }
    }
}

// Other links referenced/mentioned in the dojo, plus yet more that are useful:
// - Scala lang: http://www.scala-lang.org/
// - Scala @ skillsmatter - http://skillsmatter.com/go/scala
// - Scala meet the maestros (7/10/10 registration reqd) - http://skillsmatter.com/event/scala/qa-with-martin-odersky-jonas-boner-and-david-pollak
// - Specs: http://code.google.com/p/specs/
// - EasyB: http://www.easyb.org/
// - SBT (another one from Tony Morris !): http://code.google.com/p/simple-build-tool/
// - Akka: http://akkasource.org/
// - ScalaIDE:http://www.scala-ide.org/
// - IntelliJ plugin (w/ Java -> Scala convereter - untested): http://confluence.jetbrains.net/display/SCA/Scala+Plugin+for+IntelliJ+IDEA 
// - Scalify (now dead project I think): http://video.google.com/videoplay?docid=-3493190786110154189#
// - Lift: http://liftweb.net/
//
// Shameless plugs
// - LSUG: http://liftweb.net/
// - SW Craftsmen London UG: http://skillsmatter.com/user-group/java-jee/lscc
// - scalabound (my blog !): http://scalabound.org

