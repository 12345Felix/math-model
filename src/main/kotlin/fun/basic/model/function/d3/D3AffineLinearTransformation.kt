package `fun`.basic.model.function.d3

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.function.AffineLinearTransformation
import `fun`.basic.model.function.Translation
import `fun`.basic.model.function.d2.D2Matrix

class D3AffineLinearTransformation: AffineLinearTransformation<D2Point, D2Matrix> {

    constructor(toZeroTranslation: Translation<D2Point>,
                toPointTranslation: Translation<D2Point>, matrix: D2Matrix): super(toZeroTranslation, toPointTranslation, matrix)

    constructor(e0: D2Point, e0Img: D2Point, e1: D2Point, e1Img: D2Point, e2: D2Point, e2Img: D2Point): this(Translation<D2Point>(e0), Translation<D2Point>(e0Img), createMatrix(e1.subtract(e0), e2.subtract(e0),
        e1Img.subtract(e0Img), e2Img.subtract(e0Img)))

    companion object {
        fun createMatrix(e1: D2Point, e2: D2Point, e1Image: D2Point, e2Image: D2Point): D2Matrix {

            val common = 1.0 / (e2.get(1) * e1.get(0) - e1.get(1) * e2.get(0))
            val column1 = DoubleArray(2)
            column1[0] = (e2.get(1) * e1Image.get(0) - e2Image.get(0) * e1.get(1)) * common
            column1[1] = (e2.get(1) * e1Image.get(1) - e2Image.get(1) * e1.get(1)) * common
            val column2 = DoubleArray(2)
            column2[0] = (e2Image.get(0) * e1.get(0) - e1Image.get(0) * e2.get(0)) * common
            column2[1] = (e2Image.get(1) * e1.get(0) - e1Image.get(1) * e2.get(0)) * common
            return D2Matrix(arrayOf(column1, column2))
        }
    }
}