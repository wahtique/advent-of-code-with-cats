package lib

import cats.effect.IO
import fs2.io.file.Files
import fs2.io.file.Path
import io.github.iltotore.iron.*
import scala.io.Source

trait InputsOps:
  def path(year: Year, day: Day): Path

  def readlines(year: Year, day: Day): IO[Seq[String]] =
    Files[IO].readUtf8Lines(path(year, day)).filterNot(_.isBlank).compile.toList.map(_.toSeq)

object Inputs extends InputsOps:
  def path(year: Year, day: Day): Path =
    val resourceId = s"year$year/day${day.toString.reverse.padTo(2, '0').reverse}.txt"
    val resource   = getClass.getClassLoader.getResource(resourceId)
    Path(resource.getPath)
