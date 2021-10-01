package `fun`.basic.model.function.d2.barycentric.quadrilateral

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.exception.InvalidArgumentException

class MeanValueWeightFunction: BarycentricWeightFunction {

    @Throws(InvalidArgumentException::class)
    constructor(v0: D2Point?, v1: D2Point?, v2: D2Point?, v3: D2Point?): super(v0, v1, v2, v3)

    override fun getLambda(x: D2Point): DoubleArray {
        val w = DoubleArray(4)
        var W = 0.0
        for (i in indices) {
            w[i] = w(x, i)
            W += w[i]
        }
        val lambda = DoubleArray(4)
        for (i in indices) {
            lambda[i] = w[i] / W
        }
        return lambda
    }

    private fun w(point: D2Point, i: Int): Double {
        var i = i
        i = i % 4
        val iMin1 = (i + 3) % 4
        val alphai: Double = alpha(point, i)
        val alphaiMin1: Double = alpha(point, iMin1)
        val numerator = Math.tan(alphaiMin1 * 0.5) + Math.tan(alphai * 0.5)
        val distance = point.euclideanDistanceTo(vertices[i])
        return numerator / distance
    }

    private fun alpha(point: D2Point, i: Int): Double {
        val iPlusOne = (i + 1) % 4
        val e1 = point.subtract(vertices[iPlusOne])
        val e2 = point.subtract(vertices[i])
        return e1.getAngle(e2)
    }
}