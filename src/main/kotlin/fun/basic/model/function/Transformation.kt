package `fun`.basic.model.function

import `fun`.basic.model.domain.Domain
import `fun`.basic.model.domain.GenericDomain
import `fun`.basic.model.domain.Vector

interface Transformation<DomPoint: Vector<DomPoint>, CoDomPoint: Vector<CoDomPoint>> {

    /**
     * transform a point from one Domain to another.
     * @param point
     * the point to transform
     * @return the image of the point
     */
    fun transform(point: DomPoint): CoDomPoint

    /**
     * transform a Domain to another.
     * @param domain
     * thedomain to transform
     * @return the image of the domain
     */
    fun transform(domain:Domain<DomPoint>): Domain<CoDomPoint> {
        val points: MutableSet<CoDomPoint> = HashSet()
        for (point in domain.points) {
            points.add(transform(point))
        }
        return GenericDomain(points)
    }
}