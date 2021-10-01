package `fun`.basic.model.function.complex

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.domain.D3Point
import `fun`.basic.model.function.Transformation

class MandelbrotFunction: Transformation<D2Point, D3Point> {
    private val THRESHOLD: Double
    private val MAX_ITERATION: Int
    private val MAX_COLOR = 16777215.0
    private val colorConstant: Double
    private val RED_1 = 16*16
    private val RED_2 = 16
    private val GREEN_1 = RED_1*RED_1
    private val GREEN_2 = RED_1*RED_2


    constructor(threshold: Double, maxIteration: Int) {
        THRESHOLD = threshold
        MAX_ITERATION = maxIteration
        colorConstant = MAX_COLOR/MAX_ITERATION
    }

    constructor(): this(100000.0, 5000)

    override fun transform(point: D2Point): D3Point {
        if (point == null) {
            return D3Point.new(1.0,1.0,1.0)
        }
        val mandelbrotColor = (mandelbrotIterations(point) * colorConstant).toInt()


        TODO("Not yet implemented")
    }

    private fun mandelbrotIterations(c: D2Point): Int {
        var z = D2Point.new(0.0, 0.0)
        var i = 0
        while (i <= MAX_ITERATION || z.absolute() <= THRESHOLD) {
            z = z.complexMult(z).add(c)
            i += 1
        }
        return i
    }
}