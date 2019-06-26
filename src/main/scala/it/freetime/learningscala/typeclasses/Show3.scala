package it.freetime.learningscala.typeclasses


trait Show3[A] {

  def show(a: A): String

}

//TypeClass defining an apply method
object Show3 {

  def apply[A](implicit sh: Show3[A]): Show3[A] = sh

  // def show[A: Show3](a: A) = Show3[A].show(a)

  def show[A: Show3](a: A) = Show3.apply[A].show(a)

  implicit val intCanShow: Show3[Int] =
    new Show3[Int] {
      def show(int: Int): String = s"int $int"
    }


}
