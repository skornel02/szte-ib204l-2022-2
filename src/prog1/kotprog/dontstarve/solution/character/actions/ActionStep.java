package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Direction;

import java.util.List;

/**
 * A lépés akció leírására szolgáló osztály: a karakter egy lépést tesz balra, jobbra, fel vagy le.
 */
public class ActionStep extends Action {
    /**
     * A mozgás iránya.
     */
    private final Direction direction;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param direction a mozgás iránya
     */
    public ActionStep(Direction direction) {
        super(ActionType.STEP);
        this.direction = direction;
    }

    /**
     * A direction gettere.
     * @return a mozgás iránya
     */
    public Direction getDirection() {
        return direction;
    }

    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        var nextPosition = character.getCurrentPosition().getNextPosition(getDirection(), character.getSpeed());
        var nextField = level.getField(nextPosition);
        if (nextField != null && nextField.isWalkable()) {
            character.setPosition(nextPosition);
        }
    }
}
