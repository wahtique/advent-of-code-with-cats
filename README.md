# Advent of Code with Cats

Advent of Code challenge solutions in Scala 3 + typelevel libraries.

## TODO

- [x] Scaffold day 01
- [x] Add scaffolding thru templating
- [x] Add integration with aoc-cli
- [ ] Actually do the challenges

## Requirements

- [scala-cli](https://scala-cli.virtuslab.org/)
- [just](https://github.com/casey/just)
- [aoc-cli](https://github.com/scarvalhojr/aoc-cli)
  - recommanded usage : [direnv](https://direnv.net/) + `.envrc` exporting `ADVENT_OF_CODE_SESSION`

## Usage

### Start a new day

```bash
just scaffold 2023 01
# or 
just s 2023 01 
```

### Run

```bash
# run year 2023, day 1, part 1
just run 2023 01 1
# or
just r 2023 01 1
```

### Test

```bash
just test 2023 01
# or 
just t 2023 01
```
