package `fun`.basic.model.function.d3

import `fun`.basic.model.domain.D3Point
import `fun`.basic.model.function.Matrix

class D3Identity: D3Rotation {

    constructor(): super(0.0, 0.0, 0.0)

    override fun transform(point: D3Point): D3Point {
        return point
    }

    override fun mult(matrix: Matrix<D3Point>): D3Matrix {
        return D3Matrix(matrix.map)
    }
}