package year2023.day01

import lib.SolutionSuite
import munit.CatsEffectSuite

class Part1Test extends SolutionSuite[Int](year2023.day01.Part1):
  override val expected: Int = 142

class Part2Test extends SolutionSuite[Int](year2023.day01.Part2):
  override val expected: Int = 281

  test("unspell digits in a string with overlapping digits"):
    val input    = "oneight"
    val expected = "18"
    assertEquals(Part2.unspellDigits(input).filter(_.isDigit), expected)
