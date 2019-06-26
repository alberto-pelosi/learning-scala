package it.freetime.learningscala.typeclasses

trait MaddenRefactored[A] {

  def madden(a: A) : A

}

object MaddenRefactored{

  def apply[A](implicit mad:MaddenRefactored[A]) : MaddenRefactored[A] = mad

  def madden[A: MaddenRefactored](a: A) = MaddenRefactored[A].madden(a)

  //Interface Syntax
  implicit class MaddenOps[A: MaddenRefactored](a: A) {
    def madden = MaddenRefactored[A].madden(a)
  }


  def instance[A](func: A => A): MaddenRefactored[A] = new MaddenRefactored[A] {
    def madden(a: A): A = func(a)
  }

  implicit val intMad: MaddenRefactored[Int] =
    instance(int => {
      val rand = scala.util.Random
      int + rand.nextInt(int)
    })


  implicit val stringMad: MaddenRefactored[String] =
    instance(str => "tartarugando " + str + " opolpo")



}