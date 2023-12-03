package lib

import fs2.io.file.Path
import munit.CatsEffectSuite

trait SolutionSuite[A](solution: Solution[A]) extends CatsEffectSuite with InputsOps:
  // expected output for the given test input in each part
  def expected: A

  def resourceId(year: Year, day: Day): String =
    s"year$year/day${day.toString.reverse.padTo(2, '0').reverse}/part${solution.part}.txt"

  def path(year: Year, day: Day): Path =
    val resourceId = s"year$year/day${day.toString.reverse.padTo(2, '0').reverse}/part${solution.part}.txt"
    val resource   = getClass.getClassLoader.getResource(resourceId)
    Path(resource.getPath)

  test("given test input should return expected test output"):
    assertIO(
      readlines(solution.year, solution.day).map(solution.solve),
      expected
    )
