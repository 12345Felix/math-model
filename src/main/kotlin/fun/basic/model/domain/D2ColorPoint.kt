package `fun`.basic.model.domain

class D2ColorPoint(override val coordinates: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)): Vector<D2ColorPoint> {
    override val dimension: Int
        get() = 5

    companion object {
        fun new(coord1: Double, coord2: Double, coord3: Double, coord4: Double, coord5: Double) = D2ColorPoint(doubleArrayOf(coord1, coord2, coord3, coord4, coord5))
    }

    override fun add(vector: Vector<D2ColorPoint>?): D2ColorPoint {
        if (vector == null) {
            return this
        }
        val pointCoords = vector.coordinates.mapIndexed{ index, d -> d + coordinates[index]}.toDoubleArray()
        return D2ColorPoint(pointCoords)
    }

    override fun scale(scalar: Double): D2ColorPoint {
        return D2ColorPoint(coordinates.map { it -> scalar * it }.toDoubleArray())
    }

}