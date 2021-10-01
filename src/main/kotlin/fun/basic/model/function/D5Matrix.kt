package `fun`.basic.model.function

import `fun`.basic.model.domain.D2ColorPoint

class D5Matrix : Matrix<D2ColorPoint> {

    constructor(mp: Array<DoubleArray>) : super(mp)

    constructor(diagonal: DoubleArray) : super(diagonal)



    override fun add(matrix: Matrix<D2ColorPoint>): Matrix<D2ColorPoint> {
        var addMap = map.mapIndexed { i, da -> matrix.map[i].mapIndexed { j, d -> d + da[j] }.toDoubleArray() }.toTypedArray()
        return D5Matrix(addMap)
    }

    override fun mult(matrix: Matrix<D2ColorPoint>): Matrix<D2ColorPoint> {
        TODO("Not yet implemented")
    }

    override fun transform(point: D2ColorPoint): D2ColorPoint {
        if (point == null) {
            return D2ColorPoint()
        }
        var transformed = point.coordinates.mapIndexed{ i, _ -> point.scalarProduct(map[i])}.toDoubleArray()
        return D2ColorPoint(transformed)
    }
}