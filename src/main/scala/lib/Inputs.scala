package lib

import cats.effect.IO
import fs2.io.file.Files
import fs2.io.file.Path
import io.github.iltotore.iron.*

object Inputs:

  def path(inputType: InputType)(year: Year, day: Day, part: Part): Path =
    // assume script is launched from project root directory
    val scopeSegment = inputType match
      case InputType.Main => "main"
      case InputType.Test => "test"
    val base        = s"src/$scopeSegment/inputs"
    val yearSegment = s"year$year"
    val daySegment  = s"day${day.toString.reverse.padTo(2, '0').reverse}"
    val partSegment = s"part$part"
    Path(s"$base/$yearSegment/$daySegment/$partSegment")

  def readlines(inputType: InputType)(year: Year, day: Day, part: Part): IO[Seq[String]] =
    Files[IO].readUtf8Lines(path(inputType)(year, day, part)).compile.toList.map(_.toSeq)
