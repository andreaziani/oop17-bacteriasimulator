package model.simulator;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import model.Direction;
import model.simulator.bacteria.Collidable;
import model.state.Position;
import model.state.PositionImpl;

/**
 * 
 * Utility class for geometric operations.
 *
 */
public final class EnvironmentUtil {
    private static final double ZERO_DEGREE = 0.0;
    private static final double ANGLE_PERIOD = 360.0;
    /**
     * The minimum amount of space in the environment.
     */
    public static final double UNIT_OF_TIME = 1.0;

    private EnvironmentUtil() {
    }

    /**
     * Calculate the distance between two Position in a 2D space.
     * 
     * @param current
     *            the original Position
     * @param other
     *            the other Position from which calculate the distance
     * @return the absolute value of the distance between two Position
     */
    public static double distance(final Position current, final Position other) {
        return Math.sqrt((Math.pow(current.getX() - other.getX(), 2) + Math.pow(current.getY() - other.getY(), 2)));
    }

    /**
     * Calculate the angle of the vector between two Position.
     * 
     * @param current
     *            the Position considered as the origin of the vector
     * @param other
     *            the Position considered as the end of the vector
     * @return the angle of the vector in the range [0, 360]
     */
    public static double angle(final Position current, final Position other) {
        final double slope = (other.getY() - current.getY()) / (other.getX() - current.getX());
        double angle = Math.toDegrees(Math.atan(slope));
        if (other.getX() < current.getX()) {
            angle += ANGLE_PERIOD / 2;
        } else if (other.getY() < current.getY()) {
            angle += ANGLE_PERIOD;
        }
        return angle;
    }

    /**
     * Check if the angle is included in angleInterval.
     * 
     * @param angle
     *            the angle that have to be checked
     * @param angleInterval
     *            the interval in which the angle should fall into
     * @return if the angle in inside the interval
     */
    public static boolean angleInInterval(final double angle, final Pair<Double, Double> angleInterval) {
        // case when the angle interval is [337.5, 22.5]
        if (angleInterval.getRight() < angleInterval.getLeft()) {
            return (angle >= angleInterval.getLeft() && angle <= ZERO_DEGREE + ANGLE_PERIOD)
                    || (angle >= ZERO_DEGREE && angle < angleInterval.getRight());
        }
        return angle >= angleInterval.getLeft() && angle < angleInterval.getRight();
    }

    /**
     * Convert an angle to the closest Direction.
     * 
     * @param angle
     *            the angle to be converted in Direction
     * @return the Direction
     */
    public static Direction angleToDir(final double angle) {
        if (angleInInterval(angle, Direction.NORTHEAST.angleInterval())) {
            return Direction.NORTHEAST;
        } else if (angleInInterval(angle, Direction.NORTH.angleInterval())) {
            return Direction.NORTH;
        } else if (angleInInterval(angle, Direction.NORTHWEST.angleInterval())) {
            return Direction.NORTHWEST;
        } else if (angleInInterval(angle, Direction.WEST.angleInterval())) {
            return Direction.WEST;
        } else if (angleInInterval(angle, Direction.SOUTHWEST.angleInterval())) {
            return Direction.SOUTHWEST;
        } else if (angleInInterval(angle, Direction.SOUTH.angleInterval())) {
            return Direction.SOUTH;
        } else if (angleInInterval(angle, Direction.SOUTHEAST.angleInterval())) {
            return Direction.SOUTHEAST;
        } else {
            return Direction.EAST;
        }
    }

    /**
     * Generate stream of Position at distance radius from bacteriaPos.
     * 
     * @param radius
     *            the distance to which generate the Position
     * @param bacteriaPos
     *            the original Position
     * @param maxPosition
     *            the maximum position in the simulation
     * @return a Stream of Position at distance radius
     */
    public static Stream<Position> circularPositionStream(final int radius, final Position bacteriaPos,
            final Position maxPosition) {
        return IntStream.range((int) ZERO_DEGREE, (int) ANGLE_PERIOD).mapToObj(angle -> {
            final int x = (int) (bacteriaPos.getX() + radius * Math.cos(Math.toRadians(angle)));
            final int y = (int) (bacteriaPos.getY() + radius * Math.sin(Math.toRadians(angle)));
            return new PositionImpl(x, y);
        }).map(position -> (Position) position).distinct().filter(position -> position.getX() < maxPosition.getX()
                && position.getX() > 0 && position.getY() < maxPosition.getY() && position.getY() > 0);
    }

    /**
     * Generate stream of Position in the range [(startX, startY), (endX, endY)].
     * 
     * @param startX
     *            the start value for the X coordinate
     * @param endX
     *            the end value for the X coordinate
     * @param startY
     *            the start value for the Y coordinate
     * @param endY
     *            the end value for the Y coordinate
     * @param bacteriaPos
     *            the original Position of the Bacteria
     * @param maxPosition
     *            the maxiumPosition representing a limit for each position
     * @return a stream of Position in the given range excluding the same Position
     */
    public static Stream<Position> positionStream(final int startX, final int endX, final int startY, final int endY,
            final Position bacteriaPos, final Position maxPosition) {
        return IntStream.range(startX, endX)
                .mapToObj(x -> IntStream.range(startY, endY).filter(y -> x != 0 || y != 0)
                        .mapToObj(y -> new PositionImpl(bacteriaPos.getX() + x, bacteriaPos.getY() + y)))
                .flatMap(position -> position).map(position -> (Position) position)
                .filter(position -> position.getX() < maxPosition.getX() && position.getX() > 0
                        && position.getY() < maxPosition.getY() && position.getY() > 0);
    }

    /**
     * Generate stream of Position in the range [(start, start), (end, end)].
     * 
     * @param start
     *            the start value for both the X and the Y coordinate
     * @param end
     *            the end value for both the X and the Y coordinate
     * @param bacteriaPos
     *            the original Position of the Bacteria
     * @param maxPosition
     *            the upper limit that every position must not exceed
     * @return a stream of Position
     */
    public static Stream<Position> positionStream(final int start, final int end, final Position bacteriaPos,
            final Position maxPosition) {
        return positionStream(start, end, start, end, bacteriaPos, maxPosition);
    }

    /**
     * Generate stream of Position up to distance "distance" from bacteriaPost (in
     * the range [(bacteriaPos - distance, bacteriaPos - distance), (bacteriaPos +
     * distance, bacteriaPos + distance)]).
     * 
     * @param distance
     *            the maximum distance of each position
     * @param bacteriaPos
     *            the original Position of the Bacteria
     * @param maxPosition
     *            the upper limit that every position must not exceed
     * @return a stream of Position
     */
    public static Stream<Position> positionStream(final int distance, final Position bacteriaPos,
            final Position maxPosition) {
        return positionStream(-distance, distance, bacteriaPos, maxPosition)
                .filter(position -> EnvironmentUtil.distance(bacteriaPos, position) <= distance);
    }

    /**
     * Given two entry, check if they collide.
     * 
     * @param entry1
     *            the first entry
     * @param entry2
     *            the second position
     * @return boolean that indicate if the collision takes place
     */
    public static boolean isCollision(final Pair<Position, ? extends Collidable> entry1,
            final Pair<Position, ? extends Collidable> entry2) {
        final double distance = distance(entry1.getLeft(), entry2.getLeft());
        return (distance <= entry1.getRight().getRadius() + entry2.getRight().getRadius());
    }

    /**
     * Check if the entity in the given Position would collide with other entity at
     * distance radius.
     * 
     * @param position
     *            the position of the entity
     * @param radius
     *            the distance from the entity to check for collision
     * @param maxPosition
     *            the maximum position of the environment in which perform the
     *            operation
     * @param collide
     *            a Predicate describing the logic for obtaining the result
     * @return true is the entity in the position would cause a collision wrt to the
     *         Predicate collide, false otherwise
     */
    public static boolean causeCollision(final Position position, final double radius, final Position maxPosition,
            final Predicate<Position> collide) {
        return EnvironmentUtil.circularPositionStream((int) Math.ceil(radius), position, maxPosition)
                .noneMatch(pos -> collide.test(pos));
    }
}
