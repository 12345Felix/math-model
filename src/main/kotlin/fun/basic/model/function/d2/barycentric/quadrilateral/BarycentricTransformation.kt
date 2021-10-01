package `fun`.basic.model.function.d2.barycentric.quadrilateral

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.function.Transformation

abstract class BarycentricTransformation<WeightFunction : BarycentricWeightFunction> :
    Transformation<D2Point, D2Point> {

    private val weightFunction: WeightFunction
    private val e0: D2Point
    private val e1: D2Point
    private val e2: D2Point
    private val e3: D2Point
    private val e0Image: D2Point
    private val e1Image: D2Point
    private val e2Image: D2Point
    private val e3Image: D2Point

    constructor(
        weightFunction: WeightFunction, e0: D2Point, e1: D2Point, e2: D2Point,
        e3: D2Point, e0Image: D2Point, e1Image: D2Point, e2Image: D2Point, e3Image: D2Point
    ) {
        this.weightFunction = weightFunction
        this.e0 = e0
        this.e1 = e1
        this.e2 = e2
        this.e3 = e3
        this.e0Image = e0Image
        this.e1Image = e1Image
        this.e2Image = e2Image
        this.e3Image = e3Image
    }

    override fun transform(point: D2Point): D2Point {
        val lambda = weightFunction.getLambda(point)
        var image = e0Image.scale(lambda[0])
        image = image.add(e1Image.scale(lambda[1]))
        image = image.add(e2Image.scale(lambda[2]))
        image = image.add(e3Image.scale(lambda[3]))
        return image
    }
}