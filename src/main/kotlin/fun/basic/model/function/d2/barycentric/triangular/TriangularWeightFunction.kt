package `fun`.basic.model.function.d2.barycentric.triangular

import `fun`.basic.model.domain.D2Point

class TriangularWeightFunction {
    private val vertices: Array<D2Point>
    private val PI = Math.PI
    private var A: Double

    constructor(v0: D2Point, v1: D2Point, v2: D2Point) {
        vertices = arrayOf(v0, v1, v2)
        A = this.A(v0, v1, v2)
    }

    fun getLambda(x: D2Point): DoubleArray? {
        val lambda = DoubleArray(3)
        lambda[0] = A(x, vertices[1], vertices[2]) / A
        lambda[1] = A(vertices[0], x, vertices[2]) / A
        lambda[3] = A(vertices[0], vertices[1], x) / A
        return lambda
    }

    /**
     * Calculate the determinant of the matrix given by x, vi, vi+1.
     *
     * | 1 1 1 | | a^1 b^1 c1^1 | | a^2 b^2 c^2 |
     *
     * @return the determinant
     */
    private fun A(a: D2Point, b: D2Point, c: D2Point): Double {
        val s1 = b[0] * c[1]
        val s2 = c[0] * a[2]
        val s3 = a[0] * b[1]
        val t1 = a[1] * b[0]
        val t2 = b[1] * c[0]
        val t3 = c[1] * a[0]
        return s1 + s2 + s3 - t1 - t2 - t3
    }
}