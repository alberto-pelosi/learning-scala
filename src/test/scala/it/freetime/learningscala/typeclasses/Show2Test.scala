package it.freetime.learningscala.typeclasses

import it.freetime.learningscala.typeclasses.Show2._
import org.scalatest.FunSuite


class Show2Test extends FunSuite{

  test("Test a second version of a Type Class, defined using implicitly"){
    println(show(42))

  }

}
