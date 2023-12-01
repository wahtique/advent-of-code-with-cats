package lib

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*

type Year = Int :| Interval.Closed[2023, 2023]
type Day  = Int :| Interval.Closed[1, 25]
type Part = Int :| Interval.Closed[1, 2]

enum InputType:
  case Main, Test
