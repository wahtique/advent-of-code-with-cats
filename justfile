DEFAULT_YEAR := '2023'
DEFAULT_DAY := '01'
DEFAULT_PART := '1'

default:
  @just --choose

alias r := run
alias t := test

# example: `just run 2023 01 1`
run YEAR=DEFAULT_YEAR DAY=DEFAULT_DAY PART=DEFAULT_PART:
  scala-cli run . -M year{{YEAR}}.day{{DAY}}.Part{{PART}}

test YEAR DAY:
  scala-cli test . --test-only 'year{{YEAR}}.day{{DAY}}*'

# test: year day part
#   scala-cli test . -M year{{year}}.day{{day}}.Part{{part}}