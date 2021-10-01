package fun.basic.model.function;

import fun.basic.model.domain.Domain;
import fun.basic.model.domain.GenericDomain;
import fun.basic.model.domain.Interval;
import fun.basic.model.domain.Vector;

import java.util.HashSet;
import java.util.Set;

public interface ParametricTransformation<CoDomPoint extends Vector<CoDomPoint>> {

    /**
     * transform a point from one Domain to another.
     *
     * @param point the point to transform
     * @return the image of the point
     */
    CoDomPoint transform(double point);

    /**
     * transform a Domain to another.
     *
     * @param domain thedomain to transform
     * @return the image of the domain
     */
    default Domain<CoDomPoint> transform(Set<Double> domain) {
        Set<CoDomPoint> points = new HashSet<CoDomPoint>();
        for (double point : domain) {
            points.add(transform(point));
        }
        return new GenericDomain<CoDomPoint>(points);
    }

    /**
     * transform a Domain to another.
     *
     * @param domain thedomain to transform
     * @return the image of the domain
     */
    default Domain<CoDomPoint> transform(Interval domain) {
        return transform(domain.getPoints());
    }
}
