package it.freetime.learningscala.typeclasses

trait Madden[A] {

  def madden(a: A) : A

}

object Madden{

  def apply[A](implicit mad:MaddenRefactored[A]) : MaddenRefactored[A] = mad

  def madden[A: MaddenRefactored](a: A) = MaddenRefactored[A].madden(a)

  implicit class MaddenOps[A: MaddenRefactored](a: A) {
    def madden = MaddenRefactored[A].madden(a)
  }

  implicit val intMad: MaddenRefactored[Int] = new MaddenRefactored[Int]{
    def madden(int: Int): Int = {
      val rand = scala.util.Random
      int + rand.nextInt(int)
    }
  }

  implicit val stringMad: MaddenRefactored[String] = new MaddenRefactored[String] {
    def madden(str: String) : String = "tartarugando " + str + " opolpo"
  }


}
