import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.higherKinds
import cats.Functor
import cats.instances.list._
import cats.instances.option._
import cats.instances.function._
import cats.syntax.functor._


//future composition (futures are not referential transparent!)
val future: Future[String] = Future(123).map(n => n+1).map(n => n*2).map(n => n + "!")

Await.result(future, 1.second)

//function composition

val func1: Int => Double = (x: Int) => x.toDouble

val func2: Double => Double = (y: Double) => y * 2


func1.andThen(func2)(1)
func2(func1(1))

// function Type Class

val list1 = List(1,2,3)

val list2 = Functor[List].map(list1)(_ * 2)

val option1 = Option(123)

val option2 = Functor[Option].map(option1)(_.toString())

//lift method
//convert a funtion of type A => B to one that operates over a functor
//and has type F[A] => F[B]

val func = (x: Int) => x+1
val liftedFunc = Functor[Option].lift(func)
liftedFunc(Option(1))

//another example

val funcs = (x: String) => x + x
val liftedFuncs = Functor[List].lift(funcs)
val list = List("lol")
liftedFuncs(list)

//functor syntax
def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] = start.map(n => n+1)


doMath(Option(20))




