package prog1.kotprog.dontstarve.solution.utility;

/**
 * Status of the game
 */
public enum GameStatus {
    /**
     * Loading map
     */
    LOAD_MAP,
    /**
     * Still waiting for players
     */
    JOINING,
    /**
     * Game in progress
     */
    IN_GAME,
    /**
     * Game has ended
     */
    ENDED,
}
