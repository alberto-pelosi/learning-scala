package it.freetime.learningscala.typeclasses

import org.scalatest.FunSuite
import it.freetime.learningscala.typeclasses.Madden._


class MaddenTest extends FunSuite{

  test("Test Madden TypeClass on Int"){

    println(madden(77))
    println(42.madden)
  }

  test("Test Madden TypeClass on String"){
    println("Andrea".madden)
  }




}
