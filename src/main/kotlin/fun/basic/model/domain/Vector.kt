package `fun`.basic.model.domain

import java.util.*
import kotlin.math.acos
import kotlin.math.sqrt

interface Vector<P : Vector<P>> {

    val dimension: Int
    val coordinates: DoubleArray

    fun scalarProduct(coordinates: DoubleArray?): Double {
        if (coordinates == null) {
            return 0.0
        }
        val dimension: Int = this.dimension
        if (dimension != coordinates.size) {
            throw RuntimeException("The dimensions do not match. " + this + ", " + Arrays.toString(coordinates))
        }
        var scalar = this[0] * coordinates[0]
        for (i in 1 until dimension) {
            scalar += this[i] * coordinates[i]
        }
        return scalar
    }

    fun scalarProduct(vector: Vector<P>?): Double {
        if (vector == null) {
            return 0.0
        }
        require(this.dimension == vector.dimension) { "The dimension of two points must match for a scalar product! $this $vector" }
        return scalarProduct(vector.coordinates)
    }

    fun add(vector: Vector<P>?): P

    fun scale(scalar: Double): P

    fun subtract(point: Vector<P>): P {
        return this.add(point.scale(-1.0))
    }

    fun equals(point: Vector<P>?): Boolean {
        return if (point != null && this.dimension == point.dimension) {
            this.coordinates.contentEquals(point.coordinates)
        } else false
    }

    fun euclideanDistanceTo(point: Vector<P>): Double {
        val difference = add(point.scale(-1.0))
        return difference.absolute()
    }

    fun absolute(): Double {
        val scalarProduct = this.scalarProduct(this)
        return sqrt(scalarProduct)
    }

    fun getAngle(point: Vector<P>): Double {
        return try {
            val absThis = absolute()
            val absPoint = point.absolute()
            var scalarProduct = this.scalarProduct(point)
            scalarProduct /= absThis
            scalarProduct /= absPoint
            acos(scalarProduct)
        } catch (e: IllegalArgumentException) {
            throw e
        }
    }

    operator fun get(axis: Int): Double {
        return try {
            this.coordinates[axis]
        } catch (e: IndexOutOfBoundsException) {
            throw RuntimeException("The axis you picked is not available for this Point: $axis $this", e)
        }
    }

    fun toString(vector: Vector<*>): String? {
        return """Dimension: ${vector.dimension}
 Coordinate: ${vector.coordinates.contentToString()}"""
    }
}