# Knapsack Problem

This is a small implementation of an algorithm that solves the knapsack problem.
You can read more about it here: [Knapsack Problem](https://en.wikipedia.org/wiki/Knapsack_problem#Dynamic_programming_in-advance_algorithm).

Since the problem is NP-complete the program won't be able to provide an output
for bigger inputs in a timely manner.

## Usage

See the `input.json` file to see how to format the input data for the program.
The program itself can be executed by running:

``` sh
./knapsack <CAPACITY> <INPUT JSON FILE>
```

It will then generate an output file with the complete table of the data it
calculated as well as the maximum amount of value and weight it was able to
find for the given capacity. Furthermore, the file will contain the set of items
that solves the problem for the returned value and weight pair.
