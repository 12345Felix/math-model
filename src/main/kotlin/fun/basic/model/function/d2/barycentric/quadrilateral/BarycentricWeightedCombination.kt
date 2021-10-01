package `fun`.basic.model.function.d2.barycentric.quadrilateral

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.exception.InvalidArgumentException
import `fun`.basic.model.function.Transformation
import `fun`.basic.model.function.d3.D3AffineLinearTransformation

abstract class BarycentricWeightedCombination<WeightFunction : BarycentricWeightFunction> :
    Transformation<D2Point, D2Point> {
    private val weightFunction: WeightFunction
    private val e0Transformation: D3AffineLinearTransformation
    private val e1Transformation: D3AffineLinearTransformation
    private val e2Transformation: D3AffineLinearTransformation
    private val e3Transformation: D3AffineLinearTransformation

    @Throws(InvalidArgumentException::class)
    protected constructor(
        e0: D2Point, e1: D2Point, e2: D2Point, e3: D2Point,
        e0Image: D2Point, e1Image: D2Point, e2Image: D2Point, e3Image: D2Point, weightFunction: WeightFunction
    ) {
        this.weightFunction = weightFunction
        e0Transformation = D3AffineLinearTransformation(e0, e0Image, e1, e3, e1Image, e3Image)
        e1Transformation = D3AffineLinearTransformation(e1, e1Image, e0, e2, e0Image, e2Image)
        e2Transformation = D3AffineLinearTransformation(e2, e2Image, e3, e1, e3Image, e1Image)
        e3Transformation = D3AffineLinearTransformation(e3, e3Image, e2, e0, e2Image, e0Image)
    }

    override fun transform(point: D2Point): D2Point {
        val lambda = weightFunction.getLambda(point!!)
        var transformedPoint = e0Transformation.transform(point).scale(lambda!![0])
        transformedPoint = transformedPoint!!.add(e1Transformation.transform(point).scale(lambda[1])!!)
        transformedPoint = transformedPoint!!.add(e2Transformation.transform(point).scale(lambda[2])!!)
        transformedPoint = transformedPoint!!.add(e3Transformation.transform(point).scale(lambda[3])!!)
        return transformedPoint
    }

}