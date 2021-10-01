package `fun`.basic.model.function

import `fun`.basic.model.domain.D3Point
import `fun`.basic.model.domain.Vector

class D3Projection<DomPoint: Vector<DomPoint>>:
    Transformation<DomPoint, D3Point> {

    override fun transform(point: DomPoint): D3Point {
        val pointCoordinates = point.coordinates
        return if (point.dimension == 2) {
            D3Point.new(pointCoordinates[0], pointCoordinates[1], 0.0)
        } else {
            D3Point.new(pointCoordinates[0], pointCoordinates[1], pointCoordinates[2])
        }
    }

}