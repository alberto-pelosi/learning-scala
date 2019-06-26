import cats.Monoid
import cats.Semigroup
import cats.instances.string._
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._
import cats.instances.map._
import cats.instances.tuple._




Monoid[String].combine("Hi ", "Monoid")

//equivalet to

Monoid.apply[String].combine("Hi ", "Monoid with apply")

Semigroup[String].combine("Hi ", "Semigroup")

Monoid[Int].combine(12, 30)

// |+| alternative syntax for combine
val intResult = 1 |+| 2 |+| Monoid[Int].empty

val optionResult = Option(1) |+| Option(3)

val anotherOptionResult = Option(1) |+| None

val map1 = Map("a" ->1, "b" -> 2)
val map2 = Map("b"->3, "d"->4)

map1 |+| map2

val tuple1 = ("hello", 123)
val tuple2 = ("world", 321)

tuple1 |+| tuple2

val tuple3 = ("hello", 123)

tuple1 |+| tuple3

def addAll[A](values: List[A])(implicit monoid: Monoid[A]): A = values.foldRight(monoid.empty)(_ |+| _)

val l = List(1,2,3)

val l1 = List("a", "b", "c")

addAll(l)

addAll(l1)




