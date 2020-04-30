# 2-SAT solver

This is a small implementation of a SAT solver which decides whether a given 2
SAT formula is satisfiable or not in polynomial time.

## Usage

``` sh
$ lein run "(A OR B) AND (A OR nB) AND (nA OR B) AND (nA OR nB)"
The expression is NOT satisfiable
$ lein run "(A OR B) AND (A OR nB)"                             
The expression is satisfiable
```

## Algorithm

The algorithm works in the following way:

#### 1. Parse the 2 SAT formula and build an implication graph

Every clause of the form `(A OR B)` can be transformed into
`(¬A ⇒ B) AND (¬B ⇒ A)`. This in turn can be visualized as a graph where every
literal corresponds to a vertex and every implication corresponds to an edge.

The complexity of this step is `Θ(n)` with n corresponding to the number of literals.

#### 2. Execute a depth first search with the implication graph as input

This is a standard depth first search implementation which has `O(|V| + |E|)`
as complexity.

#### 3. Transpose the implication graph

This again has the complexity of `O(|V| + |E|)`.

#### 4. Execute a depth first search, however, visit the vertices using the finishing times of 2. in decreasing order

Same as 2.

#### 5. Combine all depth first search trees together which also are all strongly connected components

This is currently implemented by taking all vertices without predecessors, i.e.
the roots of each tree, and expanding each tree until there are no children
anymore. The complexity of this step should be equal to `O((|V| + |V|) * |V|)`
which is equal to `O(|V|^2)`. Therefore it is one of the slowest step in the
algorithm

#### 6. Look for contradications inside each strongly connected component

Finally the algorithm iterates over every vertex in each strongly connected
component and checks whether the same strongly connected component contains the
inverse literal. If it finds its inverse literal then the 2 SAT formula contains
a contradiction which makes the formula not satisfiable. Otherwise the formula
is satisfiable. The complexity of this step again is `O(|V|^2)` because of the
iteration over basically all vertices and the verification whether the inverse
literal can be found or not.


All in all the algorithm therefore has the complexity of `O(|V|^2)`.
