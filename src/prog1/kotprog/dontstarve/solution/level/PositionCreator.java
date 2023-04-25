package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.Arrays;

public class PositionCreator {
    private final Position[] positions;
    private final Level level;

    /**
     * This will create a position creator.
     *
     * @param level max x.
     * @param positions positions.
     */
    public PositionCreator(Level level, Position[] positions) {
        this.positions = positions.clone();
        this.level = level;
    }

    /**
     * This will create a position.
     * @return position.
     */
    public Position createPosition() {
        int minDistance = 50;
        while (minDistance > 0) {
            System.out.println("Looking for player distance (min: " + minDistance + ")");

            Position position = this.getPositionInsideDistance(minDistance);
            if (position != null) {
                return position;
            }

            minDistance-=5;
        }

        return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public Position getPositionInsideDistance(int minDistance) {
        for (int x = 0; x < this.level.getWidth(); x++) {
            for (int y = 0; y < this.level.getHeight(); y++) {
                if (this.level.getField(x, y).empty() && isFieldSuitable(x, y, minDistance)) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    public boolean isFieldSuitable(int x, int y, int minDistance) {
        return Arrays.stream(this.positions).noneMatch(pos -> pos.getDistance(new Position(x, y)) < minDistance);
    }
}
