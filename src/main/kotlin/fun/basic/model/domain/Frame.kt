package `fun`.basic.model.domain

class Frame(override var points: MutableSet<D2Point> = HashSet(), center: D2Point, width: Double, height: Double, fineness: Int) : Domain<D2Point> {

    init {
        val lowerY = center[1] - height / 2.0
        val lowerX = center[0] - width / 2.0
        var xVal = lowerX
        var yVal = lowerY
        val xStep = width / fineness.toDouble()
        val yStep = height / fineness.toDouble()
        for (i in 0 until fineness) {
            points.add(D2Point.new(xVal, lowerY))
            points.add(D2Point.new(xVal, lowerY + height))
            points.add(D2Point.new(lowerX, yVal))
            points.add(D2Point.new(lowerX + width, yVal))
            xVal += xStep
            yVal += yStep
        }
    }
}