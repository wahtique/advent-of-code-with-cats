package year2023.day01

import cats.effect.implicits.*
import cats.implicits.*
import io.github.iltotore.iron.*
import lib.*

object Puzzle extends PuzzleApp(2023, 1):

  // part 1
  override def part1(lines: Seq[String]): Int = lines
    .map: l =>
      val digits = l.filter(_.isDigit).toSeq
      Seq(digits.head, digits.last).mkString.toInt
    .sum

  // part 2

  def unspellDigits(s: String) =
    def loop(acc: String, step: Int, stop: Int): String =
      if acc.isBlank || step >= stop + 1 then acc
      else
        val current   = acc.substring(0, step)
        val remaining = acc.substring(step)
        val unspelled = current
          // replace only the first letter as the next ones might be part of the next digit
          // ... yeah it's ugly :D
          .replace("one", "1ne")
          .replace("two", "2wo")
          .replace("three", "3hree")
          .replace("four", "4our")
          .replace("five", "5ive")
          .replace("six", "6ix")
          .replace("seven", "7even")
          .replace("eight", "8ight")
          .replace("nine", "9ine")

        loop(unspelled + remaining, step + 1, stop)

    // start at 3 as digits cannot be spelled with less anyway
    loop(s, 3, s.size)

  override def part2(lines: Seq[String]): Int = part1(lines.map(unspellDigits))
