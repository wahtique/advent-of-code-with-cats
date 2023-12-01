package lib

import cats.Show
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.cps.*
import cats.effect.implicits.*
import cats.implicits.*
import io.github.iltotore.iron.*

trait Solution[A: Show](val year: Year, val day: Day, val part: Part) extends IOApp.Simple:
  def solve(lines: Seq[String]): A
  def inputLines: IO[Seq[String]] = Inputs.readlines(InputType.Main)(year, day, part)
  def solution: IO[A]             = inputLines.map(solve)
  def run: IO[Unit] = async[IO]:
    val response = solution.await
    IO.println(response.show).await