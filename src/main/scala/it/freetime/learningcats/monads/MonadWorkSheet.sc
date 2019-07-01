import cats.{Eval, Monad}
import cats.instances.option._
import cats.instances.list._
import cats.instances.future._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import cats.data.{Reader, State, Writer}
import cats.instances.vector._

val opt1 = Monad[Option].pure(3)

val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))

val opt3 = Monad[Option].map(opt2)(a => 100 * a)



val list1 = Monad[List].pure(3)

val list2 = Monad[List].flatMap(List(1, 2, 3))(a => List(a, a*10))

val list3 = Monad[List].map(list2)(a => a +123)


//to define a Monad for Future, you need to define an implicit ExecutionContext
val fm = Monad[Future]

val future = fm.flatMap(fm.pure(1))(x => fm.pure(x+2))

Await.result(future, 1.second)

//Eval

val ans = for {
  a <- Eval.now{println("Calculating A"); 40}
  b <- Eval.always{println("Calculating B"); 2}
}yield{
  println("Adding A and B")
  a + b
}

ans.value

//You can memoize a chain of cumputations

val saying = Eval.always{println("Step 1 "); "The cat"}
  .map{str => println("Step 2"); s"$str sat on"}
.memoize.map{str => println("Steo 3"); s"$str the mat"}

saying.value


//Eval.defer (trampolined like map and flatMap). Do not consume stack frame

def factorial(n: BigInt) : Eval[BigInt] =
if(n==1) {
  Eval.now(n)
} else {
  Eval.defer(factorial(n-1).map(_ * n))
}

val f = factorial(500)

f.value


//Writer monad (to log along with a computation)

Writer(Vector("a", "b"), 1989)

//Reader monad (useful for dependency injection)


case class Cat(name: String, favoriteFood: String)

val catName: Reader[Cat, String] = Reader(cat => cat.name)

catName.run(Cat("Garfield", "Lasagne"))

//Reader composition
val greetKitty: Reader[Cat, String] = catName.map(name => s"Hello ${name}")

greetKitty.run(Cat("Silvestro", "Sausage"))


val feedKitty: Reader[Cat, String] = Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

val greetAndFeed: Reader[Cat, String] =
  for {
    greet <- greetKitty
    feed <- feedKitty
  } yield s"$greet. $feed"

greetAndFeed.run(Cat("Garfield", "Lasagne"))

// The State Monad
val a = State[Int, String] {
  state => (state, s"The state is $state")
}

//get the state and the result
val (state, result) = a.run(10).value

//get the state, ignore result
val state1 = a.runS(10).value

//get the result, ignore the state
val result1 = a.runA(10).value

