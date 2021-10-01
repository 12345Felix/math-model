package `fun`.basic.model.domain

class D2Point(override val coordinates: DoubleArray = doubleArrayOf(0.0, 0.0)) : Vector<D2Point> {

    override val dimension: Int
        get() = 2

    companion object {
        fun new(coord1: Double, coord2: Double) = D2Point(doubleArrayOf(coord1, coord2))
    }

    override fun add(vector: Vector<D2Point>?): D2Point {
        if (vector == null) {
            return this
        }
        val pointCoords = vector.coordinates.mapIndexed{ index, d -> d + coordinates[index]}.toDoubleArray()
        return D2Point(pointCoords)
    }

    override fun scale(scalar: Double): D2Point {
        return new(scalar * coordinates[0], scalar * coordinates[1])
    }

    fun complexMult(point: Vector<D2Point>): D2Point {
        val x = this[0]*point[0] - this[1]*point[1]
        val y = this[0]*point[1] + this[1]*point[0]
        return new(x, y)
    }

}