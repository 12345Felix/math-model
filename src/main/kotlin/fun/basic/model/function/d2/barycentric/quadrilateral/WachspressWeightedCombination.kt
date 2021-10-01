package `fun`.basic.model.function.d2.barycentric.quadrilateral

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.exception.InvalidArgumentException

class WachspressWeightedCombination : BarycentricWeightedCombination<WachspressWeightFunction> {

    @Throws(InvalidArgumentException::class)
    constructor(
        e0: D2Point, e1: D2Point, e2: D2Point, e3: D2Point,
        e0Image: D2Point, e1Image: D2Point, e2Image: D2Point, e3Image: D2Point
    ) : super(e0, e1, e2, e3, e0Image, e1Image, e2Image, e3Image, WachspressWeightFunction(e0, e1, e2, e3))
}