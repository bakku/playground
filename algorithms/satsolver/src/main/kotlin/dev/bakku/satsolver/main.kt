package dev.bakku.satsolver

fun main() {
    val satisfiableExpr = { vars: Array<Boolean> ->
        (vars[0] || !vars[1] || vars[2]) && (!vars[0] || vars[1] || !vars[2])
    }

    val unsatisfiableExpr = { vars: Array<Boolean> ->
        (vars[0] || vars[1] || vars[2]) && (!vars[0] || vars[1] || vars[2]) &&
                (vars[0] || !vars[1] || vars[2]) && (vars[0] || vars[1] || !vars[2]) &&
                (!vars[0] || !vars[1] || vars[2]) && (!vars[0] || vars[1] || !vars[2]) &&
                (vars[0] || !vars[1] || !vars[2]) && (!vars[0] || !vars[1] || !vars[2])
    }

    println(SatSolver(satisfiableExpr, 3).isSatisfiable())
    println(SatSolver(unsatisfiableExpr, 3).isSatisfiable())
}