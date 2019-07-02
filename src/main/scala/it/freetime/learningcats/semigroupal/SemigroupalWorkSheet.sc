import cats.Semigroupal
import cats.instances.option._

Semigroupal[Option].product(Some(123), Some("abc"))


Semigroupal[Option].product(None, Some("abc"))


Semigroupal.map3(Option(1), Option(2), Option(3))(_ + _ + _)