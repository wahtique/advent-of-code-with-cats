package year2023.day01

import lib.SolutionSuite
import munit.CatsEffectSuite

class Part1Test extends SolutionSuite(Puzzle.solution1):
  override val expected: Int = 142

class Part2Test extends SolutionSuite(Puzzle.solution2):
  override val expected: Int = 281

  test("unspell digits in a string with overlapping digits"):
    val input    = "oneight"
    val expected = "18"
    assertEquals(Puzzle.unspellDigits(input).filter(_.isDigit), expected)
