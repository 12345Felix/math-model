package `fun`.basic.model.function

import `fun`.basic.model.domain.Domain
import `fun`.basic.model.domain.Vector

interface ScalarTransformation<DomPoint: Vector<DomPoint>> {

    /**
     * transform a point from one Domain to another.
     * @param point
     * the point to transform
     * @return the image of the point
     */
    fun transform(point: DomPoint): Double

    /**
     * transform a Domain to another.
     * @param domain
     * thedomain to transform
     * @return the image of the domain
     */
    fun transform(domain: Domain<DomPoint>): Set<Double> {
        val points: MutableSet<Double> = HashSet()
        for (point in domain.points) {
            points.add(transform(point))
        }
        return points
    }
}