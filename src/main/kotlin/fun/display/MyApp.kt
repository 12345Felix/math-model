package `fun`.display

import `fun`.basic.model.domain.*
import `fun`.basic.model.function.D3Projection
import `fun`.basic.model.function.D5Matrix
import `fun`.basic.model.function.PerspectiveProjection
import `fun`.basic.model.function.d2.barycentric.quadrilateral.*
import `fun`.basic.model.function.d3.D3Rotation
import `fun`.display.view.MainView
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(MainView::class, Styles::class) {
    override fun start(primaryStage: Stage) {


//        val offset = D2Point(150.0, 140.0)
        val offset = D2ColorPoint.new(150.0, 140.0, 0.0, 0.0, 0.0)
        val tranformation = D5Matrix(doubleArrayOf(50.0, 50.0, 1.0, 1.0, 1.0))

//        val curve = ButterflyCurve(D2Point(0.0, 0.0), 20000)
//        var scaledCurve: Domain<D2Point> = curve.scale(50.0)
//        scaledCurve = scaledCurve.translate(offset)


        val curve = ButterflyCurveColor(center = D2ColorPoint(), fineness = 20000)
        var scaledCurve: Domain<D2ColorPoint> = tranformation.transform(curve)
        scaledCurve = scaledCurve.translate(offset)

        val root = Group()
//        paintColorDomain(root, scaledCurve)
//        paintDomain(root, scaledCurve, Color.CORAL)
        paintColorDomain(root, scaledCurve)
        // Set up the scene
        val scene = Scene(root, 1600.0, 900.0)
        primaryStage.title = "3D - Test"
        primaryStage.scene = scene

        primaryStage.show()
    }

    /**
     * Function that paints the points of a given two 2-domain on the Scene with a
     * given Group as root.
     *
     * @param root   The Group were the points of the domain should be stored to
     * paint.
     * @param domain The domain with the points that should be painted.
     */
    private fun paintDomain(root: Group, domain: Domain<D2Point>) {
        for (point in domain.points) {
            root.children.add(Rectangle(point[0], point[1], 1.0, 1.0))
        }
    }

    private fun paintDomain(root: Group, domain: Domain<D2Point>, color: Color) {
        for (point in domain.points) {
            val rectangle = Rectangle(point[0], point[1], 1.0, 1.0)
            rectangle.fill = color
            rectangle.stroke = color
            root.children.add(rectangle)
        }
    }

    private fun paintColorDomain(root: Group, domain: Domain<D2ColorPoint>) {
        for (point in domain.points) {
            val rectangle = Rectangle(point[0], point[1], 1.0, 1.0)
            val color = Color(point[2], point[3], point[4], 1.0)
//            val color = Color.AQUA
            rectangle.fill = color
            rectangle.stroke = color
            root.children.add(rectangle)
        }
    }
}