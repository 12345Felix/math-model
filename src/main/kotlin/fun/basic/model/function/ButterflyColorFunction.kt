package `fun`.basic.model.function

import `fun`.basic.model.domain.D2ColorPoint
import kotlin.math.*

class ButterflyColorFunction : ParametricTransformation<D2ColorPoint> {
    override fun transform(point: Double): D2ColorPoint {
        val cosT = cos(point)
        val factor = exp(cosT) - 2 * cos(4 * point) - sin(point / 12).pow(5.0)

        val xValue = sin(point) * factor
        val yValue = cosT * factor
        var red = cos(point)
        red *= red
        var green = cos(point.times(2))
        green *= green;
        var blue = cos(point.times(3))
        blue *= blue
        if (red > 1 || red < 0 || green > 1 || green < 0 || blue > 1 || blue < 0) {
            System.err.println("Invalid param $red, $green, $blue, $point")
        }
        return D2ColorPoint(doubleArrayOf(xValue, yValue, red, green, blue))
    }
}