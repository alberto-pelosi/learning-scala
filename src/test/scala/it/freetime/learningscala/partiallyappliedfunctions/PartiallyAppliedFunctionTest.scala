package it.freetime.learningscala.partiallyappliedfunctions

import org.scalatest.FunSuite

class PartiallyAppliedFunctionTest extends FunSuite{

  test("Example of Partially Applied Function"){

    val sum = (a: Int, b: Int, c: Int) => a + b + c

    val s = sum(1, _:Int, _: Int)


    assert(s(1,3).equals(5))


  }

}
