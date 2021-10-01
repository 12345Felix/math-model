package `fun`.basic.model.domain

import `fun`.basic.model.function.ButterflyColorFunction

class ButterflyCurveColor(override var points: MutableSet<D2ColorPoint> = HashSet(), private val center: D2ColorPoint = D2ColorPoint(doubleArrayOf(0.0,0.0,0.0,0.0,0.0)), fineness: Int = 500) : Domain<D2ColorPoint> {
    private val butterflyFunction: ButterflyColorFunction = ButterflyColorFunction()

    init {
        updatePoints(fineness)
    }

    private fun updatePoints(fineness: Int) {
        val interval = Interval(0.0, 12 * Math.PI, fineness)
        val genericButterFly: Domain<D2ColorPoint> = butterflyFunction.transform(interval)
        points = genericButterFly.translate(center).points
    }
}