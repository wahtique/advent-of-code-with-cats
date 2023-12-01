package year2023.day01

import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.cps.*
import cats.effect.implicits.*
import cats.implicits.*
import io.github.iltotore.iron.*
import lib.*
import lib.Inputs

object Part1 extends Solution[String](2023, 1, 1):

  def solve(lines: Seq[String]): String = lines.mkString(" ")

object Part2 extends Solution[String](2023, 1, 2):

  def solve(lines: Seq[String]): String = lines.mkString(" ")
