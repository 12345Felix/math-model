package `fun`.basic.model.domain


interface Domain<DomPoint: Vector<DomPoint>> {
    var points: MutableSet<DomPoint>

    /**
     * Function that returns the translation of this domain by the given point.
     * Be aware that the domain and the given point have the same dimension.
     *
     * @param point The translation point.
     * @return This domain translated by the given point.
     */
    fun translate(point: DomPoint): Domain<DomPoint> {
        val points: MutableSet<DomPoint> = HashSet()
        for (domainPoint in points) {
            points.add(domainPoint.add(point))
        }
        return GenericDomain(points)
    }

    fun scale(scale: Double): Domain<DomPoint> {
        val points: MutableSet<DomPoint> = HashSet()
        for (point in points) {
            points.add(point.scale(scale))
        }
        return GenericDomain(points)
    }

    operator fun contains(point: DomPoint): Boolean {
        val dimPoint = point.dimension
        for (setPoint in points) {
            if (setPoint.dimension != dimPoint) {
                println("WRONG DIMENSION: Given point $point Point in set: $setPoint")
                return false
            }
            if (setPoint.equals(point)) {
                return true
            }
        }
        return false
    }

    fun join(domain: Domain<DomPoint>): Domain<DomPoint>? {
        val joinedPoints = HashSet<DomPoint>()
        joinedPoints.addAll(points)
        return GenericDomain(joinedPoints)
    }
}