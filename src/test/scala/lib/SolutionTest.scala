package lib

import munit.CatsEffectSuite

trait SolutionSuite[A](solution: Solution[A]) extends CatsEffectSuite:
  // expected output for the given test input in each part
  def expected: A

  test("given test input should return expected test output"):
    assertIO(
      Inputs.readlines(InputType.Test)(solution.year, solution.day, solution.part).map(solution.solve),
      expected
    )
