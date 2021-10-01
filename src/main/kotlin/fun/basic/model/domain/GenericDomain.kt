package `fun`.basic.model.domain

class GenericDomain<DomPoint: Vector<DomPoint>>(override var points: MutableSet<DomPoint> = HashSet()) : Domain<DomPoint>{

}