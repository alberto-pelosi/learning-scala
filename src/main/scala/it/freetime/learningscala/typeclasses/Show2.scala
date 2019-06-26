package it.freetime.learningscala.typeclasses

trait Show2[A] {

  def show(a: A) : String

}

//TypeClass using implicity keyword
object Show2 {

  implicit val intCanShow: Show2[Int] = new Show2[Int] {
    def show(int: Int): String = s"int $int"
  }


  def show[A: Show2](a: A)= implicitly[Show2[A]].show(a)

}
