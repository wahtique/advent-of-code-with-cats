package lib

import cats.effect.ExitCode
import cats.effect.IO
import com.monovore.decline.Opts
import com.monovore.decline.effect.CommandIOApp
import io.github.iltotore.iron.*

abstract class PuzzleApp(val year: Year, val day: Day)
    extends CommandIOApp(
      name = s"Advent of Code $year Day $day",
      header = s"Advent of Code $year Day $day"
    ):

  // override those methods to implement the solutions
  def part1(lines: Seq[String]): Int
  def part2(lines: Seq[String]): Int

  val solution1 = new Solution(year, day, 1):
    def solve(lines: Seq[String]): Int = part1(lines)

  val solution2 = new Solution(year, day, 2):
    def solve(lines: Seq[String]): Int = part2(lines)

  override def main: Opts[IO[ExitCode]] =
    Opts
      .option[Int]("part", "The part to run (1 or 2).")
      .map: p =>
        p.refineOption[PartConstraint] match
          case Some(1) => solution1.run.as(ExitCode.Success)
          case Some(2) => solution2.run.as(ExitCode.Success)
          case _       => IO.raiseError(new IllegalArgumentException(s"Invalid part: $p")).as(ExitCode.Error)
