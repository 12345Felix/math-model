package `fun`.basic.model.function

import `fun`.basic.model.domain.Domain
import `fun`.basic.model.domain.GenericDomain
import `fun`.basic.model.domain.Vector
import java.util.*
import kotlin.collections.HashSet

abstract class Matrix<VectorImpl: Vector<VectorImpl>>:
    Transformation<VectorImpl, VectorImpl> {
    ///////////////////////// instance variables
    val map: Array<DoubleArray>

    ///////////////////////// constructors

    constructor(mp: Array<DoubleArray>) {
        map = mp
    }

    constructor(diagonal: DoubleArray): this(getDiagonalMatrix(diagonal))

    constructor(dimension: Int, scale: Double): this(getArray(scale, dimension))

    ///////////////////////// instance methods
    protected open fun mapCoordinates(point: VectorImpl): DoubleArray {
        val length: Int = map.size
        val mappedCoordinate = DoubleArray(length)
        val pointCoordinates = point.coordinates
        for (i in 0 until length) {
            for (j in 0 until length) {
                mappedCoordinate[i] += map[j][i] * pointCoordinates[j]
            }
        }
        return mappedCoordinate
    }

    override fun transform(domain: Domain<VectorImpl>): Domain<VectorImpl> {
        val mappedPoints: MutableSet<VectorImpl> = HashSet()
        for (point in domain.points) {
            mappedPoints.add(transform(point))
        }
        return GenericDomain(mappedPoints)
    }

    open fun addMaps(matrix: Matrix<VectorImpl>): Array<DoubleArray> {
        val dimension = map.size
        val newMap = Array(dimension) {
            DoubleArray(
                dimension
            )
        }
        for (i in 0 until dimension) {
            val thisColumn = map[i]
            val matrixColumn: DoubleArray = map.get(i)
            val sum = DoubleArray(dimension)
            for (j in 0 until dimension) {
                sum[j] = thisColumn[j] + matrixColumn[j]
            }
            newMap[i] = sum
        }
        return newMap
    }

    open fun multMaps(matrix: Matrix<VectorImpl>): Array<DoubleArray> {
        val dimension = map.size
        val newMap = Array(dimension) {
            DoubleArray(
                dimension
            )
        }
        for (i in 0 until dimension) {
            for (j in 0 until dimension) {
                val column: DoubleArray = map.get(j)
                for (l in 0 until dimension) {
                    newMap[j][i] += column[l] * map[l][i]
                }
            }
        }
        return newMap
    }

    abstract fun add(matrix: Matrix<VectorImpl>): Matrix<VectorImpl>

    abstract fun mult(matrix: Matrix<VectorImpl>): Matrix<VectorImpl>

    open fun equals(matrix: Matrix<VectorImpl>): Boolean {
        return map.contentDeepEquals(matrix.map)
    }

    override fun toString(): String {
        if (map.isEmpty()) {
            return ""
        }
        var matrixString: String = map[0].contentToString()
        for (i in 1 until map.size) {
            matrixString = """
            $matrixString
            ${Arrays.toString(map[i])}
            """.trimIndent()
        }
        return matrixString
    }

    ///////////////////////// class methods

    companion object {
        fun getDiagonalMatrix(diagonal: DoubleArray): Array<DoubleArray> {
            val length = diagonal.size
            val map = Array(length) { DoubleArray(length) }
            for (i in 0 until length) {
                val row = DoubleArray(length)
                row[i] = diagonal[i]
                map[i] = row
            }
            return map
        }

        fun getArray(value: Double, dimension: Int): DoubleArray {
            val diagonal = DoubleArray(dimension)
            Arrays.fill(diagonal, value)
            return diagonal
        }
    }
}