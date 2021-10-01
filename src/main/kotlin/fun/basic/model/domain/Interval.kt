package `fun`.basic.model.domain

class Interval(private val start: Double = 0.0, private val end: Double = 0.0, private val fineness: Int = 500) {

    var points: MutableSet<Double> = HashSet()

    init {
        calcValues()
    }

    private fun calcValues() {
        points.add(start)
        points.add(end)
        val step = (end - start) / fineness.toDouble()
        var value = start
        for (i in 1 until fineness) {
            value += step
            points.add(value)
        }
    }
}