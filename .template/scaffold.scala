//> using scala 3.3.1
//> using toolkit typelevel:latest
//> using dep com.monovore::decline:2.4.1

import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.implicits.*
import cats.implicits.*
import com.monovore.decline.*
import com.monovore.decline.effect.*
import fs2.io.file.Files
import fs2.io.file.Path

object Templates:
  val testInput = "replace me with the test input from the puzzle page"
  def test(year: String, day: String): String =
    require(day.length == 2)
    require(year.length == 4)
    val pack = s"year$year.day$day"
    s"""
      |package $pack
      |
      |import lib.SolutionSuite
      |import munit.CatsEffectSuite
      |
      |class Part1Test extends SolutionSuite(Puzzle.Part1):
      |  override val expected: Int = ???
      |
      |class Part2Test extends SolutionSuite(Puzzle.Part2):
      |  override val expected: Int = ???
    """.stripMargin
  def solution(year: String, day: String): String =
    require(day.length == 2)
    require(year.length == 4)
    val pack       = s"year$year.day$day"
    val trimmedDay = day.dropWhile(_ == '0')
    s"""
      |package $pack
      |
      |import io.github.iltotore.iron.*
      |import lib.*
      |
      |object Puzzle extends PuzzleApp($year, $trimmedDay):
      |
      |  override def part1(lines: Seq[String]): Int = ???
      |
      |  override def part2(lines: Seq[String]): Int = ???
    """.stripMargin

object Scaffolder
    extends CommandIOApp(
      name = "scaffolder",
      header = "A simple CLI to scaffold an AoC puzzle solution"
    ):
  override def main: Opts[IO[ExitCode]] =
    val dayOpt  = Opts.option[String]("day", help = "The day to scaffold.")
    val yearOpt = Opts.option[String]("year", help = "The year to scaffold.")

    (yearOpt, dayOpt).mapN: (year, day) =>
      // paths
      val mainPath             = Path(s"src/main/scala/year$year/day$day.scala")
      val testPath             = Path(s"src/test/scala/year$year/day$day.scala")
      val testInputsPath       = Path(s"src/test/inputs/year$year/day$day")
      def testInput(part: Int) = testInputsPath / s"part$part.txt"

      // content
      val solution = Templates.solution(year, day)
      val test     = Templates.test(year, day)

      // write operations
      val writeMain                 = fs2.Stream.emit(solution).through(Files[IO].writeUtf8(mainPath))
      val writeTest                 = fs2.Stream.emit(test).through(Files[IO].writeUtf8(testPath))
      val createTestInputsDirectory = Files[IO].createDirectories(testInputsPath)
      def writeTestInput(part: Int) = fs2.Stream.emit(Templates.testInput).through(Files[IO].writeUtf8(testInput(part)))

      // write effects
      createTestInputsDirectory >>
        fs2
          .Stream(writeMain, writeTest, writeTestInput(1), writeTestInput(2))
          .parJoinUnbounded
          .compile
          .drain
          .as(ExitCode.Success)
