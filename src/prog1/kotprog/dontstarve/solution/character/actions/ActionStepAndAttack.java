package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Direction;

import java.util.List;

/**
 * A lépés és támadás akció leírására szolgáló osztály: a karakter egy lépést tesz balra, jobbra, fel vagy le,
 * majd megtámadja a legközelebbi karaktert.
 */
public class ActionStepAndAttack extends Action {
    /**
     * A mozgás iránya.
     */
    private final Direction direction;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionStepAndAttack(Direction direction) {
        super(ActionType.STEP_AND_ATTACK);
        this.direction = direction;
    }

    /**
     * Az irány lekérdezésére szolgáló getter.
     * @return a mozgás iránya
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        ActionStep step = new ActionStep(getDirection());
        ActionAttack attack = new ActionAttack();
        step.handleAction(character, level, manager, characters);
        attack.handleAction(character, level, manager, characters);
    }
}
