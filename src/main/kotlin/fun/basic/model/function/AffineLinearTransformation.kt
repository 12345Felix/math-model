package `fun`.basic.model.function

import `fun`.basic.model.domain.Vector


open class AffineLinearTransformation<VectorImpl : Vector<VectorImpl>, MatrixImpl : Matrix<VectorImpl>>(
    var toZeroTranslation: Translation<VectorImpl>,
    var toPointTranslation: Translation<VectorImpl>,
    var matrix: MatrixImpl
) :
    Transformation<VectorImpl, VectorImpl> {

    /**
     * Maps by default a given point first with the inverse of the translation then
     * the matrix and finally the translation.
     */
    override fun transform(point: VectorImpl): VectorImpl {
        var image = toZeroTranslation.transformInverse(point)
        image = matrix.transform(image)
        image = toPointTranslation.transform(image)
        return image
    }
}