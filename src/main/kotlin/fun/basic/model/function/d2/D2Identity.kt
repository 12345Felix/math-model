package `fun`.basic.model.function.d2

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.function.Matrix

class D2Identity: D2Matrix {

    constructor(): super(arrayOf(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0)))

    override fun add(matrix: Matrix<D2Point>): Matrix<D2Point> {
        return D2Matrix(addMaps(matrix))
    }

    override fun mult(matrix: Matrix<D2Point>): Matrix<D2Point> {
        return D2Matrix(multMaps(matrix))
    }

}