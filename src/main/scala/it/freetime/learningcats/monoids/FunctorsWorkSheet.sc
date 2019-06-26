import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


//future composition (futures are not referential transparent!)
val future: Future[String] = Future(123).map(n => n+1).map(n => n*2).map(n => n + "!")

Await.result(future, 1.second)

//function composition

val func1: Int => Double = (x: Int) => x.toDouble

val func2: Double => Double = (y: Double) => y * 2


func1.andThen(func2)(1)
func2(func1(1))

