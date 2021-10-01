package `fun`.basic.model.function

import `fun`.basic.model.domain.D2Point
import `fun`.basic.model.domain.D3Point
import `fun`.basic.model.function.d3.D3Identity
import `fun`.basic.model.function.d3.D3Rotation

class PerspectiveProjection : Transformation<D3Point, D2Point> {

    private var canvas: D3Point
    private var camera: D3Point
        set(camera: D3Point) {
            field = camera
            cameraTranslation = Translation(camera.scale(-1.0))
        }
    private var rotationAngles: D3Point
        set(rotationAngles: D3Point) {
            field = rotationAngles
            if (rotationAngles.absolute() == 0.0) {
                this.cameraRotation = D3Identity()
            } else {
                this.cameraRotation = D3Rotation(rotationAngles)
            }
        }

    private lateinit var cameraRotation: D3Rotation
    private lateinit var cameraTranslation: Translation<D3Point>

    constructor(camera: D3Point, canvas: D3Point, rotationAngles: D3Point) {
        this.canvas = canvas
        this.camera = camera
        this.rotationAngles = rotationAngles
    }

    constructor(camera: D3Point, canvas: D3Point) : this(camera, canvas, D3Point())

    constructor() : this(D3Point(), D3Point(), D3Point())

    override fun transform(point: D3Point): D2Point {

        // translate the point by the camera coordinate
        var difference = cameraTranslation.transform(point)
        // turn the difference point by the camera orientation
        difference = cameraRotation.transform(difference)

        // map to 2-D
        val factor: Double = this.canvas.get(2) / difference[2]
        val xValue = factor * difference[0] + canvas[0]
        val yValue = factor * difference[1] + canvas[1]
        return D2Point.new(xValue, yValue)
    }

}