package `fun`.basic.model.function.d2.barycentric.quadrilateral

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.exception.InvalidArgumentException

class WachspressWeightFunction: BarycentricWeightFunction {
    private val B = DoubleArray(4)

    /**
     * The constructor only accepts vertices that form a convex quadrilateral if you
     * connect the vertices counter clock vice.
     *
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     * @throws InvalidArgumentException
     */
    @Throws(InvalidArgumentException::class)
    constructor(v0: D2Point?, v1: D2Point?, v2: D2Point?, v3: D2Point?): super(v0, v1, v2, v3) {

        for (i in indices) {
            B[i] = A(vertices[i], i + 1)
        }
    }

    override fun getLambda(x: D2Point): DoubleArray {
        val A = DoubleArray(4)
        for (i in indices) {
            A[i] = A(x, i)
        }
        val w = DoubleArray(4)
        var W = 0.0
        for (i in indices) {
            w[i] = B[i] * A[(i + 1) % 4] * A[(i + 2) % 4]
            W += w[i]
        }
        val lambda = DoubleArray(4)
        for (i in indices) {
            lambda[i] = w[i] / W
        }
        return lambda
    }

    /**
     * Calculate the determinant of the matrix given by x, vi, vi+1.
     *
     * | 1 1 1 | | x^1 vi^1 vi+1^1 | | x^2 vi^2 vi+1^2 |
     *
     * @param x The Vector x.
     * @param i The index of the vertex.
     * @return
     * @throws InvalidArgumentException
     */
    private fun A(x: D2Point, i: Int): Double {
        // We use the indices cyclically
        var i = i
        i %= 4
        val i1 = (i + 1) % 4
        val vi = vertices[i]
        val vi1 = vertices[i1]
        val s1 = x[0] * (vi[1] - vi1[1])
        val s2 = x[1] * (vi1[0] - vi[0])
        return (s1 + s2 + vi[0] * vi1[1]
                - vi[1] * vi1[0])
    }
}