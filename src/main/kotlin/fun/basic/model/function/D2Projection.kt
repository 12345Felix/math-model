package `fun`.basic.model.function

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.domain.Vector

class D2Projection<DomPoint: Vector<DomPoint>>:
    Transformation<DomPoint, D2Point> {

    override fun transform(point: DomPoint): D2Point {
        val pointCoordinates = point.coordinates
        return D2Point.new(pointCoordinates[0], pointCoordinates[1])
    }
}