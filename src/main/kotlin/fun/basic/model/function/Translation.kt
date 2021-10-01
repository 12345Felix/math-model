package `fun`.basic.model.function

import `fun`.basic.model.domain.Vector

class Translation<VectorImpl: Vector<VectorImpl>>:
    Transformation<VectorImpl, VectorImpl> {
    private var translationPoint: VectorImpl

    constructor(translation: VectorImpl) {
        translationPoint = translation
    }

    override fun transform(point: VectorImpl): VectorImpl {
        return point.add(translationPoint)
    }

    fun transformInverse(point: VectorImpl): VectorImpl {
        return point.subtract(translationPoint)
    }
}