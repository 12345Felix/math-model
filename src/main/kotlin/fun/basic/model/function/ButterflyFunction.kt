package `fun`.basic.model.function

import `fun`.basic.model.domain.D2Point
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin

class ButterflyFunction: ParametricTransformation<D2Point> {
    override fun transform(point: Double): D2Point {
        val cosT = cos(point)
        val factor = exp(cosT) - 2 * cos(4 * point) - sin(point / 12).pow(5.0)

        val xValue = sin(point) * factor
        val yValue = cosT * factor

        return D2Point.new(xValue, yValue)
    }
}