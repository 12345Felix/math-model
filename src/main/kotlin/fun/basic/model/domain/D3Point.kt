package `fun`.basic.model.domain

class D3Point(override val coordinates: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0)) :
    Vector<D3Point> {

    override val dimension:Int
        get() = 3

    companion object {
        fun new(coord1: Double, coord2: Double, coord3: Double) = D3Point(doubleArrayOf(coord1, coord2, coord3))
    }

    override fun add(vector: Vector<D3Point>?): D3Point {
        if (vector == null) {
            return this
        }
        return new(
            this[0] + vector[0],
            this[1] + vector[1],
            this[2] + vector[2]
        )
    }

    override fun scale(scalar: Double): D3Point {
        return new(scalar * coordinates[0], scalar * coordinates[1], scalar * coordinates[2])
    }

}