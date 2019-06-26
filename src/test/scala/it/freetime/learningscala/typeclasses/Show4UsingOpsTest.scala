package it.freetime.learningscala.typeclasses

import org.scalatest.FunSuite
import it.freetime.learningscala.typeclasses.Show4UsingOps._

class Show4UsingOpsTest extends FunSuite{

  test("Test TypeClass with Ops") {
    println(42.show)
  }

}
