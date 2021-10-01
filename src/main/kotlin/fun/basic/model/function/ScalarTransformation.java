package fun.basic.model.function;

import fun.basic.model.domain.Domain;
import fun.basic.model.domain.Vector;

import java.util.HashSet;
import java.util.Set;

public interface ScalarTransformation<DomPoint extends Vector<DomPoint>> {
    /**
     * transform a point from one Domain to another.
     * @param point
     * 			the point to transform
     * @return the image of the point
     */
    double transform(DomPoint point);

    /**
     * transform a Domain to another.
     * @param domain
     * 			thedomain to transform
     * @return the image of the domain
     */
    default Set<Double> transform(Domain<DomPoint> domain) {
        Set<Double> points = new HashSet<>();
        for (DomPoint point: domain.getPoints()) {
            points.add(transform(point));
        }
        return points;
    }
}
