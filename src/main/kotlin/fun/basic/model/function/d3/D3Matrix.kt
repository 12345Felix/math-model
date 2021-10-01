package `fun`.basic.model.function.d3

import `fun`.basic.model.domain.D3Point
import `fun`.basic.model.function.Matrix

open class D3Matrix: Matrix<D3Point> {

    constructor(mp: Array<DoubleArray>): super(mp)
    constructor(): super(arrayOf(DoubleArray(3), DoubleArray(3), DoubleArray(3)))
    constructor(first: DoubleArray, second: DoubleArray, third: DoubleArray): super(arrayOf(first, second, third))

    override fun transform(point: D3Point): D3Point {
        return D3Point(mapCoordinates(point))
    }

    override fun add(matrix: Matrix<D3Point>): D3Matrix {
        return D3Matrix(addMaps(matrix))
    }

    override fun mult(matrix: Matrix<D3Point>): D3Matrix {
        return D3Matrix(multMaps(matrix))
    }

}