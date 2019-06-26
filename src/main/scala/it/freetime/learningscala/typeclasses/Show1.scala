package it.freetime.learningscala.typeclasses

trait Show1[A] {

  def show(a: A): String

}

//A first example of TypeClass
object Show1 {

  implicit val intCanShow: Show1[Int] = new Show1[Int] {
    def show(int: Int): String = s"int $int"
  }


  //Interface Object
  def show[A](a: A)(implicit sh: Show1[A]) = sh.show(a)

}
