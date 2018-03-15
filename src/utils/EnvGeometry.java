package utils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.Direction;
import model.Position;
import model.PositionImpl;

/**
 * 
 * Utility class for geometric operations.
 *
 */
public final class EnvGeometry {
    private static final double ZERO_DEGREE = 0.0;
    private static final double ANGLE_PERIOD = 360.0;

    private EnvGeometry() {
    }
    /**
     * Calculate the distance between two Position in a 2D space.
     * @param current the original Position
     * @param other the other Position from which calculate the distance
     * @return the absolute value of the distance between two Position
     */
    public static double distance(final Position current, final Position other) {
        return Math.sqrt((Math.pow(current.getX() - other.getX(), 2) + Math.pow(current.getY() - other.getY(), 2)));
    }

    /**
     * Calculate the angle of the vector between two Position.
     * @param current the Position considered as the origin of the vector
     * @param other the Position considered as the end of the vector
     * @return the angle of the vector in the range [0, 360]
     */
    public static double angle(final Position current, final Position other) {
        final double slope = (other.getY() - current.getY()) / (other.getX() - current.getX());
        double angle = Math.toDegrees(Math.atan(slope));
        if (other.getX() < current.getX()) {
            angle += 180.0;
        } else if (other.getY() < current.getY()) {
            angle += 360.0;
        }
        return angle;
    }

    /**
     * Check if the angle is included in angleInterval.
     * @param angle the angle that have to be checked
     * @param angleInterval the interval in which the angle should fall into
     * @return if the angle in inside the interval
     */
    private static boolean isIncluded(final double angle, final Pair<Double, Double> angleInterval) {
        // case when the interval angles is [337.5, 22.5]
        if (angleInterval.getSecond() < angleInterval.getFirst()) {
            return (angle >= angleInterval.getFirst() && angle <= ZERO_DEGREE + ANGLE_PERIOD)
                    || (angle >= ZERO_DEGREE && angle < angleInterval.getSecond());
        }
        return angle >= angleInterval.getFirst() && angle < angleInterval.getSecond();
    }

    /**
     * Convert an angle to the closest Direction.
     * @param angle the angle to be converted in Direction
     * @return the Direction
     */
    public static Direction directionFromAngle(final double angle) {
        if (isIncluded(angle, Direction.NORTHEAST.angleInterval())) {
            return Direction.NORTHEAST;
        } else if (isIncluded(angle, Direction.NORTH.angleInterval())) {
            return Direction.NORTH;
        } else if (isIncluded(angle, Direction.NORTHWEST.angleInterval())) {
            return Direction.NORTHWEST;
        } else if (isIncluded(angle, Direction.WEST.angleInterval())) {
            return Direction.WEST;
        } else if (isIncluded(angle, Direction.SOUTHWEST.angleInterval())) {
            return Direction.SOUTHWEST;
        } else if (isIncluded(angle, Direction.SOUTH.angleInterval())) {
            return Direction.SOUTH;
        } else if (isIncluded(angle, Direction.SOUTHEAST.angleInterval())) {
            return Direction.SOUTHEAST;
        } else {
            return Direction.EAST;
        }
    }

    /**
     * Generate stream of Position in the range [(startX, startY), (endX, endY)].
     * @param startX the start value for the X coordinate
     * @param endX the end value for the X coordinate
     * @param startY the start value for the Y coordinate
     * @param endY the end value for the Y coordinate
     * @param bacteriaPos the original Position of the Bacteria
     * @return a stream of Position
     */
    public static Stream<Position> positionStream(final int startX, final int endX, final int startY, final int endY, final Position bacteriaPos) {
        return IntStream.range(startX, endX)
                        .mapToObj(x -> IntStream.range(startY, endY)
                                                .mapToObj(y -> new PositionImpl(bacteriaPos.getX() + x, bacteriaPos.getY() + y)))
                        .flatMap(position -> position);
    }
}
