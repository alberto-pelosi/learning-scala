package it.freetime.learningscala.typeclasses

trait Show[A] {

  def show(a: A): String

}



// companion object that holds an implementation of Show trait for Int
object Show {

  val intCanShow: Show[Int] = new Show[Int] {
    def show(int: Int): String = s"int $int"
  }




}
