package it.freetime.learningscala.typeclasses

import org.scalatest.FunSuite
import it.freetime.learningscala.typeclasses.MaddenRefactored._

class MaddenRefactorTest extends FunSuite {

  test("Test MaddenRefactor TypeClass on Int") {

    println(42.madden)

  }

  test("Test MaddenRefactor TypeClass on String") {
    println("Andrea".madden)
  }

  test("Test MaddenRefactor TypeClass on custom class passing implicit TypeClass") {

    case class Flower(name: String)

    implicit val flower: MaddenRefactored[Flower] = new MaddenRefactored[Flower] {
      def madden(a: Flower): Flower = new Flower(a.name + " Opolpo me")
    }

    println(Flower("daisy").madden)


  }



}
