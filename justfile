DEFAULT_YEAR := '2023'
DEFAULT_DAY := '01'
DEFAULT_PART := '1'

default:
  @just --choose

alias r := run
alias t := test
alias s := scaffold

# example: `just run 2023 01 1`
run YEAR=DEFAULT_YEAR DAY=DEFAULT_DAY PART=DEFAULT_PART:
  scala-cli run . -M year{{YEAR}}.day{{DAY}}.Part{{PART}}

test YEAR DAY:
  scala-cli test . --test-only 'year{{YEAR}}.day{{DAY}}*'

# download input and puzzle for a given year and day
get YEAR DAY:
  #!/usr/bin/env zsh
  # make sure input directory exists
  if [[ ! -a src/main/inputs/year{{YEAR}} ]]; then
    mkdir -p src/main/inputs/year{{YEAR}}
  fi  
  # make sure puzzle directory exists
  if [[ ! -a src/main/md/year{{YEAR}} ]]; then
    mkdir -p src/main/md/year{{YEAR}}
  fi
  aoc d -o -d {{DAY}} -y {{YEAR}} -i src/main/inputs/year{{YEAR}}/day{{DAY}}.txt -p src/main/md/year{{YEAR}}/day{{DAY}}.txt

scaffold YEAR DAY: (get YEAR DAY)
  scala-cli run .template/scaffold.scala -- --year {{YEAR}} --day {{DAY}}
