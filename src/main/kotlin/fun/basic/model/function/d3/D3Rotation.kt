package `fun`.basic.model.function.d3

import `fun`.basic.model.domain.D3Point
import `fun`.basic.model.domain.Domain
import `fun`.basic.model.domain.GenericDomain

open class D3Rotation: D3Matrix {
    /**
     * The x-rotation.
     */
    private val xRotation: D3Matrix

    /**
     * The y-rotation.
     */
    private val yRotation: D3Matrix

    /**
     * The z-rotation.
     */
    private val zRotation: D3Matrix

    constructor(xAngle: Double, yAngle: Double , zAngle: Double): super() {
        xRotation = createxRotation(xAngle)
        yRotation = createyRotation(yAngle)
        zRotation = createzRotation(zAngle)
        initCompleteRotation()
    }

    constructor(point: D3Point): super() {

        xRotation = createRotation(0, point)
        yRotation = createRotation(1, point)
        zRotation = createRotation(2, point)

        initCompleteRotation()
    }

    private fun initCompleteRotation() {
        val completeRotation = zRotation.mult(yRotation).mult(xRotation)
        val newMap: Array<DoubleArray> = completeRotation.map
        for (i in 0..2) {
            for (j in 0..2) {
                map[i][j] = newMap[i][j]
            }
        }
    }

    private fun createRotation(axis: Int, point: D3Point): D3Matrix {
        return if (point.absolute() == 0.0) {
            D3Identity()
        } else {
            when (axis) {
                0 -> {
                    this.createxRotation(point[0])
                }
                1 -> {
                    this.createyRotation(point[1])
                }
                2 -> {
                    this.createzRotation(point[2])
                }
                else -> throw IllegalArgumentException("The point has three axis you cannot access another: $axis")
            }
        }
    }

    private fun createxRotation(xAngle: Double): D3Matrix {
        val firstColumn: DoubleArray = getColumn(1.0, 0.0, 0.0)
        val secondColumn: DoubleArray = getColumn(0.0, Math.cos(xAngle), Math.sin(xAngle))
        val thirdColumn: DoubleArray = getColumn(0.0, -Math.sin(xAngle), Math.cos(xAngle))
        return D3Matrix(firstColumn, secondColumn, thirdColumn)
    }

    private fun createyRotation(yAngle: Double): D3Matrix {
        val firstColumn: DoubleArray = getColumn(Math.cos(yAngle), 0.0, -Math.sin(yAngle))
        val secondColumn: DoubleArray = getColumn(0.0, 1.0, 0.0)
        val thirdColumn: DoubleArray = getColumn(Math.sin(yAngle), 0.0, Math.cos(yAngle))
        return D3Matrix(firstColumn, secondColumn, thirdColumn)
    }

    private fun createzRotation(zAngle: Double): D3Matrix {
        val firstColumn: DoubleArray = getColumn(Math.cos(zAngle), Math.sin(zAngle), 0.0)
        val secondColumn: DoubleArray = getColumn(-Math.sin(zAngle), Math.cos(zAngle), 0.0)
        val thirdColumn: DoubleArray = getColumn(0.0, 0.0, 1.0)
        return D3Matrix(firstColumn, secondColumn, thirdColumn)
    }

    override fun transform(point: D3Point): D3Point {
        var mappedPoint = xRotation.transform(point)
        mappedPoint = yRotation.transform(mappedPoint)
        mappedPoint = zRotation.transform(mappedPoint)
        return mappedPoint
    }

    private fun getColumn(x: Double, y: Double, z: Double): DoubleArray {
        return doubleArrayOf(x, y, z)
    }

    override fun transform(domain: Domain<D3Point>): Domain<D3Point> {
        val mappedPoints: MutableSet<D3Point> = HashSet()
        for (point in domain.points) {
            mappedPoints.add(this.transform(point))
        }
        return GenericDomain(mappedPoints)
    }
}