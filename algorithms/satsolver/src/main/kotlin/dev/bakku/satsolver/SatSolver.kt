package dev.bakku.satsolver

class SatSolver(private val expr: (vars: Array<Boolean>) -> Boolean, private val n: Int) {
    fun isSatisfiable(): Boolean {
        val initialValues = Array(this.n) { false }
        return solve(initialValues)
    }

    private fun solve(values: Array<Boolean>): Boolean {
        return solveRek(values, 0)
    }

    private fun solveRek(values: Array<Boolean>, currentIndex: Int): Boolean {
        values[currentIndex] = false

        return if (currentIndex == (this.n - 1)) {
            if (this.expr(values)) true
            else {
                values[currentIndex] = true
                this.expr(values)
            }
        } else {
            if (solveRek(values, currentIndex + 1)) true
            else {
                values[currentIndex] = true
                this.expr(values)
            }
        }
    }
}