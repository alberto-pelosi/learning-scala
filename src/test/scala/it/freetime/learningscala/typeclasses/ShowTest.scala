package it.freetime.learningscala.typeclasses

import it.freetime.learningscala.typeclasses.Show._
import org.scalatest.FunSuite




class ShowTest extends FunSuite {

  test("Test Show.scala, the first step to Type Classes") {
    println(intCanShow.show(32))

  }





}
