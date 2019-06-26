package it.freetime.learningscala.typeclasses

trait Show4UsingOps[A] {

  def show(a: A): String

}

object Show4UsingOps{

  def apply[A](implicit sh: Show4UsingOps[A]): Show4UsingOps[A] = sh


  def show[A: Show4UsingOps](a: A) = Show4UsingOps[A].show(a)


  //tell compiler to convert class A, having instance of type class classShow4UsingOps, to class Show4Ops
  //which have one function show
  implicit class Show4Ops[A: Show4UsingOps](a: A) {
    def show = Show4UsingOps[A].show(a)
  }

  implicit val intCanShow: Show4UsingOps[Int] = new Show4UsingOps[Int] {
    def show(int: Int): String = s"int $int"
  }


}
