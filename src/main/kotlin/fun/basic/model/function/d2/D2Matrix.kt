package `fun`.basic.model.function.d2

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.function.Matrix

open class D2Matrix: Matrix<D2Point> {
    constructor(map: Array<DoubleArray>): super(map)

    override fun transform(point: D2Point): D2Point {
        val newCoords = mapCoordinates(point)
        return D2Point.new(newCoords[0], newCoords[1])
    }

    override fun add(matrix: Matrix<D2Point>): Matrix<D2Point> {
        return D2Matrix(addMaps(matrix))
    }

    override fun mult(matrix: Matrix<D2Point>): Matrix<D2Point> {
        return D2Matrix(multMaps(matrix))
    }


}