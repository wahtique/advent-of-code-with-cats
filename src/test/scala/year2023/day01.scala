package year2023.day01

import munit.CatsEffectSuite
import lib.SolutionSuite

class Part1Test extends SolutionSuite[String](year2023.day01.Part1):
  override val expected: String = "hello world"

class Part2Test extends SolutionSuite[String](year2023.day01.Part2):
  override val expected: String = "hello world bis"