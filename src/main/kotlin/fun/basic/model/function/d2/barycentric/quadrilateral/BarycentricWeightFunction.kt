package `fun`.basic.model.function.d2.barycentric.quadrilateral

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.exception.InvalidArgumentException

abstract class BarycentricWeightFunction {
    val vertices: Array<D2Point>
    val indices = intArrayOf(0, 1, 2, 3)
    val PI = Math.PI

    @Throws(InvalidArgumentException::class)
    constructor(v0: D2Point? , v1: D2Point? , v2: D2Point? , v3: D2Point? ) {
        validateVertices(v0, v1, v2, v3)
        // set the vertices of the corresponding quadrilateral
        vertices = arrayOf(v0!!, v1!!, v2!!, v3!!)
    }

    abstract fun getLambda(x: D2Point): DoubleArray

    /**
     * Validate if the quadrilateral given by these vertices is convex.
     *
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     *
     * @throws InvalidArgumentException
     */
    @Throws(InvalidArgumentException::class)
    private fun validateVertices(v0: D2Point?, v1: D2Point?, v2: D2Point?, v3: D2Point?) {
        // null check
        if (v0 == null || v1 == null || v2 == null || v3 == null) {
            throw InvalidArgumentException(
                "The vertices of a quadrilateral must not be null.\n" + "v0: " + v0
                        + ", v1: " + v1 + ", v2: " + v2 + ", v3: " + v3
            )
        }

        // check is sum of Angles match 2 PI (360�)
        val a0 = v1.subtract(v0).getAngle(v3.subtract(v0))
        val a1 = v2.subtract(v1).getAngle(v0.subtract(v1))
        val a2 = v3.subtract(v2).getAngle(v1.subtract(v2))
        val a3 = v0.subtract(v3).getAngle(v2.subtract(v3))
        val sum = a0 + a1 + a2 + a3
        if (sum != 2 * PI) {
            throw InvalidArgumentException(
                ("The vertices do not define a convex quadrilateral. Please mind that the vertices must be given counterclock-wise.\n"
                        + "v0: " + v0 + ", v1: " + v1 + ", v2: " + v2 + ", v3: " + v3
                        + "\n Angles in vertices:\n a0: " + a0 + " a1:" + a1 + "a2" + a2 + "a3: " + a3)
            )
        }
    }
}