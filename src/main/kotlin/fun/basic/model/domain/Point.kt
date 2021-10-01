package `fun`.basic.model.domain

class Point(override val coordinates: DoubleArray, override val dimension: Int = coordinates.size) : Vector<Point> {

    override fun add(vector: Vector<Point>?): Point {
        if (vector != null && dimension == vector.dimension) {
            val dim = dimension
            val newCoordinate = DoubleArray(dim)
            for (i in 0 until dim) {
                newCoordinate[i] = this[i] + vector[i]
            }
            return Point(newCoordinate)
        } else {
            throw IllegalArgumentException("To add two Points the Dimension must match")
        }
    }

    override fun scale(scalar: Double): Point {
        val newCoordinate = DoubleArray(dimension)
        for (i in 0 until dimension) {
            newCoordinate[i] = this[i] * scalar
        }
        return Point(newCoordinate)
    }
}