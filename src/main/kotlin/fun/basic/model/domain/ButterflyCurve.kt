package `fun`.basic.model.domain

import `fun`.basic.model.function.ButterflyFunction

class ButterflyCurve(override var points: MutableSet<D2Point> = HashSet(), private val center: D2Point = D2Point.new(0.0,0.0), fineness: Int = 500) : Domain<D2Point> {

    private val butterflyFunction: ButterflyFunction = ButterflyFunction()

    init {
        updatePoints(fineness)
    }

    private fun updatePoints(fineness: Int) {
        val interval = Interval(0.0, 12 * Math.PI, fineness)
        val genericButterFly: Domain<D2Point> = butterflyFunction.transform(interval)
        points = genericButterFly.translate(center).points
    }
}